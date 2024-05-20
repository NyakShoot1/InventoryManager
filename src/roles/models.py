from typing import TYPE_CHECKING

from sqlalchemy.orm import Mapped, relationship

from src.base import Base

if TYPE_CHECKING:
    from src.users.models import User


class Role(Base):
    name: Mapped[str]

    users: Mapped[list["User"]] = relationship("User", back_populates="role")
