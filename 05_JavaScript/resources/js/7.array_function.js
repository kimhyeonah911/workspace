let members = [
    "최지원",
    "김지원",
    "이지원",
    "박지원",
    "남궁지원"
]

//push
console.log(members.push("신지원"));
console.log(members);

//splice(인덱스, 갯수) -> 원본에 영향을 줌
console.log(members.splice(1, 3));
console.log(members);

//slice(인덱스, 마지막인덱스 - 1) -> 원본에 영향을 주지 않음
console.log(members.slice(0, 2));
console.log(members);

members = [
    "최지원",
    "김지원",
    "이지원",
    "박지원",
    "남궁지원"
]

//...배열 또는 ...객체 -> spread연산자
//배열이나 객체의 요소를 개별적으로 펼쳐서 복사하거나 전달할 때 사용
let members2 = [
    ... members,
    "신지원"
]

console.log(members2);

let choi = {
    name: "jiwon",
    age: 55,
    gender: "남"
}

//값을 추가하거나
choi = {
    ...choi,
    address : "경기도 광명시"
}

//값을 수정할 때
choi = {
    ...choi,
    age: 12
}

//비구조할당
//배열이나 객체에서 값을 추출할 때 개별변수에 할당해서 추출하는 문법
members = [
    "최지원",
    "김지원",
    "이지원"
]

const [cho, kim, lee] = members
console.log(cho);
console.log(kim);

const {name, age} = choi;
console.log(name + ", " + age);

const {name:userName, age:userAge} = choi;
console.log(userName + ", " + userAge);

console.log("-----------------------------------");

//join() -> 배열을 문자열로 변경해줌
console.log(members.join());
console.log(members.join("/"));

//sort()
members2.sort(); //오름차순 정렬
console.log(members2);
members2.reverse();
console.log(members2);

let numbers = [1,9,7,5,3,2];
console.log(numbers);

//a,b를 비교한다.
//1) a를 b보다 나중에 정렬하고 싶다면(뒤에 두고 싶다면) 0보다 큰 수를 반환
//2) a를 b보다 먼저 정렬하고 싶다면(앞에 두고 싶다면) 0보다 작은 수를 반환
//3) 원래 순서 유지 -> 0 반환
numbers.sort(function(a, b){
    return a > b ? 1 : -1; //오름차순정렬
})
console.log(numbers);

console.log("-----------------------------------");

//map
//기존 배열의 요소를 전부 반복하면서 return된 값으로 새로운 배열을 만들어주는 함수
//기존 배열을 이용해서 새로운 배열을 만들 때 사용

let tmpMember = members2.map(m => m); //값복사

console.log(tmpMember);

let userList = [
    {
        userKey : 1,
        userName : "최지원",
        age: 42
    }, {
        userKey : 2,
        userName : "김수민",
        age: 34
    }, {
        userKey : 3,
        userName : "박지수",
        age: 18
    }
]

let buyHistory = [
    {
        userKey: 2,
        productName: "TV",
        price: 10000
    }, {
        userKey: 1,
        productName: "냉장고",
        price: 40000
    }, {
        userKey: 1,
        productName: "모니터",
        price: 20000
    }, {
        userKey: 3,
        productName: "냉장고",
        price: 70000
    }, {
        userKey: 2,
        productName: "자전거",
        price: 30000
    }
]

buyHistory = buyHistory.map((history) => {
    for(let user of userList){
        if(user.userKey===history.userKey){
            return{
                ...user,
                ...history
            }
        }
    }
})

console.log(buyHistory);

buyHistory = buyHistory.map((h, i) => {
    return {
        ...h,
        index: i+1
    }
})

console.log(buyHistory);
console.log("-----------------------------------");

//filter
//return값이 false인 요소를 제외하고 true인 요소만 가져오고 싶을 때
//배열에서 특정값만 필터링하고 싶을 때 사용
let number2 = [1,6,7,9,10,15];
let tmp2=[];
//number2 -> 짝수만 tmp2로 복사
/*for(let num of number2){
    if(num % 2 === 0){
        tmp2.push(num);
    }
}*/

tmp2 = number2.filter((num) => num % 2 === 0);
console.log(tmp2);

buyHistory = buyHistory.filter(h => h.userKey !== 2);
console.log(buyHistory);

//find()
//return되는 조건에 값이 true인 첫 요소를 반환
//모든 요소가 조건에 부합하지 않는다면 undefind를 반환
console.log(tmp2.find(n => n%2===0));
console.log(userList.find(u => u.userKey===2));

//findIndex()
//return되는 조건에 값이 true인 첫 인덱스를 반환
//모든 요소가 조건에 부합하지 않는다면 -1을 반환
console.log(tmp2.findIndex(n => n%2===0));
console.log(userList.findIndex(u => u.userKey===2));