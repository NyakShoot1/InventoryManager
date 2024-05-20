from sqlalchemy.orm import Mapped, mapped_column

from src import Base


class Supplier(Base):
    email: Mapped[str] = mapped_column(unique=True)
    address: Mapped[str]
    phone_number: Mapped[str]
    status: Mapped[bool]
