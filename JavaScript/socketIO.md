# Socket IO

## SocketIO 얹기

`npm i [socket.io](http://socket.io)` 로 install

pug에는 `script(src="/socket.io/socket.io.js")` 심어놓기

```jsx
// server

import http from "http";
import SocketIO from "socket.io";

const httpServer = http.createServer(app);
const wsServer = SocketIO(httpServer);

// client
const socket = io();
```

- 실행해서 `/socket.io/socket.io.js` 으로 들어가면 **SocketIO**의 함수들을 볼 수 있음
