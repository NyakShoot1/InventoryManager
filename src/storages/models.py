from typing import TYPE_CHECKING

from sqlalchemy.orm import Mapped, mapped_column, relationship
from sqlalchemy import String

from src.base import Base

if TYPE_CHECKING:
    from src.places.models import Place
    from src.users.models import User


class Storage(Base):
    address: Mapped[str] = mapped_column(String(50), unique=True)

    places: Mapped[list["Place"]] = relationship(back_populates="storage")
    users: Mapped[list["User"]] = relationship(back_populates="storage")
