# Man Pages

## **Manual Pages**

== Man pages

- 모르는 명령어를 봤을 때 어떻게 해야할까

```bash
$ man ncal # 원하는 명령어

NAME
     cal, ncal – displays a calendar and the date of Easter

SYNOPSIS
     cal [-3hjy] [-A number] [-B number] [[month] year]
     cal [-3hj] [-A number] [-B number] -m month [year]
     ncal [-3hjJpwy] [-A number] [-B number] [-s country_code] [[month] year]
     ncal [-3hJeo] [-A number] [-B number] [year]
     ncal [-CN] [-H yyyy-mm-dd] [-d yyyy-mm]

DESCRIPTION
     The cal utility displays a simple calendar in traditional format and ncal
     offers an alternative layout, more options and the date of Easter.  The new
     format is a little cramped but it makes a year fit on a 25x80 terminal.  If
     arguments are not specified, the current month is displayed.

     The options are as follows:

     -h      Turns off highlighting of today.

...
```

## 탐색 & 검색

### 한 줄씩 이동하기

- 위아래 방향키

### 페이지별로 이동하기

- 스페이스바나 `F` 키(Forward) 와 `B` 키 (Back)

### Help!

- `H` 키 (Help)

### Searching

- `/pattern` : 맨 앞부터 검색
- `?pattern` : 맨 뒤부터 검색
