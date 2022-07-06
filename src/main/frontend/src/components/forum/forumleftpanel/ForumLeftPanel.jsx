import React, {useState} from 'react';
import {Menu, MenuItem, ProSidebar, SubMenu} from "react-pro-sidebar";
import {
    FaComment,
    FaCommentDots,
    FaFilter,
    FaGlobeAmericas,
    FaGlobeEurope,
    FaHeart,
    FaHourglassHalf
} from "react-icons/fa";

import AddNewPost from "../forumRightPanel/AddNewPost";
import MustBeLogIn from "../../mustBeLogIn/MustBeLogIn";
import UserLeftBar from "../../userPage/userLeftBar/UserLeftBar";
import UserMainBar from "../../userPage/userMainBar/UserMainBar";


const ForumLeftPanel = () => {

    const [NewModalOpen, setNewModalOpen] = useState(false);

    const openModal = () =>{
        setNewModalOpen(true)
    }

    if (sessionStorage.getItem("userId") === null){
        return (
            <header>
                <ProSidebar className="sidebar" style={{height: "1000px"}}>
                    <Menu iconShape="square">
                        <SubMenu title="Filter" icon={<FaFilter />}>
                            <MenuItem icon={<FaHourglassHalf />}>Latest / Oldest</MenuItem>
                            <MenuItem icon={<FaGlobeAmericas />}><input type="text" placeholder="Country"/></MenuItem>
                            <MenuItem icon={<FaGlobeEurope />}><input type="text" placeholder="City"/></MenuItem>
                        </SubMenu>
                        {NewModalOpen && <AddNewPost open={NewModalOpen}/>}
                    </Menu>
                </ProSidebar>
            </header>
        );
    } else {
        return (
            <header>
                <ProSidebar className="sidebar" style={{height: "1000px"}}>
                    <Menu iconShape="square">
                        <MenuItem icon={<FaComment />}>My comments</MenuItem>
                        <SubMenu title="Filter" icon={<FaFilter />}>
                            <MenuItem icon={<FaHourglassHalf />}>Latest / Oldest</MenuItem>
                            <MenuItem icon={<FaGlobeAmericas />}><input type="text" placeholder="Country"/></MenuItem>
                            <MenuItem icon={<FaGlobeEurope />}><input type="text" placeholder="City"/></MenuItem>
                        </SubMenu>
                        <MenuItem icon={<FaHeart />}>Favourite comments</MenuItem>
                        <MenuItem variant="outline-warning" onClick={() => openModal()} icon={<FaCommentDots />}>Add Post</MenuItem>
                        {NewModalOpen && <AddNewPost open={NewModalOpen}/>}
                    </Menu>
                </ProSidebar>
            </header>
        );
    }
};

export default ForumLeftPanel;