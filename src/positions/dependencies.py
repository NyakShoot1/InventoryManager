from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.positions.models import Position

from src.positions import crud


async def position_by_id(
    position_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Position:
    position = await crud.get_position(session=session, position_id=position_id)
    if position is not None:
        return position

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Position {position_id} not found!",
    )
