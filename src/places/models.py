from typing import TYPE_CHECKING

from sqlalchemy.orm import Mapped, mapped_column, relationship
from sqlalchemy import String, UniqueConstraint

from src.storages.mixin import StorageRelationMixin
from src.base import Base

if TYPE_CHECKING:
    from src.positions.models import Position


class Place(StorageRelationMixin, Base):
    _storage_back_populates = "places"

    place_code: Mapped[str] = mapped_column(String(4))
    place_number: Mapped[int]
    busy: Mapped[bool]

    positions: Mapped[list["Position"]] = relationship(back_populates="place")

    __table_args__ = (
        UniqueConstraint(
            "place_code",
            "storage_id",
            name="uq_place_code_storage_id",
        ),
        UniqueConstraint(
            "place_number",
            "storage_id",
            name="uq_place_number_storage_id",
        ),
    )
