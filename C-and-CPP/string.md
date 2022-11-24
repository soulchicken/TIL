# 문자열과 형식 맞춘 입출력

## 문자열 입출력하기

- `char`로만 하면 문자를 하나만 입력할 수 있다!

```c
#include <stdio.h>

int main(void)
{
    char fruit_name; // stores only one character.

    printf("What is your favorite fruit?\n");

    scanf("%c", &fruit_name); // be careful with &

    printf("You like %c!\n", fruit_name);

    return 0;
}
```

- string을 만들어보기
  - 여기에서는 `scanf`에서 `&`을 쓰지 않는다. `fruit_name` 자체가 주소이기 때문!

```c
#include <stdio.h>

int main(void)
{
    char fruit_name[40]; // stores only one character.

    printf("What is your favorite fruit?\n");

    scanf("%s", fruit_name); // be careful with &

    printf("You like %s!\n", fruit_name);

    return 0;
}
```

## sizeof 연산자

### sizeof 사용법

```c
#include <stdio.h>
#include <stdlib.h> // malloc(
int main(void)
{
    /* 1. sizeof basic types */

    int a = 0;
    unsigned int int_size1 = sizeof a;    // 4
    unsigned int int_size2 = sizeof(int); // 4
    unsigned int int_size3 = sizeof(a);   // 4

    // size_t는 %zu로 형식 지정자를 사용한다.
    size_t int_size4 = sizeof(a);      // typedef unsigned long
    size_t float_size = sizeof(float); // typeof unsigned long

    printf("Size of int type is %u bytes.\n", int_size1);
    printf("Size of int type is %zu bytes.\n", int_size4);
    printf("Size of float type is %zu bytes.\n", float_size);
    // Size of int type is 4 bytes.
    // Size of int type is 4 bytes.
    // Size of float type is 4 bytes.

    return 0;
}
```

### 배열의 sizeof

```c
#include <stdio.h>
#include <stdlib.h> // malloc()

int main(void)
{
    /* 2. sizeof arrays */

    // 30개의 메모리 주소
    int int_arr[30]; // int_arr[0] = 1024; ...
    int *int_ptr = NULL;
    int_ptr = (int *)malloc(sizeof(int) * 30); // int_ptr[0] 1024; ...

    printf("Size of array = %zu bytes\n", sizeof(int_arr));
    printf("Size of pointer = %zu bytes\n", sizeof(int_ptr));
    // Size of array = 120 bytes
    // Size of pointer = 8 bytes

    return 0;
}
```

### 문자열과 sizeof + 구조체와 sizeof

```c
#include <stdio.h>
#include <stdlib.h> // malloc()

// 구조체
struct MyStruct
{
    int i;
    float f;
};

int main(void)
{
    /* 3. sizeof character array */

    char c = 'a';
    char string[10]; // maximally 9 charator + '/0' (null character)

    size_t char_size = sizeof(char);
    size_t str_size = sizeof(string);

    printf("Size of char type is %zu bytes.\n", char_size);
    printf("Size of string type is %zu bytes.\n", str_size);
    // Size of char type is 1 bytes.
    // Size of string type is 10 bytes.

    printf("%zu\n", sizeof(struct MyStruct));
    // 8 출력 : int 4bytes, float 4bytes
    return 0;
}
```

## 문자열이 메모리에 저장되는 구조

- 숫자 하나 : 1
- 숫자 배열 : 0 1 2 3 4 5 6 7 8 9
- 문자 하나 : ‘a’
- 문자 배열 : ‘H’ ‘e’ ‘l’ ‘l’ ‘o’ ‘\0’ ? ? ? ?

⇒ 문자열은 길이가 다 다를 수 있어서 마지막에 `NULL`을 넣어줘서 문자열의 끝을 알려준다.

### 배열을 만들고 접근하는 방법

- 인덱스는 0부터 접근하고 인덱스 범위를 넘어가면 에러 발생

```c
#include <stdio.h>

int main(void)
{
    int a = 1;
    int int_arr[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    printf("%i %i %i\n", int_arr[0], int_arr[1], int_arr[2]);

    // printf("%i\n", int_arr[10000]);
    return 0;
}
```

### 문자열 선언, 출력

```c
#include <stdio.h>

int main(void)
{
    char c = 'a';
    char str1[10] = "Hello"; // null을 포함해서 6글자
    char str2[10] = {'H', 'i'};

    printf("%c\n", c);    // a
    printf("%s\n", str1); // Hello
    printf("%s\n", str2); // Hi

    // 10칸을 선언했는데 왜 출력이 안될까?
    // '\0'를 확인하면 프린트가 멈춘다!

    // "hi"를 확인하기!
    printf("%hhi %hhi %hhi %hhi %hhi\n", str2[0], str2[1], str2[2], str2[3], str2[4]);
}
```

### 문자열 특이한 케이스

```c
// 특이한 케이스
    char str3[10] = "Hello, World"; // array size is not enough
    char str4[20] = "Hello, \0World";
    printf("%s\n", str3);                  // "Hello, " 출력!
    printf("%c %c\n", str4[10], str4[11]); // r l 출력!
```

## strlen() 함수

- **문자열의 길이를 알려주는 함수**

```c
#include <stdio.h>
#include <string.h>

int main(void)
{
    char str1[100] = "Hello";
    char str2[] = "Hello"; // str2[6]으로 자동으로 맞춰줌.
    char str3[100] = "\0";
    char str4[100] = "\n";

    // sizeof vs strlen 비교
    printf("%zu %zu\n", sizeof(str1), strlen(str1)); // 100 5
    printf("%zu %zu\n", sizeof(str2), strlen(str2)); // 6 5
    printf("%zu %zu\n", sizeof(str3), strlen(str3)); // 100 0
    printf("%zu %zu\n", sizeof(str4), strlen(str4)); // 100 1
}
```

- **동적 할당**

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
    char *str5 = (char *)malloc(sizeof(char) * 100); // 메모리에 char 100칸 지정
    str5[0] = 'H';
    str5[1] = 'e';
    str5[2] = 'l';
    str5[3] = 'l';
    str5[4] = 'o';
    str5[5] = '\0';

    // sizeof(str5)는 char의 자체 사이즈가 나오게 된다
    printf("%zu %zu", sizeof(str5), strlen(str5)); // 8 5 출력!

    return 0;
}
```
