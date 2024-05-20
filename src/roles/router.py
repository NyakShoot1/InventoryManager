from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.roles.dependencies import role_by_id
from src.roles.schemas import (
    Role,
    RoleCreate,
    RoleUpdate,
    RoleUpdatePartial,
)
from src.roles import crud
from src.database import database_manager

role_router = APIRouter(tags=["Role"])


@role_router.post(
    "/create_role/",
    response_model=Role,
    status_code=status.HTTP_201_CREATED,
)
async def create_role(
    new_role: RoleCreate,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_role(session, new_role)


@role_router.get(
    "/roles",
    response_model=list[Role],
)
async def get_roles(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_roles(session=session)


@role_router.get(
    "/{role_id}/",
    response_model=Role,
)
async def get_role(
    role_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    role = await crud.get_role(session, role_id)
    if role is not None:
        return role
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Role with role_id: {role_id} not found",
    )


@role_router.put("/{role_id}/")
async def update_role(
    role_update: RoleUpdate,
    role: Role = Depends(role_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_role(
        session=session,
        role=role,
        role_update=role_update,
    )


@role_router.patch("/{role_id}/")
async def update_role_partial(
    role_update: RoleUpdatePartial,
    role: Role = Depends(role_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_role(
        session=session,
        role=role,
        role_update=role_update,
        partial=True,
    )


@role_router.delete("/{role_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_role(
    role: Role = Depends(role_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_role(session=session, role=role)
