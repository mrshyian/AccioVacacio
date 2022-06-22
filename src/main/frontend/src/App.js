import './App.css';
import React, {useState} from 'react';
import Header from "./components/header/Header";
import 'bootstrap/dist/css/bootstrap.min.css';
import SearchBox from "./components/searchCity/searchBox/SearchBox";
import AllCarousel from "./components/carousel/AllCarousel";



function App() {
    return (
        <div>
            <Header inSession={false}/>
            <AllCarousel/>
        </div>
    );
}
export default App;
