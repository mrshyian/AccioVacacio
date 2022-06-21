import React, {useEffect, useState} from 'react';
import Header from "../header/Header";
import ForumLeftPanel from "./forumleftpanel/ForumLeftPanel";
import ForumRightPanel from "./forumRightPanel/ForumRightPanel";
import axios from "axios";


const Forum = () => {

    const [comment, setComment] = useState([]);

    const fetchComment = () => {
        axios.get(`http://localhost:8080/comments`)
            .then(res =>{setComment(res.data);
            console.log(res.data)})
        .catch(err => {console.log(err)});
    };

    useEffect(() => {
            fetchComment();
        }, [])

    return (
        <div>
            <Header/>
            <div style={{display: "flex"}}>
                <ForumLeftPanel/>
                <ForumRightPanel comments={comment}/>
            </div>

        </div>
    );
};

export default Forum;