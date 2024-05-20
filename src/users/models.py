from datetime import datetime
from typing import TYPE_CHECKING

from sqlalchemy.orm import Mapped, mapped_column, relationship
from sqlalchemy import String, func, ForeignKey

from src.base import Base

if TYPE_CHECKING:
    from src.roles.models import Role
    from src.movements.models import Movement
    from src.shipments.models import Shipment
    from src.storages.models import Storage


class User(Base):
    email: Mapped[str] = mapped_column(unique=True)
    login: Mapped[str] = mapped_column(String(32), unique=True)
    password: Mapped[str] = mapped_column(String(20))
    fullname: Mapped[str]
    phone_number: Mapped[str] = mapped_column(String(12))
    register_at: Mapped[datetime] = mapped_column(
        server_default=func.now(), default=datetime.now
    )

    storage_id: Mapped[int] = mapped_column(ForeignKey("storages.id"), nullable=False)
    storage: Mapped["Storage"] = relationship("Storage", back_populates="users")

    role_id: Mapped[int] = mapped_column(ForeignKey("roles.id"), nullable=False)
    role: Mapped["Role"] = relationship("Role", back_populates="users")

    movements: Mapped[list["Movement"]] = relationship(
        "Movement", back_populates="user"
    )

    shipments: Mapped[list["Shipment"]] = relationship(
        "Shipment", back_populates="user"
    )
