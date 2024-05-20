from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.documents.dependencies import document_by_id
from src.documents.schemas import (
    Document,
    DocumentCreate,
)
from src.documents import crud
from src.database import database_manager

document_router = APIRouter(tags=["Document"])


@document_router.post(
    "/create_document/",
    response_model=Document,
    status_code=status.HTTP_201_CREATED,
)
async def create_document(
    new_document: DocumentCreate,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_document(session, new_document)


@document_router.get(
    "/documents",
    response_model=list[Document],
)
async def get_documents(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_documents(session=session)


@document_router.get(
    "/{document_id}/",
    response_model=Document,
)
async def get_document(
    document_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    document = await crud.get_document(session, document_id)
    if document is not None:
        return document
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Document with document_id: {document_id} not found",
    )


@document_router.delete(
    "/{document_id}/",
    status_code=status.HTTP_204_NO_CONTENT,
)
async def delete_document(
    document: Document = Depends(document_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_document(session=session, document=document)
