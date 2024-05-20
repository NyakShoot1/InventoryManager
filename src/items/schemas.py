from pydantic import BaseModel, ConfigDict


class ItemBase(BaseModel):
    name: str
    cost: int
    purchase_price: int
    description: str | None = None
    country: str | None = None
    item_number: int | None = None
    wight: int | None = None
    volume: int | None = None
    unit: str
    barcode: int


class ItemCreate(ItemBase):
    pass


class ItemUpdate(ItemCreate):
    pass


class ItemUpdatePartial(ItemCreate):
    name: str | None = None
    cost: int | None = None
    purchase_price: int | None = None
    description: str | None = None
    country: str | None = None
    item_number: int | None = None
    wight: int | None = None
    volume: int | None = None
    unit: str | None = None
    barcode: int | None = None


class Item(ItemBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int


class ShipmentItem(Item):
    count: int
    full_item_cost: int
