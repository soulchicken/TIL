# 단일 연결 리스트 (Singly Linked List)

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
