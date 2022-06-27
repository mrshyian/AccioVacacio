import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Forum from "./components/forum/Forum";
import UserPage from "./components/userPage/UserPage";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

      <BrowserRouter>
          <Routes>
              <Route path="/" element={<App/>}>
                  <Route path="/forum" element={<Forum />}/>
                  <Route path="/userpage" element={<UserPage />}/>
              </Route>
          </Routes>
      </BrowserRouter>


);

// <button><Link to="/forum">Foruum</Link></button>