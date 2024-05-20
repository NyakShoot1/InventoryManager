from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.items.models import Item
from src.items.schemas import ItemCreate, ItemUpdate, ItemUpdatePartial


async def get_item_by_barcode(session: AsyncSession, barcode: int):
    return await session.scalar(select(Item).where(Item.barcode == barcode))


async def get_items(session: AsyncSession):
    stmt = select(Item).order_by(Item.id)
    items = await session.scalars(stmt)
    return items


async def get_item(session: AsyncSession, item_id: int) -> Item | None:
    return await session.get(Item, item_id)


async def create_item(session: AsyncSession, new_item: ItemCreate) -> Item:
    item = Item(**new_item.model_dump())  # как работает ** и model_dump()?
    session.add(item)
    await session.commit()
    return item


async def update_item(
    session: AsyncSession,
    item: Item,
    item_update: ItemUpdate | ItemUpdatePartial,
    partial: bool = False,
) -> Item:
    for name, value in item_update.model_dump(exclude_unset=partial).items():
        setattr(item, name, value)
    await session.commit()
    return item


async def delete_item(
    session: AsyncSession,
    item: Item,
) -> None:
    await session.delete(item)
    await session.commit()
