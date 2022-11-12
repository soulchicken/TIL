# Big-O of Arrays and Objects (배열과 오브젝트의 성능 평가)

- 빅오의 시점에서 오브젝트와 배열이 어떻게 작동하는 지 확인하기

## 목차

- [Objects](#objects)
  - [Whrn to use objects](#when-to-use-obejcts)
  - [Big O of Objects](#big-o-of-ojects)
  - [Searching이 O(N)인 이유](#searching이-on인-이유)
  - [Big O of Objects Methods](#big-o-of-object-methods)
- [오브젝트 연산 퀴즈](#오브젝트-연산-퀴즈)
- [Arrays](#arrays)
  - [Whrn to use Arrays](#when-to-use-arrays)
  - [Big O of Arrays](#big-o-of-arrays)
  - [Big O of Arrays Operations](#big-o-of-arrays-operations)
- [배열 연산 퀴즈](#배열-연산-퀴즈)

## Objects

- **unordered, key value pairs!**

```jsx
let instructor = {
  firstName: "Kelly",
  isInstructor: true,
  favoriteNumber: [1, 2, 3, 4],
};
```

### When to use obejcts

- When you don’t need order
  ⇒ 정렬이 필요하지 않을 때
- When you need fast access / insertion and removal
  ⇒ 빠르게 접근, 입력과 제거를 원할 때 좋음

### Big O of Ojects

- **Insertion** - `O(1)`
- **Removal** - `O(1)`
- **Searching** - `O(n)`
- **Access** - `O(1)`

### Searching이 O(n)인 이유

- 탐색은 key가 아니라 value를 찾기 때문이다

### Big O of Object Methods

- `Object.keys` - `O(n)`
- `Object.values` - `O(n)`
- `Object.entries` - `O(n)`
- `hasOwnProperty` - `O(1)`

```jsx
Object.keys(instructor) >> ["firstName", "isInstructor", "favoriteNumbers"];

instructor.hasOwnProperty("firstName") >> true;
instructor.hasOwnProperty("lastName") >> false;
```

## 오브젝트 연산 퀴즈

질문 1:오브젝트에 키와 값을 추가하기 위한 빅오는 무엇일까요?
**• `O(1)`** 
• `O(n)`
• `O(n log n)`

질문 2:오브젝트의 키에 접근하기 위한 빅오는 무엇일까요?
**• `O(1)`**
• `O(n)`

질문 3:오브젝트의 키를 제거하기 위한 빅오는 무엇일까요?
• `O(n)` 
**• `O(1)`** 
• `O(n^2)`

## Arrays

- **Ordered lists!**

```jsx
let name = ["Kim", "Lee", "Choi"];
let values = [true, {}, [], 2, "awesome"];
```

- 배열에 가장 중요한 점은 정렬이 되어있다는 점이다.
- 정렬되어 있는 것이 필요하다면 유용하지만, 연산을 하는 시간이 오브젝트보다 더 걸릴 수 있다.

### When To Use Arrays

- **When you need order**
  - 선형 리스트 구조로 엘리먼트마다 특정한 위치가 있고 순서대로 연결되어 있어야하는 경우

### Big O of Arrays

- **Insertion** - `It depends…`
- **Removal** - `It depends…`
  - 추가, 삭제의 경우는 어디에 입력하고 삭제하느냐에 따라 다르다.
  - 만약 마지막 인덱스에 추가, 삭제라면 `O(1)`
  - 만약 맨 앞 인덱스에 추가, 삭제라면 `O(n)`
    맨 앞에 추가, 삭제하면 그 뒤의 요소들의 인덱스 번호도 1씩 옮겨줘야한다.
- **Searching** - `O(n)`
- **Access** - `O(1)`
  - 접근은 **상수** `O(1)` 로 가능하다

### Big O of Arrays Operations

- 전부는 몰라도 됨.
- **push** - `O(1)`
- **pop** - `O(1)`
- **shift** - `O(n)` - Linked List는 `O(1)`으로 구현
- **unshift** - `O(n)` - Linked List는 `O(1)`으로 구현
- **concat** - `O(n)`
- **slice** - `O(n)`
- **splice** - `O(n)`
- **sort** - `O(n * log n)` : 정렬 알고리즘에서 다룸
- **forEach/map/filter/reduce/etc.** - `O(n)`

## 배열 연산 퀴즈

질문 1:배열 안에 데이터를 삽입하는 빅오는 무엇일까요?
• `O(1)` : 이게 정답이라 나오는 오류가 있음
**• `O(n)`** 
• `O(n^2)`

질문 2:배열 안에 데이터를 이동하는 빅오는 무엇일까요?
• `O(1)`
**• `O(n)`** 
• `O(n/2)`

질문 3:forEach 함수를 위한 빅오는 무엇일까요?
• `O(1)` 
• `O(n/2)` 
**• `O(n)`**
