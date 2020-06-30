import React, {createContext, useState} from 'react';
import './App.css';
import Landing from "./components/Landing";
import {BrowserRouter} from "react-router-dom";

export const ThemeContext = createContext('dark');


function App() {

    const [theme, setTheme] = useState('');
  return (

    <div className="App">
        <ThemeContext.Provider value={theme}>
        <BrowserRouter>
            <button onClick={()=> setTheme(theme === 'dark' ? 'light' : 'dark')}>Change theme</button>
            <Landing/>
        </BrowserRouter>
      </ThemeContext.Provider>
    </div>

  );
}

export default App;
