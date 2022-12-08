## Controller

```bash
$ nest g co
? What name would you like to use for the controller? movies
CREATE src/movies/movies.controller.ts (101 bytes)
CREATE src/movies/movies.controller.spec.ts (492 bytes)
UPDATE src/app.module.ts (211 bytes)
```

- 생성으로 끝나지 않고 `app.module.ts`까지 수정해준다.

### Get 요청 만들어보기

```tsx
import { Controller, Get } from '@nestjs/common';

@Controller('movies')
export class MoviesController {
  @Get()
  getAll() {
    return 'This will return all movies';
  }
}
```

- `http://localhost:3000/movies`

```tsx
@Get(':id')
  getOne(@Param('id') movieId: string) {
    return `This will return one movie with the id : ${movieId}`;
  }
```

- `http://localhost:3000/movies/1`
- return : `This will return one movie with the id : 1`

### CRUD 만들기

```tsx
import {
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
  Put,
} from '@nestjs/common';

@Controller('movies')
export class MoviesController {
  @Get()
  getAll() {
    return 'This will return all movies';
  }

  @Get(':id')
  getOne(@Param('id') movieId: string) {
    return `This will return one movie with the id : ${movieId}`;
  }

  @Post()
  create() {
    return 'This will create a movie';
  }

  @Delete(':id')
  remove(@Param('id') movieId: string) {
    return `This will delete a movie with the id : ${movieId}`;
  }

  @Patch(':id')
  patch(@Param('id') movieId: string) {
    return `This will patch a movie with the id : ${movieId}`;
  }
}
```

## Param & Query

### Post에 json으로 body을 얹기

```tsx
{
    "name":"Tenet",
    "director":"Nolan"
}
```

- `@Body` 를 사용한다

```tsx
@Post()
create(@Body() movieData) {
  return movieData;
}
```

`{ name: 'Tenet', director: 'Nolan' }` 출력됨!

### Patch도 수정하기

```tsx
@Patch(':id')
  patch(@Param('id') movieId: string, @Body() updateData) {
    return {
      updatedMovie: movieId,
      ...updateData,
    };
```

- 결과 출력

```json
{
  "updatedMovie": "1",
  "name": "Tenet",
  "director": "Nolan"
}
```

### Query

```tsx
@Get('search')
  search(@Query('year') searchingYear: string) {
    return `We are searching for a movie made after : ${searchingYear}`;
  }

  @Get(':id')
  getOne(@Param('id') movieId: string) {
    return `This will return one movie with the id : ${movieId}`;
  }
```

- `:id` 보다 위에 있어야 검색을 할 수 있다. (express와 동일)
- `http://localhost:3000/movies/search?year=2000`
- return : `We are searching for a movie made after : 2000`

## Service

### Single-responsibility Principle

- **단일 책임 원칙**
- 하나의 module, class, function이 하나의 기능만을 책임져야한다.

### 커맨드라인으로 service 생성

```bash
$ nest g s
? What name would you like to use for the service? movies
CREATE src/movies/movies.service.ts (90 bytes)
CREATE src/movies/movies.service.spec.ts (460 bytes)
UPDATE src/app.module.ts (281 bytes)
```

### 일단 fakeDB를 array로 만들어놓기

```tsx
export class Movie {
  id: number;
  title: string;
  year: number;
  genres: string[];
}
```

### Get 요청 구현

- **Service**

```tsx
import { Injectable } from '@nestjs/common';
import { Movie } from './entities/movie.entity';

@Injectable()
export class MoviesService {
  private movies: Movie[] = [];

  getAll(): Movie[] {
    return this.movies;
  }

  getOne(id: string): Movie {
    return this.movies.find((movie) => movie.id === +id);
  }
}
```

- **Controller**

```tsx
@Get()
getAll(): Movie[] {
  return this.moviesService.getAll();
}

@Get(':id')
getOne(@Param('id') movieId: string): Movie {
  return this.moviesService.getOne(movieId);
}
```

### GetOne 예외처리

```tsx
getOne(id: string): Movie {
    const movie = this.movies.find((movie) => movie.id === +id);
    if (!movie) {
      throw new NotFoundException(`Not found Movie with ID : ${id}`);
    }
    return movie;
  }
```

- 만약 해당 `id`의 영화가 없다면? → 예외처리!

### CRUD 코드 전체

- **SERVICE**

```tsx
import { Injectable, NotFoundException } from '@nestjs/common';
import { Movie } from './entities/movie.entity';

@Injectable()
export class MoviesService {
  private movies: Movie[] = [];

  getAll(): Movie[] {
    return this.movies;
  }

  getOne(id: string): Movie {
    const movie = this.movies.find((movie) => movie.id === +id);
    if (!movie) {
      throw new NotFoundException(`Not found Movie with ID : ${id}`);
    }
    return movie;
  }

  deleteOne(id: string) {
    this.getOne(id);
    this.movies = this.movies.filter((movie) => movie.id !== +id);
    return;
  }

  create(movieData) {
    this.movies.push({
      id: this.movies.length + 1,
      ...movieData,
    });
  }

  update(id: string, updateData) {
    const movie = this.getOne(id);
    this.deleteOne(id);
    this.movies.push({ ...movie, ...updateData });
  }
}
```

- **CONTROLLER**

```tsx
import { Movie } from './entities/movie.entity';
import { MoviesService } from './movies.service';
import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
  Put,
  Query,
} from '@nestjs/common';

@Controller('movies')
export class MoviesController {
  constructor(readonly moviesService: MoviesService) {}

  @Get()
  getAll(): Movie[] {
    return this.moviesService.getAll();
  }

  @Get(':id')
  getOne(@Param('id') movieId: string): Movie {
    return this.moviesService.getOne(movieId);
  }

  @Post()
  create(@Body() movieData) {
    return this.moviesService.create(movieData);
  }

  @Delete(':id')
  remove(@Param('id') movieId: string) {
    return this.moviesService.deleteOne(movieId);
  }

  @Patch(':id')
  patch(@Param('id') movieId: string, @Body() updateData) {
    return this.moviesService.update(movieId, updateData);
  }
}
```

## DTOs and Validation

### **데이터 전송 객체(Data Transfer Object)**

```tsx
export class CreateMovieDto {
  readonly title: string;
  readonly year: number;
  readonly genres: string[];
}
```

- `id`를 뺀 모델을 만들어서 원하는 값만 받아서 사용하도록 한다.

### Pipe

**유효성 확인**

```bash
$ npm i class-validator class-transformer
```

- `main.ts`에 유효성 검사해줄 `ValidationPipe`를 얹는다.

```tsx
import { ValidationPipe } from '@nestjs/common';
import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.useGlobalPipes(new ValidationPipe());
  await app.listen(3000);
}
bootstrap();
```

- 파이프라인에 유용한 기능

```
new ValidationPipe({
      whitelist: true,
      forbidNonWhitelisted: true,
      transform: true,
    }),
```

- 만약 없는 property를 보낸다면 막게 된다.

→ `property OOOOO should not exist`

- `transform`을 사용하면 우리가 원하는 타입으로 변환해준다.

  - url로 요청을 받다보면 당연히 `string`으로 값을 받게 된는데, `number`로 받고 싶다면 이 기능을 활용한다.

- **DTO class**

```tsx
import { IsString, IsNumber } from 'class-validator';
export class CreateMovieDto {
  @IsString()
  readonly title: string;

  @IsNumber()
  readonly year: number;

  @IsString({ each: true })
  readonly genres: string[];
}
```
