from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from src.movements.models import Movement
from src.movements.schemas import MovementCreate, MovementStatusUpdate


async def get_movements(session: AsyncSession):
    stmt = select(Movement).order_by(Movement.id)
    movements = await session.scalars(stmt)
    return movements


async def get_movement(session: AsyncSession, movement_id: int) -> Movement | None:
    return await session.get(Movement, movement_id)


async def create_movement(
    session: AsyncSession, new_movement: MovementCreate
) -> Movement:
    movement = Movement(**new_movement.model_dump())  # как работает ** и model_dump()?
    session.add(movement)
    await session.commit()
    return movement


async def update_status_movement(
    session: AsyncSession,
    movement: Movement,
    movement_update: MovementStatusUpdate,
    partial: bool = False,
) -> Movement:
    for name, value in movement_update.model_dump(exclude_unset=partial).items():
        setattr(movement, name, value)
    await session.commit()
    return movement


async def delete_movement(
    session: AsyncSession,
    movement: Movement,
) -> None:
    await session.delete(movement)
    await session.commit()
