# Big-O of Arrays and Objects (배열과 오브젝트의 성능 평가)

- 빅오의 시점에서 오브젝트와 배열이 어떻게 작동하는 지 확인하기

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

### Big O of Obejct Methods

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
