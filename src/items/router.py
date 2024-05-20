from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.items.dependencies import item_by_id
from src.items.schemas import Item, ItemCreate, ItemUpdate, ItemUpdatePartial
from src.items import crud
from src.database import database_manager

item_router = APIRouter(tags=["Item"])


@item_router.get(
    "/barcode/{barcode}/",
    response_model=Item,
)
async def get_item_by_barcode(
    barcode: str,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_item_by_barcode(session, int(barcode))


@item_router.post(
    "/create_item/",
    response_model=Item,
    status_code=status.HTTP_201_CREATED,
)
async def create_item(
    new_item: ItemCreate,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_item(session, new_item)


@item_router.get(
    "/items/",
    response_model=list[Item],
)
async def get_items(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_items(session=session)


@item_router.get(
    "/{item_id}/",
    response_model=Item,
)
async def get_item(
    item_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    item = await crud.get_item(session, item_id)
    if item is not None:
        return item
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Item with item_id: {item_id} not found",
    )


@item_router.put("/{item_id}/")
async def update_item(
    item_update: ItemUpdate,
    item: Item = Depends(item_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_item(
        session=session,
        item=item,
        item_update=item_update,
    )


@item_router.patch("/{item_id}/")
async def update_item_partial(
    item_update: ItemUpdatePartial,
    item: Item = Depends(item_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_item(
        session=session,
        item=item,
        item_update=item_update,
        partial=True,
    )


@item_router.delete("/{item_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_item(
    item: Item = Depends(item_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_item(session=session, item=item)
