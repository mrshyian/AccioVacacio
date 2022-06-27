import React, {useEffect, useReducer, useState} from 'react';
import Header from "../header/Header";
import ForumLeftPanel from "./forumleftpanel/ForumLeftPanel";
import ForumRightPanel from "./forumRightPanel/ForumRightPanel";
import axios from "axios";
import {availiablePages} from "../../types";


const Forum = (props) => {

    const [ignored, forceUpdate] = useReducer(x => x + 1, 0);
    const [currentPage, setCurrentPage] = useState(availiablePages.forum);

    useEffect(() =>{
        props.setPage(currentPage)
    }, [])

    const [comment, setComment] = useState([]);
    const [post, setPost] = useState([]);


    const fetchComment = () => {
        axios.get(`http://localhost:8080/comments`)
            .then(res =>{setComment(res.data);
            console.log(res.data)})
        .catch(err => {console.log(err)});
    };

    const fetchPost = () => {
        axios.get(`http://localhost:8080/posts`)
            .then(res =>{setPost(res.data);
            console.log(res.data)})
        .catch(err => {console.log(err)});
    };

    useEffect(() => {
            fetchComment();
            fetchPost();
        }, [])

    return (
        <div>
            <div style={{display: "flex"}}>
                <ForumLeftPanel/>
                <ForumRightPanel posts={post} comments={comment} setPage={setCurrentPage}/>
            </div>

        </div>
    );
};

export default Forum;