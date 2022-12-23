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
