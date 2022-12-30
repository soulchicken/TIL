# 반복문

## while 반복 루프에서 scanf()의 반환값 사용하기

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
    int num, sum = 0;
    int status;
    printf("Enter next integer (q to quit) : ");
    status = scanf("%d",&num);
    while (status)
    {
        sum += num;
        printf("Enter next integer (q to quit) : ");
        status = scanf("%d",&num);
    }
    printf("Sum = %d\n", sum);
    return 0;
}
```

- scanf의 return 값 : 입력 성공한 인자의 갯수
- 만약 코드를 줄인다면?

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
    int num, sum = 0;
    printf("Enter next integer (q to quit) : ");
    while (scanf("%d",&num))
    {
        sum += num;
        printf("Enter next integer (q to quit) : ");
    }
    printf("Sum = %d\n", sum);
    return 0;
}
```

## 의사 코드

**Psuedocode**

- 사람의 언어로 코드를 작성하는 것. 문법보다는 논리 자체에 집중한다.
  - 언어와 분리되어 있기 때문에 누구든 논리를 이해할 수 있다.
- 예시로 사용했던 코드 옆에 의사코드를 적어본다면 이러하다.

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
    int num, sum = 0; // sum을 0으로 초기화한다.
    printf("Enter next integer (q to quit) : "); // 사용자에게 안내한다.
    while (scanf("%d",&num)) // 입력을 받고, 입력이 정수인동안에는 입력을 sum에 더한다.
    {
        sum += num;
        printf("Enter next integer (q to quit) : "); // 사용자에게 안내한다.
    }
    printf("Sum = %d\n", sum); // sum을 출력한다.
    return 0;
}
```

## 진입조건 루프 while

**Entry-Condition Loop**

```bash
while (expression)
        statement
```

- `expression`이 `true`일 때 `statement` 실행

### 무한 루프

```c
int i;
i = 1;
while (i < 5) // infinite loop
    printf("Hi!\n");
```

### 잘못된 루프 - 탈출조건

```c
int i;
i = 1;
while (--i < 5) // wrong direction
    printf("Hi!\n");
```

### 잘못된 루프 2 - 중괄호를 사용하자

```c
int i;
i = 1;
while (i < 5) // wrong direction
    printf("Hi!\n");
    i++; // while문 바깥에 있다
```

### 진입할 수 없는 루프

```c
int i;
i = 10;
while (i++ < 5) // cannot enter
    printf("Hi!\n");
```

### null statement

```c
int i;
i = 0;
while (i++ < 5); // null statement
    printf("%i\n", i);
```

## 관계 연산자

**Reational Operators**

```c
<, <=, ==, >=, > !=
```

## 참과 거짓

참 : 1 (0이 아님)

거짓 : 0

```c
#include <stdio.h>

int main()
{
    int tv, fv;
    tv = (1 < 2);
    fv = (1 > 2);

    printf("True is %d\n", tv);
    printf("False is %d\n", fv);
    return 0;
}

>> True is 1
>> False is 0
```

### 0이 아니면 양수는 True

```c
#include <stdio.h>

int main()
{
    int i = 5;
    while (i)
        printf("%d is true\n", i--);
    printf("%d is false\n",i);
    return 0;
}

>> 5 is true
>> 4 is true
>> 3 is true
>> 2 is true
>> 1 is true
>> 0 is false
```

### 0이 아니면 음수라도 True

```c
#include <stdio.h>

int main()
{
    int i = -5;
    while (i)
        printf("%d is true\n", i++);
    printf("%d is false\n",i);
    return 0;
}

>> -5 is true
>> -4 is true
>> -3 is true
>> -2 is true
>> -1 is true
>> 0 is false
```

## \_Bool 자료형

- 전통적인 C언어에서는 정수 0, 1으로 참 거짓을 판별했다.
- 기존의 판별 방법과 호환하기 위해서 남겨놓고 언더스코어(`_`)가 붙은 자료형으로 생겨났다.

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdbool.h>

int main()
{
    _Bool boolean_true = (2 > 1);
    _Bool boolean_false = (2 < 1);
    printf("True is %d\n", boolean_true);
    printf("False is %d\n", boolean_false);

    // 삼항 연산자 or 조건 연산자 사용하기
    printf(boolean_true ? "true" : "false");
    printf("\n");
    printf(boolean_false ? "true" : "false");
    return 0;
}

>> True is 1
>> False is 0
>> true
>> false
```

## 관계 연산자의 우선순위

[Operators Precedence in C](https://www.tutorialspoint.com/cprogramming/c_operators_precedence.htm)

| Category       | Operator                         | Associativity |
| -------------- | -------------------------------- | ------------- | ------------- | ------------- |
| Postfix        | () [] -> . ++ - -                | Left to right |
| Unary          | + - ! ~ ++ - - (type)\* & sizeof | Right to left |
| Multiplicative | \* / %                           | Left to right |
| Additive       | + -                              | Left to right |
| Shift          | << >>                            | Left to right |
| Relational     | < <= > >=                        | Left to right |
| Equality       | == !=                            | Left to right |
| Bitwise AND    | &                                | Left to right |
| Bitwise XOR    | ^                                | Left to right |
| Bitwise OR     |                                  |               | Left to right |
| Logical AND    | &&                               | Left to right |
| Logical OR     |                                  |               |               | Left to right |
| Conditional    | ?:                               | Right to left |
| Assignment     | = += -= \*= /= %=>>= <<= &= ^=   | =             | Right to left |
| Comma          | ,                                | Left to right |

- 의외로 `== !=`는 `< <= > >=` 보다 우선순위가 낮다.

## for 루프 소개

- counting loop (vs Indefinite loop `while`)
- 유연하고 의도를 분명하게 사용할 수 있는 반복문이라서 자주 사용

```c
#include <stdio.h>

int main()
{
    for (int i = 0; i < 10; i++)
    {
        printf("%d ", i);
    }
}
```

- `i++`이 일어난 이후에 `i < 10`을 판별한다.

## for 문의 유연성

- 유연하게 여러 방식으로 사용 가능한 for 문

```c
for (int i = 10; i > 0; i--)
{
    printf("%d ", i);
}

for (int i = 0; i < 100; i = i + 8)
{
    printf("%d ", i);
}

for (char c = 'A'; c <= 'Z'; c++)
{
    printf("%c ", c);
}

for (int i = 0; i * i < 10; i++)
{
    printf("%d ", i);
}

for (int x = 1, y = 5; y <= 20; y = (++x * 3) + 10)
{
    printf("%d ", x);
}

for (double d = 100.0; d < 300; d = d * 1.1)
{
    printf("%f\n", d);
}
```

### for문 내의 blank

```c
int i, n;
n = 2;
for (i = 2; n < 10;)
{
    n = n * i;

    /* 어쩌고 저쩌고 */
}

for (;;) /* while문과 같음 */
    printf("I love U!");
```

### 과하지만 for 문을 활용하는 방법

```c
int i = 0;
for (printf("Let's go!\n"); i != 7; scanf("%d", &i))
    ; // null statement
```
