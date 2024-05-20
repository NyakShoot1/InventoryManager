from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.users.dependencies import user_by_id
from src.users.schemas import (
    User,
    UserCreate,
    UserUpdate,
    UserUpdatePartial,
)
from src.users import crud
from src.database import database_manager

user_router = APIRouter(tags=["User"])


@user_router.post(
    "/create_user/",
    response_model=User,
    status_code=status.HTTP_201_CREATED,
)
async def create_user(
    new_user: UserCreate,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_user(session, new_user)


@user_router.get(
    "/users",
    response_model=list[User],
)
async def get_users(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_users(session=session)


@user_router.get(
    "/{user_id}/",
    response_model=User,
)
async def get_user(
    user_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    user = await crud.get_user(session, user_id)
    if user is not None:
        return user
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"User with user_id: {user_id} not found",
    )


@user_router.put("/{user_id}/")
async def update_user(
    user_update: UserUpdate,
    user: User = Depends(user_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_user(
        session=session,
        user=user,
        user_update=user_update,
    )


@user_router.patch("/{user_id}/")
async def update_user_partial(
    user_update: UserUpdatePartial,
    user: User = Depends(user_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_user(
        session=session,
        user=user,
        user_update=user_update,
        partial=True,
    )


@user_router.delete("/{user_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_user(
    user: User = Depends(user_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_user(session=session, user=user)
