import './App.css';
import React from 'react';
import Header from './components/header/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Outlet} from "react-router-dom";

function App() {
    return (
        <div style={{ textAlign: 'center' }}>
            <Header />
            <Outlet />
        </div>
    );
}
export default App;
