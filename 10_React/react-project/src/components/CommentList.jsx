import React, { Component } from 'react'
import Comment from './Comment';

const serverComments = [
    {
        id: 1,
        message: "배고프다"
    },
    {
        id: 2,
        message: "하이"
    },
    {
        id: 3,
        message: "집 가고 싶어요"
    }
]

class CommentList extends Component {
    constructor(props){
        super(props);
        this.state = {
            commentList: [],
        }
    }

    componentDidMount(){
        //setInterval : 일정 시간마다 반복해서 동작하는 비동기 함수
        const {commentList: tmpCommentList} = this.state;
        setInterval(() => {
            if(tmpCommentList.length < serverComments.length){
                const index = tmpCommentList.length; //0
                tmpCommentList.push(serverComments[index]);
                this.setState({
                    commentList: tmpCommentList
                })
            } else{
                this.setState({
                    commentList: []
                })
            }
        }, 3000);
    }

    componentWillUnmount(){
        clearInterval(this.interval);
    }

    render() {
        const {commentList} = this.state;
        return (
        <div>
            {commentList.map(c => <Comment key={c.id} id={c.id} message={c.message}/>)}
        </div>
        )
    }
}

export default CommentList