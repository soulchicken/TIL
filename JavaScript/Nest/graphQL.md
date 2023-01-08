# GraphQL

**공식 문서**

[Documentation | NestJS - A progressive Node.js framework](https://docs.nestjs.com/graphql/quick-start)

```jsx
$ npm i @nestjs/graphql @nestjs/apollo graphql apollo-server-express
```

## Code First

### 모듈 얹기

```tsx
import { Module } from "@nestjs/common";
import { GraphQLModule } from "@nestjs/graphql";

@Module({
  imports: [GraphQLModule.forRoot()],
  controllers: [],
  providers: [],
})
export class AppModule {}
```

- `imports` 에 잘 심어준다. `GraphQLModule`의 `Root` 모듈로 `app.module`이 사용될 것이다.

### Resolver

모듈을 만들어주자

```jsx
$ nest g mo restaurants
```

```jsx
imports: [
  GraphQLModule.forRoot({
    autoSchemaFile: join(process.cwd(), "src/schema.gql"),
  }),
  RestaurantsModule,
],
```
