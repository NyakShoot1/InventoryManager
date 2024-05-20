from typing import Annotated

from annotated_types import MinLen, MaxLen
from pydantic import BaseModel, ConfigDict


class PlaceBase(BaseModel):
    place_code: Annotated[str, MinLen(1), MaxLen(2)]
    place_number: int
    busy: bool


class PlaceCreate(PlaceBase):
    pass


class PlaceUpdate(PlaceCreate):
    pass


class PlaceUpdatePartial(PlaceCreate):
    place_code: Annotated[str, MinLen(1), MaxLen(2)] | None = None
    place_number: int | None = None
    busy: bool | None = None


class Place(PlaceBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int


class PlaceWithStorageId(PlaceBase):
    model_config = ConfigDict(from_attributes=True)
    id: int
    storage_id: int
