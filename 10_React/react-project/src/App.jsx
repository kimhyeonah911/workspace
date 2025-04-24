import './App.css'
import MyForm from './components/customHook/MyForm'
import ToggleBox from './components/customHook/ToggleBox'
import UseCallbackTest from './components/useCallback/UseCallbackTest'
import BlackOrWhite from './components/useContext/BlackOrWhite'
import Header from './components/useContext/Header'
import { UserProvider } from './components/useContext/UserContext'
import EffectView from './components/useEffect/EffectView'
import UseMemoTest from './components/useMemo/UseMemoTest'
import UseRefScroll from './components/useRef/UseRefScroll'
import UseRefTest from './components/useRef/UseRefTest'
import LandingPage from './components/useState/LandingPage'
import SignUp from './components/useState/SignUp'
import UseStateTest from './components/useState/UseStateTest'

function App() {
  return (
    <>
    {/* <UseStateTest/> */}
    {/* <SignUp/> */}
    {/* <LandingPage/> */}
    {/* <UseRefTest/> */}
    {/* <UseRefScroll/> */}
    {/* <UseMemoTest/> */}
    {/* <UseCallbackTest/> */}
    {/* <EffectView/> */}
    {/* <BlackOrWhite/> */}
    {/* <MyForm/> */}
    {/* <ToggleBox/> */}
    <UserProvider>
      <Header/>
    </UserProvider>
    </>
  )
}

export default App
