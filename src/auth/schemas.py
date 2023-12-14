from typing import Optional, Annotated

from annotated_types import MaxLen, MinLen
from fastapi_users import schemas
from pydantic import EmailStr, BaseModel


class UserRead(schemas.BaseUser[int]):
    id: int
    email: EmailStr
    username: Annotated[str, MaxLen(20), MinLen(3)]
    phone_number: str
    role_id: int
    is_active: bool = True
    is_superuser: bool = False
    is_verified: bool = False

    class Config:
        from_attributes = True


class UserCreate(schemas.BaseUserCreate):
    username: Annotated[str, MaxLen(20), MinLen(3)]
    email: EmailStr
    phone_number: str
    password: str
    role_id: int
    is_active: Optional[bool] = True
    is_superuser: Optional[bool] = False
    is_verified: Optional[bool] = False


class Item(BaseModel):
    username: str
