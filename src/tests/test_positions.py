# import pytest
# from fastapi import Depends
# from sqlalchemy.ext.asyncio import AsyncSession
# from src.database import test_database_manager
# from src.positions.models import Position
# from src.positions.schemas import PositionCreate, PositionUpdate
# from src.positions.crud import (
#     create_position,
#     get_position,
#     get_positions_with_storage,
#     update_position,
#     delete_position,
# )
#
#
# @pytest.mark.asyncio
# async def test_create_position(
#     async_session: AsyncSession = Depends(
#         test_database_manager.scoped_session_dependency
#     ),
# ):
#     new_position = PositionCreate(count=10, item_id=1)
#     position = await create_position(async_session, new_position)
#     assert position.count == 10
#     assert position.item_id == 1
#
#
# @pytest.mark.asyncio
# async def test_get_position(async_session: AsyncSession):
#     new_position = PositionCreate(count=10, item_id=1)
#     position = await create_position(async_session, new_position)
#     fetched_position = await get_position(async_session, position.id)
#     assert fetched_position.id == position.id
#     assert fetched_position.count == 10
#
#
# @pytest.mark.asyncio
# async def test_get_positions_with_storage(async_session: AsyncSession):
#     new_position1 = PositionCreate(count=10, item_id=1)
#     new_position2 = PositionCreate(count=20, item_id=2)
#     await create_position(async_session, new_position1)
#     await create_position(async_session, new_position2)
#     positions = await get_positions_with_storage(async_session)
#     positions_list = [position async for position in positions]
#     assert len(positions_list) == 2
#
#
# @pytest.mark.asyncio
# async def test_update_position(async_session: AsyncSession):
#     new_position = PositionCreate(count=10, item_id=1)
#     position = await create_position(async_session, new_position)
#     position_update = PositionUpdate(count=15, item_id=1)
#     updated_position = await update_position(async_session, position, position_update)
#     assert updated_position.count == 15
#
#
# @pytest.mark.asyncio
# async def test_delete_position(async_session: AsyncSession):
#     new_position = PositionCreate(count=10, item_id=1)
#     position = await create_position(async_session, new_position)
#     await delete_position(async_session, position)
#     fetched_position = await get_position(async_session, position.id)
#     assert fetched_position is None
