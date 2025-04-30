import React, { useEffect, useState } from 'react'
import { Container, Loading, Error, PostCard, Title, Content, ButtonContainer, LoadingOverlay } from './styled/postList.styled'
import usePostStore from '../store/postStore'
import { Button } from './styled/common';
import styled from 'styled-components';

const ControlButton = styled(Button)`
    margin: 0;
`


const PostList = () => {
    const {posts, loading, error, getPosts, deletePost} = usePostStore();
    const [deletePostId, setDeletePostId] = useState(null);

    useEffect(() => {
        getPosts();
    },[getPosts]);

    if(loading && posts.length === 0) return <Loading>로딩중...</Loading>
    if(error) return <Error>에러발생 : {error}</Error>

    const handelDelete = async (id) => {
        setDeletePostId(id);
        await deletePost(id);
        setDeletePostId(null);
    }

    return (
        <Container>
            {posts.map((post) => (
                <PostCard key={post.id}>
                    <Title>{post.title}</Title>
                    <Content>{post.body}</Content>
                    <ButtonContainer>
                        <ControlButton>수정</ControlButton>
                        <ControlButton onClick={() => handelDelete(post.id)}>
                            {/* {deletePostId === post.id ? "삭제중..." : "삭제"} */}
                            삭제
                        </ControlButton>
                    </ButtonContainer>
                    {deletePostId === post.id &&(
                    <LoadingOverlay>
                        <div>삭제중...</div>
                    </LoadingOverlay>
                    )}
                    
                </PostCard>
            ))}
        </Container>
    )
}

export default PostList