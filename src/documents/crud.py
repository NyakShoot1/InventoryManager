import mimetypes
from io import BytesIO

from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.documents.models import Document
from src.documents.schemas import DocumentCreate
from src.minio import MinioDB


async def get_documents(session: AsyncSession):
    stmt = select(Document).order_by(Document.id)
    documents = await session.scalars(stmt)
    return documents


async def get_document(
    session: AsyncSession,
    document_id: int,
) -> Document | None:
    return await session.get(Document, document_id)


async def create_document(
    session: AsyncSession,
    new_document: DocumentCreate,
) -> Document:
    document = Document(**new_document.model_dump())  # как работает ** и model_dump()?
    session.add(document)
    await session.commit()
    return document


async def create_document_with_minio(
    session: AsyncSession,
    new_document: DocumentCreate,
    file: BytesIO,
) -> Document:

    MinioDB.get_instance().put_object(
        file_data=file,
        file_name=new_document.document_name,
        content_type=mimetypes.guess_type(new_document.document_name),
    )

    return await create_document(
        session,
        DocumentCreate(
            document_name=new_document.document_name,
            document_bucket_name=MinioDB.get_instance().bucket_name,
        ),
    )


async def delete_document(
    session: AsyncSession,
    document: Document,
) -> None:
    await session.delete(document)
    await session.commit()
