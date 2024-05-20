from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.positions.models import Position
from src.positions.schemas import PositionCreate, PositionUpdate, PositionUpdatePartial


async def get_positions_with_storage(session: AsyncSession):
    stmt = select(Position).order_by(Position.id)
    positions = await session.scalars(stmt)
    return positions


async def get_position(session: AsyncSession, position_id: int) -> Position | None:
    return await session.get(Position, position_id)


async def create_position(
    session: AsyncSession, new_position: PositionCreate, place_id: int = None
) -> Position:
    position = Position(**new_position.model_dump())  # как работает ** и model_dump()?
    position.place_id = place_id
    session.add(position)
    await session.commit()
    return position


async def update_position(
    session: AsyncSession,
    position: Position,
    position_update: PositionUpdate | PositionUpdatePartial,
    partial: bool = False,
) -> Position:
    for name, value in position_update.model_dump(exclude_unset=partial).items():
        setattr(position, name, value)
    await session.commit()
    return position


async def delete_position(
    session: AsyncSession,
    position: Position,
) -> None:
    await session.delete(position)
    await session.commit()
