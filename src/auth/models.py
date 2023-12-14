from datetime import datetime

from fastapi_users_db_sqlalchemy import SQLAlchemyBaseUserTable
from sqlalchemy import String, ForeignKey, Boolean
from sqlalchemy.ext.asyncio import AsyncAttrs
from sqlalchemy.orm import DeclarativeBase
from sqlalchemy.orm import Mapped
from sqlalchemy.orm import mapped_column


class Base(AsyncAttrs, DeclarativeBase):
    pass


class Role(Base):
    __tablename__ = "role"

    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str]

    # TODO add permission

    def __str__(self):
        return f"Role(id={self.id!r}, name={self.name!r})"

    def __repr__(self):
        return str(self)


class User(Base, SQLAlchemyBaseUserTable[int]):
    __tablename__ = "user"

    id: Mapped[int] = mapped_column(primary_key=True)
    username: Mapped[str]
    phone_number: Mapped[str]
    registered_at: Mapped[datetime] = mapped_column(default=datetime.utcnow)
    role_id: Mapped[int] = mapped_column(ForeignKey("role.id"))
    email: Mapped[str] = mapped_column(unique=True, index=True, nullable=False)
    hashed_password: Mapped[str] = mapped_column(String(length=1024), nullable=False)
    is_active: Mapped[bool] = mapped_column(Boolean, default=True, nullable=False)
    is_superuser: Mapped[bool] = mapped_column(Boolean, default=False, nullable=False)
    is_verified: Mapped[bool] = mapped_column(Boolean, default=False, nullable=False)

    def __str__(self):
        return f"User(id={self.id!r}, username={self.username!r}, phone_number={self.phone_number!r}, email={self.email!r}, registered_at={self.registered_at!r})"

    def __repr__(self):
        return str(self)

# role = Table(
#     "role",
#     metadata,
#     Column("id", Integer, primary_key=True),
#     Column("name", String, nullable=False)
#
# )
#
# user = Table(
#     "user",
#     metadata,
#     Column("id", Integer, primary_key=True),
#     Column("email", String, nullable=False),
#     Column("username", String, nullable=False),
#     Column("hashed_password", String, nullable=False),
#     Column("phone_number", String, nullable=False),
#     Column("registered_at", TIMESTAMP, default=datetime.utcnow),
#     Column("role_id", Integer, ForeignKey(role.c.id)),
#     Column("is_active", Boolean, default=True, nullable=False),
#     Column("is_superuser", Boolean, default=False, nullable=False),
#     Column("is_verified", Boolean, default=False, nullable=False),
# )
