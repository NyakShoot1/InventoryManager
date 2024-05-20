from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.places.models import Place

from src.places import crud


async def place_by_id(
    place_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Place:
    place = await crud.get_place(session=session, place_id=place_id)
    if place is not None:
        return place

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Place {place_id} not found!",
    )
