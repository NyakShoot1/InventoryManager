from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.movements.dependencies import movement_by_id
from src.movements.schemas import (
    Movement,
    MovementCreate,
    MovementStatusUpdate,
)
from src.movements import crud
from src.database import database_manager

movement_router = APIRouter(tags=["Movement"])


@movement_router.put("/{movement_id}/")
async def update_status_movement(
    movement_update: MovementStatusUpdate,
    movement: Movement = Depends(movement_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_status_movement(
        session=session,
        movement=movement,
        movement_update=movement_update,
    )


@movement_router.post(
    "/create_movement/",
    response_model=Movement,
    status_code=status.HTTP_201_CREATED,
)
async def create_movement(
    new_movement: MovementCreate,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_movement(session, new_movement)


@movement_router.get(
    "/movements",
    response_model=list[Movement],
)
async def get_movements(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_movements(session=session)


@movement_router.get(
    "/{movement_id}/",
    response_model=Movement,
)
async def get_movement(
    movement_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    movement = await crud.get_movement(session, movement_id)
    if movement is not None:
        return movement
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Movement with movement_id: {movement_id} not found",
    )


@movement_router.delete("/{movement_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_movement(
    movement: Movement = Depends(movement_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_movement(session=session, movement=movement)
