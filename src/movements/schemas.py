from datetime import datetime
from typing import Union

from pydantic import BaseModel, ConfigDict


class MovementBase(BaseModel):
    out: int
    where: int
    status: str
    type: bool
    date: datetime
    user_id: int


class MovementCreate(MovementBase):
    pass


class MovementStatusUpdate(BaseModel):
    status: str


class Movement(MovementBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int
