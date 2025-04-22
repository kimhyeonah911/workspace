import React from 'react'
import styled from 'styled-components'

const TodoItemContainer = styled.li`
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 6px 12px;
    border: 1px solid #a3a3a3;
`
const TodoContent = styled.div`
    display: flex;
    align-items: center;
    gap: 12px;
`
const TodoText = styled.span`
    text-decoration: ${props => (props.completed ? 'line-through' : 'none')}; //line-through
    color: ${props => (props.completed ? '#b9b9b9' : 'inherit')}; //#444444 ;
`
const Checkbox = styled.input`
    width: 18px;
    height: 18px;
`
const DeleteButton = styled.button`
    color: #ca2424;
    background: none;
    border: none;
    cursor: pointer;
    outline: none;

    &:hover{
        color: #ad0f0f;
    }
`
const TodoItem = ({todo, onDelete, onToggle}) => {
    return (
        <TodoItemContainer>
            <TodoContent>
                <Checkbox 
                    type="checkbox"
                    checked={todo.completed}
                    onChange={() => onToggle(todo.id)}
                />
                <TodoText completed={todo.completed}>{todo.text}</TodoText>
            </TodoContent>
            <DeleteButton onClick={() => onDelete(todo.id)}>삭제</DeleteButton>
        </TodoItemContainer>
    )
}

export default TodoItem