from typing import TYPE_CHECKING

from sqlalchemy import Integer, String, ForeignKey
from sqlalchemy.orm import Mapped, mapped_column, relationship

from src import Base

if TYPE_CHECKING:
    from src.photos.models import Photo


class Item(Base):
    name: Mapped[str]
    cost: Mapped[int]
    purchase_price: Mapped[int]
    description: Mapped[str] = mapped_column(Integer, nullable=True)
    country: Mapped[str] = mapped_column(Integer, nullable=True)
    item_number: Mapped[int] = mapped_column(Integer, nullable=True)
    wight: Mapped[int] = mapped_column(Integer, nullable=True)
    volume: Mapped[int] = mapped_column(Integer, nullable=True)
    unit: Mapped[str] = mapped_column(String, nullable=False)
    # QR: Mapped[int]
    barcode: Mapped[int]

    # photo_id = mapped_column(Integer, ForeignKey("photos.id"))
    # photo: Mapped["Photo"] = relationship(
    #     "Photo", back_populates="item", foreign_keys=[photo_id]
    # )
    # supplier_name
    # Возможно можно добавить отношения с таблицей position, но пока не надо(как в role и user)
