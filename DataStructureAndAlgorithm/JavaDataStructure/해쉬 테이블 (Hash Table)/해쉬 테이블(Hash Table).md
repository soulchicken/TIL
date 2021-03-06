# 해쉬 테이블 (Hash Table)
- 키(Key)와 값(Value)를 매핑할 수 있는 자료 구조
- 해쉬 함수를 통해, 배열에 키에 대한 데이터를 저장할 수 있는 주소(인덱스 번호)를 계산
- Key를 통해 바로 데이터가 저장되어 있는 주소를 알 수 있으므로, 저장 및 탐색 속도가 빨라짐
- 미리 해쉬 함수가 생성할 수 있는 주소(인덱스 번호)에 대한 공간을 배열로 할당한 후, 키에 따른 데이터 저장 및 탐색 지원

## 용어
- 해쉬 함수 (Hash Function) : 임의의 데이터를 고정된 길이의 값으로 리턴해주는 함수
  - 해쉬 (Hash), 해쉬 값(Hash Value) 또는 해쉬 주소(Hash address) : 해싱 함수를 통해 리턴된 고정된 길이의 값
- 해쉬 테이블 (Hash Table) : 키 값의 연산에 의해 직접 접근이 가능한 데이터 구조
  - 슬롯(Slot) : 해쉬 테이블에서 한 개의 데이터를 저장할 수 있는 공간

## 객체 배열
- 배열의 아이템은 각 객체를 참조할 수 있는 주소를 담을 수 있는 공간만 할당
  - 각 아이템 생성시, 별도로 각 객체를 생성해줘야 한다
  - 객체 배열 선언시, 각 생성할 객체를 가리킬 주소만 저장할 공간을 배열로 만든다

## 장단점
- 장점
  - 데이터의 저장 / 읽기 속도가 빠르다
  - 중복 확인이 쉽다
- 단점
  - 저장 공간이 많이 필요한 편
  - 여러 키의 해당하는 주소가 동일한 경우 **충돌**하기 때문에 해결해야함

## 용도
- 검색이 많이 필요한 경우
- 저장 / 삭제 / 읽기 빈번한 경우
- 캐쉬 구현 (중복 확인이 쉬움)

## 충돌(Collision) 해결
### Chaining 기법
  - 해쉬 테이블 저장 공간 외의 공간 활용
  - 충돌이 일어나면 링크드 리스트로 데이터를 추가해 연결
### Linear Probing 기법
  - 해쉬 테이블 저장 공간 안에서 충돌 문제를 해결
  - 충돌이 일어나면 해당 hash address 다음 address 중 빈 공간에 저장
### 충돌 개선
  - 해쉬 테이블의 저장 공간을 확대 및 해쉬 함수 재정의

## HashMap (Collection Framework)
  - `import java.util.HashMap`

## 시간 복잡도
  - 일반적인 경우 : O(1) (해쉬 테이블은 일반적인 상태를 가정하고 사용한다.)
  - 최악의 경우 (모든 부분에서 충돌 발생) : O(n)
  - 배열에 데이터를 저장하고, 검색할 때 : O(n)
  - 이상적인 해쉬 테이블 상태에서 검색할 때 : O(1)