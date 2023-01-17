# Overview

## Library vs Framework

### Library : 내가 사용하는 것

내가 라이브러리를 불러와서 내가 사용해서 뭔가를 만들어냄

내가 원하는 코드를 작성하고 필요할 때 라이브러리의 코드를 사용함

내가 원할 때 어떤 방법이든 문제가 되지 않음

- REACT 앱을 만들 때에는 **자유도**가 생김
  Routes, Componenets 폴더를 어디에 어떻게 놓을 지 내가 선택할 수 있음

### Framework : 내가 들어감

프레임워크는 내 코드를 불러옴.

프레임워크에서 내가 원하는 부분에 잘 적기만 한다면 내 코드를 불러와서 모든 것을 동작함.

- Next에서는 특정한 규칙에 따라 특정한 코드를 작성해야함. 규칙을 따라야만 정상적으로 동작하고 우리를 Next가 도와줌.
  → react의 어딘가에서 root 앨리먼트에 컴포넌트를 꽂아주는 일을 Next 어딘가에서 해주는데. 이 것을 숨김! (추상화)

## import React를 안해도 됨!

```jsx
export default function Home() {
  return (
    <div>
      <h1>hello!</h1>
    </div>
  );
}
```

- `index.js` 파일에 `import` 문이 없지만 잘 나오는 것을 확인할 수 있음

## Static Pre Rendering

- create react app 으로 프로젝트를 만들면 클라이언트 사이드 랜더링이 된다.
  : 브라우저가 유저가 보는 UI를 만들게 됨.
  - 사실상 소스코드를 떼보면 `div` 태그의 `root` 밖에 없고 나머지는 자바스크립트 코드임 유저는 루트만 보게 됨
- `next`의 경우 api까지는 못받아오더라도 유저는 `html` 파일을 직접 받아오게 된다.
  : 미리 랜더링이 되어있는, 서버 사이드 랜더링

## CSS Modules

- react 컴포넌트에 css를 얹을 수 있게 된다.
- 클래스 이름을 추가해줄 때, 클래스 이름을 텍스트로 적지 않는다.
- 실제로 랜더링된 페이지에서 보기 되면 랜덤하게 클래스 이름이 적용되기 때문에 CSS 충돌이 일어나지 않는다.

```jsx
import styles from "./NavBar.module.css";
...
<nav className={styles.nav}>
```

### 클래스 네임을 여러 개 넣고 싶을 때

```jsx
<Link href="/">
        <a
          className={`${styles.link} ${
            router.pathname === "/" ? styles.active : ""
          }`}
        >
          Home
        </a>
      </Link>
      <Link href="/about">
        <a
          className={[
            styles.link,
            router.pathname === "/about" ? styles.active : "",
          ].join(" ")}
        >
          About
        </a>
      </Link>
```

- `join`을 활용한다.
- 백틱(` `)을 활용한다.
- 둘 다 별로다.

## styled JSX

- Next 고유의 방법이라고 볼 수 있다.
- CSS를 해당 파일만 직접 먹일 수 있다. 다른 파일에 있는 style에는 적용이 안되서 좋다.

```jsx
import Link from "next/link";
import { useRouter } from "next/router";

export default function NavBar() {
  const router = useRouter();
  return (
    <nav>
      <Link href="/">
        <a>Home</a>
      </Link>
      <Link href="/about">
        <a>About</a>
      </Link>
      <style jsx>{`
        nav {
          background-color: tomato;
        }
        a {
          text-decoration: none;
        }
      `}</style>
    </nav>
  );
}
```

### global styles

```jsx
import NavBar from "../components/NavBar";

export default function Home() {
  return (
    <div>
      <NavBar />
      <h1>hello</h1>
      <style jsx global>
        {`
          a {
            color: white;
          }
        `}
      </style>
    </div>
  );
}
```

- global 키워드를 사용하면 이 페이지 내의 모든 style에 적용된다. 다른 페이지에는 적용되지 않C는다.

## App Component

- 청사진. NextJS가 모든 페이지에 렌더링할 수 있게하는 컴포넌트.
- `pages` 디렉토리에 `_app.js` 파일을 생성한다. 이 파일을 먼저 랜더링하고 그 이후 해당 페이지에 맞는 파일이 실행될 것이다 .
- `navBar` 같이 모든 페이지에 들어가는 요소를넣어 두면 편리하다.
- `global`로 적용할 `style`을 먹일 때 편리하다.
- `module.css`이 아닌 `globals.css`같은 파일을 적용할 수 있는 것은 `_app.js` 같은 앱 컴포넌트 뿐이다.

```jsx
import NavBar from "../components/NavBar";
import "../styles/globals.css";

export default function App({ Component, pageProps }) {
  return (
    <div>
      <NavBar />
      <Component {...pageProps} />
      <span>hello</span>
      <style jsx global>
        {`
          a {
            color: white;
          }
        `}
      </style>
    </div>
  );
}
```
