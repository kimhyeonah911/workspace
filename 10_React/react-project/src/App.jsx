import { ThemeProvider } from 'styled-components';
import './App.css';
import IconButtons from './components/IconButtons';
import GlobalStyle from './GlobalStyle';
import { useState } from 'react';
import { darkTheme, lightTheme } from './themes';
import ThemeBox from './components/ThemeBox';
import { toast, ToastContainer } from 'react-toastify';
import { performToast } from './utils/performToast';
import SimpleForm from './components/SimpleForm';
import LoaderDemo from './components/LoaderDemo';
import TodoList from './components/TodoList';

// toast.success('요청에 성공하였습니다.');
// toast.error('요청에 실패하였습니다.');
// toast.warning('요청이 올바르지 않습니다.');
performToast({ msg: '요청에 성공하였습니다.', type: 'success' });
performToast({ msg: '요청에 실패하였습니다.', type: 'error' });
performToast({ msg: '요청이 올바르지 않습니다.', type: 'warning' });

function App() {
  const [isDark, setIsDark] = useState(false);
  const toggleTheme = () => setIsDark(!isDark);

  return (
    <>
      {/* <IconButtons/> */}
      {/* <ThemeProvider theme={isDark ? darkTheme : lightTheme}>
        <GlobalStyle />
        <ThemeBox onToggleTheme={toggleTheme} isDark={isDark} />
      </ThemeProvider> */}
      {/* <ToastContainer /> */}
      {/* <SimpleForm /> */}
      {/* <LoaderDemo /> */}
      <TodoList />
    </>
  );
}

export default App;
