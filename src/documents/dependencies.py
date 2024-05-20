from typing import Annotated

from fastapi import Path, Depends, HTTPException, status
from sqlalchemy.ext.asyncio import AsyncSession

from src.database import database_manager
from src.documents.models import Document

from src.documents import crud


async def document_by_id(
    document_id: Annotated[int, Path],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> Document:
    document = await crud.get_document(session=session, document_id=document_id)
    if document is not None:
        return document

    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Document {document_id} not found!",
    )
