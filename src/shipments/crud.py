from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.shipments.models import Shipment
from src.shipments.schemas import ShipmentCreate, ShipmentUpdate, ShipmentUpdatePartial


async def get_shipments(session: AsyncSession):
    stmt = select(Shipment).order_by(Shipment.id)
    shipments = await session.scalars(stmt)
    return shipments


async def get_shipment(session: AsyncSession, shipment_id: int) -> Shipment | None:
    return await session.get(Shipment, shipment_id)


async def create_shipment(
    session: AsyncSession, new_shipment: ShipmentCreate
) -> Shipment:
    shipment = Shipment(**new_shipment.model_dump())  # как работает ** и model_dump()?
    session.add(shipment)
    await session.commit()
    return shipment


async def update_shipment(
    session: AsyncSession,
    shipment: Shipment,
    shipment_update: ShipmentUpdate | ShipmentUpdatePartial,
    partial: bool = False,
) -> Shipment:
    for name, value in shipment_update.model_dump(exclude_unset=partial).items():
        setattr(shipment, name, value)
    await session.commit()
    return shipment


async def delete_shipment(
    session: AsyncSession,
    shipment: Shipment,
) -> None:
    await session.delete(shipment)
    await session.commit()
