from fastapi import FastAPI, Depends

from src.auth.auth_config import fastapi_users, auth_backend
from src.auth.schemas import UserRead, UserCreate, Item
import uvicorn

from src.auth.models import User

app = FastAPI(
    title="Inventory Manager"
)

app.include_router(
    fastapi_users.get_auth_router(auth_backend),
    prefix="/auth/jwt",
    tags=["Auth"],
)

app.include_router(
    fastapi_users.get_register_router(UserRead, UserCreate),
    prefix="/auth",
    tags=["Auth"],
)

current_active_user = fastapi_users.current_user(active=True)

current_user = fastapi_users.current_user()


@app.get("/protected-route2")
def protected_route(user: User = Depends(current_user)):
    return f"Hello, {user.email}"


@app.get("/penis", dependencies=[Depends(current_active_user)])
def username(item: Item):
    return f"Hello, {item.username}"


@app.get("/protected-route")
def protected_route(user: User = Depends(current_active_user)):
    return f"Hello, {user.email}"


#if __name__ == '__main__':
#    uvicorn.run("app:app", reload=True)
