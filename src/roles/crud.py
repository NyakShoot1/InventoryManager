from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.roles.models import Role
from src.roles.schemas import RoleCreate, RoleUpdate, RoleUpdatePartial


async def get_roles(session: AsyncSession):
    stmt = select(Role).order_by(Role.id)
    roles = await session.scalars(stmt)
    return roles


async def get_role(session: AsyncSession, role_id: int) -> Role | None:
    return await session.get(Role, role_id)


async def create_role(session: AsyncSession, new_role: RoleCreate) -> Role:
    role = Role(**new_role.model_dump())  # как работает ** и model_dump()?
    session.add(role)
    await session.commit()
    return role


async def update_role(
    session: AsyncSession,
    role: Role,
    role_update: RoleUpdate | RoleUpdatePartial,
    partial: bool = False,
) -> Role:
    for name, value in role_update.model_dump(exclude_unset=partial).items():
        setattr(role, name, value)
    await session.commit()
    return role


async def delete_role(
    session: AsyncSession,
    role: Role,
) -> None:
    await session.delete(role)
    await session.commit()
