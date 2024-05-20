from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.shipments.models import Shipment

from src.shipments import crud


async def shipment_by_id(
    shipment_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Shipment:
    shipment = await crud.get_shipment(session=session, shipment_id=shipment_id)
    if shipment is not None:
        return shipment

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Shipment {shipment_id} not found!",
    )
