from pydantic import BaseModel, ConfigDict


class PhotoBase(BaseModel):
    photo_name: str
    photo_bucket_name: str


class PhotoCreate(PhotoBase):
    pass


class PhotoCreateForShipment(PhotoBase):
    shipment_id: int


class PhotoUpdateShipment(BaseModel):
    shipment_id: int


class Photo(PhotoBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int
