# Command Basics

## The Prompt

- `clear` : 터미널 청소하기
- 따옴표로 열고 닫는다면 프롬프트는 모든 라인이 하나의 명령어로 인식한다.

```c
>> "
멋진 커맨드 내용
"
>> not found command :D
```

### date

- `date` : 지금의 연,월,일, 요일, 시간 분 초, 시간대까지 나온다.
- `Date` (대문자)는 잘못된 표현, 근데 맥에서는 됨 (쓰지는 말기)
- 잘못 입력했을 때 오류 차이 확인해보기

```c
$ dateadasdasd // not found
$ date asdasd // invalid date 'asdasd'
```

### ncal : new calender

- 현재 달력을 볼 수 있고, 오늘에 체크가 되어있다.
- 신기한 점은 왼쪽에서 오른쪽으로가 아니라 위에서 아래로 읽는다는 점이다.

```c
$ ncal
    11월 2022
월      7 14 21 28
화   1  8 15 22 29
수   2  9 16 23 30
목   3 10 17 24
금   4 11 18 25
토   5 12 19 26
일   6 13 20 27
```

### cal : calender

- 전통적인 방식의 달력

```c
$ cal
      11월 2022
일 월 화 수 목 금 토
       1  2  3  4  5
 6  7  8  9 10 11 12
13 14 15 16 17 18 19
20 21 22 23 24 25 26
27 28 29 30
```

## Use The Arrow Keys (방향키 사용하기)

- 좌우키로는 커서가 움직인다.
- 위아래키로는 이전에 사용했던 명령어를 순회한다.

## Command Structure

**명령어 기본 구조 (규칙)**

```jsx
$ command -options arguments
```

### Arguments

**인자, 매개변수, 피연산자**

- 명령어에 같이 제공하는 값

```jsx
$ echo mwahahaha
mwahahaha
```

- echo는 값을 넣으면 그대로 출력해줄 것이다.

```bash
$ ncal 2021
2012년의 달력들 출력

$ ncal 1999
1999년의 달력들 출력

$ ncal july 1969
1969년 july의 달력 출력

$ ncal 2021 may
에러 발생~! (인자는 월, 달 순서)
```

- sort로 파일의 내용을 정렬하기

```bash
$ sort colors.txt
blue
green
indigo
orange
red
violet
yellow
```

## Options

- 명령어들에는 다양한 옵션들이 주어진다. 옵션을 사용하면 동작 방식이 조금씩 바뀌고, 항상 앞에 `-`를 붙여줘야한다.

```bash
$ command -option
$ ncal -j
$ sort -r colors.txt // 정렬 순서 반대로
```

- `ncal`을 하면 오늘 날짜에 하이라이팅이 되는데, `-h` 옵션을 통해 하이라이팅을 지울 수 있다.
  - `ncal -j`는 1월 1일부터 1씩 증가한 날짜를 보여준다. (마지막날은 365일)
  - `ncal -M`은 월요일을 먼저로 해서 보여준다.
  - `ncal -3`는 이전 달, 다음 달의 달력이 같이 표시된다.

### 옵션도 대소문자를 구분한다!

- a와 A는 a와 z처럼 다르다.

```bash
ncal -h != ncal -H
```

## Combining Options

**여러 옵션 묶어쓰기**

```bash
$ ncal -3 -h // 하이라이트를 없애고 3달치 달력 출력
$ ncal -3 -h -j
$ ncal -3hj // 묶어서 사용 가능
```

## Long Form Options

**긴 형태 옵션 사용하기**

```bash
date -u == date --universal

sort -r color.txt == sort --reverse colors.txt

sort --reverse --unique colors.txt
== sort -r -u colors.txt
== sort -ru colors.txt
```

- 대부분 옵션에는 Long Form을 지원해주지 않지만 종종 있다. 직관적으로 보고 이해할 수 있는 장점이 있다.

## Options With Parameters

**매개변수를 필요로 하는 옵션**

- 일부 옵션은 옵션에 대한 값을 같이 지정해줘야한다.

```bash
# 지금 달력과 이후 1달치 달력까지 보여줘
$ ncal -A1 # A : 이후(after)

# 이전 2달치 달력과 함께 보여줘
$ ncal -B2 # B : 이전(before)

# 합치면?
$ ncal -B2 -A1
```

### 옵션을 여러 개 넣을 수 있다

```bash
$ ncal -B1 -A2 july 1969
```

## Command Structure Quiz

질문 1:**가상의 `cheese` 커맨드는 `x` 옵션을 허용합니다. 다음 중 `x` 옵션으로 `cheese`를 실행하는 유효한 방법은 무엇일까요?**

• `cheese x`
• `-cheese x`
**• `cheese -x`**
• `-x cheese`

질문 2:**가상의 `makebaby` 커맨드는 우리가 두 개의 이름을 두 개의 개별 인수로 전달할 것으로 예상합니다. 다음 중 `makebaby` 커맨드에 두 개의 인수를 제공하는 유효한 방법은 무엇일까요?**

• `makebaby david,iman`
• `makebaby -david -iman`
**• `makebaby david iman`**

질문 3:**ls 커맨드(아직 본 적이 없음)는 S 옵션과 r 옵션을 허용합니다. 커맨드를 실행하지 않고... 다음 중 두 옵션을 모두 사용하여 ls를 실행하는 유효한 방법이 아닌 것은 무엇일까요?**

• `ls -Sr`
• `ls -r -S`
**• `ls -RS`**
