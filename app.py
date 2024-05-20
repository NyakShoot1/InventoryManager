from contextlib import asynccontextmanager

import uvicorn
from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

from src.settings import settings
from src import api_v1_router


@asynccontextmanager
async def lifespan(app: FastAPI):
    yield


app = FastAPI(title="Inventory Manager", lifespan=lifespan)
app.include_router(api_v1_router, prefix=settings.api_v1_prefix)


#
# if __name__ == "__main__":
#     uvicorn.run("app:app", reload=True)
