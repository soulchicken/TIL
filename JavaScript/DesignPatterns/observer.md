# Observer Pattern

- 행동 / 이벤트 카테고리에 들어간다.
- 한 주제 객체의 상태가 바뀌면 다른 구독 객체들에게 **상태와 변경**을 알림

## Polling

- event 카테고리에 들어간다.
- 객체의 상태가 변경되었는지 옵저버들이 감지하는 방식.
  1. (폴링) 만화방에 1시간에 1번씩 A,B,C 라는 사람이 같은 내용으로 ‘신간 나왔나요?’라고 연락을 한다면?

     위에서 시간과 자원낭비를 엄청 하고 있다.

  2. (옵저버) A, B, C가 책방에 ‘신간 나왔나요?’라고 묻게 하지 않고, 성함을 남겨주시고 알림 구독 해주시면 신간 나오면 연락 주겠다고 해버리기.
- 옵저버가 폴링보다 **위의 상황**에서는 효율적이다.

## Observer Pattern

관찰 대상의 “주제객체”, 관찰을 하는 “구독객체”라고 칭한다.

구독객체는 자유롭게 주제객체를 등록 / 비등록 할 수 있다.

한 주제 객체의 상태가 바뀌면 다른 구독 객체들에게 상태와 변경을 알림

### subscribe class

```tsx
class Subject {
  constructor() {
    this.observers = [];
  }
  getObserversList() {
    return this.observers;
  }

  subscribe(observer) {
    this.observers.push(observer);
  }
  unSubscribe(observer) {
    this.observers = this.observers.filter((obs) => obs !== observer);
  }

  notifyAll() {
    this.observers.forEach((subscriber) => {
      try {
        subscriber.update(this.constructor.name);
      } catch (error) {
        console.error("error", error);
      }
    });
  }
}
```

### observer class

```tsx
class Observer {
  constructor(name) {
    this.name = name;
  }

  update(subj) {
    console.log(`${this.name}: notified from ${subj} class!`);
  }
}
```

### 실행 코드

```tsx
const subj = new Subject();

const a = new Observer("A");
const b = new Observer("B");
const c = new Observer("C");

subj.subscribe(a);
subj.subscribe(b);
subj.subscribe(c);

console.log(subj.getObserversList());

subj.notifyAll();

subj.unSubscribe(c);
console.log();
subj.notifyAll();
```
