from typing import List

from fastapi import APIRouter, HTTPException, status, Depends
from sqlalchemy.ext.asyncio import AsyncSession

from src.positions.dependencies import position_by_id
from src.positions.schemas import (
    Position,
    PositionCreate,
    PositionUpdate,
    PositionUpdatePartial,
    PositionWithItemId,
)
from src.positions import crud
from src.database import database_manager

position_router = APIRouter(tags=["Position"])


@position_router.post(
    "/create_positions/",
    response_model=List[Position],
    status_code=status.HTTP_201_CREATED,
)
async def create_positions(
    new_positions: List[PositionCreate],
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    created_positions = []
    for new_position in new_positions:
        created_position = await crud.create_position(session, new_position)
        created_positions.append(created_position)
    return created_positions


@position_router.post(
    "/create_position/",
    response_model=Position,
    status_code=status.HTTP_201_CREATED,
)
async def create_position(
    new_position: PositionCreate,
    place_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.create_position(session, new_position, place_id)


@position_router.get(
    "/positions",
    response_model=list[PositionWithItemId],
)
async def get_positions(
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.get_positions_with_storage(session=session)


@position_router.get(
    "/{position_id}/",
    response_model=Position,
)
async def get_position(
    position_id: int,
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    position = await crud.get_position(session, position_id)
    if position is not None:
        return position
    raise HTTPException(
        status_code=status.HTTP_404_NOT_FOUND,
        detail=f"Position with position_id: {position_id} not found",
    )


@position_router.put("/{position_id}/")
async def update_position(
    position_update: PositionUpdate,
    position: Position = Depends(position_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_position(
        session=session,
        position=position,
        position_update=position_update,
    )


@position_router.patch("/{position_id}/")
async def update_position_partial(
    position_update: PositionUpdatePartial,
    position: Position = Depends(position_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
):
    return await crud.update_position(
        session=session,
        position=position,
        position_update=position_update,
        partial=True,
    )


@position_router.delete("/{position_id}/", status_code=status.HTTP_204_NO_CONTENT)
async def delete_position(
    position: Position = Depends(position_by_id),
    session: AsyncSession = Depends(database_manager.scoped_session_dependency),
) -> None:
    await crud.delete_position(session=session, position=position)
