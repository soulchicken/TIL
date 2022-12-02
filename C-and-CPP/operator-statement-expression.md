# 연산자, 표현식, 문장

## 반복 루프와의 첫만남

**LOOP**

- 사람이 지루해하는 반복 작업을 해보기

### 1부터 100까지 출력하기

- 하나하나 다 작성한다면

```c
#include <stdio.h>

int main()
{
	printf("1\n");
	printf("2\n");
	printf("3\n");
...
	printf("99\n");
	printf("100\n");
}
```

- 반복문이 있기 전에 사용한 `goto`문
  - `goto`문은 이제 쓰지 않는 단종되가는 코드.

```c
#include <stdio.h>

int main()
{
	int n = 1;

label:
	printf("%d\n", n);
	n = n + 1;

	if (n == 101) goto out;

	goto label;

out:

	return 0;
}
```

- `while` 문
  - 초기값, 탈출조건, 초기값의 변화

```c
#include <stdio.h>

int main()
{
	int n = 1;

	while (n < 101)
	{
		printf("%d\n", n);
		n = n + 1;
	}

	return 0;
}
```

## 대입 연산자와 몇 가지 용어

- Object, L-value, R-value, 피연산자

### 기본 연산자

- =, +, -, \*, /

### 연산자(operator)와 피연산자(operand)

```c
int i;
i = 1024;
i = i + 1;
```

1. = 은 같다가 아니라 i에 1024를 넣는다!
2. i + 1로 1025를 만들고 i에 1025를 넣는다!

### Data object, L-value, R-value

- Data Object : object
  - 데이터가 메모리 안에 존재하고 있을 때, 그 것이 오브젝트
- L-value : object locator value
  - 등호 기준 왼쪽, 대입을 받는, 메모리 주소가 존재하는.
  - 메모리를 차지하고 있는 특정 데이터 객체 (개체)
- R-value : value of an exprssion
  - 등호 기준 오른쪽, 대입을 해주는.
  - 수정 가능한 L-value에 대입될 수는 있지만 자기 자신은 L-value가 될 수 없는 것들

## 더하기, 빼기, 부호 연산자

**Addition, Sutraction, Sign**

```c
int income, salary, bonus;
income = salary = bonus = 100; // triple assignment;
```

- bonus에 100을 넣고, salary에 bonus(100)을 넣고 income에 salary(bonus, 100)을 넣는다.

```c
int main()
{
	int a, b, c;
	a = -7;
	b = -a; // b = 7
	c = +a; // c = -7

	1.0f + 2; // ? float로 리턴됨
	return 0;
}
```

### 연산 (operator)

- 이항 연산 (Binary operator) `3 - 2` : 피연산자 2개, 값이 1
- 단항 연산 (Unary operator) `-16` : 피연산자 1개, 값이 -16
- 복합 `-(12-11)` : 값이 -1
