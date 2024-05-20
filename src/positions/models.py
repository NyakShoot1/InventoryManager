from typing import TYPE_CHECKING

from sqlalchemy import Integer, ForeignKey
from sqlalchemy.orm import Mapped, mapped_column, relationship

from src import Base

if TYPE_CHECKING:
    from src.places.models import Place
    from src.items.models import Item


class Position(Base):
    count: Mapped[int] = mapped_column(Integer, nullable=False)

    item_id: Mapped[int] = mapped_column(ForeignKey("items.id"), nullable=False)
    # item: Mapped["Item"] = relationship(back_populates="positions") вроде не надо

    place_id: Mapped[int] = mapped_column(ForeignKey("places.id"), nullable=True)
    place: Mapped["Place"] = relationship(back_populates="positions")
