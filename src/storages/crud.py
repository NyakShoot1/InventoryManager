from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.storages.models import Storage
from src.storages.schemas import StorageCreate, StorageUpdate, StorageUpdatePartial


async def get_storages(session: AsyncSession):
    stmt = select(Storage).order_by(Storage.id)
    storages = await session.scalars(stmt)
    return storages


async def get_storage(session: AsyncSession, storage_id: int) -> Storage | None:
    return await session.get(Storage, storage_id)


async def create_storage(session: AsyncSession, new_storage: StorageCreate) -> Storage:
    storage = Storage(**new_storage.model_dump())  # как работает ** и model_dump()?
    session.add(storage)
    await session.commit()
    return storage


async def update_storage(
    session: AsyncSession,
    storage: Storage,
    storage_update: StorageUpdate | StorageUpdatePartial,
    partial: bool = False,
) -> Storage:
    for name, value in storage_update.model_dump(exclude_unset=partial).items():
        setattr(storage, name, value)
    await session.commit()
    return storage


async def delete_storage(
    session: AsyncSession,
    storage: Storage,
) -> None:
    await session.delete(storage)
    await session.commit()
