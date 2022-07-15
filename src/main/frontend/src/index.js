import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Forum from "./components/forum/Forum";
import UserPage from "./components/userPage/UserPage";
import SearchCity from "./components/searchCity/SearchCity";
import SearchBox from "./components/searchCity/searchBox/SearchBox";
import AllCarousel from "./components/carousel/AllCarousel";
import UserNotes from "./components/userPage/userLeftBar/userLeftBarComponents/userNotes/UserNotes";
import PlaceWantToGo from "./components/userPage/userLeftBar/userLeftBarComponents/placeWantToGo/PlaceWantToGo";
import Calculator from "./components/userPage/userLeftBar/userLeftBarComponents/calculator/Calculator";
import AlbumsFromTrips from "./components/userPage/userLeftBar/userLeftBarComponents/albumsFromTrips/AlbumsFromTrips";
import MyComments from "./components/forum/forumRightPanel/myComments/MyComments";
import FavouriteComments from "./components/forum/forumRightPanel/favouriteComments/FavouriteComments";
import FilterComments from "./components/forum/forumRightPanel/FilterComments";

import SingleAlbumView
    from "./components/userPage/userLeftBar/userLeftBarComponents/albumsFromTrips/singleAlbum/SingleAlbumView";
import AddImage from "./components/userPage/userMainBar/userMainBarComponents/addImage/AddImage";


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

      <BrowserRouter>
          <Routes>
              <Route path="/" element={<App/>}>
                  <Route path="/forum" element={<Forum />}/>
                  <Route path="/userpage" element={<UserPage />}/>
                  <Route path="/SearchBox" element={<SearchBox />}/>
                  <Route path="/SearchCity" element={<SearchCity />}/>
                  <Route path="/userpage/note" element={<UserNotes/>}/>
                  <Route path="/userpage/place_want_to_go" element={<PlaceWantToGo />}/>
                  <Route path="/userpage/calculator" element={<Calculator />}/>
                  <Route path="/userpage/albums_from_trips" element={<AlbumsFromTrips />}/>
                  <Route path="/userpage/albums_from_trips/album" element={<SingleAlbumView />}/>
                  <Route path="/forum/my_comments" element={<MyComments />}/>
                  <Route path="/forum/favourite_comments" element={<FavouriteComments />}/>
                  <Route path="/sort_by" element={<FilterComments />}/>
                  <Route path="/users" element={<AddImage />}/>
                  <Route path="/" element={<AllCarousel />}/>
              </Route>
          </Routes>
      </BrowserRouter>


);
