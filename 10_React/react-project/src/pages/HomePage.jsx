import React from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import { Container } from '../components/styled/common'

const Title = styled.h1`
    color: #e4e4e4;
    margin-bottom: 24px;
`

const Button = styled(Link)`
    display: inline-block;
    background: #def7ff;
    color: #575757;
    padding: 12px 24px;
    border-radius: 4px;
    text-decoration: none;
    margin: 12px;

    &:hover{
        opacity: 0.9;
        color: #575757;
    }
`

const HomePage = () => {
  return (
    <Container>
        <Title>게시판 관리</Title>
        <Button to="/posts">게시글 목록</Button>
        <Button to="/posts/new">게시글 작성</Button>
    </Container>
  )
}

export default HomePage