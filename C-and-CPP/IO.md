# 문자 입출력과 입력 유효성 검증

## 입출력 버퍼

![입출력 버퍼](./IO/1.png){: width="300”}

- 버퍼 : 입,출력시 한꺼번에 모아서 사용하면 효율이 높기 때문에 사용한다.
- 버퍼는 효율성을 높힐 수 있는 구조로 사이즈를 만든다.
- 버퍼에 모아둔 데이터를 한 번에 처리하는 시점
  - 버퍼가 꽉찼을 때
  - escape sequence(엔터, ‘\n’)같은 데이터가 들어왔을 때

### 버퍼를 사용하는 경우

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <conio.h> // windows, _getch(), _getche()

int main()
{
	char c;
	while ((c = getchar() != '.')) {
		putchar(c);
	}
	return 0;
}
<< Hello. 입력!
>> Hello 출력!
```

### 버퍼를 사용하지 않는 경우

- **get char echo**

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <conio.h> // windows, _getch(), _getche()

int main()
{
	char c;
	while ((c = _getche() != '.')) {
		putchar(c);
	}
	return 0;
}
>> (a 입력!) aa
>> (b 입력!) aabb
```

- **getch**

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <conio.h> // windows, _getch(), _getche()

int main()
{
	char c;
	while ((c = _getch() != '.')) {
		putchar(c);
	}
	return 0;
}
>> hello. 입력!
>> (hello.은 사라짐) hello
```

## 파일의 끝

**EOP (End of File)**

- `-1` 이다 (…)
- 운영체제가 더 이상 입력받을 것이 없다는 것을 알려주면 `getchar()`에서 `-1`을 리턴해준다.
- 파일 입력과 콘솔 입력은 사실 동일하다.
  - 콘솔에서 `EOF`를 날려주려면 `Ctrl + Z`

```c
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
    char c;
    while ((c = getchar() != EOF)) // End of File
    {
        putchar(c);
    }
    return 0;
}
```

### EOF 확인하기

```c
#define_CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main()
{
    char c;
    while (1)// End of File
{
        c = getchar();
        printf("%d\n", c);
        if (c ==EOF)
            break;
    }
    return 0;
}
<< hi엔터 입력!
>> 104 출력(h)
>> 105 출력(i)
>> 10 출력(엔터)
<< Ctrl+Z 엔터 입력!
>> -1 출력 (EOF)
```
