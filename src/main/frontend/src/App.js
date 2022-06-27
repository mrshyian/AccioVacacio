import './App.css';
import React, {useState} from 'react';
import Header from './components/header/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import AllCarousel from './components/carousel/AllCarousel';
import {Button} from 'react-bootstrap';
import {availiablePages} from './types/index';
import UserPage from './components/userPage/UserPage';
import Forum from './components/forum/Forum';
import SearchBox from "./components/searchCity/searchBox/SearchBox";
import SearchCity from "./components/searchCity/searchCity";
import { Outlet, Link } from "react-router-dom";
import {logDOM} from "@testing-library/react";

function App() {
    const [currentPage, setCurrentPage] = useState(availiablePages.travelHelper);
    const [selectedCity, setSelectedCity] = useState("");
    const [selectedCountry, setSelectedCountry] = useState("");

    const getPageToDisplay = () => {
        switch (currentPage) {
            case availiablePages.travelHelper: {
                return (
                    <div>
                        <AllCarousel />
                        <Button
                            style={{
                                marginTop: '1%',
                                width: '20%',
                                lineHeight: '3',
                                border: '1px solid black',
                                fontSize: 30,
                                boxShadow: '2px 2px 4px #000000',
                            }}
                            size="lg"
                            variant="warning"
                            onClick={() => setCurrentPage(availiablePages.searchBox)}
                        >
                            Search City
                        </Button>
                    </div>
                );
            }
            case availiablePages.myProfile: {
                console.log(currentPage)
                return <UserPage />;

            }
            case availiablePages.forum: {
                console.log(currentPage)
                return <Forum />;
            }
            case availiablePages.searchCity: {
                console.log(currentPage)
                return <SearchCity country={selectedCountry} city={selectedCity}/>;
            }
            case availiablePages.searchBox: {
                console.log(currentPage)
                return <SearchBox setCountry={setSelectedCountry} setCity={setSelectedCity} setPage={setCurrentPage} />;
            }
        }
    };

    const pageToDisplay = getPageToDisplay();

    return (

        <div style={{ textAlign: 'center' }}>
            {/*<Link to="/forum">Foruum</Link>*/}
            <Header setPage={setCurrentPage}/>
            {pageToDisplay}
            {/*<Outlet />*/}

        </div>
    );
}
export default App;
