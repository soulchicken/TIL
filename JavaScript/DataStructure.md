# 자료구조 & 알고리즘

## 목차

- [Big O 표기법](#big-o-표기법)
  - [Big O의 필요성](#big-o의-필요성)
  - [코드의 시간 재기](#코드의-시간-재기)

## Big O 표기법

### Big O의 필요성

- 그저 굴러가는 코드, 에러만 잡는 것이 아니라 더 시간이 걸리고 렉이 걸리는 코드의 이유를 알아야하기 때문이다.
- 어떤 방식으로도 문제를 해결할 수 있다. 그러나 그 중에서 어떤 것이 더 좋을 지(Best)를 고민하려면 판단 기준이 필요하다.
  → 코드를 **분류**하거나 **비교**할 수 있는 시스템이 있으면 얼마나 좋을까?
  : 코드의 성능을 얘기할 때 **정확한 용어**로 소통할 수 있어야한다.

### 코드의 시간 재기

**1에서 N까지 모든 정수를 더하는 함수를 만든다면?**

1. for문으로 1 + 2 + 3 … + N 더하기
2. N \* (N + 1) // 2 로 계산하기

→ 방식이 2가지인데, 그렇다면 어떤 함수가 더 나을까?

- **Faster**? (Let’s focus here first!)
- Less memory-intensive?
- More readable?

**시간을 확인해보자**

1번 함수

```jsx
function addUpTo(n) {
 let total = 0;
 for (let i = 1; i <= n; i++){
     total += i;
 }
 return total;
}

const t1 = performance.now();
addUpTo(1000000000);
const t2 = performance.now();
console.log(`Time Elapsed : ${(t2 - t1) / 1000} seconds`);

>> Time Elapsed : 0.9812999999998138 seconds
```

2번 함수

```jsx
function addUpTo(n) {
 return (n + 1)*n / 2
}

const t1 = performance.now();
addUpTo(1000000000);
const t2 = performance.now();
console.log(`Time Elapsed : ${(t2 - t1) / 1000} seconds`);

>> Time Elapsed : 0.00009999999962747097 seconds
```

<aside>
💡 **The Problem with Time**

- 기기마다 다른 방식으로 시간을 기록하고 사양에 따라 시간이 달라질 수 있다.
- 기기가 같아도 다른 시간이 찍힐 수 있다.
- 빠른 알고리즘에는 너무 빠른 시간에 처리되다보니 시간을 기록하기 어렵다.

**→ 시간을 직접 측정하면 정확도가 떨어진다**

</aside>
