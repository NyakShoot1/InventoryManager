from pydantic import BaseModel, ConfigDict


class RoleBase(BaseModel):
    name: str


class RoleCreate(RoleBase):
    pass


class RoleUpdate(RoleCreate):
    pass


class RoleUpdatePartial(RoleCreate):
    name: str | None = None


class Role(RoleBase):
    model_config = ConfigDict(from_attributes=True)  # ?

    id: int
