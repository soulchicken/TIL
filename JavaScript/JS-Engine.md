# 인간 JS 엔진되기

## 목차

- [함수와 함수호출](#함수와-함수호출)
- [호출 스택](#호출-스택-call-stack)

---

## 함수와 함수호출

```jsx
// 화살표 함수 선언
const add = (a, b) => a + b;

// 화살표 함수로 객체를 리턴하고 싶다면?
const objReturn = (a, b) => ({a + b});
// 이러면 안됨 : const objReturn = (a, b) => {a + b};

add(3, 5); // 8이 return됨!
```

- **함수의 선언** : 함수 만들기
- **함수** : 함수 그 자체
- **함수의 호출** : 함수를 실행 (사용할 때는 `return 값`으로 대체해서 생각)

```jsx
const add = (a, b) => a + b;

function calculator(func, a, b) {
  return func(a, b);
}

calculator(add, 3, 5); // 8이 return됨!
// 이러면 안됨 calculator(add(), 3, 5);
```

- `add`는 **함수**, `add()`는 **함수의 호출 (값)**을 의미한다.

### **Example**

`addEventListener`에서 넣는 **함수**

```jsx
const add = (a, b) => a + b;

document.querySelector("#header").addEventListener("click", add); // add()를 넣지 않는다.
```

### 고차함수 : 괄호는 죄가 없음

```jsx
const onClick = () => () => {
  console.log("hi!");
};

document.querySelector("#header").addEventListener("click", onClick());
```

- `onClick`의 화살표 함수가 읽기 어렵다면?
  → `return 문`으로 바꿔주자

```jsx
const onClick = () => () => {
  console.log("hi!");
};

// 위 함수는 아래와 같다.

const onClick = () => {
  return () => {
    console.log("hi!");
  };
};

// 따라서

document.querySelector("#header").addEventListener("click", onClick());

// 위 문장은 아래와 같다.

document.querySelector("#header").addEventListener("click", () => {
  console.log("hi!");
});
```

---

## 호출 스택 (Call Stack)

```jsx
const x = "x";
function h() {
  const y = "y";
  console.log("h");
}

function f() {
  console.log("f");
  function g() {
    const z = "z";
    console.log("g");
    h();
  }
  g();
}

f(); // f, g, h 출력!
h(); // h 출력!
```

막 엄청 꼬여있는 코드들을 보고 정확하게 어떻게 변수를 가져올 수 있는 지 확인할 수 있어야한다.

→ 그치만 **자바스크립트 스펙 공식문서를 외우면 안된다.**

- 코드는 위에서 아래로 읽힌다. (1차원적)
  → 1차원적인 흐름을 벗어나야한다.
- 위 함수를 호출스택으로 그려보기

```jsx
>> f()  f 실행
>> f() console.log('f')  콘솔로그가 실행될 것임
>> f()  콘솔 끝 사라짐
>> f() g()   g 실행
>> f() g() console.log('g')   콘솔로그 실행
>> f() g() 콘솔 끝 사라짐
>> f() g() h()   h 실행
>> f() g() h() console.log('h')   콘솔로그 실행
>> f() g() h()   콘솔 끝 사라짐
>> f() g()   h 종료
>> f()   g 종료
>>    f 종료
>> h()   h 실행
>> h() console.log('h')   콘솔로그 실행
>> h()   콘솔 끝 사라짐
>>   h 종료
```

### debugger 사용

위 상황을 확실히 보려면 `debugger`를 사용하면 된다.

`h()`의 마지막에 `debugger`를 얹고 개발자도구에서 실행하면 Sources 창으로 옮겨가면서 호출 스택 (Call Stack)을 확인할 수 있다.

<img src="./JS-Engine/debugger.png" height="500px" title="debugger"/>

```jsx
// 시점은?
>> f() g() h()   콘솔 끝 사라짐
```
