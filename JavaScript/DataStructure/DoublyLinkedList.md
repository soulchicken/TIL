**Almost** identical to Singly Linked Lists, except every node has **another** pointer, to the **previous** node!

- 사실 Singly와 똑같은데, 포인터가 늘어난 것 뿐이다.
- 사용하는 메모리가 늘어나고 융통성이 더 생기는 버전이라고 생각하면 된다.

## Node 셋업

- `Node`, `DoublyLinkedList` 클래스를 만들기

```jsx
class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
    this.prev = null;
  }
}

class DoublyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }
}
```

## Push 메소드

Adding a node to the **end** of the Doubly Linked List

### Pushing pseudocode

- Create a new node with the value passed to the function
- If the head property is null set the head and tail to be the newly created node
- If not, set the next property on the tail to be that node
- Set the previous property on the newly created node to be the tail
- set the tail to be the newly created node
- Increment the length
- Return the Doubly Linked List

### Push 구현

```jsx
push(val) {
  const newNode = new Node(val);
  if (!this.head) {
    this.head = newNode;
  } else {
    this.tail.next = newNode;
    newNode.prev = this.tail;
  }

  this.length++;
  this.tail = newNode;
  return this;
}
```

## Pop 메소드

Removing a node from the **end** of the Doubly Linked List

### Poping pseudocode

- If there is no head, return undefined
- Store the current tail in a variable to return later
- If the length is 1, set the head and tail to be null
- Upadte the tail to be the previous Node
- Set the newTail’s next to null
- Decrement the length
- Return the value removed

### Pop 구현

```jsx
pop() {
  if (!this.head) {
    return undefined;
  }
  const tail = this.tail;

  if (this.length === 1) {
    this.head = null;
    this.tail = null;
  } else {
    this.tail = tail.prev;
    this.tail.next = null;
  }
  this.length--;
  return tail;
}
```

## Shift 메소드

Removing a node from the **beginning** of the Doubly Linked List

- 사실상 `pop()`과 다를 바 없다.

### Shifting pseudocode

- If length is 0, return undefined
- Store the current head property in a variable
- If the length is one
  - set the head to be null
  - set the tail to be null
- Update the head to be the next of the old head
- Set the head’s prev property to null
- Set the old head’s next to null
- Decrement the length
- Return old head

### Shift 구현

```jsx
shift() {
  if (!this.head) {
    return undefined;
  }
  const head = this.head;

  if (this.length === 1) {
    this.head = null;
    this.tail = null;
  } else {
    this.head = head.next;
    this.head.prev = null;
  }

  this.length--;
  return head;
}
```

## Unshift 메소드

### Unshifting pseudocode

- Create a new node with the value passed to the function
- If the length is 0
  - Set the head to be the new node
  - Set the tail to be the new node
- Otherwise
  - Set the prev property on the head of the list to be the new node
  - Set the next property on the new node to be the head property
  - Update the head to be the new node
- Increment the length
- Return the list

### Unshift 구현

```jsx
unshift(val) {
  const newNode = new Node(val);
  if (!this.length) {
    this.tail = newNode;
  } else {
    const prevHead = this.head;
    newNode.next = prevHead;
    prevHead.prev = newNode;
  }
  this.head = newNode;

  this.length++;

  return this;
}
``
```
