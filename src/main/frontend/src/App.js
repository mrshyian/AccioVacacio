import './App.css';
import React, {useState} from 'react';
import Header from "./components/header/Header";
import 'bootstrap/dist/css/bootstrap.min.css';
import SearchBox from "./components/searchBox/SearchBox";
import AllCarousel from "./components/carousel/AllCarousel";
import {Button} from "react-bootstrap";
import ReactDOM from "react-dom/client";



function App() {

    const root = ReactDOM.createRoot(document.getElementById('root'));

    const renderToSearchPage = () => {
        root.render(
            <React.Profiler>
                <div className="App">
                    <Header inSession={false}/>
                    <SearchBox/>
                </div>
            </React.Profiler>
        );
    }

    return (
        <div style={{textAlign: "center"}}>
            <Header inSession={false}/>
            <AllCarousel/>
            <Button style={{marginTop: "1%", width: "20%", lineHeight: "3", border: "1px solid black", fontSize: 30, boxShadow: "2px 2px 4px #000000"}} size="lg" variant="warning" onClick={renderToSearchPage} >Search
                City</Button>
        </div>
    );
}
export default App;
