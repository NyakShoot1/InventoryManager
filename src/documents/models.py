from typing import TYPE_CHECKING

from sqlalchemy.orm import Mapped, mapped_column, relationship

from src import Base

if TYPE_CHECKING:
    from src.shipments.models import Shipment


class Document(Base):
    document_name: Mapped[str] = mapped_column(unique=True)
    document_bucket_name: Mapped[str] = mapped_column()

    shipment: Mapped["Shipment"] = relationship("Shipment", back_populates="document")
