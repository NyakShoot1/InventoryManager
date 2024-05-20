from sqlalchemy.ext.asyncio import AsyncSession

from src.photos.models import Photo
from src.photos.schemas import PhotoCreate, PhotoCreateForShipment, PhotoUpdateShipment


async def get_photo(session: AsyncSession, photo_id: int) -> Photo | None:
    return await session.get(Photo, photo_id)


async def create_photo(
    session: AsyncSession,
    new_photo: PhotoCreate | PhotoCreateForShipment,
) -> Photo:
    photo = Photo(**new_photo.model_dump())
    session.add(photo)
    await session.commit()
    return photo


async def update_photo_shipment(
    session: AsyncSession,
    photo: Photo,
    shipment_id: int,
):
    setattr(photo, "shipment_id", shipment_id)
    await session.commit()
    return photo
