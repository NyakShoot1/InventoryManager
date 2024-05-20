from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.storages.dependencies import storage_by_id
from src.storages.schemas import (
    Storage,
    StorageCreate,
    StorageUpdate,
    StorageUpdatePartial,
)
from src.storages import crud
from src.database import database_manager

storage_router_base_crud = APIRouter(tags=["Storage - Base CRUD"])


@storage_router_base_crud.post(
    "/create_storage/",
    response_model=Storage,
    status_code=status.HTTP_201_CREATED,
)
async def create_storage(
    new_storage: StorageCreate,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_storage(session, new_storage)


@storage_router_base_crud.get(
    "/storages",
    response_model=list[Storage],
)
async def get_storages(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_storages(session=session)


@storage_router_base_crud.get(
    "/{storage_id}/",
    response_model=Storage,
)
async def get_storage(
    storage_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    storage = await crud.get_storage(session, storage_id)
    if storage is not None:
        return storage
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Storage with storage_id: {storage_id} not found",
    )


@storage_router_base_crud.put("/{storage_id}/")
async def update_storage(
    storage_update: StorageUpdate,
    storage: Storage = Depends(storage_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_storage(
        session=session,
        storage=storage,
        storage_update=storage_update,
    )


@storage_router_base_crud.patch("/{storage_id}/")
async def update_storage_partial(
    storage_update: StorageUpdatePartial,
    storage: Storage = Depends(storage_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_storage(
        session=session,
        storage=storage,
        storage_update=storage_update,
        partial=True,
    )


@storage_router_base_crud.delete(
    "/{storage_id}/", status_code=status.HTTP_204_NO_CONTENT
)
async def delete_storage(
    storage: Storage = Depends(storage_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_storage(session=session, storage=storage)
