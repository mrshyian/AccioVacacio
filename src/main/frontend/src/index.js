import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Forum from "./components/forum/Forum";
import UserPage from "./components/userPage/UserPage";
import SearchCity from "./components/searchCity/searchCity";
import SearchBox from "./components/searchCity/searchBox/SearchBox";
import AllCarousel from "./components/carousel/AllCarousel";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

      <BrowserRouter>
          <Routes>
              <Route path="/" element={<App/>}>
                  <Route path="/forum" element={<Forum />}/>
                  <Route path="/userpage" element={<UserPage />}/>
                  <Route path="/searchCity" element={<SearchBox />}/>
                  <Route path="/" element={<AllCarousel />}/>
              </Route>
          </Routes>
      </BrowserRouter>


);
