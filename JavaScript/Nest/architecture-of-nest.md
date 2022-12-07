# NestJS

NodeJS, Express 위에서 움직이는 프레임워크

Node는 자유도가 높다. 아무런 룰이 없기 때문에 어떤 방식으로 해도 된다. 그러나 Nest같은 프레임워크의 경우 다 정해준 구조에서 순서, 룰을 지키면서 백엔드를 구축한다.

## Installation & Setup

```c
$ npm i -g @nestjs/cli
$ nest

// nest 관련 커맨드를 알려줌

// 새로운 프로젝트 시작
$ nest new project-name
```

## Overview

- `$ npm run start:dev` 를 통해 실행하고 `localhost:3000`에 접속해보자! → `Hello World`가 보인다!

### 코드를 떼보자

**main.ts**

```tsx
import { NestFactory } from "@nestjs/core";
import { AppModule } from "./app.module";

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  await app.listen(3000);
}
bootstrap();
```

- 여기서 AppModule은 뭘까?

**app.module.ts**

```tsx
import { Module } from "@nestjs/common";
import { AppController } from "./app.controller";
import { AppService } from "./app.service";

@Module({
  imports: [],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
```

- `@Module` 같은 것을 데코레이터라고 부른다.
  - 클래스에 함수 기능을 추가할 수 있다.
  - 그렇다면 이 안에 있는 AppController, AppService 코드는 어떨까?

**app.controller.ts**

```tsx
import { Controller, Get } from "@nestjs/common";
import { AppService } from "./app.service";

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }
}
```

- 이 파일이 컨트롤러의 역할을 하고, get요청을 받으면 string을 반환한다는 것을 알 수 있다.

**app.service.ts**

```tsx
import { Injectable } from "@nestjs/common";

@Injectable()
export class AppService {
  getHello(): string {
    return "Hello World!";
  }
}
```

## Controller

Express의 라우터같은 존재

- url을 가지고 함수를 실행한다
- Controller에 `/hello` 를 추가하기

```tsx
import { Controller, Get } from "@nestjs/common";
import { AppService } from "./app.service";

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get("/hello")
  sayHello(): string {
    return "Hello everyone~!";
  }
}
```

- 그러나 `controller`에서 비즈니스 로직을 처리하는 것이 아니니 이 부분은 `service` 레이어로 내려줘야할 필요가 있다.

## Services

- 함수의 이름은 다를 수 있다. 굳이 controller의 함수명과 service의 함수명을 일치하게 만들 필요는 없다.
  - 아래는 controller의 sayHello 함수에서 service의 sayHi 함수를 실행한 결과

**controller**

```tsx
import { Controller, Get } from "@nestjs/common";
import { AppService } from "./app.service";

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get("/hello")
  sayHello(): string {
    return this.appService.sayHi();
  }
}
```

**service**

```tsx
import { Injectable } from "@nestjs/common";

@Injectable()
export class AppService {
  getHello(): string {
    return "Hello World!";
  }

  sayHi(): string {
    return "hello~! Everyone~!!!!";
  }
}
```

- 두 함수의 이름이 달라도 상관 없다.
