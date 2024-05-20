from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.suppliers.models import Supplier

from src.suppliers import crud


async def supplier_by_id(
    supplier_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Supplier:
    supplier = await crud.get_supplier(session=session, supplier_id=supplier_id)
    if supplier is not None:
        return supplier

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Supplier {supplier_id} not found!",
    )
