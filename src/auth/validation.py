from fastapi import Form, Depends
from fastapi.security import OAuth2PasswordBearer
from jwt import InvalidTokenError
from sqlalchemy.ext.asyncio import AsyncSession

from src.auth import utils as auth_utils
from src.auth.exc import error_401
from src.auth.token_create import (
    TOKEN_TYPE_FIELD,
    ACCESS_TOKEN_TYPE,
    REFRESH_TOKEN_TYPE,
)
from src.auth.utils import decode_jwt
from src.database import database_manager
from src.users import crud as user_crud
from src.users.schemas import User

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="/api/v1/jwt/login/", auto_error=False)


async def validate_auth_user(
    login: str = Form(),
    password: str = Form(),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> User:
    unauthed_exc = error_401("invalid login or password")

    if not (user := await user_crud.get_user_by_login(session, login)):
        raise unauthed_exc

    if not auth_utils.validate_password(
        password=password,
        hashed_password=user.password,
    ):
        raise unauthed_exc

    # if not user.active:
    #     raise HTTPException(
    #         status_code=status.HTTP_403_FORBIDDEN,
    #         detail="user inactive",
    #     )

    return user


async def get_current_token_payload(
    token: str = Depends(oauth2_scheme),
) -> dict:
    try:
        payload = decode_jwt(
            token=token,
        )
    except InvalidTokenError as e:
        raise error_401(f"invalid token error: {e}")
    return payload


# async def get_current_auth_user(
#     payload: dict = Depends(get_current_token_payload),
#     session: AsyncSession = Depends(database_manager.scoped_session_dependency),
# ) -> User:
#     user_id: int = payload.get("sub")
#     if user := await user_crud.get_user(session, user_id):
#         return user
#     raise error_401("token invalid (user not found)")


async def validate_token_type(
    payload: dict,
    token_type: str,
) -> bool:
    current_token_type = payload.get(TOKEN_TYPE_FIELD)
    if current_token_type == token_type:
        return True
    raise error_401(
        f"invalid token type {current_token_type!r} expected {token_type!r}"
    )


async def get_user_by_token_sub(
    payload: dict,
) -> User:
    session = database_manager.get_scoped_session()
    user_id: int | None = payload.get("sub")
    if user := await user_crud.get_user(session=session, user_id=user_id):
        await session.close()
        return user
    raise error_401("token invalid (user not found)")


def get_auth_user_from_token_of_type(
    token_type: str,
):
    async def get_auth_user_from_token(
        payload: dict = Depends(get_current_token_payload),
    ) -> User:
        await validate_token_type(payload, token_type)
        return await get_user_by_token_sub(payload)

    return get_auth_user_from_token


get_current_auth_user = get_auth_user_from_token_of_type(ACCESS_TOKEN_TYPE)

get_current_auth_user_for_refresh = get_auth_user_from_token_of_type(REFRESH_TOKEN_TYPE)
