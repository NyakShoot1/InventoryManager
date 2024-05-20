from typing import TYPE_CHECKING

from sqlalchemy import ForeignKey
from sqlalchemy.orm import Mapped, mapped_column, relationship

from src import Base

if TYPE_CHECKING:
    from src.shipments.models import Shipment
    from src.items.models import Item


class Photo(Base):
    photo_name: Mapped[str] = mapped_column(unique=True)
    photo_bucket_name: Mapped[str] = mapped_column()

    shipment_id: Mapped[int] = mapped_column(ForeignKey("shipments.id"), nullable=True)
    shipment: Mapped["Shipment"] = relationship("Shipment", back_populates="photos")

    # item_id: Mapped[int] = mapped_column(ForeignKey("items.id"), nullable=True)
    # item: Mapped["Item"] = relationship("Item", back_populates="photo")
