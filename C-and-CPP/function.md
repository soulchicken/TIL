# 함수

## 함수가 필요할 때

- 반복되는 부분을 함수로 사용하면 재사용성이 올라간다.
- 깔끔한 관리가 가능하다.
- 여러가지 장점이 더 많다.

### 예시

- 아래처럼 내 정보를 출력하고 싶다.
  - 좌우 길이는 바뀔 수도 있게 미리 함수를 만들어놓으면 편할 것 같다!
  - 별표를 길이에 맞게 찍기!
  - 텍스트의 좌우 여백도 올바르게 찍기!

```
*********************
     soulchicken
     Seoul,Korea
*********************
```

- **코드**

```c
#include <stdio.h>
#include <string.h>

#define WIDTH 30
#define NAME "soulchicken"
#define ADDRESS "Seoul,Korea"

void print_multiple_chars(char c, int n_stars)
{
    for (int i = 0; i < n_stars; i++)
    {
        printf("%c", c); // putchar(c)
    }
}

void print_centered_str(char str[])
{
    int n_blanks = (WIDTH - strlen(str)) / 2;
    print_multiple_chars(' ', n_blanks);
    printf("%s\n", str);
}

int main(void)
{
    print_multiple_chars('*', WIDTH);
    printf("\n");

    print_centered_str(NAME);
    print_centered_str(ADDRESS);
    print_multiple_chars('*', WIDTH);
    printf("\n");
}
```
