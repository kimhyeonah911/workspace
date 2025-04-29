import styled from 'styled-components'
import './App.css'
import CounterDisplay from './components/CounterDisplay'
import CounterControls from './components/CounterControls'
import TodoList from './components/TodoList'

const AppContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  width: 100vw;
  padding: 24px;
  text-align: center;
  transition: all 0.3s;
`

const Section = styled.section`
  width: 100%;
  margin: 0 auto;
  max-width: 800px;
  padding: 18px;
  border: 8px;
  margin-bottom: 20px;
`

function App() {
  return (
    <AppContainer>
      <Section>
        <h2>Zustand 전역 상태 관리</h2>
        <CounterDisplay/>
        <CounterControls/>
      </Section>
      <Section>
        <h2>Zustane TodoList</h2>
        <TodoList/>
      </Section>
    </AppContainer>
  )
}

export default App
