from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.roles.models import Role
from src.roles import crud


async def role_by_id(
    role_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Role:
    role = await crud.get_role(session=session, role_id=role_id)
    if role is not None:
        return role

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Role {role_id} not found!",
    )
