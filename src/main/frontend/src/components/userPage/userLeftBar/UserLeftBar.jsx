import React, {useState} from "react"
import './UserLeftBar.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { ProSidebar, Menu, MenuItem } from 'react-pro-sidebar';
import 'react-pro-sidebar/dist/css/styles.css';
import { FaPencilAlt, FaMapMarked, FaHeart, FaPhotoVideo, FaBuromobelexperte } from 'react-icons/fa' ;
import {availiableUserPages} from "../index";
import axios from "axios";
import {Link} from "react-router-dom";



const UserLeftBar = (props) => {
    return (
        <div className="App">
            <header>
                <ProSidebar className="sidebar" style={{height: "100%"}}>
                    <Menu iconShape="square">

                        <MenuItem icon={<FaPencilAlt />}><Link to="/userpage/note"> Notes</Link></MenuItem>
                        <MenuItem icon={<FaMapMarked />}><Link to="/userpage/place_want_to_go"> Places i want to go </Link></MenuItem>
                        <MenuItem icon={<FaBuromobelexperte />}><Link to="/userpage/calculator"> Calculator </Link></MenuItem>
                        <MenuItem icon={<FaPhotoVideo />}><Link to="/userpage/albums_from_trips"> Albums from trips </Link></MenuItem>
                        <MenuItem icon={<FaHeart />}><Link to="/userpage/favourite_forum_comments"> Favourite comments in forum </Link></MenuItem>
                    </Menu>
                </ProSidebar>
            </header>
        </div>
    );
}

export default UserLeftBar;
