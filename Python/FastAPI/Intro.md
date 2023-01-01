# FastAPI

## What is FastAPI?

### 프레임워크

- 웹 프레임워크 : Spring, Django
- **마이크로 프레임워크** : Flask, FastAPI

### ASGI 애플리케이션

- WSGI : 파이썬에서 정의한 일종의 웹애플리케이션 가이드라인
- ASGI : WSGI + 비동기

## Why FastAPI?

- 쉽다.
- 모던 파이썬 문법
  - 비동기 키워드 : async, await
  - 타입 힌트
- **OpenAPI** 기반 (+GraphQL)
- 자동 문서 생성 : 스웨거처럼 외부 서드파티가 필요하지 않다
- **마이크로 프레임워크**
  - API 서버
  - **MSA**

## vs Flask

### Flask

```python
from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello():
	return "Hello, World!"
```

### FastAPI

```python
from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def hello():
	return "Hello, World!"
```

## FastAPI 설치

```bash
$ python -v
>> 파이썬 3.6이상이어야함

# 프로젝트 디렉토리를 만들기
$ mkdir fastapi_tutorial
$ cd fastapi_tutorial

# 가상환경 세팅, venv가 아니어도 됨
$ python -m venv venv

# mac
$ source venv/bin/activate
# windows
$ .\venv\Scripts\activate

# 확인하기 (파이썬을 어떤 곳에서 끌어다 쓰는지 묻기)
$ which python

# FastAPI 설치
$ pip install fastapi
```

### FastAPI 실행

- 개발용 내장서버가… 없다! 따로 설치해야한다.

```bash
# uvicorn install!
$ pip install uvicorn

# main.py
$ uvicorn main:app --reload
```

- `main` : `main.py`
- `app` : `main.py`의 `app`
- `--reload` : 파일에 변화가 생기면 재시작하겠다는 옵션

### 명령어를 입력하지 않고 실행

```python
import uvicorn

from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def hello():
	return "Hello, World!"

if __name__ == "__main__":
    uvicorn.run(app)
    # uvicorn.run("main:app", reload=True)
```

### 실행 성공

- `http://127.0.0.1:8000/docs` 에 들어가면 **swagger UI**가 바로 떠버린다!
- `http://127.0.0.1:8000/redoc` 에 들어가면 깔끔하게 읽을 수 있는 명세서 **UI**가 바로 떠버린다!

## starlette & pydatic

- 두 라이브러리 위에서 FastAPI는 작동한다.
- starlette : FastAPI가 사용하는 웹프레임워크
- pydantic : 파이썬 타입 어노테이션 문법에 근거하여 데이터 검증을 해주는 라이브러리
