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