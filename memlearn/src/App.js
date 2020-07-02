import React, {createContext, useContext, useEffect, useState} from 'react';
import './App.css';
import Landing from "./components/Landing";
import {BrowserRouter} from "react-router-dom";
import {LOGIN_SUCCESS, UserContextProvider} from "./context/UserContextProvider";
import {UserDispatchContext} from "./context/UserContext";

export const ThemeContext = createContext('dark');


function App() {

    const dispatch = useContext(UserDispatchContext);

    useEffect(()=> {
        const token = localStorage.getItem("planning-user-token")
        if (token) {
            dispatch({type: LOGIN_SUCCESS})
        }
    }, [dispatch])

    const [theme, setTheme] = useState('');
    return (

        <div className="App">
            <UserContextProvider>
                <ThemeContext.Provider value={theme}>
                    <BrowserRouter>
                        <button onClick={() => setTheme(theme === 'dark' ? 'light' : 'dark')}>Change theme</button>
                        <Landing/>
                    </BrowserRouter>
                </ThemeContext.Provider>
            </UserContextProvider>
        </div>

    );
}

export default App;
