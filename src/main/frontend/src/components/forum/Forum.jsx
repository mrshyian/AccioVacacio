import React, {useEffect, useState} from 'react';
import ForumLeftPanel from "./forumleftpanel/ForumLeftPanel";
import ForumRightPanel from "./forumRightPanel/ForumRightPanel";
import axios from "axios";
import {getResponseFromAxiosGet} from "../../axios";


const Forum = () => {
    let csrfToken= null;
    if(document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1') != null){
        csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
        // console.log(csrfToken)
    }

    const getCommentsUrl = `http://localhost:8080/comments`;
    const getPostsUrl = `http://localhost:8080/posts`;

    const [comment, setComment] = useState([]);
    const [post, setPost] = useState([]);


    const fetchComment = async () => {
        let resp = await getResponseFromAxiosGet(getCommentsUrl, 2);
        setComment(resp.data);
    };

    const fetchPost = async () => {
        let resp = await getResponseFromAxiosGet(getPostsUrl, 2);
        setPost(resp.data);
    };

    useEffect(
        () => {
            (async () => {
                await fetchPost();
                await fetchComment();
            })();
        }, []);


    return (
        <div>
            <div style={{display: "flex"}}>
                <ForumLeftPanel posts={post} comments={comment}/>
                <ForumRightPanel posts={post} comments={comment}/>
            </div>

        </div>
    );
};

export default Forum;