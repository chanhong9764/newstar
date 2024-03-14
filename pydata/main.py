from fastapi import FastAPI
from sqlalchemy import Table, select, MetaData

from category_crawling import do_crawling
from database import get_db, engine
from models import init_db
from routers.elasticsearch import es_router
from routers.recode import recode_router
from routers.recommend import recommend_router
from routers.search import search_router

app = FastAPI()
# 메타데이터를 생성한다.
metadata_obj = MetaData()

@app.on_event("startup")
async def startup():
  init_db()

@app.get("/crawling")
async def start_crawling():
  do_crawling().to_sql(name='article', con= engine, if_exists='append', index=False)
  article_id = es_router.last_article_id()

  if article_id.loc[0]['article_id'] == 0:
    es_router.init_es()
  else:
    es_router.add_es(article_id.loc[0]['article_id'])

  es_router.update_last_article_id()
  return {"message": "complete crawling"}


app.include_router(recommend_router.router)
app.include_router(recode_router.router)
app.include_router(search_router.router)


@app.get("/member" )
def read_items():
  connection = next(get_db())
  try:
    some_table = Table("member", metadata_obj, autoload_with=engine)
    stmt = select(some_table)
    datas = connection.execute(stmt)
    for data in datas:
      print(data.member_id, data.email, data)

    # result = session.query(Member).all()

    # result = connection.execute(text("SELECT * FROM member"))
    # member = result.first()
    # print(member)

  finally:
    # 데이터베이스 연결 종료
    connection.close()

