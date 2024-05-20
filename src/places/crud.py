from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.places.models import Place
from src.places.schemas import PlaceCreate, PlaceUpdate, PlaceUpdatePartial


async def get_places_with_storage(session: AsyncSession):
    stmt = select(Place).order_by(Place.id)
    places = await session.scalars(stmt)
    return places


async def get_place(session: AsyncSession, place_id: int) -> Place | None:
    return await session.get(Place, place_id)


async def create_place(
    session: AsyncSession, new_place: PlaceCreate, storage_id: int
) -> Place:
    place = Place(**new_place.model_dump())  # как работает ** и model_dump()?
    place.storage_id = storage_id
    session.add(place)
    await session.commit()
    return place


async def update_place(
    session: AsyncSession,
    place: Place,
    place_update: PlaceUpdate | PlaceUpdatePartial,
    partial: bool = False,
) -> Place:
    for name, value in place_update.model_dump(exclude_unset=partial).items():
        setattr(place, name, value)
    await session.commit()
    return place


async def delete_place(
    session: AsyncSession,
    place: Place,
) -> None:
    await session.delete(place)
    await session.commit()
