# URI 설계 패턴

## URI (Uniform Resource Identifier)

- 인터넷에서 특정 자원을 나타내는 주소값. 해당 값은 유일하다. (응답은 달라질 수 있다.)

```tsx
요청: //www.google.com/resource/sample/1
https: 응답: google.pdf;
```

## URL (Uniform Resource Locator)

- 인터넷 상에서 자원, 특정 파일이 어디에 위치하는지 식별하는 주소
- URI의 하위 개념

```tsx
요청 : https://www.google.com/google.pdf
```

## URI 설계 원칙 (RFC-3986)

### 슬래시 구분자 (`/`)는 계층 관계를 나타내는 데 사용

```
https://www.google.com**/**resource**/**sample**/**1
```

### URI 마지막 문자로 (`/`)는 포함하지 않는다.

```
https://www.google.com**/**resource**/**sample**/**1**/**
```

### 하이픈(`-`)은 URI 가독성을 높이는데 사용한다

```
https://www.google.com**/**resource**/**sample-file**/**1
```

### 밑줄(`_`)은 사용하지 않는다.

```
https://www.google.com**/**resource**/**sample**_**file**/**1
```

### URI 경로에는 소문자가 적합하다.

```
GOOD ) https://www.google.com**/**resource**/**sample**/**1
BAD ) https://www.google.com/RESOURCE**/**sample**/**1
```

### 파일 확장자는 URI에 포함하지 않는다.

```
https://www.google.com**/**resource**/**sample.jsp
// 해커 : 파일이 jsp구나?
```

### 프로그래밍 언어에 의존적인 확장자를 사용하지 않는다.

```
https://www.google.com**/**resource**/**sample.do
// 해커 : 자바로 만들었구나?
```

### 구현에 의존적인 경로를 사용하지 않는다.

```
https://www.google.com**/**servlet/classes/java/resource**/**sample**/**1
// 해커 : 서블릿을 사용했구나?
```

### 세션 ID를 포함하지 않는다.

```
https://google.com/resource/sample?session-id=asdasd
```

### 프로그래밍 언어의 매소드명을 이용하지 않는다.

```
https://google.com/resource/sample?action=intro
```

### 명사에 단수형보다는 복수형을 사용한다. 컬렉션에 대한 표현은 복수로 사용

```
https://google.com/classes/resource/curriculums
```

### 컨트롤러 이름으로는 동사나 동사구를 사용한다.

```
https://google.com/resource/sample/re-order
```

### 경로 부분 중 변하는 부분은 유일한 값으로 대체 한다.

```
생략.../lessons/{lesson-id}/users/{user-id}
생략.../lessons/2/users/100
```

### CRUD 기능을 나타내는 것은 URI에 사용하지 않는다.

```
BAD ) 생략.../lessons/users/READ
GOOD ) 생략.../lessons/users/
```

### URI Query Parameter 디자인

- URI 쿼리 부분으로 컬렉션 결과에 대해서 필터링 할 수 있다.

```
생략.../lessons/users?chapter=2
```

### API에 있어서 서브 도메인은 일관성 있게 사용해야 한다.

```
https://google.com
https://api.google.com
https://api-google.com
```

### 클라이언트 개발자 포탈 서브 도메인은 일관성 있게 만든다.

```
https://dev-google.com
https://developer-google.com
```
