from pydantic import BaseModel, ConfigDict


class SupplierBase(BaseModel):
    email: str
    address: str
    phone_number: str
    status: bool


class SupplierCreate(SupplierBase):
    pass


class SupplierUpdate(SupplierCreate):
    pass


class SupplierUpdatePartial(SupplierCreate):
    email: str | None = None
    address: str | None = None
    phone_number: str | None = None
    status: bool | None = None


class Supplier(SupplierBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int
