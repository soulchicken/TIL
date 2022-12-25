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
