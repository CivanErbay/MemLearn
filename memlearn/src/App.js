import React from 'react';
import './App.css';
import Landing from "./components/Landing";
import {BrowserRouter} from "react-router-dom";

function App() {
  return (

    <div className="App">
        <BrowserRouter>
            <Landing/>
        </BrowserRouter>
    </div>
  );
}

export default App;
