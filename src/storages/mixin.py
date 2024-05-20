from typing import TYPE_CHECKING

from sqlalchemy import ForeignKey
from sqlalchemy.orm import declared_attr, mapped_column, Mapped, relationship

if TYPE_CHECKING:
    from src.storages.models import Storage


class StorageRelationMixin:
    _storage_id_nullable: bool = False
    # _storage_id_unique: bool = False
    _storage_back_populates: str | None = None

    @declared_attr
    def storage_id(cls) -> Mapped[int]:
        return mapped_column(
            ForeignKey("storages.id"),
            # unique=cls._storage_id_unique,
            nullable=cls._storage_id_nullable,
        )

    @declared_attr
    def storage(cls) -> Mapped["Storage"]:
        return relationship(
            "Storage",
            back_populates=cls._storage_back_populates,
        )
