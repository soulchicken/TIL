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

- **이걸 왜 투포인터로 풀어야할까**

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

- **억지로 포인터를 사용하기**

```jsx
function countUniqueValues(arr) {
  const LENGTH = arr.length;
  if (LENGTH === 0) {
    return 0;
  }
  let count = 1;
  let left = 0;
  for (let right = 0; right < LENGTH; right++) {
    if (arr[left] < arr[right]) {
      left++;
      let tmp = arr[right];
      arr[right] = arr[left];
      arr[left] = tmp;
      count++;
    }
  }
  return count;
}
```

### Answer

```jsx

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

### My Answer Code

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

### Solution

```jsx
function sameFrequency(num1, num2) {
  let strNum1 = num1.toString();
  let strNum2 = num2.toString();
  if (strNum1.length !== strNum2.length) return false;

  let countNum1 = {};
  let countNum2 = {};

  for (let i = 0; i < strNum1.length; i++) {
    countNum1[strNum1[i]] = (countNum1[strNum1[i]] || 0) + 1;
  }

  for (let j = 0; j < strNum1.length; j++) {
    countNum2[strNum2[j]] = (countNum2[strNum2[j]] || 0) + 1;
  }

  for (let key in countNum1) {
    if (countNum1[key] !== countNum2[key]) return false;
  }

  return true;
}
```

## 코딩 연습 4 : **Frequency Counter / Multiple Pointers - areThereDuplicates**

Implement a function called, **areThereDuplicates** which accepts a **variable number of arguments,** and \*\*checks whether there are any duplicates among the arguments passed in.  You can solve this using the frequency counter pattern OR the multiple pointers pattern.

**Restrictions:**

- Time - O(n)
- Space - O(n)

**Bonus:**

- Time - O(n log n)
- Space - O(1)

**Sample Input**

```jsx
areThereDuplicates(1, 2, 3); // false
areThereDuplicates(1, 2, 2); // true
areThereDuplicates("a", "b", "c", "a"); // true
```

### My Answer Code

```jsx
function areThereDuplicates() {
  const arr = [...arguments];
  const set = new Set();
  for (let i of arr) {
    if (set.has(i)) {
      return true;
    }
    set.add(i);
  }
  return false;
}
```

### Solution - **빈도 수 세기**

```jsx
function areThereDuplicates() {
  let collection = {};
  for (let val in arguments) {
    collection[arguments[val]] = (collection[arguments[val]] || 0) + 1;
  }
  for (let key in collection) {
    if (collection[key] > 1) return true;
  }
  return false;
}
```

### Solution - **다중 포인터**

```jsx
function areThereDuplicates(...args) {
  // Two pointers
  args.sort((a, b) => a > b);
  let start = 0;
  let next = 1;
  while (next < args.length) {
    if (args[start] === args[next]) {
      return true;
    }
    start++;
    next++;
  }
  return false;
}
```

### Solution - One Liner

```jsx
function areThereDuplicates() {
  return new Set(arguments).size !== arguments.length;
}
```

## 코딩 연습 5 : **Multiple Pointers - averagePair**

Write a function called **averagePair.** Given a sorted array of integers and a target average, determine if there is a pair of values in the array where the average of the pair equals the target average. There may be more than one pair that matches the average target.

**Restrictions:**

- Time - O(n)
- Space - O(1)

**Sample Input**

```jsx
averagePair([1, 2, 3], 2.5); // true
averagePair([1, 3, 3, 5, 6, 7, 10, 12, 19], 8); // true
averagePair([-1, 0, 3, 4, 5, 6], 4.1); // false
averagePair([], 4); // false
```

### My Answer Code

```jsx
function averagePair(arr, avg) {
  if (arr.length < 2) return false;
  let i = 0;
  let j = arr.length - 1;
  avg *= 2;
  while (i < j) {
    const n = arr[i] + arr[j];
    if (n === avg) {
      return true;
    }
    if (n > avg) {
      j--;
    } else {
      i++;
    }
  }
  return false;
}
```

### Solution

```jsx
function averagePair(arr, num) {
  let start = 0;
  let end = arr.length - 1;
  while (start < end) {
    let avg = (arr[start] + arr[end]) / 2;
    if (avg === num) return true;
    else if (avg < num) start++;
    else end--;
  }
  return false;
}
```

## 코딩 연습 6 : **Multiple Pointers - isSubsequence**

Write a function called **isSubsequence** which takes in two strings and checks whether the characters in the first string form a subsequence of the characters in the second string. In other words, the function should check whether the characters in the first string appear somewhere in the second string, **without their order changing.**

**Restrictions:**

- Time - O(N + M)
- Space - O(1)

**Sample Input**

```jsx
isSubsequence("hello", "hello world"); // true
isSubsequence("sing", "sting"); // true
isSubsequence("abc", "abracadabra"); // true
isSubsequence("abc", "acb"); // false (order matters)
```

### My Answer Code

```jsx
function averagePair(arr, avg) {
  if (arr.length < 2) return false;
  let i = 0;
  let j = arr.length - 1;
  avg *= 2;
  while (i < j) {
    const n = arr[i] + arr[j];
    if (n === avg) {
      return true;
    }
    if (n > avg) {
      j--;
    } else {
      i++;
    }
  }
  return false;
}
```

### Solution - 반복

```jsx
function isSubsequence(str1, str2) {
  var i = 0;
  var j = 0;
  if (!str1) return true;
  while (j < str2.length) {
    if (str2[j] === str1[i]) i++;
    if (i === str1.length) return true;
    j++;
  }
  return false;
}
```

### Solution - **O(1) 공간이 아닌 재귀**

```jsx
function isSubsequence(str1, str2) {
  if (str1.length === 0) return true;
  if (str2.length === 0) return false;
  if (str2[0] === str1[0]) return isSubsequence(str1.slice(1), str2.slice(1));
  return isSubsequence(str1, str2.slice(1));
}
```
