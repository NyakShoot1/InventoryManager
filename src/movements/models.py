from datetime import datetime
from typing import TYPE_CHECKING

from sqlalchemy.orm import Mapped, mapped_column, relationship
from sqlalchemy import String, func, ForeignKey

from src.base import Base

if TYPE_CHECKING:
    from src.users.models import User


class Movement(Base):
    out: Mapped[int] = mapped_column(nullable=False)  # Str?
    where: Mapped[int] = mapped_column(nullable=False)  # Str?
    type: Mapped[bool]  # Если true - внутреннее, false - внешнее
    status: Mapped[str]  # enum?
    date: Mapped[datetime] = mapped_column(
        server_default=func.now(), default=datetime.now
    )

    # document_id: Mapped[int]

    user_id: Mapped[int] = mapped_column(ForeignKey("users.id"), nullable=False)
    user: Mapped["User"] = relationship("User", back_populates="movements")
