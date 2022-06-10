import React from 'react';
import PlaceWantToGo from "./userLeftBarComponents/placeWantToGo/PlaceWantToGo";
import SearchHistory from "./userLeftBarComponents/searchHistory/SearchHistory";
import AlbumsFromTrips from "./userLeftBarComponents/albumsFromTrips/AlbumsFromTrips";
import FavouriteForumComments from "./userLeftBarComponents/favouriteForumComments/FavouriteForumComments";

const UserLeftBar = () => {
    return (
        <div>
            <PlaceWantToGo/>
            <SearchHistory/>
            <AlbumsFromTrips/>
            <FavouriteForumComments/>
        </div>
    );
};

export default UserLeftBar;