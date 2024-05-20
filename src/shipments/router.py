import mimetypes
from datetime import datetime
from typing import List

from fastapi import APIRouter, HTTPException, status, Depends, File, UploadFile
from sqlalchemy.ext.asyncio import AsyncSession

from src.documents.schemas import DocumentCreate
from src.minio import MinioDB
from src.photos.dependencies import photo_by_id
from src.positions.schemas import PositionCreate

from src.shipments.dependencies import shipment_by_id
from src.shipments.schemas import (
    Shipment,
    ShipmentUpdate,
    ShipmentUpdatePartial,
    ShipmentCreate,
    NewShipmentRequest,
    NewShipmentResponse,
)
from src.shipments import crud

from src.database import database_manager

from src.positions import crud as position_crud

from src.photos import crud as photo_crud
from src.photos.schemas import PhotoCreateForShipment

from src.documents import crud as document_crud
from src.documents.generators.prihod_template import generate_prihod_doc

shipment_router = APIRouter(tags=["Shipment"])


@shipment_router.post(
    "/create_shipment/",
    # response_model=NewShipmentResponse,
    status_code=status.HTTP_201_CREATED,
)
async def create_shipment(
    shipment_info: NewShipmentRequest,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    print(shipment_info.delivery_man)
    print(shipment_info.supplier_name)
    print(shipment_info.document_number)
    print(shipment_info.storage_man)
    print(shipment_info.new_positions)
    print(shipment_info.photos_id)

    created_positions = []
    for position in shipment_info.new_positions:
        created_positions.append(await position_crud.create_position(session, position))

    document_name = f"prihod_№{shipment_info.document_number}_{datetime.now().strftime('%d-%m-%Y')}.docx"
    # document = generate_prihod_doc()

    # document = document_crud.create_document_with_minio(
    #     session,
    #     DocumentCreate(
    #         document_name=document_name,
    #         document_bucket_name=MinioDB.get_instance().bucket_name,
    #     ),
    #     document,
    # )

    #
    # new_shipment = ShipmentCreate(
    #     document_id=document.id, user_id=1
    # )  # TODO user_id с клиента
    # await crud.create_shipment(session, new_shipment)

    for photo_id in shipment_info.photos_id:
        await photo_crud.update_photo_shipment(
            session, Depends(photo_by_id), 1
        )  # TODO shipment_id

    # return response


@shipment_router.get(
    "/shipments",
    response_model=list[Shipment],
)
async def get_shipments(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_shipments(session=session)


@shipment_router.get(
    "/{shipment_id}/",
    response_model=Shipment,
)
async def get_shipment(
    shipment_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    shipment = await crud.get_shipment(session, shipment_id)
    if shipment is not None:
        return shipment
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Shipment with shipment_id: {shipment_id} not found",
    )


@shipment_router.put("/{shipment_id}/")
async def update_shipment(
    shipment_update: ShipmentUpdate,
    shipment: Shipment = Depends(shipment_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_shipment(
        session=session,
        shipment=shipment,
        shipment_update=shipment_update,
    )


@shipment_router.patch("/{shipment_id}/")
async def update_shipment_partial(
    shipment_update: ShipmentUpdatePartial,
    shipment: Shipment = Depends(shipment_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_shipment(
        session=session,
        shipment=shipment,
        shipment_update=shipment_update,
        partial=True,
    )


@shipment_router.delete("/{shipment_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_shipment(
    shipment: Shipment = Depends(shipment_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_shipment(session=session, shipment=shipment)
