# DataStructure INTRO

- [Intro](#intro)
  - [What Do they Do?](#what-do-they-do)
  - [Why So Many????](#why-so-many)
- [Class](#class)
  - [How we'll be using classes](#how-well-be-using-classes)
  - [One gotcha with 'this'](#one-gotcha-with-this)
  - [문법](#문법)
- [instance 메소드 추가하기](#instance-메소드-추가하기)
- [class 메소드 추가하기](#class-메소드-추가하기)
  - [static 키워드](#static-키워드)
  - [응용하기](#응용하기)

## Intro

### What Do They Do?

- Data structures are collections of values, the relationships among them, and the functions or operations that can be applied to the data

### Why So Many?????

- Different data structures excel at different things. Some are highly specialized, while others (like arrays) are more generally used.
- **자료구조는 왤캐 종류가 많음?**
  ⇒ 특정 유형의 문제에 있어서 특정한 자료구조가 효율적이라서 그렇다.

## Class

- A blueprint for creating objects with pre-defined properties and methods.
  사전에 정의된 속성, 메소드들을 이용해 객체를 생성하기 위한 청사진
- 사실 JS는 프로토타입 기반. 진정한 기술적인 객체지향이 아니라 눈속임을 함.

### How we’ll be using classes

```jsx
class DataStructure(){
	constructure(){
		// what default properties should it have?
	}
	someInstanceMethod(){
		// what should each object created from this class be able to do?
	}

	// We will alomost never be using static methods in data structure
}
```

### One gotcha with ‘this’

- Inside all of our **instance** methods and **constructor**, the keyword ‘this’ refers to the object created from that class (also known as an **instance**)
- this 키워드는 생성자에서 사용하면 해당 인스턴스에 써먹게 된다.

### 문법

**class**

- 대문자로 시작하는 Camel case.

```jsx
class Student {
  constructor(firstName, lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
```

- `contructor`(생성자)는 새로운 인스턴스를 생성할 때 사용되는 특별한 메소드

**new**

```jsx
let firstStudent = new Student("Colt", "Steele");
let secondStudent = new Student("Blue", "Steele");
```

- `new` 키워드로 인스턴스를 생성한다.
- 만약 `constructor`가 요구하는 인자의 수를 맞추지 않는다면?

  ```jsx
  let firstStudent = new Student("Colt", "Steele");
  let secondStudent = new Student("Colt");
  let thirdStudent = new Student("Colt", "Steele", 3);

  >> firstStudent -> Student {firstName: 'Colt', lastName: 'Steele'}
  >> secondStudent -> Student {firstName: 'Colt', lastName: undefined}
  >> thirdStudent -> Student {firstName: 'Colt', lastName: 'Steele'}
  ```

  - 인자의 수보다 적다면 `undefined`, 많다면 사용하지 않음.

## instance 메소드 추가하기

```jsx
data = [1, 2, 3]
data.push(99) // return 값은 4번째 요소라는 의미로 4가 출력
>> data = [1, 2, 3, 99]
```

- Array에서 push 메소드는 사용하면 요소가 추가된다.

```jsx
class Student {
	constructor(firstName, lastName) {
		this.firstName = firstName
		this.lastName = lastName
	}
	**fullName(){
		return `Your full name is ${this.firstName} ${this.lastName}`;
	}**
}

let firstStudent = new Student("Colt", "Steele");
let secondStudent = new Student("Blue", "Steele");

firstStudent.fullName();
>> 'Your full name is Colt Steele'
secondStudent.fullName();
>> 'Your full name is Blue Steele'
```

- `Student` 클래스 내에 `fullName`이라는 메소드를 추가하고 인스턴스별로 사용한 결과이다.
  `firstStudent`와 `secondStudent`의 결과는 다르게 나온다.

## class 메소드 추가하기

### static 키워드

- static은 클래스에 종속된다. 개별 인스턴스 메소드에는 반드시 종속적일 필요가 없는 메소드를 생성하는데에 도와준다.
- static 메소드는 클래스의 인스턴스화 없이도 호출될 수 있고 인스턴스를 통해서는 호출될 수 없다.

```jsx
class Student {
	constructor(firstName, lastName) {
		this.firstName = firstName
		this.lastName = lastName
	}
	static hi(){
		return ` hi!`;
	}
}

Student.hi();
>> ' hi!'

let firstStudent = new Student("Colt", "Steele");

firstStudent.hi();
>> VM1679:1 Uncaught TypeError: firstStudent.hi is not a function
    at <anonymous>:1:14
```

### 응용하기

```jsx
class Point {
	constructor(x, y) {
		this.x = x;
		this.y = y;
	}

	static distance(a, b) {
		const dx = a.x - b.x;
		const dy = a.y - b.y;

		return Math.hypot(dx, dy);
	}
}

const p1 = new Point(5, 5);
const p2 = new Point(10, 10);

Point.distance(p1, p2);

>> 7.0710678118654755
```

- 두 점 사이의 거리를 구하는 메소드를 `static`으로 정의하고 두 인스턴스를 인자로 넣는다.
