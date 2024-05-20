from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.movements.models import Movement
from src.movements import crud


async def movement_by_id(
    movement_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Movement:
    movement = await crud.get_movement(session=session, movement_id=movement_id)
    if movement is not None:
        return movement

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Movement {movement_id} not found!",
    )
