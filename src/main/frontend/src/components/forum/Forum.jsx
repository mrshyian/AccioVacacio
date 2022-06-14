import React from 'react';
import Header from "../header/Header";
import ForumLeftPanel from "./forumleftpanel/ForumLeftPanel";
import ForumRightPanel from "./forumRightPanel/ForumRightPanel";


const Forum = () => {
    return (
        <div>
            <Header/>
            <div style={{display: "flex"}}>
                <ForumLeftPanel/>
                <ForumRightPanel/>
            </div>

        </div>
    );
};

export default Forum;