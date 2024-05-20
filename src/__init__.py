__all__ = (
    "Base",
    "Storage",
    "Place",
    "Position",
    "Item",
    "User",
    "Movement",
    "Supplier",
    "Shipment",
    "Document",
    "Photo",
    "api_v1_router",
)

from .auth.validation import get_current_auth_user
from .base import Base
from .storages import Storage
from .places import Place
from .positions import Position
from .items import Item
from .users import User
from .roles import Role
from .movements import Movement
from .suppliers import Supplier
from .shipments import Shipment
from .documents import Document
from .photos import Photo

from fastapi import APIRouter, Depends
from src.places.router import place_router
from src.storages.router import storage_router_base_crud
from src.positions.router import position_router
from src.items.router import item_router
from src.users.router import user_router
from src.roles.router import role_router
from src.movements.router import movement_router
from src.auth.router import auth_router
from src.suppliers.router import supplier_router
from src.shipments.router import shipment_router
from src.documents.router import document_router
from src.photos.router import photo_router

api_v1_router = APIRouter()
api_v1_router.include_router(router=auth_router)
# base crud
api_v1_router.include_router(
    router=storage_router_base_crud,
    prefix="/storages",
    dependencies=[Depends(get_current_auth_user)],
)
api_v1_router.include_router(router=place_router, prefix="/place")
api_v1_router.include_router(router=item_router, prefix="/item")
api_v1_router.include_router(router=position_router, prefix="/position")
api_v1_router.include_router(router=user_router, prefix="/user")
api_v1_router.include_router(router=supplier_router, prefix="/supplier")
api_v1_router.include_router(router=role_router, prefix="/role")
api_v1_router.include_router(router=movement_router, prefix="/movement")
api_v1_router.include_router(router=shipment_router, prefix="/shipment")
api_v1_router.include_router(router=document_router, prefix="/document")
api_v1_router.include_router(router=photo_router, prefix="/photo")
