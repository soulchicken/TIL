# 문제 해결 패턴

**Algorithms and Problem Solving Patterns**

## How Do You Improve?

1. **Devise** a plan for solving problems
   - 문제 해결을 위한 계획 수립
2. **Master** common problem solving patterns
   - 이번 챕터의 이야기

## Frequency Counters (빈도수 세기 패턴)

**선형으로 풀어낼 방법을 고민해야한다!**

- **This pattern uses objects or sets to collect values / frequencies of values**
- **This can often avoid the need for nested loop or O(N^2) operations with arrays / strings**

### An Example

write function call **same**, which accepts two arrays.

The function should return true if evry value in the array has it’s corresponding value squared in the second array. The frequency ofr values must be the same.

- 2개의 배열을 인자로 받는 same 함수를 작성하기
- 두 번째 배열의 값이 첫 배열의 제곱이면된다. (배열의 순서는 상관없음)

```jsx
same([1, 2, 3], [4, 1, 9]); // true
same([1, 2, 3], [1, 9]); // false
same([1, 2, 1], [4, 4, 1]); // false (must be same frequency)
```

### A navie Solution

- Time Complexity = N^2

```jsx
function same(arr1, arr2) {
  if (arr1.length !== arr2.length) {
    return false;
  }
  for (let i = 0; i < arr1.length; i++) {
    let correctIndex = arr2.indexOf(arr1[i] ** 2);
    if (correctIndex === -1) {
      return false;
    }

    arr2.splice(correctIndex, 1);
  }
  return true;
}
```

### Refactored

- Time Complexity = N

```jsx
function same(arr1, arr2) {
  if (arr1.length !== arr2.length) {
    return false;
  }
  let frequencyCounter1 = {};
  let frequencyCounter2 = {};
  for (let val of arr1) {
    frequencyCounter1[val] = (frequencyCounter1[val] || 0) + 1;
  }
  for (let val of arr2) {
    frequencyCounter2[val] = (frequencyCounter2[val] || 0) + 1;
  }
  for (let key in frequencyCounter1) {
    if (
      !(key ** 2 in frequencyCounter2) ||
      frequencyCounter2[key ** 2] != frequencyCounter1[key]
    ) {
      return false;
    }
  }
  return true;
}
```

## 코딩 연습 1 : \***\*Frequency Counter - validAnagram\*\***

Give two string, write a function to determine if the second string is an anagram of the first.

An anagram is a word, phrase, or name formed by rearranging the letters of another, such as _cinema_, formed from _iceman_

- 두 문자열에 들어가는 문자, 빈도가 동일한지 확인하기!

```jsx
validAnagram("", ""); // true
validAnagram("aaz", "zza"); // false
validAnagram("anagram", "nagaram"); // true
validAnagram("rat", "car"); // false
validAnagram("awesome", "awesom"); // false
validAnagram("qwery", "qeywrt"); // true
validAnagram("texttwisttime", "timetwisttext"); // true
```

### **Answer Code**

```jsx
function validAnagram(str1, str2) {
  if (str1.length != str2.length) {
    return false;
  }
  const obj = {};
  for (let char of str1) {
    obj[char] = (obj[char] || 0) + 1;
  }
  for (let char of str2) {
    if (!obj[char] || obj[char] == 0) {
      return false;
    }
    obj[char]--;
  }
  return true;
}
```

## Multiple Pointers (다중 포인터 패턴)

Creating **pointers** or values that correspond to an index or position and move towards the beginning, end or middle based on a certain condition

- 인덱스나 위치에 해당하는 포인터나 값을 만든 다음 특정 조건에 따라 중간 지점에서부터 시작지점이나 끝 지점이나 양쪽 지점을 향해 이동시키는 것

**Very** efficient for solving problems with minimal space complexity as well

### An Example

Write a function called **sumZero** which accepts a **sorted** array of integers. The function should find the **first** pair where the sum is 0. Return an array that includes both values that sum to zero or undefined if a pair does not exist.

- 합계가 0인 두 값을 구하기

```jsx
sumZero([-3, -2, -1, 0, 1, 2, 3]); // [-3, 3]
sumZero([-2, 0, 1, 3]); // undefined
sumZero([1, 2, 3]); // undefined
```

### Naive Solution

- **Time Complexity - O(N^2)**
- **Space Complexity - O(1)**
- 2중 for문을 활용해 전체를 확인해보기

```jsx
function sumZero(arr) {
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr.length; j++) {
      if (arr[i] + arr[j] === 0) {
        return [arr[i], arr[j]];
      }
    }
  }
}
```

### Refactor

- **Time Complexity - O(N)**
- **Space Complexity - O(1)**

```jsx
function sumZero(arr) {
  let left = 0;
  let right = arr.length - 1;
  while (left < right) {
    let sum = arr[left] + arr[right];
    if (sum === 0) {
      return [arr[left], arr[right]];
    }
    if (sum > 0) {
      right--;
    } else {
      left++;
    }
  }
}
```

## 코딩 연습 2 : Multiple Pointers - countUniqueValues

Implement a function called **countUniqueValues**, which accepts a sorted array, and counts the unique values in the array. There can be negative numbers in the array, but it will always be sorted.

```jsx
countUniqueValues([1, 1, 1, 1, 1, 2]); // 2
countUniqueValues([1, 2, 3, 4, 4, 4, 7, 7, 12, 12, 13]); // 7
countUniqueValues([]); // 0
countUniqueValues([-2, -1, -1, 0, 1]); // 4
```

- 이걸 왜 투포인터로 풀어야할까

```jsx
function countUniqueValues(arr) {
  let count = 0;
  let num = -9999999999;
  for (let i of arr) {
    if (num !== i) {
      count++;
      num = i;
    }
  }
  return count;
}
```

## 100% 선택적 도전 과제

## 코딩 연습 3 : \***\*Frequency Counter - sameFrequency\*\***

- Write a function called **sameFrequency.** Given two positive integers, find out if the two numbers have the same frequency of digits.
- Your solution MUST have the following complexities:
- Time: O(N)

**Sample Input**

```jsx
sameFrequency(182, 281); // true
ameFrequency(34, 14); // false
ameFrequency(3589578, 5879385); // true
sameFrequency(22, 222); // false
```

### Answer Code

```jsx
function sameFrequency(num1, num2) {
  num1 = String(num1);
  num2 = String(num2);
  const numArray = new Array(10).fill(0);
  for (let num of num1) {
    numArray[parseInt(num)]++;
  }

  for (let num of num2) {
    numArray[parseInt(num)]--;
  }
  for (let i = 0; i < 10; i++) {
    if (numArray[i] != 0) return false;
  }
  return true;
}

console.log(sameFrequency(182, 281) == true);
console.log(sameFrequency(34, 14) == false);
console.log(sameFrequency(3589578, 5879385) == true);
console.log(sameFrequency(22, 222) == false);
```
