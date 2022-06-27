import React from "react"
import './UserLeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { ProSidebar, Menu, MenuItem } from 'react-pro-sidebar';
import 'react-pro-sidebar/dist/css/styles.css';
import { FaPencilAlt, FaMapMarked, FaHeart, FaPhotoVideo, FaBuromobelexperte } from 'react-icons/fa' ;
import {availiableUserPages} from "../index";


const UserLeftBar = (props) => {
    return (
        <div className="App">
            <header>
                <ProSidebar className="sidebar" style={{height: "100%"}}>
                    <Menu iconShape="square">
                        <MenuItem icon={<FaPencilAlt />} onClick={() => props.setPage(availiableUserPages.notes)}>Notes</MenuItem>
                        <MenuItem icon={<FaMapMarked />}onClick={() => props.setPage(availiableUserPages.placesIWantToGo)}>Places i want to go</MenuItem>
                        <MenuItem icon={<FaBuromobelexperte />}onClick={() => props.setPage(availiableUserPages.calculator)}>Calculator</MenuItem>
                        <MenuItem icon={<FaPhotoVideo />}onClick={() => props.setPage(availiableUserPages.albumsFromTrips)}>Albums from trips</MenuItem>
                        <MenuItem icon={<FaHeart />}onClick={() => props.setPage(availiableUserPages.favouriteCommentsInForum)}>Favourite comments in forum</MenuItem>
                    </Menu>
                </ProSidebar>
            </header>
        </div>
    );
}

export default UserLeftBar;
