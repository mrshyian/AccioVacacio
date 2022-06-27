import React, {useState} from 'react';
import UserLeftBar from "./userLeftBar/UserLeftBar";
import UserMainBar from "./userMainBar/UserMainBar";
import PlaceWantToGo from "./userLeftBar/userLeftBarComponents/placeWantToGo/PlaceWantToGo";
import AlbumsFromTrips from "./userLeftBar/userLeftBarComponents/albumsFromTrips/AlbumsFromTrips";
import FavouriteForumComments from "./userLeftBar/userLeftBarComponents/favouriteForumComments/FavouriteForumComments";
import UserNotes from "./userLeftBar/userLeftBarComponents/userNotes/UserNotes";
import Calculator from "./userLeftBar/userLeftBarComponents/calculator/Calculator";
import {availiableUserPages} from "./index";

const UserPage = () => {

    const [currentUserPage, setCurrentUserPage] = useState(availiableUserPages.mainUserPage);

    const getCurrentUserPageToDisplay = () => {
        switch (currentUserPage) {
            case availiableUserPages.notes: {
                return <UserNotes/>;
            }
            case availiableUserPages.placesIWantToGo: {
                return <PlaceWantToGo/>;
            }
            case availiableUserPages.calculator: {
                return <Calculator/>;
            }
            case availiableUserPages.albumsFromTrips: {
                return <AlbumsFromTrips/>;
            }
            case availiableUserPages.favouriteCommentsInForum: {
                return <FavouriteForumComments/>;
            }
            case availiableUserPages.mainUserPage: {
                return <UserMainBar/>;
            }
        }
    };

    const pageToDisplay = getCurrentUserPageToDisplay();


    return (
        <div>
            <UserLeftBar setPage={setCurrentUserPage}/>
            {pageToDisplay}
        </div>
    );
};

export default UserPage;