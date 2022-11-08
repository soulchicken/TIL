# http 모듈로 서버 만들기

## http 서버 만들기

### http 요청에 응답하는 노드 서버

- `createServer`로 요청 이벤트 대기
- `req` 객체는 요청에 관한 정보가, `res` 객체는 응답에 관한 정보가 담겨 있음

```jsx
const http = require("http");

http.createServer((req, res) => {
  // 여기에 어떻게 요청에 반응할 지가 들어감.
});
```

![노드서버예시](./http-module/1.png)

- 서버를 실행하면 `createServer`가 실행
- 실행하는 포트는 `listen`을 통해 `8080` 포트로 된다.

```jsx
const http = require("http");

const server = http
  .createServer((req, res) => {
    res.write("<h1>Hello Node!</h1>");
    res.write("<p>Hello server</p>");
    res.end("<h1>Hello Soul</h1>");
  })
  .listen(8080);

// listen의 콜백함수를 아래 on.~ listening으로 뺄 수 있다.

server.on("listening", () => {
  console.log("8080번 보트에서 서버 대기 중입니다.");
});

// 에러에 대한 이야기도 만들어낼 수 있다.

server.on("error", (error) => {
  console.error(error);
});
```

- 브라우저로 데이터를 보내줄 때 정확히 **html**인지 **문자열**인지 알려줘야한다.

```jsx
const server = http
  .createServer((req, res) => {
    **res.writeHead(200, { "Content-Type": "text/html; charset=utf-8" });**
    res.write("<h1>Hello Node!</h1>");
    res.write("<p>Hello server</p>");
    res.end("<h1>Hello Soul</h1>");
  })
  .listen(8080);
```

- `writeHead`로 **Content-Type**을 정의해주고, 한글이 안먹힐 수 있으니 **utf-8**까지 낭낭하게 사용한다.
  - html은 `text/html`, 일반 문자열은 `text/plain`

## fs로 HTML을 읽어 제공하기

- `res.write`로 하면 불편하니 `html` 파일을 직접 보내기

```jsx
const http = require("http");
**const fs = require("fs").promises;**

const server = http
  .createServer(async (req, res) => {
    try {
      res.writeHead(200, { "Content-Type": "text/html; charset=utf-8" });
      **const data = await fs.readFile("./httpServer.html");**
      res.end(data);
    } catch (error) {
      console.error(error);
      res.writeHead(200, { "Content-Type": "text/plain; charset=utf-8" });
      res.end(error.massage);
    }
  })
  .listen(8080);
```
