import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import './App.css'
import About from './pages/About'
import Home from './pages/Home'
import Profile from './pages/Profile'
import NotFound from './pages/NotFound'

function App() {
  return (
    <BrowserRouter>
      <nav style={{marginBottom: 20}}>
        {/* Link : a태그와 동일한 역할을 하지만 react-router-dom을 활용해 spa방식으로 자연스럽게 화면 전환 */}
        <Link to="/" style={{marginRight: 12}}>홈</Link>
        <Link to="/about" style={{marginRight: 12}}>소개</Link>
        <Link to="/profile/최지원">프로필</Link>
      </nav>
      <Routes>
        <Route path='/' element={<Home />}/>
        <Route path='/about' element={<About />}/>
        <Route path='/profile/:username' element={<Profile />}/>
        <Route path='*' element={<NotFound />}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
