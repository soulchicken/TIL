# 힙, 가비지 컬렉터

메모리를 간단하게 관리하기 위해서 존재하고, 동적으로 할당한 메모리 영역 중 사용하지 않는 영역을 탐지하여 해제하는 기능

- stack : 정적으로 할당한 메모리 영역
- **heap** : 동적으로 할당한 메모리 영역

## **Heap organization**

- **New-space:** 대다수 객체가 할당되는 범위. 공간의 크기는 다른 공간에 비해 작은 편이고 가비지 컬렉터가 빠르게 수집한다
- **Old-pointer-space:** 포인터가 있을 수 있는 객체. 대부분의 객체는 new-space에서 옮겨진다고 한다
- **Old-data-space:** raw data(다른 객체에 대한 포인터가 없는)
- **Large-object-space:** 다른 스페이스 공간보다 더 큰 공간을 점유한다. 각 객체마다 mmap 메모리 영역을 가지고 있고 가비지 컬렉터가 수집하지 않는다고 한다.
  - **mmap** : 메모리의 내용을 파일이나 디바이스에 대응(mapping)하기 위해서 사용하는 시스템 호출이다.
    - **mmap은 메모리의 특정 영역을 파일로 대응시킬수 있도록 도와준다.** 파일을 시스템 전역적인 객체이므로 다른 프로세스에서 접근가능하도록 할 수 있으며, 이러한 mmap의 특징때문에 IPC 용도로 사용가능하다.
    - 메모리의 내용을 파일에 대응시킬 수 있다면 프로세스간 데이터의 교환을 위한 용도로 사용가능할 것이다. 프로세스간 공유하고자 하는 데이터를 파일에 대응시키고 이것을 읽고 쓰면 된다. 물론 접근제어를 해줘야 하겠지만 말이다.
    - 메모리의 내용을 파일에 직접 대응시킨다면 성능향상을 생각할 수 있을 것이다. 고전적인 방법은 파일지정자를 얻어서 직접 입출력하는 방식으로 open, read, write, lseek와 같은 함수를 이용한다. 이러한 함수의 사용은 당연하지만 상당한 비용을 지불해야 하는데, mmap을 이용하면 비용을 줄일수 있다.
- **Code-space:** JIT를 포함한 code objects가 할당된 공간. 실행가능한 메모리가 있는 유일한 공간이라고는 소개한다. (Large-object-space 에서도 할당된 객체는 실행할 수 있다고 한다.)
  - JIT(just in time)는 브라우저에 표시하기 전에 컴파일러를 다운로드하고 코드를 컴파일한다. (JIT와 AOT ahead-of time의 차이는 멀까)
  - 자바스크립트의 경우 인터프리터 방식으로 동작한다고 알고 있지만, 초기 JS나 v8에서는 바로(Just-in-time) 컴파일하는 방식을 사용한다.
- **Cell-space, property-cell-space and map-space:** 셀, 속성 셀 및 맵을 포함한다. 각 공간에는 크기가 모두 같고 어떤 종류의 객체를 가비지컬렉터가 수집할지에 대한 조건이 있어서 간단하게 수집한다고 한다. (셀, 프로퍼티셀, 맵이 정확히 무엇을 의미하는 지 잘 모르겠음)

## **Discovering pointers**

힙에서 포인터와 데이터를 구분하는 것이 가비지 컬렉터가 해야할 첫 번째 과제다.

가비지컬렉터의 경우 포인터를 식별하기 위해 세 가지 접근법을 사용한다.

