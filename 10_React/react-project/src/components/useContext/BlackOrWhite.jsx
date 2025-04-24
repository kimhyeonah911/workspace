import React, { useState } from 'react'
import MainContent from './MainContent';
import { ThemeContext } from 'styled-components';

const BlackOrWhite = () => {
    const [theme, setTheme] = useState("black");

    const toggleTheme = () => {
        setTheme(theme === "black" ? "white" : "black");
    }

    return (
        <ThemeContext.Provider value={{theme, toggleTheme}}>
            <MainContent/>
        </ThemeContext.Provider>
    )
}

export default BlackOrWhite