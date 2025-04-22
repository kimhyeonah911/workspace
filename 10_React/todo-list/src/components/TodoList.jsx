import React, { useState } from 'react'
import styled from 'styled-components'
import TodoItem from './TodoItem'

const Container = styled.div`
    width: 100%;
    margin: 0 auto;
    padding: 24px;
    background: #292929;
    max-width: 500px;
    border-radius: 12px;
    box-shadow: 0 4px 6px black;
`
const Title = styled.h1`
    font-size: 48px;
    font-weight: bold;
    margin-bottom: 24px;
`
const InputContainer = styled.div`
    display: flex;
    margin-bottom: 24px;
`
const Input = styled.input`
    flex: 1;
    padding: 12px;
    background: #333333;
    border: 1px solid #444444;
    outline: none;
    border-radius: 4px 0 0 4px;
    font-size: 16px;

    &:focus{
        border-color: #4d4d4d;
    }
`
const AddButton = styled.div`
    padding: 12px 24px;
    background: #444444;
    border: none;
    border-radius: 0 4px 4px 0;
    cursor: pointer;

    &:hover{
        background: #474747;
    }
`
const TodoListContainer = styled.ul`
    list-style: none;
`

const TodoList = () => {
    const [newTodo, setNewTodo] = useState('');
    const [todos, setTodos] = useState ([]);

    const addTodo = () => {
        if(newTodo.trim() === '') return;
        const todo = {
            id: Date.now(),
            text: newTodo,
            completed: false
        }
        setTodos([...todos, todo]);
        setNewTodo('');
    }

    const deleteTodo = (id) => {
        setTodos(todos.filter(todo => todo.id !== id));
    }

    const handelKeyDown = (e) => {
        if(e.key === "Enter"){
            addTodo();
        }
    }

    const onToggle = (id) => {
        setTodos(todos.map(todo => 
            todo.id === id ? {...todo, completed: !todo.completed} : todo
        ))
    }

    return (
        <Container>
            <Title>TodoList</Title>
            <InputContainer>
                <Input 
                    type="text" 
                    value={newTodo}
                    onChange={(e) => setNewTodo(e.target.value)}
                    onKeyDown={handelKeyDown}
                    placeholder='할 일을 입력하세요.'
                />
                <AddButton onClick={addTodo}>추가</AddButton>
            </InputContainer>
            <TodoListContainer>
                {todos.map((todo) => (
                    <TodoItem key={todo.id} todo={todo} onDelete={deleteTodo} onToggle={onToggle} />
                ))}

            </TodoListContainer>
        </Container>
    )
}

export default TodoList