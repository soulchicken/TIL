# C언어 BEFORE INTRO

## C언어의 탄생

- **Dennis Ritchie**, **Ken Thompson**이 **C언어**(1972)를 만들었다.
  사실 다른 사람들과 **UNIX 운영체제**를 **어셈블리어**로 만들고 있었다.
  - 어셈블리어로 다 만들기 힘들 것 같아서 운영체제 하에서 추가적으로 사용할 응용프로그램을 만들기 위해서 C언어를 만들었다. (나중엔 UNIX에서 어셈블리 떼고 C로 다 갈아치움)

## C언어의 특징

**장점**

- 프로그램의 효율성을 높일 수 있다.
- 강력하면서도 유연하다.
- 프로그래머 중심이다.
- 다른 시스템으로 이식하기 좋다.

**C언어가 쓰이는 곳**

- Unix 운영 체제
- 컴퓨터 게임
- 루카스 필름 (스타워즈 VFX)
- 임베디드 시스템
- 자동화 공장

**C언어는 어디로 가는가?**

- 언어 랭킹이 매년 바뀌는데, C/C++는 랭킹에서 사라지지 않는다. (1등을 할 일은 없다.)
- 주력으로 쓰는 것은 아닐 가능성이 높지만 C/C++는 공부하는데에 많이 도움이 된다.

## C 언어의 표준

- **K&R C (Classic C)**
  : 유닉스와 함께 제공된 라이브러리가 사실상의 표준
- **C89 ANSI, C90 ISO**
  - The Spirit of C
- **C99 - ANSI/ISO C9X Committee**
  - 국제적인 문자 집합 추가
  - 64비트 프로세서 대응
  - 과학 공학 수치 계산 적합도 개선 (FORTRAN 대체)
  - 언어를 단순하게 유지
- **The C11 Standard**
  - C99의 일부를 선택적인 기능으로 변경
  - 멀티 프로세서 대응 concurrent programming
- **The C18 Standard**
  - 새로운 기능 추가 없이 C11의 결함 수정

## 소프트웨어 개발의 7가지 단계

1. 목적을 정의한다.
2. 프로그램을 설계한다.
3. 코드를 작성한다.
4. 컴파일 한다.
5. 실행시켜본다.
6. 시험해보고 오류가 있다면 수정한다.
7. 프로그램을 유지하고 개선해 나간다.

## C 언어와 함수

**입력(Input) → 함수(Function, 기능) → 출력(Output)**

### 함수

- 입력과 출력이 하나일 수도 있고, 없을 수도 있고 많을 수도 있다.
- 함수는 그 함수 안에 조금 더 작은 함수가 있을 수 있고 그 안에 더더 작은 함수가 있을 수 있다.

### 함수의 기본적인 형태

- 함수의 **output**은 `return`으로 알려준다.
  - 전통적으로 `main` 함수의 `return` 값이 `0`일때 올바르게 실행됐다는 것으로 이해한다.
- 운영체제는 프로그램을 실행하면 `main`이 가장 먼저 실행된다.

```c
int main(void)
{
    return 0;
}
```

### 컴파일러와 링커가 하는 일

- **빌드 과정** : 소스코드(hello.c) → (컴파일러) → 오브젝트 코드(Hello.obj) →(+ 라이브러리 코드와 착수 start up 코드) → (링커) → 실행 파일(Hello.exe)
- 소스코드를 번역하는 역할을 컴파일러.
- c 파일 하나당 오브젝트 코드를 하나 만들게 된다.
- 링커는 여러가지 오브젝트코드, 착수코드, 라이브러리 코드를 연결해주는 역할을 한다.

```c
#include <stdio.h> // 링커가 연결해줄 라이브러리

int main(void)
{
    printf("hello!"); // stdio.h에서 제공해주는 화면에 출력해주는 '함수'
    return 0;
}
```
