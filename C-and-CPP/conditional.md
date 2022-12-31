# 분기

## 분기문 if

**Branching Statement**

### if문 사용하기

```c
if (expression)
	Statement
```

- expression이 0이 아니라면 Statement를 실행한다!

- 짝수와 홀수를 분기하기 ver. 1

```c
#include <stdio.h>

int main()
{
	int number;
	printf("Input a positive integer : ");
	scanf("%d", &number);

	if (number % 2 == 0)
		printf("Even");
	if (number % 2 != 0)
		printf("Odd");

	return 0;
}
```

### else도 사용하기

```c
if (expression)
	Statement1
else
	Statement2
```

- expression이 0이 아니라면 Statement1를 실행한다!
- expression이 0이라면 Statement2를 실행한다!

- 짝수와 홀수를 분기하기 ver. 2

```c
#include <stdio.h>

int main()
{
	int number;
	printf("Input a positive integer : ");
	scanf("%d", &number);

	if (number % 2 == 0)
		printf("Even");
	else
		printf("Odd");

	return 0;
}
```

## 표준 입출력 함수 getchar(), putchar()

### **‘문자’를 입력받고, 출력하는 함수**

```c
#include <stdio.h> // getchar, putchar

int main()
{
    char ch; // int ch; 도 마찬가지로 작동한다.
    ch = getchar(); // 문자 입력, 버퍼 개념을 나중에 알게 되면 더 깊게 알 수 있음
    putchar(ch); // 문자 출력

    return 0;
}
>> A 입력
>> A 출력
```

### printf처럼 hello world를 출력하기

```c
#include <stdio.h> // getchar, putchar

int main()
{
    char ch;
    ch = getchar();

    while(ch != '\n') // Use '\n' to find the end of a sentence
    {
        putchar(ch);
        ch = getchar();
    }
    putchar();
    return 0;
}
>> Hello World 입력
>> Hello World 출력
```

- 왜 엔터를 누르기 전까지 출력이 나오지 않는지는 버퍼개념을 알면 깊게 알 수 있다.
- 코드의 양을 줄여본다면

```c
#include <stdio.h> // getchar, putchar

int main()
{
    char ch;
    while((ch = getchar()) != '\n') // Use '\n' to find the end of a sentence
    {
        putchar(ch);
    }
    putchar(ch);
    return 0;
}
```

### if, else if로 printf 필터링 하기

```c
#include <stdio.h> // getchar, putchar

int main()
{
    char ch;
    while((ch = getchar()) != '\n') // Use '\n' to find the end of a sentence
    {
        if (ch == 'f')
            ch = 'X';
        else if (ch == 'F')
            ch = 'X';
        putchar(ch);
    }
    putchar(ch);
    return 0;
}
>> Funk You 입력
>> Xunk You 출력
```

- OR 연산을 사용한다면 필터링은 더 편하다.

```c
#include <stdio.h> // getchar, putchar

int main()
{
    char ch;
    while((ch = getchar()) != '\n') // Use '\n' to find the end of a sentence
    {
        if (ch == 'f' || ch == 'F')
            ch = 'X';
        putchar(ch);
    }
    putchar(ch);
    return 0;
}
```

### 숫자를 \*로 바꾸기

```c
#include <stdio.h> // getchar, putchar

int main()
{
    char ch;
    while((ch = getchar()) != '\n') // Use '\n' to find the end of a sentence
    {
        if (ch >='0' && ch <= '9')
            ch = '*';
        putchar(ch);
    }
    putchar(ch);
    return 0;
}
```

### 소문자와 대문자 바꾸기

```c
#include <stdio.h> // getchar, putchar

int main()
{
    char ch;
    while((ch = getchar()) != '\n') // Use '\n' to find the end of a sentence
    {
        if (ch >='a' && ch <= 'z')
            ch -= 'a' - 'A';
        else if (ch >= 'A' && ch <= 'Z')
            ch += 'a' - 'A';
        putchar(ch);
    }
    putchar(ch);
    return 0;
}
```

## ctype.h 문자 함수

- **튜토리얼**

[ctype.h - Google Search](http://google.com/search?q=ctype.h&sourceid=chrome&ie=UTF-8)

- 위에서 만든 소문자와 대문자 바꾸기와 숫자를 \*로 바꾸는 방법을 라이브러리를 활용해서 만들 수 있다.
- 문자에 대한 판별, 변환을 해주는 라이브러리이기 때문에 갱장히 편리함

```c
#include <stdio.h>
#include <ctype.h>

int main()
{
    char ch;
    while((ch = getchar()) != '\n')
    {
        if (islower(ch))
            ch = toupper(ch);
        else if (isupper(ch))
            ch = tolower(ch);
        else if (isdigit(ch))
            ch = '*';
        putchar(ch);
    }
    putchar(ch);
    return 0;
}
```

## 다중 선택 else if

종합 소득세를 계산해보자 (2018년 기준)

```c
// 미리 소득의 기점, 세금율을 정해놓자

// tax base
#define BASE1  12000000.0
#define BASE2  46000000.0
#define BASE3  88000000.0
#define BASE4 150000000.0
#define BASE5 300000000.0
#define BASE6 500000000.0

// percent of rate
#define RATE1 (6.0 / 100.0)
#define RATE2 (15.0 / 100.0)
#define RATE3 (24.0 / 100.0)
#define RATE4 (35.0 / 100.0)
#define RATE5 (38.0 / 100.0)
#define RATE6 (40.0 / 100.0)
#define RATE7 (42.0 / 100.0)

#define BASIC_DEDUCTION 1500000.0
```

- 2번째 구간까지만 구현한다면?

```c
int main()
{
	double income = 0.0;
	double tax = 0.0;

	printf("소득을 입력해주세요 : ");
	scanf("%lf", &income);

	income -= BASIC_DEDUCTION;

	if (income <= BASE1)
	{
		tax = income * RATE1;
	}
	else if (income <= BASE2)
	{
		tax = BASE1 * RATE1 + (income - BASE1) * RATE2;
	}

	printf("세금으로 %lf원이 나갑니당\n", tax);
	printf("실 수령 금액은 %lf원 입니다.\n", income - tax);
	return 0;
}
```

## else와 if의 짝짓기

컴파일러는 인덴트를 무시하기 때문에 조심해야한다.

```c
int number;
scanf("%d", &number);

/* 속으면 안된다! */
if (number > 5)
	if (number < 10)
		printf("Larger than 5 smaller than10\n");
else
	printf("Less than or equal to 5\n");

/* 위와 똑같은 코드 */
if (number > 5)
	if (number < 10)
		printf("Larger than 5 smaller than10\n");
	else
	printf("Less than or equal to 5\n");

/* 괄호로 묶으면 아래와 같다. */
/* else의 위치로 속으면 안된다. 복잡한 상황에서는 괄호로 묶어주자 */
if (number > 5) {
		if (number < 10)
			printf("Larger than 5 smaller than10\n");
		else
			printf("Less than or equal to 5\n");
}
```

## 소수 판단 예제

```c
#include <stdio.h>

int main()
{
    unsigned num;
    int isPrime = 1; // flag

    scanf("%u", &num);

    for (unsigned div = 2; div < num; div++)
    {
        if (!(num % div))
        {
            isPrime = 0;
            break;
        }
    }

    if (isPrime)
        printf("%u is a prime number.\n", num);
    else
        printf("%u is not a prime number.\n", num);
}
```
