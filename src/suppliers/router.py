from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.suppliers.dependencies import supplier_by_id
from src.suppliers.schemas import (
    Supplier,
    SupplierCreate,
    SupplierUpdate,
    SupplierUpdatePartial,
)
from src.suppliers import crud
from src.database import database_manager

supplier_router = APIRouter(tags=["Supplier"])


@supplier_router.post(
    "/create_supplier/",
    response_model=Supplier,
    status_code=status.HTTP_201_CREATED,
)
async def create_supplier(
    new_supplier: SupplierCreate,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_supplier(session, new_supplier)


@supplier_router.get(
    "/suppliers",
    response_model=list[Supplier],
)
async def get_suppliers(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_suppliers(session=session)


@supplier_router.get(
    "/{supplier_id}/",
    response_model=Supplier,
)
async def get_supplier(
    supplier_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    supplier = await crud.get_supplier(session, supplier_id)
    if supplier is not None:
        return supplier
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Supplier with supplier_id: {supplier_id} not found",
    )


@supplier_router.put("/{supplier_id}/")
async def update_supplier(
    supplier_update: SupplierUpdate,
    supplier: Supplier = Depends(supplier_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_supplier(
        session=session,
        supplier=supplier,
        supplier_update=supplier_update,
    )


@supplier_router.patch("/{supplier_id}/")
async def update_supplier_partial(
    supplier_update: SupplierUpdatePartial,
    supplier: Supplier = Depends(supplier_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_supplier(
        session=session,
        supplier=supplier,
        supplier_update=supplier_update,
        partial=True,
    )


@supplier_router.delete("/{supplier_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_supplier(
    supplier: Supplier = Depends(supplier_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_supplier(session=session, supplier=supplier)
