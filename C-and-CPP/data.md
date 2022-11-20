# 데이터와 C언어

**목차**

- [데이터와 자료형](#데이터와-자료형)
- [변수와 상수](#변수와-상수)
- [scanf() 함수의 기본적인 사용법](#scanf-함수의-기본적인-사용법)
- [정수와 실수](#정수와-실수)
- [정수와 오버플로우](#정수와-오버플로우)
- [다양한 정수형](#다양한-정수형들)
- [8진수와 16진수](#8진수와-16진수)
- [이식성이 높은 고정 너비 정수형](#이식성이-높은-고정-너비-정수형)
- [문자형](#문자형)
- [부동소수점형](#부동소수점형)
- [부동소수점형의 한계](#부동소수점형의-한계)
- [불리언형](#불리언형)

## 데이터와 자료형

![자료형1](./data/1.png){: width="300”}

![자료형2](./data/2.png){: width="300”}

- 프로그래머가 자신이 사용할 목적에 따라 사용할 자료형을 정해야한다.
- 범위가 넓은 수를 쓰면 편하지 않나? 싶을 수 있지만 일반적으로는 자료형의 메모리사이즈가 커질수록 낭비도 심해지고 느려진다.
- 컴퓨터는 내부에서 문자를 ‘정수’로 받아들인다.

## 변수와 상수

```c
int angel = 1004;
```

- `int` : 자료형
- `angel` : 변수 (Variable) 변수는 저장될 수 있는 메모리 공간을 의미한다
- `1004` : 리터럴 상수 (Literal Constant) 문자그대로의 의미를 갖고 값이 바뀔 수 없는 것

```c
const int angel = 1004;
```

- `const` : 한정자, 제한자 (Qualifier)로 값을 바꿀 수 없게 된다
- `angel` : 기호적 상수 (Symbolic constant)

## scanf() 함수의 기본적인 사용법

```c
#include <stdio.h>

int main()
{
    int i = 0;
    scanf("%d",&i);// & : ampersand

    printf("Value is %d\n", i);

    return 0;
}
```

- scanf로 값을 입력 받는다.
- `&i` : 변수 자체를 넘겨주는 것이 아니라 변수의 주소를 넘겨주는 행위

### 비쥬얼 스튜디오에서의 오류

- `#define _CRT_SECURE_NO_WARNINGS`
- `scanf`는 때때로 보안의 위험성이 있어서 MS에서 막아놔서 이런 오류가 생김

### 간단한 콘솔 입출력 프로그램 만들기

**숫자 2개를 입력 받아서 합을 출력하기**

```c
#include <stdio.h>

int main()
{
    int i = 0, j = 0;
    printf("Input Two integers : ");
    scanf("%d%d",&i, &j);
    printf("%d + %d = %d\n",i ,j, i + j);

    return 0;
}
```

### 자료형의 차이 확인하기

- `int` 자료형

```c
#include <stdio.h>

int main(void)
{
    int won = 0;
    int dollar = 0;

    printf("Input won : ");
    scanf("%d", &won);

    dollar = won * 0.00089;

    printf("Dollar = %d\n", dollar);
    return 0;
}

// 2000을 입력하면 1이 나온다.
```

- `float` 자료형

```c
#include <stdio.h>

int main(void)
{
    float won = 0;
    float dollar = 0;

    printf("Input won : ");
    scanf("%f", &won);

    dollar = won * 0.00089f;

    printf("Dollar = %f\n", dollar);
    return 0;
}
// 2000을 입력하며 1.780000이 나온다.
```

### printf 자료형 서식 지정자

| 서식 지정자 | 출력 데이터 형태                                         |
| ----------- | -------------------------------------------------------- |
| %c          | 하나의 문자                                              |
| %s          | 문자열                                                   |
| %d          | 부호 있는 10진 정수                                      |
| %i          | 부호 있는 10진 정수 (%d와 동일)                          |
| %f          | 고정 소수점으로 표현한 실수 (소수점 이하 6자리까지 표현) |
| %o          | 부호 없는 8진 정수                                       |
| %u          | 부호 없는 10진 정수                                      |
| %x          | 부호 없는 16진 정수 (소문자 사용)                        |
| %X          | 부호 없는 16진 정수 (대문자 사용)                        |
| %e          | 부동 소수점으로 표현한 실수 (e-표기법)                   |
| %E          | 부동 소수점으로 표현한 실수 (E-표기법)                   |
| %g          | 값에 따라 %f나 %e를 사용함.                              |
| %G          | 값에 따라 %f나 %E를 사용함.                              |
| %%          | 퍼센트(%) 기호 출력                                      |

## 정수와 실수

### 정수 Integers

- 음의 정수, 0, 양의 정수
- 내부적으로 2진수

### 실수 Real numbers

- 2.0, 3.16 …
- 내부적으로 ‘부동 소수점 floating point’ 표현법 사용
- 3.14 = 3.14E0 = 3.14e0 = 0.314E1 = 31.4E-1
  - E : 지수 Exponent
- 내부적으로 2진수

### 부호 없는 정수 Unsigned

- Unsigned는 양의 정수를 다루게 된다
- 0101 1110 (2) = 94

### 부호 있는 정수 Signed

- 첫 비트는 +, - 부호 표현에 사용된다.
- 0 : 양수, 1 : 음수
- 2의 보수 표현법이 일반적이다.
- 부호있는 정수와 범위가 다르다. (대략 절반)
- 0101 1110 (2) = +94

### 부동 소수점 수

- 실제와는 다르지만 이런 형태를 보인다. (실제는 2진수로 나타나진다.)
- +,- 부호(Sign) / 지수(Exponent) / 분수(Fraction)
- - / -1 / 0.3141592 = 0.3141592 X 10^-1 = 0.0314….
- - / 1 / 0.3141592 = 0.3141592 X 10^1 = 3.14….
- - / 2 / 0.3141592 = 0.3141592 X 10^2 = 31.4….

**32bit Single Prcision**

- 1bit : sign
- 8bits : exponent
- 23bits : fraction
- 0 01111100 01000000000000000000000 : `float a = 1.234f;`

**64bit Double Precision**

- 1bit : sign
- 11bits : exponent
- 52bits : fraction

## 정수와 오버플로우

```c
#include <stdio.h>

int main(void)
{
    unsigned int i = 0;
    printf("%u\n", sizeof(unsigned int));
    printf("%u", sizeof(i));
}

// 4, 4가 출력된다! 4바이트 (32비트)
```

- `int`의 가장 큰 숫자를 2진수를 통해 만들어보자
- 2진수는 `0b`로 시작하면 된다

```c
#include <stdio.h>
#include <limits.h>
int main(void)
{
    unsigned int i = 0b11111111111111111111111111111111;
    printf("%u\n", i);

	// limits.h 를 사용하면 바로 int의 최대값을 사용할 수 있다.
    unsigned int u = UINT_MAX;
    printf("%u", u);
}
// 4,294,967,295 int형의 최대값
```

- `%u`가 아니라 `%d` 라면?

```c
#include <stdio.h>
#include <limits.h>
int main(void)
{
    unsigned int u_max = UINT_MAX;
    printf("%u\n", u_max); // 4,294.....
    printf("%d\n", u_max); // -1

    signed int i_max = INT_MAX;
    signed int i_min = INT_MIN;

    printf("%d %d", i_max, i_min); // 2147483647 -2147483648
}
```

### 오버플로우

```c
#include <stdio.h>
#include <limits.h>

int main(void)
{
    unsigned int u_max = UINT_MAX;
    unsigned int u_min = 0;
    unsigned int u_max_plus1 = UINT_MAX + 1;
    unsigned int u_max_minus1 = 0 - 1;

    printf("u_max: %u\n", u_max);
    printf("u_max_plus1: %u\n", u_max_plus1);
    printf("u_min: %u\n", u_min);
    printf("u_max_minus1: %u\n", u_max_minus1);

    return 0;
}

>> u_max: 4294967295
>> u_max_plus1: 0
>> u_min: 0
>> u_max_minus1: 4294967295
```

## 다양한 정수형들

다음 표는 각각의 정수 타입에 따른 메모리의 크기 및 데이터의 표현 범위를 나타냅니다.

- `signed`는 생략이 가능하다.

| 정수형 타입 | 최소 메모리의 크기 | 데이터의 표현 범위 | 형식 지정자
(Format Spectifier) |
| --- | --- | --- | --- |
| (signed) char | 1 바이트 | -128 ~ 127 | hhd 또는 c (문자) |
| unsigned char | 1 바이트 | 0 ~ 255 | hhd 또는 c (문자) |
| (signed) short (int) | 2 바이트 | - 32,768 ~ 32,767 | hd |
| unsigned short (int) | 2 바이트 | - 0 ~ 65,535 | hu |
| (signed) int
또는 signed (int) | (2 또는) 4 바이트 | - 2,147,483,648 ~ 2,147,483,647 | d 또는 i |
| unsigned int | (2 또는) 4 바이트 | - 0 ~ 4,294,967,296 | u |
| (signed) long (int) | 4 바이트 | - 2,147,483,648 ~ 2,147,483,647 | ld |
| unsigned long (int) | 4 바이트 | - 0 ~ 4,294,967,296 | lu |
| long long (int) | 8 바이트 | -9,223,372,036,854,775,808~
9,223,372,036,854,775,807 | lld |
| unsigned long long (int) | 8 바이트 | 0 ~ 18,446,744,073,709,551,615 | llu |

## 8진수와 16진수

- 8진수 1 ~ 7
- 16진수 1 ~ 9 A ~ F

```c
#include <stdio.h>

int main(void)
{
    unsigned int decimal = 4294967295;
    unsigned int binary = 0b11111111111111111111111111111111;
    unsigned int oct = 037777777777;
    unsigned int hex = 0xffffffff;

    printf("%u\n%u\n%u\n%u\n", decimal, binary, oct, hex);

    printf("%o %x %#o %#x %#X", decimal, decimal, decimal, decimal, decimal);
    return 0;
}
>> 4294967295
>> 4294967295
>> 4294967295
>> 4294967295
>> 37777777777 ffffffff 037777777777 0xffffffff 0XFFFFFFFF%
```

## 이식성이 높은 고정 너비 정수형

**Fixed-width Integers**

```c
#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>

int main(void)
{
    int i;
    int32_t i32;     // 32 bit integer 사용하는 메모리의 너비는 32비트로 고정.
    int_least8_t i8; // smallest 8 bit 적어도 8비트 사용.
    int_fast8_t f8;  // fastest minimum 8비트 중에서 가장 바른 것.
    intmax_t imax;   // biggest signed integers 사인드 정수에서 가장 큰 것.
    uintmax_t uimax; // biggest unsigned integers 언사인드 정수 가장 큰 것.

    i32 = 1004;

    // printf를 하면 문제가 될 수 있어서 출력시 형식 지정을 잘 해야한다.
    printf("me32 = %d\n", i32);
    printf("me32 = %" "d" "\n", i32);
    printf("me32 = %" PRId32 "\n", i32); // 32비트 정수
}
```

## 문자형

- 문자를 다루기
- ASCII Chart : [https://www.ascii-code.com/](https://www.ascii-code.com/)

### 아스키코드

```c
#include <stdio.h>

int main(void)
{
    char c = 'A';
    char d = 65;

    printf("%c %hhd\n", c, c); // A 65
    printf("%c %hhd\n", d, d); // A 65

    printf("%c %hhd\n", c + 1, c + 1); // B 66

    return 0;
}
```

### 띵동 알람 소리

```c
#include <stdio.h>

int main(void)
{
    char a = '\a';
    printf("%c", a); // 띵동 소리
    // printf("\07"); 와 같음
    // printf("\x7"); 와 같음
    return 0;
```

### 백스페이스 (\b) 사용하기

```c
#include <stdio.h>

int main(void)
{
    printf("$_______\n");
    printf("$_______\b\b\b");
    return 0;
}
>> $_______
>> $____
```

- 급여 입력하기 예시

```c
#include <stdio.h>

int main(void)
{
    float salary;
    printf("$______\b\b\b\b\b\b");
    scanf("%f", &salary);
    return 0;
}
```

### 탭 (\t) 사용하기

- 줄 맞춤

```c
#include <stdio.h>

int main(void)
{
    printf("AB\tCDEF\n");
    printf("ABC\tDEF\n");
}
>> AB      CDEF
>> ABC     DEF
```

## 부동소수점형

### 과학적 표기법 (Scientific Notations)

- 123.45 = 12345 X 10^(-2) = 1.2345 X 10^2
  - 위 예시에서 유효숫자는 5개
  - 유효숫자 6개라면 1.23450 X 10^2
- m X 10^n (m : significand, n : exponent)

### **4바이트(32비트) 부동소수점수**

- 0 01111100 01000000000000000000000 = 0.15625
  - sign : 0
  - exponent (8bits) : 01111100
  - fraction (23bits) : 01000000000000000000000
  - (+1) X 2(124-127) X (1 + 2^(-2))
  - - 0.125 X 1.25
  - - 0.15625

### 4바이트 정수 범위와 부동소수점수 범위

- **정수**
  - -2,147,483,648 ~ 2,147,483,647
  - 대략 -2.14 X 10^9 ~ 2.14 X 10^9
- **부동소수점수**
  - 대략 -3.4 X 10^38 ~ 3.4 X 10^38
  - 10진수 유효숫자 6개 (정밀도가 떨어지게 된다)
  - 정밀도 때문에 고정소수점을 위해서 정수를 활용하기도 한다.

### 부동소수점수 사용하기

- `float`형의 경우 뒤에 `f`를 추가하기!

```c
#include <stdio.h>

int main(void)
{
    printf("%u\n", sizeof(float));
    printf("%u\n", sizeof(double));
    printf("%u\n", sizeof(long double));

    float f = 123.456f;
    double d = 123.456;

    float f2 = 123.456;
    double d2 = 123.456f;

    int i = 3;
    float f3 = 3.f; // 3.0f
    double d3 = 3.; // 3.0

    float f4 = 1.234e10f; // 1.234f x 10^10
    // 1.234E10f도 가능!
    printf("%f", f4);

    float f5 = 0xb.aP1; // 23.250000
    double d5 =1.0625e0; // 0.0625

}
```

### 부동소수점 printf

```c
#include <stdio.h>

int main(void)
{
    float f = 123.456f;
    double d = 123.456;
    float f5 = 0xb.aP1;
    double d5 = 1.0625e0;
    printf("%f %F %e %E\n", f, f, f, f);
    printf("%f %F %e %E\n", d, d, d, d);
    printf("%a %A\n", f5, f5); // 16진수와 대응하는 프린트 방식
    printf("%a %A\n", d5, d5);
}
>> 123.456001 123.456001 1.234560e+02 1.234560E+02
>> 123.456000 123.456000 1.234560e+02 1.234560E+02
>> 0x1.74p+4 0X1.74P+4
>> 0x1.1p+0 0X1.1P+0
```

## 부동소수점형의 한계

### rount-off errors (ex1)

```c
#include <stdio.h>

int main(void)
{
    // rount-off errors (ex1)
    float a, b;
    a = 1000.0f + 1.0f;
    b = a - 1000.0f;
    printf("%f\n", b); // 1.00000이 출력된다.

    a = 1.0E20f + 1.0f;
    b = a - 1 / 0E20f; // b는 1.0이 나와야한다!
    printf("%f\n", b); // -inf가 출력됐다.
    // 숫자가 너무 크면 의도치않게 연산 에러가 나오게 된다.

    return 0;
}
```

### rount-off errors (ex2)

```c
#include <stdio.h>

int main(void)
{
    // rount-off errors (ex2)
    float a = 0.0f;
    for (int i = 0; i < 100; i++)
    {
        a = a + 0.01f; // 100번 0.01을 더한다면 1.0이 나와야한다.
    }
    printf("%f\n", a); // 0.99999가 나왔다!?
		// 정밀도가 떨어진다!
    return 0;
}
```

### Overflow & underflow

```c
#include <stdio.h>
#include <float.h>
int main(void)
{
    // overflow
    float max = FLT_MAX;
    printf("%f\n", max); // float가 가질 수 있는 가장 큰 숫자
    // 340282346638528859811704183484516925440.000000
    //  3.40282346638528859811704183484516925440e+38F
    max = max * 100.0f;  // 100배 하기!
    printf("%f\n", max); // inf 가 출력된다!

    // underflow
    float min = 1.401298464e-45F; // float가 가질 수 있는 가장 작은 숫자
    printf("%e\n", min);          // 1.175494e-38
    min = min / 100.0f;           // subnormal때문에 0.0이 나와야함
    printf("%e\n", min);

    return 0;
}
```

### Zero Division

```c
#include <stdio.h>
#include <float.h>
int main(void)
{
    // zero division
    float f = 104.0f;
    printf("%f\n", f); // 104.000000 출력!
    f = f / 0.0f;      // 0으로 나누기!
    printf("%f\n", f); // inf 출력!
    return 0;
}
```

### Not a Number (NAN)

```c
#include <stdio.h>
#include <float.h>
#include <math.h>

int main(void)
{
    float f = asinf(1.0f); // 아크사인 1은?
    printf("%f\n", f);     // 1.570796

    f = asin(2.0f);    // 아크사인 2는 없는 수인데 어케 나올까?
    printf("%f\n", f); // nan <- not a number 라는 뜻!

    return 0;
}
```

## 불리언형

**Boolean Type**

- 불리언은 결국 1, 0 (true, false)

```c
#include <stdio.h>
#include <stdbool.h>

int main(void)
{
    printf("%u\n", sizeof(_Bool)); // 1byte

    _Bool b1;
    b1 = 0;                     // false
    printf("false : %d\n", b1); // 0
    b1 = 1;                     // true
    printf("true : %d\n", b1);  // 1

    bool b2, b3;
    b2 = true;
    b3 = false;

    printf("%d %d\n", b2, b3); // true, false : 1, 0

    return 0;
}
```
