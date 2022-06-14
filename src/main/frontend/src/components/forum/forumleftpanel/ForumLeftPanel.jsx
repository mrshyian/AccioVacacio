import React from 'react';
import {Menu, MenuItem, ProSidebar, SubMenu} from "react-pro-sidebar";
import {FaComment, FaFilter, FaHeart, FaHourglassHalf, FaGlobeAmericas, FaGlobeEurope} from "react-icons/fa";

const ForumLeftPanel = () => {
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
                </Menu>
            </ProSidebar>
        </header>
    );
};

export default ForumLeftPanel;