import './App.css';
import React from 'react';
import Header from "./components/header/Header";
import SearchBox from "./components/searchBox/SearchBox";


function App() {

    return (
        <div className="App">
            <Header inSession={false}/>
            <SearchBox/>
        </div>
    );
}

export default App;
