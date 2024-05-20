from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.suppliers.models import Supplier
from src.suppliers.schemas import SupplierCreate, SupplierUpdate, SupplierUpdatePartial


async def get_suppliers(session: AsyncSession):
    stmt = select(Supplier).order_by(Supplier.id)
    suppliers = await session.scalars(stmt)
    return suppliers


async def get_supplier(session: AsyncSession, supplier_id: int) -> Supplier | None:
    return await session.get(Supplier, supplier_id)


async def create_supplier(
    session: AsyncSession, new_supplier: SupplierCreate
) -> Supplier:
    supplier = Supplier(**new_supplier.model_dump())  # как работает ** и model_dump()?
    session.add(supplier)
    await session.commit()
    return supplier


async def update_supplier(
    session: AsyncSession,
    supplier: Supplier,
    supplier_update: SupplierUpdate | SupplierUpdatePartial,
    partial: bool = False,
) -> Supplier:
    for name, value in supplier_update.model_dump(exclude_unset=partial).items():
        setattr(supplier, name, value)
    await session.commit()
    return supplier


async def delete_supplier(
    session: AsyncSession,
    supplier: Supplier,
) -> None:
    await session.delete(supplier)
    await session.commit()
