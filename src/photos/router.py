from io import BytesIO
from typing import List

from fastapi import APIRouter, Depends, UploadFile, File
from sqlalchemy.ext.asyncio import AsyncSession
from starlette import status

from src.database import database_manager
from src.minio import MinioDB
from src.photos import crud
from src.photos.dependencies import photo_by_id
from src.photos.schemas import Photo, PhotoCreate, PhotoUpdateShipment

photo_router = APIRouter(tags=["Photo"])


@photo_router.post(
    "/create_photos/",
    response_model=List[Photo],
    status_code=status.HTTP_201_CREATED,
)
async def create_photos(
    photos: List[UploadFile] = File(...),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):

    # Загружаем фотографии в Minio
    photo_names = await MinioDB.get_instance().put_objects(photos)

    new_photos = []
    for photo_name in photo_names:
        photo = PhotoCreate(
            photo_name=photo_name, photo_bucket_name=MinioDB.get_instance().bucket_name
        )
        new_photo = await crud.create_photo(session, photo)
        new_photos.append(new_photo)

    return new_photos


@photo_router.patch("/{photo_id}/")
async def update_photo_shipment(
    photo_update: PhotoUpdateShipment,
    photo: Photo = Depends(photo_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_photo_shipment(
        session=session, photo=photo, shipment_id=photo_update.shipment_id
    )
