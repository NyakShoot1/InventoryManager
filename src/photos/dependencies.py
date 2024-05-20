from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.photos.models import Photo
from src.photos import crud


async def photo_by_id(
    photo_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Photo:
    photo = await crud.get_photo(session=session, photo_id=photo_id)
    if photo is not None:
        return photo

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Photo {photo_id} not found!",
    )
