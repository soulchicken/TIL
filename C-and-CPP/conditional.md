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

### 숫자를 *로 바꾸기

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