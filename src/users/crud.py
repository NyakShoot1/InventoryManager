from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.auth.utils import hash_password
from src.users.models import User
from src.users.schemas import UserCreate, UserUpdate, UserUpdatePartial


async def get_users(session: AsyncSession):
    stmt = select(User).order_by(User.id)
    users = await session.scalars(stmt)
    return users


async def get_user(session: AsyncSession, user_id: int) -> User | None:
    return await session.get(User, user_id)


async def get_user_by_login(session: AsyncSession, user_login: str) -> User | None:
    return await session.scalar(select(User).where(User.login == user_login))


async def create_user(session: AsyncSession, new_user: UserCreate) -> User:
    user = User(**new_user.model_dump())  # как работает ** и model_dump()?
    user.password = hash_password(user.password)
    session.add(user)
    await session.commit()
    return user


async def update_user(
    session: AsyncSession,
    user: User,
    user_update: UserUpdate | UserUpdatePartial,
    partial: bool = False,
) -> User:
    for name, value in user_update.model_dump(exclude_unset=partial).items():
        setattr(user, name, value)
    await session.commit()
    return user


async def delete_user(
    session: AsyncSession,
    user: User,
) -> None:
    await session.delete(user)
    await session.commit()
