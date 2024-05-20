from pydantic import BaseModel, ConfigDict


class StorageBase(BaseModel):
    address: str


class StorageCreate(StorageBase):
    pass


class StorageUpdate(StorageCreate):
    pass


class StorageUpdatePartial(StorageCreate):
    address: str | None = None


class Storage(StorageBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int
