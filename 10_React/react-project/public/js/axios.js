import axios from "axios";

// axios.get("https://jsonplaceholder.typicode.com/posts/1")
//     .then(response => {
//         console.log("받은 데이터: ",response.data);
//     })
//     .catch(error => {
//         console.log("에러 발생: ", error);
//     })

async function getPost(){
    try {
        const response = await axios.get("https://jsonplaceholder.typicode.com/posts/1");
        console.log("받은 데이터: ",response.data);
    } catch (error) {
        console.log("에러 발생: ", error);
    }
}

getPost();

fetch('https://jsonplaceholder.typicode.com/posts', {
    method: 'POST',
    body: JSON.stringify({
        title: 'foo',
        body: 'bar',
        userId: 1,
    }),
    headers: {
        'Content-type' : 'application/json; charset=UTF-8',
    },
})
    .then((response) => response.json())
    .then((json) => console.log(json));

async function addPost(){
    const response = axios.post("https://jsonplaceholder.typicode.com/posts",{
        title: 'foo',
        body: 'bar',
        userId: 1,
    });
    console.log("받은 데이터: ", response.data);
}

addPost();