import React, {useEffect, useState} from 'react';
import ForumLeftPanel from "./forumleftpanel/ForumLeftPanel";
import ForumRightPanel from "./forumRightPanel/ForumRightPanel";
import axios from "axios";
import header from "../header/Header";


const Forum = () => {
    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');

    const [comment, setComment] = useState([]);
    const [post, setPost] = useState([]);

    const fetchComment = () => {
        axios.get(`http://localhost:8080/comments`
            ,
            {headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res =>{setComment(res.data);
                console.log(res.data)})
        .catch(err => {console.log(err)});
    };

    const fetchPost = () => {
        axios.get(`http://localhost:8080/posts`
            ,
            {headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}})
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
                <ForumLeftPanel tokenCsrf={csrfToken} posts={post} comments={comment}/>
                <ForumRightPanel tokenCsrf={csrfToken} posts={post} comments={comment}/>

            </div>

        </div>
    );
};

export default Forum;