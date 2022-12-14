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

### 나누기 연산

- 정수 나눗셈을 하면 0에 가깝게 소숫점을 절삭한다
- 정수와 실수형을 나눗셈하면 실수로 결과를 만든다

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
	printf("Integer divisions\n");
	printf("%d\n", 14 / 7);
	printf("%d\n", 7 / 2); // 3.5
	printf("%d\n", 7 / 3); // 2.333
	printf("%d\n", 7 / 4); // 1.75
	printf("%d\n", 8 / 4); // 2

	printf("\nTruncating toward zero (C99)\n");
	printf("%d\n", -7 / 2); // -3.5
	printf("%d\n", -7 / 3); // -2.333
	printf("%d\n", -7 / 4); // -1.75
	printf("%d\n", -8 / 4); // -2

	printf("\nInteger divisions\n");
	printf("%f\n", 9.0 / 4.0);
	printf("%f\n", 9.0 / 4);
	return 0;
}
```

**결과**

```c
Integer divisions
2
3
2
1
2

Truncating toward zero (C99)
-3
-2
-1
-2

Integer divisions
2.250000
2.250000
```

## 연산자 우선순위와 표현식 트리

### 표현식 트리 (Expression Tree)

![표현식트리](./expression/1.png){: width="300”}

### 연산자 우선순위

![연산자 우선순위](./expression/2.png){: width="300”}

## 나머지 연산자

- `%`를 사용한다.

```c
int a = 13 % 5; // a = 3
int b = 90 % 60; // b = 30
```

- 전체 ‘초’를 가지고 시간, 분, 초로 나눠서 계산하기

```c
int main()
{
	int input_seconds = 0;
	int hours = 0, minutes = 0, seconds = 0;

	printf("Input seconds : ");
	scanf("%d", &input_seconds);
	seconds = input_seconds % 60;
	input_seconds /= 60;

	minutes = input_seconds % 60;
	input_seconds /= 60;

	hours = input_seconds % 60;
	input_seconds /= 60;
	printf("%d hours, %d minutes, %d seconds\n", hours, minutes, seconds);


	printf("Good bye~!\n");
	return 0;
}
```

### 음수 나누기

```c
int main()
{
	int div, mod;
	div = 11 / 5;
	mod = 11 % 5;
	printf("div : %d, mod : %d\n", div, mod);
	div = 11 / -5;
	mod = 11 % -5;
	printf("div : %d, mod : %d\n", div, mod);
	div = -11 / -5;
	mod = -11 % -5;
	printf("div : %d, mod : %d\n", div, mod);
	div = -11 / 5;
	mod = -11 % 5;
	printf("div : %d, mod : %d\n", div, mod);

	return 0;
}
>> div : 2, mod : 1
>> div : -2, mod : 1
>> div : 2, mod : -1
>> div : -2, mod : -1
```

- 나머지의 경우 피 연산자(앞의 수)가 양수면 양수, 음수면 음수가 된다.
