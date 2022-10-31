# Big O 표기법

## 목차

- [Big O의 필요성](#big-o의-필요성)
- [코드의 시간 재기](#코드의-시간-재기)
- [연산의 갯수 세기](#연산의-갯수-세기)
- [빅오에 대한 공식 소개](#빅오에-대한-공식-소개)

## Big O의 필요성

- 그저 굴러가는 코드, 에러만 잡는 것이 아니라 더 시간이 걸리고 렉이 걸리는 코드의 이유를 알아야하기 때문이다.
- 어떤 방식으로도 문제를 해결할 수 있다. 그러나 그 중에서 어떤 것이 더 좋을 지(Best)를 고민하려면 판단 기준이 필요하다.
  → 코드를 **분류**하거나 **비교**할 수 있는 시스템이 있으면 얼마나 좋을까?
  : 코드의 성능을 얘기할 때 **정확한 용어**로 소통할 수 있어야한다.

## 코드의 시간 재기

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

💡 **The Problem with Time**

- 기기마다 다른 방식으로 시간을 기록하고 사양에 따라 시간이 달라질 수 있다.
- 기기가 같아도 다른 시간이 찍힐 수 있다.
- 빠른 알고리즘에는 너무 빠른 시간에 처리되다보니 시간을 기록하기 어렵다.

**→ 시간을 직접 측정하면 정확도가 떨어진다**

## 연산의 갯수 세기

코드가 실행될때 걸리는 정확한 시간을 초로 측정하는 것이 아니라 컴퓨터가 처리해야하는 **대략적인 연산 갯수, 추이**를 세기

**→ 컴퓨터의 연산 갯수는 사양이 달라져도 그대로**

```jsx
function addUpTo(n) {
 return n * (n + 1) / 2
}

-> 연산 횟수 : 곱하기, 더하기, 나누기 = 총 3회

function addUpTo(n) {
 let total = 0;
 for (let i = 1; i <= n; i++){
     total += i;
 }
 return total;
}

-> 연산  횟수 : 더하기+'=' 연산 n번 등등 = 5n + 2회
-> n의 값에 따라 비례하면서 증가
```

## 빅오에 대한 공식 소개

- **Big-O Notation is a way to formalize fuzzy counting** : 대략적으로 숫자를 세는 것
- **It allows us to talk formally about how the runtime of an algorithm grows as the inputs grow** : 정식으로 입력값이 늘어날수록 알고리즘의 실행시간이 어떻게 변하는 지 설명하는 공식
- **We won’t care about the details, only the trends** : 전반적인 추세에만 주목

### 빅오의 정의 (쉬운 버전)

- f(n) could be linear (f(n) = n)
- f(n) could be quadratic (f(n) = n\*\*2)
- f(n) could be constant (f(n) = 1)
- f(n) could be sometihing entirely different!

```jsx
function addUpTo(n) {
 return n * (n + 1) / 2
}

-> Big-O : O(1)

function addUpTo(n) {
 let total = 0;
 for (let i = 1; i <= n; i++){
     total += i;
 }
 return total;
}

-> Big-O : O(n)

function addUpTo(n) {
 let total = 0;
 for (let i = 1; i <= n; i++){
	for (let j = 1; j <= i; j++){
     total += 1;
	}
 }
 return total;
}

-> Big-O : O(n**2)

```