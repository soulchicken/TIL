# 문제 해결 접근법

- **Algorithms and Problem Solving Patterns**

- [What is an algorithm](#what-is-an-algorithm)
- [Why do I need to know this?](#why-do-i-need-to-know-this)
- [How do you improve?](#how-do-you-improve)
- [Problem Solving](#problem-solving)
  - [1. Understand the Problem](#1-understand-the-problem)
  - [2. Explore Concrete Examples](#2-explore-concrete-examples)
  - [3. Break It Down](#3-break-it-down)
  - [4. Solve / Simplify](#4-solve--simplify)
  - [5. Look Back & Refactor](#5-look-back--refactor)

## What is an algorithm?

- A **process** or **set of steps** to accomplish a certain task.
  - 특정 작업을 달성하기 위한 과정이나 일련의 단계

## Why do I need to know this?

- Alomost **everything** that you do in programming involves smoe kind of **algorithm**
- **It’s the foundation for being a successful problem solving and developer**
- 프로그래밍에서 수행하는 거의 모든 작업에는 아주 기본적인 작업이든 복잡하든 일종의 알고리즘이 포함되고, 문제를 해결할 방법을 결정해야한다.
- Also… **INTERVIEWS (면접에서 쓰니까…)**

## How do you improve?

1. **Devise** a plan for solving problems : 문제 해결을 위한 계획 수립
2. **Master** common problem soling patterns : 일반적인 문제 해결 패턴을 파악하기

## PROBLEM SOLVING

1. **Understand the Problem** 문제를 이해하기
2. **Explore Concrete Examples** 구체적인 예시를 알아보기
3. **Break It Down** : 문제를 세분화하기
4. **Solve / Simplify** : 문제를 해결, 단순화하기
5. **Look Back and Refactor** : 문제를 복습하고 재구성하기

## 1. Understand the Problem

### **문제 해결의 이해**

- 면접자리에서 문제를 받으면 심장이 뛰고 무서워서 바로 시작하려고 한다. 프로그래밍이 매우 그렇다.
- 코드 몇 줄 빨리 직으면 진행한다는 느낌은 받을 수 있지만 유효한 도움을 받지는 못한다.
- **한 걸음 물러서서 확실히 문제를 이해하는 것이 필요하다.**

### 질문 사항

1. **Can I restate the problem in my own words?**
   - 문제를 내 방식대로 다시 생각할 수 있는가?
2. **What are the inputs that go into the problem?**
   - 문제가 어떤 입력값을 담고 있는가를 이해하는 것이 중요하다.
3. **What are the outputs that should come from the solution to the problem?**
   - 문제에 대한 해결책으로 결과는 어떻게 나와야하는지를 알아야한다.
4. **Can the outputs be determined from the inputs? In other words, do I have enough information to solve the problem? (You may not be able to answer this question until you set about solving the problem. That’s okay; it’s still worth considering the question at this early stage.)**
   - 결과를 정의할 수 있는가. 문제를 해결할 충분항 정보가 주어졌는가. 입력만 정해지면 해당 정보만 사용해서 예상되는 출력값이 반환될까?
5. **How should I label the important pieces of data that are a part of the problem?**
   - 문제의 일부인 데이터의 중요한 부분에 어떻게 라벨을 지정할 수 있을까? 문제에서 정말 중요한 라벨이 무엇인지. 어떤 용어를 사용해야 좋을 지?

### Practice

**Write a function which takes two numbers and returns their sum. 두 숫자를 가지고 합계를 반환하는 함수 작성하기.**

```jsx
1. Can I restate the problem in my own words?
  => "implement addition", 덧셈 구현하기
2. What are the inputs that go into the problem?
  => int? floats? what about string for large numbers?
  => 입력값의 범위는 어떻게 될까?
3. What are the outputs that should come from the solution to the problem?
  => 결과의 숫자는 어떻게 출력해줄까? 정수? 소수? 문자열?
4. Can the outputs be determined from the inputs? In other words, do I have enough information to solve the problem? (You may not be able to answer this question until you set about solving the problem. That’s okay; it’s still worth considering the question at this early stage.)
  => 위같은 정보를 얻을 수 있는가?
5. How should I label the important pieces of data that are a part of the problem?
  => 무엇이 중요한가? 두 인수가 주어져야하고 반환할 sum을 만들어줘야한다.
```

## 2. **Explore Concrete Examples**

**구체적인 예시를 알아보기**

- **Coming up with eamples can help you understand the problem better** → 예시를 떠올리는 것이 문제 이해에 도움을 준다.
- **Example also provide sanity checks that your eventual solution works how it should** → 제대로 작동하는지 검사를 할 수 있다.
- 예 ) User Stories, Unit Tests.

### 예시 알아보기 과정

1. **Start with Simple Examples** : 간단한 예시 만들기로 시작
2. **Progress to More Complex Examples** : 더 나아가 조금 더 복잡한 예시를 만들어보기
3. **Explore Examples with Empty Inputs** : 입력값이 없다면?
4. **Explore Examples with Invalid Inputs** : 입력값이 유효하지 않다면?

### Practice

**Write a function which takes in a string and returns counts of each character in the string. 문자열을 받으면 각 문자의 수를 반환하는 함수**

```jsx
// 예시 만들기
charCount("aaaa"); // {a:4}
charCount("hello"); // {h:1, e:1, l:2, o:1}
// 그런데 b : 0 같은게 포함되어야 할까? 의문이 들 수 있다.

charCount("my phone number is 123123");
// 공백도 넣어야할까?
charCount("Hello hi!");
// 대문자, 소문자를 나눠야할까? 특수문자는 넣을까?

// 입력값이 없다면?
charCount();
charCount("");

// 유효하지 않은 값이 들어간다면?
charCount(null);
charCount(123);
```

## 3. **Break It Down**

**문제를 세분화하기, 세부 분석**

- 문제 해결 단계를 작성하기. 완전히 예쁘게 적는 것이 아니라 소통하기 위해 단계를 명확하게 말할 정도로.
- **Explicitly write out the steps you need to take** → 디테일하게 적을 필요도, 모든 라인마다 작성할 필요도 없이 해결책에 필요한 기본적인 구성요소들로 작성

### Practice

**Write a function which takes in a string and returns counts of each character in the string.**

- **문제 코드**

```jsx
function charCount(str) {
  // do something
  // return an object.
  // (소문자 영문인 키를 지닌 객체 반환)
  // 대문자는 소문자로 바꾼다
}
```

- **나눠서 생각하기**

```jsx
function charCount(str) {
  // make object to return at  end
  // loop over string, for each character
  // char is a number/letter
  // if the char is a key in object, add one to count
  // if the char is not in object, add it and set value to 1
  // if character is something else (space, period, etc.) don't do anything
  // return object at end
}
```

## 4. **Solve / Simplify**

**문제를 해결, 단순화하기**

```bash
문제를 해결하기. 해결할 수 없다면 더 단순한 문제를 해결하기 (시간이 맣이 소요되는 부분은 무시하기)
가로막는 어려운 부분이 있더라도 해당 부분을 다시 통합한다는 것을 염두해놓고 가능한 작업부터 한다
문제를 단순화하는 과정에서 실제 해결책을 이해하게 되고 문제의 어려운 부분에 대한 실마리를 잡을지도 모른다
```

### Simplify 단순화 작업

- **Find the core difficulty in what you’re trying to do** → 혼란에 빠뜨리는 가장 어려운 부분을 찾게 된다
- **Temporarily ignore that difficulty** → 일단 어려운 부분은 무시한다
- **Write a simplified solution** → 단순화해서 해결한다
- **Then incorporate that difficulty back in** → 단순한 부분으로 이해한 내용으로 어려운 부분으로 돌아가서 해결한다

### Practice

**Write a function which takes in a string and returns counts of each character in the string.**

```jsx
function charCount(str) {
  // loop가 익숙하지 않다면 첫 글자, 두번째 글자만으로 적용해보기
  // object에 익숙하지 않다면 일단 loop 먼저 하기
  // 대문자 소문자 다루는데에 익숙하지 않다면, 기억이 나지 않는다면 무시하고 나중에 처리한다

  const result = {};
  for (let i = 0; i < str.length; i++) {
    // 가장 먼저 잘 문자열을 반복문으로 뽑아낼 수 있는 지 확인
    console.log(str[i]);

    // 대문자의 경우를 제거한다
    const char = str[i].toLowerCase();

    // result에 해당 문자가 존재하는 지 확인하고 추가하고 1추가
    if (result[char] > 0) {
      result[char]++;
    } else {
      result[char] = 1;
    }

    // 이제 영문이나 숫자인지만 확인할 수 있다면 이 문제는 해결
  }
  return result;
}
```

## 5. **Look Back & Refactor**

**되돌아보기와 리팩터(Refactor)**

- Congrats on solving it, but you’re not done! 작동이 잘 되는 해결책을 얻었지만 끝이 아니다.
- 각 구성요소를 한 줄씩 살펴 보면서 마음에 들지 않는 부분이나, 코드의 형태, 해석 방법, 또 이해하기 얼마나 쉬운지에 대해 고민해야한다.

### Refactoring Questions

- **Can you check the result?** → 결과 확인 했나요?
- **Can you derive the result differently?** → 결과를 다른 방식으로 도출할 수 있나요?
- **Can you understand it at a glance?** → 한 눈에 보고 이해할 수 있나요? 직관적인가요?
- **Can you use the result or method for some other problem?** → 결과나 방법을 다른 문제에도 적용할 수 있을까요?
- **Can you improve the performance of your solution?** → 성능개선을 할 수 있나요? 주로 시간복잡도, 공간복잡도.
- **Can you think of other ways to refactor?** → 다른 방법으로 리팩토링할 수 있나요? 코딩 컨벤션이라거나 여러 방법의 수정을 할 수 있을까?
- **How have other people solved this problem?** → 어떻게 다른 사람들은 해결했나요?

### Practice

- **지금까지 한 문제를 리팩토링하기**
  - 여기에서는 정규표현식을 사용했지만 아스키코드값으로 만들 수도 있을 것 같다. (속도는 더 빠를 것이다.)

```jsx
// 정규표현식으로 일단 이런 답을 만들어냈다.
function charCount(str) {
  const obj = {};
  for (let i = 0; i < str.length; i++) {
    const char = str[i].toLowerCase();
    if (/[a-z0-9]/.test(char)) {
      if (obj[char] > 0) {
        obj[char]++;
      } else {
        obj[char] = 1;
      }
    }
  }
  return obj;
}

// 리팩토링하기
function charCount(str) {
  const obj = {};

  // for문 바꾸기
  for (let char of str) {
    char = char.toLowerCase();
    if (/[a-z0-9]/.test(char)) {
      // if else 문을 1줄로 요약하기
      obj[char] = ++obj[char] || 1;
    }
  }
  return obj;
}

// 만약 아스키코드로 문제를 해결한다면?

function charCount(str) {
  const obj = {};

  // for문 바꾸기
  for (let char of str) {
    char = char.toLowerCase();
    if (isAlphaNumeric(char)) {
      // if else 문을 1줄로 요약하기
      obj[char] = ++obj[char] || 1;
    }
  }
  return obj;
}

function isAlphaNumeric(char) {
  let code = char.charCodeAt(0);
  if (
    !(code > 47 && code < 58) && // numeric
    !(code > 64 && code < 91) && // upper
    !(code > 96 && code < 123)
  ) {
    // lower
    return false;
  }
  return true;
}
```
