import './App.css';
import React from 'react';
import Header from "./components/header/Header";
import SearchBox from "./components/searchBox/SearchBox";
import AddImage from "./components/userPage/addImage/AddImage";


function App() {

    return (
        <div className="App">
            <Header inSession={false}/>
            <SearchBox/>
            <AddImage/>
        </div>
    );
}

export default App;
