from datetime import datetime
from typing import TYPE_CHECKING

from sqlalchemy import ForeignKey, func
from sqlalchemy.orm import Mapped, mapped_column, relationship

from src import Base

if TYPE_CHECKING:
    from src.users.models import User
    from src.documents.models import Document
    from src.photos.models import Photo


class Shipment(Base):

    register_at: Mapped[datetime] = mapped_column(
        server_default=func.now(), default=datetime.now
    )

    document_id: Mapped[int] = mapped_column(ForeignKey("documents.id"), nullable=False)
    document: Mapped["Document"] = relationship("Document", back_populates="shipment")

    user_id: Mapped[int] = mapped_column(ForeignKey("users.id"), nullable=False)
    user: Mapped["User"] = relationship("User", back_populates="shipments")

    photos: Mapped[list["Photo"]] = relationship("Photo", back_populates="shipment")
