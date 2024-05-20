from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.places.dependencies import place_by_id
from src.places.schemas import (
    Place,
    PlaceCreate,
    PlaceUpdate,
    PlaceUpdatePartial,
    PlaceWithStorageId,
)
from src.places import crud
from src.database import database_manager

place_router = APIRouter(tags=["Place"])


@place_router.post(
    "/create_place/",
    response_model=Place,
    status_code=status.HTTP_201_CREATED,
)
async def create_place(
    new_place: PlaceCreate,
    storage_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_place(session, new_place, storage_id)


@place_router.get(
    "/places",
    response_model=list[PlaceWithStorageId],
)
async def get_places(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_places_with_storage(session=session)


@place_router.get(
    "/{place_id}/",
    response_model=Place,
)
async def get_place(
    place_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    place = await crud.get_place(session, place_id)
    if place is not None:
        return place
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Place with place_id: {place_id} not found",
    )


@place_router.put("/{place_id}/")
async def update_place(
    place_update: PlaceUpdate,
    place: Place = Depends(place_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_place(
        session=session,
        place=place,
        place_update=place_update,
    )


@place_router.patch("/{place_id}/")
async def update_place_partial(
    place_update: PlaceUpdatePartial,
    place: Place = Depends(place_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_place(
        session=session,
        place=place,
        place_update=place_update,
        partial=True,
    )


@place_router.delete("/{place_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_place(
    place: Place = Depends(place_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_place(session=session, place=place)
