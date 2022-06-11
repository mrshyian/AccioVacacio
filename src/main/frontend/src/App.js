import './App.css';
import React from 'react';
import Header from "./components/header/Header";
import 'bootstrap/dist/css/bootstrap.min.css';


function App() {

    return (
        <div className="App">
            <Header inSession={false}/>
        </div>
    );
}

export default App;
