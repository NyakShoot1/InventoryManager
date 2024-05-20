from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.storages.models import Storage
from src.storages import crud


async def storage_by_id(
    storage_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Storage:
    storage = await crud.get_storage(session=session, storage_id=storage_id)
    if storage is not None:
        return storage

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Storage {storage_id} not found!",
    )
