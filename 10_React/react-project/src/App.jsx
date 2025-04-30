import { Route, BrowserRouter as Router, Routes } from 'react-router-dom'
import './App.css'
import HomePage from './pages/HomePage'
import PostListPage from './pages/PostListPage'

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<HomePage/>}/>
        <Route path='/posts' element={<PostListPage/>}/>
        <Route path='/posts/new' element={<HomePage/>}/>
      </Routes>
    </Router>
  )
}

export default App
