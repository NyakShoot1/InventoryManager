from datetime import datetime
from typing import Annotated

from annotated_types import MaxLen, MinLen
from pydantic import BaseModel, ConfigDict, EmailStr


class UserBase(BaseModel):
    email: EmailStr
    fullname: str
    login: Annotated[str, MinLen(3), MaxLen(32)]
    phone_number: Annotated[str, MinLen(12), MaxLen(12)]
    register_at: datetime
    role_id: int


class UserCreate(UserBase):
    password: str


class UserUpdate(UserCreate):
    pass


class UserUpdatePartial(UserCreate):
    email: EmailStr | None = None
    fullname: str | None = None
    login: Annotated[str, MinLen(3), MaxLen(32)] | None = None
    phone_number: Annotated[str, MinLen(12), MaxLen(12)] | None = None
    register_at: datetime | None = None
    role_id: int | None = None


class User(UserBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int
