# 데이터와 C언어

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
