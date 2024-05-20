from pydantic import BaseModel, ConfigDict


class PositionBase(BaseModel):
    count: int
    item_id: int


class PositionCreate(PositionBase):
    pass


class PositionUpdate(PositionCreate):
    pass


class PositionUpdatePartial(PositionCreate):
    count: int | None = None


class Position(PositionBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int


class PositionWithItemId(PositionBase):
    item_id: int
