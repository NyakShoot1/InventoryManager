from datetime import datetime
from typing import List

from pydantic import BaseModel, ConfigDict

from src.documents.schemas import Document
from src.photos.schemas import Photo
from src.positions.schemas import Position
from src.items.schemas import ShipmentItem
from src.positions.schemas import PositionCreate


class ShipmentBase(BaseModel):
    document_id: int
    user_id: int


class ShipmentCreate(ShipmentBase):
    pass


class NewShipmentRequest(BaseModel):
    delivery_man: str
    supplier_name: str
    document_number: str
    storage_man: str
    new_positions: List[PositionCreate]
    photos_id: List[int]


class NewShipmentResponse(BaseModel):
    id: int
    document: Document
    positions: List[Position]
    photos: List[Photo]


class ShipmentUpdate(ShipmentCreate):
    pass


# TODO Убрать
class ShipmentUpdatePartial(ShipmentCreate):
    register_at: datetime
    document_id: int
    user_id: int


class Shipment(ShipmentBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int
