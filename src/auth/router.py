from fastapi import APIRouter, Depends

from src.auth.models import TokenInfo
from src.auth.token_create import (
    create_access_token,
    create_refresh_token,
)
from src.auth.validation import (
    validate_auth_user,
    get_current_auth_user,
    get_current_token_payload,
    get_current_auth_user_for_refresh,
)
from src.users.schemas import User

auth_router = APIRouter(prefix="/jwt", tags=["JWT"])


@auth_router.post("/login/", response_model=TokenInfo)
async def auth_user_issue_jwt(
    user: User = Depends(validate_auth_user),
):
    access_token = create_access_token(user)
    refresh_token = create_refresh_token(user)
    return TokenInfo(
        access_token=access_token,
        refresh_token=refresh_token,
    )


@auth_router.post(
    "/refresh/",
    response_model=TokenInfo,
    response_model_exclude_none=True,
)
def auth_refresh_jwt(
    user: User = Depends(get_current_auth_user_for_refresh),
):
    access_token = create_access_token(user)
    return TokenInfo(
        access_token=access_token,
    )


@auth_router.get("/users/me/")
async def auth_user_check_self_info(
    payload: dict = Depends(get_current_token_payload),
    user: User = Depends(get_current_auth_user),
):
    iat = payload.get("iat")
    return {
        "sub": user.id,
        "login": user.login,
        "email": user.email,
        "logged_in_at": iat,
    }
