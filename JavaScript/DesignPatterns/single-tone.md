# Singleton Pattern

- 주로 시스템 로깅이나 애플리케이션 설정같은 리소스를 관리할 때 사용한다.
  한 인스턴스에서 관리하고 애플리케이션 전역에서 접근하도록 도와주는 패턴.
  - class 생성시 constructor는 private이라서 new 키워드를 사용할 수 없게 해야한다.
  - constructor를 직접적으로 접근할 수 없기 때문에 static 매소드를 사용해서 접근해야한다.

## singleton class

```tsx
class Singleton {
  constructor() {
    if (Singleton.instance) {
      return console.warn('Warning : Singleton class alraady instantiated');
    }
    Singleton.instance = this;
    this.version = Date.now();
    this.config = 'test';
  }

  static getInstance() {
    if (!this.instance) {
      this.instance = new Singleton();
    }
    return this.instance;
  }
}
```

## 예제 1 : 하나의 객체 인스턴스만 존재

```tsx
const s1 = new Singleton();
console.log(s1);
const s2 = new Singleton();
console.log(s2);
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/159fcbbd-96fd-4b7e-8770-66a8303af168/Untitled.png)

## 예제 2 : 스테틱 함수로 객체 접근

```tsx
const s1 = Singleton.getInstance();
console.log(s1);
const s2 = Singleton.getInstance();
console.log(s2);
console.log(s1 === s2);
```
