# 단일 연결 리스트 (Singly Linked List)

목차

- [What is a Linked List](#what-is-a-linked-list)
- [Node Class 구현](#node-class-구현하기)
- [Singly Linked List 구현](#singlylinkedlist-class-구현)
  - [Push 메소드 구현](#push-메소드-구현)
  - [Pop 메소드 구현](#pop-메소드-구현)
  - [Shift 메소드 구현](#shift-메소드-구현)
  - [Unshift 메소드 구현](#unshift-메소드-구현)
  - [Get 메소드 구현](#get-메소드-구현)
  - [Set 메소드 구현](#set-메소드-구현)
  - [Insert 메소드 구현](#insert-메소드-구현)
  - [Remove 메소드 구현](#remove-메소드-구현)

## What is a linked list?

**단지 연결된 노드들의 집합**

- A data structure that contains a **head**, **tail** and **length** property.
- Linked Lists consist of nodes, and each **node** has a **value** and a **pointer** to another node or null.
- Array의 경우 인덱스 번호에 따라 값에 접근하게 되는데, 연결 리스트는 데이터 엘리먼트를 가리키는 인덱스 없이 그냥 다수의 데이터 엘리먼트로 구성되어 있다. 이러한 객체들이 연속으로 연결되어 있는 일종의 기차 모양. (인덱스가 필요없다!)
  - 각각의 엘리먼트를 노드(node)라고 한다. 노드는 하나의 데이터 엘리먼트를 저장한다.
  - 노드는 다음 노드를 가리키는 정보 역시 저장해야하고 다음 노드가 없다면 **null**을 저장하게 한다.
  - 연결 리스트는 다수의 노드들로 구성되어 있다.
  - head, tail은 연결 리스트의 시작, 마지막 노드를 의미한다. 중간의 노드는 하나하나 추적하지 않는다.

### vs Arrays

**Lists**

- Do not have indexes
- Connected via nodes with a **next** pointer
  따라서 중간의 인덱스를 제거하기 편하다
- Random access is not allowd
  갑자기 10번째 인덱스로 접근하고 싶다고 해도 접근하지 못한다는 의미

**Arrays**

- Indexed in order
- Insertion and deletion can be expensive
- Can quickly be accessd at a specific index

## Node class 구현하기

- piece of data → `val`
- reference to next node → `next`

```jsx
class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
  }
}
```

### Node 구현 확인하기

```jsx
var first = new Node("Hi");
first.next = new Node("there~!");
first.next.next = new Node("How");
first.next.next.next = new Node("Are");

first
>> Node {val: 'Hi', next: Node}
first.next
>> Node {val: 'there~!', next: Node}
first.next.next
>> Node {val: 'How', next: Node}
first.next.next.next
>> Node {val: 'Are', next: null}
```

## SinglyLinkedList class 구현

```jsx
// 뼈대
class SinglyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }
}
```

### push 메소드 구현

- **This function should accept a value**
- **Create a new node using the value passed to the function**
  ⇒ 주어진 값 받아들인 후 그것을 이용해 새로운 노드를 생성한다.
- **If there is no head property on the list, set the head and tail to be the newly created node**
  ⇒ 노드가 없다면 헤드와 테일을 새로 들어온 값으로 만든 노드로 채운다.
- **Otherwise set the next property on the tail to be the new node and set the tail property on the list to be the newly created node**
  ⇒ 리스트가 비어있지 않다면, 마지막 노드의 next를 새롭게 만들고 테일로 가리킨다.
- **Increment the length by one**
  ⇒ length 값 1 증가
- **Return the linked list**
  ⇒ 링크드 리스트 반환

```jsx
push(val) {
		// 길이 증가
    this.length++;

		// val 값으로 노드 생성
    const newNode = new Node(val);

    if (!this.head) {
			// 헤드가 없다면
      this.head = newNode;
    } else {
			// 헤드가 있다면
      this.tail.next = newNode;
    }
		// 테일은 새로운 노드로
    this.tail = newNode;

		// 링크드 리스트 반환
    return this;
  }
```

### pop 메소드 구현

- **Removing a node from the end of the Linked List**
  → 리스트의 마지막 요소를 제거하는 것
- 그런데 **Singly Linked List**는 테일 노드가 그 앞의 노드를 알 수 없다. 따라서 헤드에서부터 찾아가면서 테일의 앞 노드를 찾는 과정이 필요하다.
- **일단 모든 노드를 출력해보기**
  ```jsx
  traverse() {
      let current = this.head;
      while (current) {
        console.log(current.val);
        current = current.next;
      }
    }
  ```
- **If there are no nodes in the list, return undefined**
  ⇒ node가 없다면 undefined를 반환
- **Loop through the list until you reach the tail**
  ⇒ 테일에 닿을 때까지 반복문을 돌린다
- **Set the next property of the 2nd to last node to be null**
  ⇒ 테일에 이를 때까지 계속 따라가는 동시에 이전 노드가 어떤 것이었는지를 항상 추적하고 있어야한다
- **Set the tail to be the 2nd to last node**
  ⇒ 제거를 마치면 테일 앞에 있던 노드를 테일로 만든다
- **Decrement the length of the list by 1**
  ⇒ length 값이 1 감소
- **Return the value of the node removed**
  ⇒ 지우는 노드를 반환

```jsx
pop() {
    // 일단 노드가 있는 지 확인한다
    if (!this.length) {
      return undefined;
    }
    // 현재 노드와 새롭게 지정할 테일 후보
    let current = this.head;
    let newTail = current;
    while (current.next) {
      newTail = current;
      current = current.next;
    }

    // 새롭게 테일을 지정해주고 pop할 기존 테일을 끊어준다
    this.tail = newTail;
    this.tail.next = null;
    this.length--;

    // 만약 더 이상 노드가 없다면 헤드와 테일 초기화
    if (!this.length) {
      this.head = null;
      this.tail = null;
    }
    return current;
  }
```

### Shift 메소드 구현

- Removing a new **node** from the beginning of the Linked List!
  ⇒ 헤드를 제거하고 헤드를 두 번째 노드로 옮긴다.

**Psuedocode**

- **If there are no nodes, return undefined**
  ⇒ 노드가 없다면 undefined 반환
- **Store the current head property in a variable**
  ⇒ 노드가 있다면 현재의 헤드 속성을 변수에 저장
- **Set the head property to be the current head’s next property**
  ⇒ 변수의 next 값을 헤드로 지정
- **Decrement the length by 1**
- **Return the value of the node removed**

```jsx
shift() {
    // 헤드가 없다면 undefined 반환
    if (!this.head) return undefined;

    // current head
    const currentHead = this.head;

    // new head
    this.head = currentHead.next;

    this.length--;

		// 만약 더 이상 노드가 없다면 테일 초기화
    if (!this.length) {
      this.tail = null;
    }
    return currentHead;
  }
```

### Unshift 메소드 구현

- Adding a new **node** to the beginning of the Linked List!
  ⇒ 헤드쪽에 새로운 노드를 추가하기

**Psuedocode**

- **This function should accept a value**
  ⇒ value를 인자로 받는 함수여야 한다.
- **Create a new node using the value passed to the function**
  ⇒ push 메소드처럼 새로운 노드를 생성해야한다.
- **If there is no head property on the list, set the head and tail to be the newly created node**
  ⇒ 헤드가 없는 경우 헤드와 테일 모두 새로운 노드를 가리키도록 한다.
- **Otherwise set the newly created node’s next property to be the current head property on the list**
  ⇒ 노드가 이미 있는 경우, 새롭게 생성된 노드의 next에 현재 헤드 값을 설정한다.
- **Set the head property on the list to be that newly created node**
  ⇒ 헤드는 새로운 노드를 가리키도록 한다.
- **Increment the length of the list by 1**
- **Return the linked list**

```jsx
unshift(val) {
    // val값으로 새로운 노드 생성
    const newNode = new Node(val);

    // 만약 노드가 없다면
    if (!this.length) {
      // 테일에 새로운 노드가 들어간다.
      this.tail = newNode;
    } else {
      // 노드가 있다면 지금의 헤드는 새로운 노드의 next
      newNode.next = this.head;
    }
    // 새로운 노드가 이제부터 헤드
    this.head = newNode;

    this.length++;
    return this;
  }
```

### Get 메소드 구현

- Retrieving a **node** by it’s position in the Linked List!
  - 주어진 위치의 값을 반환하는 메소드

**Psuedocode**

- **This function should accept an index**
  - 인덱스를 인자로 받는 함수
- **If the index is less than zero or greater than or equal to the length of the list, return null**
  - 인덱스 번호가 링크드 리스트를 넘어서면 null 반환
- **Loop through the list until you reach the index and return the node at thatspecific index**
  - 루프를 돌면서 인덱스가 지정한 위치에 이를 떄까지 노드를 옮겨간다.

```jsx
get(index) {
	// 예외처리
  if (!this.length || this.length <= index) return null;

  let current = this.head;
  let counter = 0;

  while (index != counter) {
    current = current.next;
    counter++;
  }
  return current;
}
```

### Set 메소드 구현

- Changing the **value** of a ode based on it’s position in the Linked List
  - 해당 인덱스에 위치한 노드의 값을 업데이트한다.

**Psuedocode**

- **This function should accept a value and an index**
  - 업데이트할 노드의 인덱스 번호와 값을 인자로 받아야한다.
- **Use your get() function to find the spectific node.**
  - 기존에 작성한 get() 함수를 활용한다.
- **If the node is not found, return false**
  - 해당 노드가 없다면 false 반환
- **If the node is found, set the value of that node to be the value passed to the function and return true**
  - 해당 노드가 있다면 값을 바꿔주고 true 반환

```jsx
set(index, val) {
	// get 함수로 찾기
  const foundNode = this.get(index);
	// 해당 노드가 존재하지 않는다면 false
  if (foundNode) return false;

	// 해당노드가 있다면 값을 바꿔주고 true
  foundNode.value = val;
  return true;
}
```

### Insert 메소드 구현

- Adding a node to the Linked List at a **specific** position
  ⇒ 주어진 위치에 노드를 **추가**하는 메소드

**Insert pseudocode**

- **If the index is less than zero or greater than the length, return false**
  ⇒ 범위를 벗어날 경우 삽입하지 않고 false 반환
- **If the index is the same as the length, push a new node to the end of the list**
  ⇒ 인덱스와 길이가 같을 경우 리스트의 맨 마지막에 삽입하는 push 매소드 사용
- **If the index is 0, unshift a new node to the start of the list**
  ⇒ 0번 인덱스에 추가하는 경우 unshift 매소드 사용
- **Otherwise, using the get method, access the node at the index -1**
  ⇒ get 메소드를 사용해 해당 위치 쪽에 있는 노드를 일단 가져온다. (이때 인덱스 값은 index - 1)
- **Set the next property on that node to be the new node**
  ⇒ 이전 노드의 next가 새롭게 생성된 후 삽입되는 노드를 가리키도록 설정
- **Set the next property on the new node to be the previous next**
  ⇒ 새 노드를 이전의 next 노드로 연결
- **Increment the length**
- **Return true**

```jsx
insert(index, val) {
  // 예외상황 처리
  if (index > this.length || index < 0) {
    return false;
  }

  // 만약 0번 인덱스에 insert
  // ! 를 붙이면 불린값의 반대가 나오는데, !!는 반대의 반대가 됨 ㄸ
  if (index === 0) return !!this.unshift(val);
  // 만약 마지막 인덱스에 insert
  if (index === this.length) return !!this.push(val);

  // prev Node 가져오기
  const prev = this.get(index - 1);
  const newNode = new Node(val);

  // newNode에 다음 노드 연결
  newNode.next = prev.next;

  // prev의 next로 newNode 지정
  prev.next = newNode;

  this.length++;
  return true;
}
```

### Remove 메소드 구현

- Removing a node from the Linked List at a **pecific** position
  ⇒ 해당 인덱스의 노드를 지운다.

**Remove pseudocode**

- **If the index isless than zero or greater than the length, return undefined**
  ⇒ 만약 해당 인덱스가 범위를 벗어나면 undefined 반환
- **If the index is the same as the length-1, pop**
  ⇒ 마지막 인덱스를 제거한다면 pop 호출
- **If the index is 0, shift**
  ⇒ 0번 인덱스를 제거한다면 shift 호출
- **Otherwise, using the get method, access the node at the index - 1**
  ⇒ get 메소드로 index - 1 번 노드를 가져온다
- **Set the next property on that node to be the next of the next node**
  ⇒ .next를 .next.next로 설정
- **Decrement the length**
- **Return the value of the node removed**

```jsx
remove(index) {
  // 예외상황 처리
  if (index < 0 || index >= this.length) return undefined;

  // 0번 인덱스 제거
  if (index === 0) return this.shift();

  // 마지막 인덱스 제거
  if (index === this.length - 1) return this.pop();

  // 이전 노드를 찾는다
  const prev = this.get(index - 1);

  // 반환할 노드
  const tmp = prev.next;

  // 이전노드의 next를 다음다음 next로 바꾼다
  prev.next = tmp.next;

  this.length--;
  return tmp;
}
```