- Conservative : 보수적으로 접근한다. 모든 데이터가 일단 포인터인 것으로 가정하고 처리하는 방식. 만약 포인터가 아니라 integer라면 포인터인 것처럼 접근했기 때문에 메모리 누수가 발생할 수 있다. C/C++ 용으로 사용하는 [Boehm-Demers-Weiser garbage collector](http://www.hpl.hp.com/personal/Hans_Boehm/gc/) 이라는 사례는 이 방식을 사용한다.
- Compiler hint : 정적 타입 언어로 한다면 (Java같은) 각 객체가 어느 클래스에서 왔는지 식별할 수 있어 포인터를 쉽게 찾을 수 있다. 그치만 JS,,,는 사용하기 어렵다.
- Tagged pointers : 각 word(단어?)의 끝 비트에 데이터인지 포인터인지 태그를 해놓는다. v8은 기본적으로 이 방식을 사용한다.

## **Generational collection**

- V8의 경우 두 세대로 나눠서 heap을 구성한다. 대부분의 객체는 일찍 사라지지만 몇몇은 길게 남아줘야한다. 이러한 이유로 나누게 된다. 객체는 new-space에 할당된다. 새 공간을 할당할 때마다 증가하는 할당 포인터가 있는데, 이 포인터가 new-space의 끝까지 닿으면 scavenge(작은 가비지컬렉터 사이클)가 돌면서 수거를 해간다. scavenge가 두 번돌때까지 살아남은 객체는 old-space로 가게 된다.
  - new-space는 두 공간으로 나뉜다. (to-space, from-space).
    - 먼저 to-space에 데이터를 채우고, 채워지면 from-space으로 swap
    - from-space 에서 승격 조건이 만족되면 old-space.
- old-space의 경우 mark-sweep, mark-compact 전략으로 수집해야하고, scavenge보다 덜 빈번하게 돌게 된다. 특정 규모의 메모리를 old-space로 이전하면 old-space에서 가비지컬렉터를 돌리게 된다. 이때 특정 규모(임계값)은 공간의 크기나 프로그램의 동작 등 여러 요인에 따라 변한다.

## **Mark-sweep**

'가비지 컬렉션’은 대개 다음 단계를 거쳐 수행

- 가비지 컬렉터는 루트(root) 정보를 수집하고 이를 ‘mark(기억)’
- 루트가 참조하고 있는 모든 객체를 방문하고 이것들을 ‘mark’
- mark 된 모든 객체에 방문하고 *그 객체들이* 참조하는 객체도 mark. 한번 방문한 객체는 전부 mark 하기 때문에 같은 객체를 다시 방문하는 일은 없다.
- 루트에서 도달 가능한 모든 객체를 방문할 때까지 위 과정을 반복
- mark 되지 않은 모든 객체를 메모리에서 삭제

**marking**

마킹은 **DFS** 방식으로 탐색하는데

- 흰색 : 아직 Garbage collector에서 발견되지 않음
- 회색 : Garbage collector에 의해 발견되었으나, 아직 모든 이웃 오브젝트가 처리되지 않았음
- 검은색 : Garbage collector에 의해 발견되었으며, 이웃 오브젝트가 모두 처리되었음

## **Incremental marking and lazy sweeping**

마크 스위프, 마크 콤팩트는 라이브 데이터가 많은 대형 힙에 적용되면 시간이 꽤 많이 걸릴 수 있다. V8 작업을 처음 시작했을 때 **500-1000ms** 동안 넘어가는 일이 많이 일어난다. 용납할 수 없는 일,,,

- incremental marking이 완료되면 lazy sweep 시작.

**incremental marking**

- **5 ~ 10ms** 시간에 점진적으로 마킹

## **Conclusion**

- 가비지 컬렉터는 오버 헤드가 있을 수도 있고 이상한 행동을 할 수 있지만
  더 중요한 것에 집중하고 메모리 관리의 복잡함에서 벗어게 한다.
- 리차드 존스와 라파엘 린스의 _[Garbage Collection](http://www.amazon.com/Garbage-Collection-Algorithms-Automatic-Management/dp/0471941484)_, JVM에서 사용하는 가비지컬렉션 알고리즘 연구논문인 _[Garbage First Garbage-Collection](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.63.6386&rep=rep1&type=pdf)_ 을 읽으면 참고할 자료가 많다고 한다.

**[10분 테코톡] 👌던의 JVM의 Garbage Collector :** [https://www.youtube.com/watch?v=vZRmCbl871I](https://www.youtube.com/watch?v=vZRmCbl871I)

A tour of V8 : Garbage Collection : [https://jayconrod.com/posts/55/a-tour-of-v8-garbage-collection](https://jayconrod.com/posts/55/a-tour-of-v8-garbage-collection)

mmap 이란 : [https://seyun99.tistory.com/entry/mmap이란](https://seyun99.tistory.com/entry/mmap%EC%9D%B4%EB%9E%80)

자바스크립트 엔진 최적화 기법 (1) JIT… : [https://meetup.toast.com/posts/77](https://meetup.toast.com/posts/77)
