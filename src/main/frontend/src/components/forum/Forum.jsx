import React, {useEffect, useState} from 'react';
import ForumLeftPanel from "./forumleftpanel/ForumLeftPanel";
import ForumRightPanel from "./forumRightPanel/ForumRightPanel";
import axios from "axios";


const Forum = () => {
    let csrfToken= null;
    if(document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1') != null){
        csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
        // console.log(csrfToken)
    }

    const [comment, setComment] = useState([]);
    const [post, setPost] = useState([]);

    const fetchComment = async () => {
        await axios.get(`http://localhost:8080/comments`,
            {headers:
                    {'X-XSRF-TOKEN': csrfToken}})
            .then(res =>{
                alert("forum /comments GET with token")
                setComment(res.data);
                })
        .catch(err => {console.log(err)});
    };

    const fetchPost = async () => {
        await axios.get(`http://localhost:8080/posts`)
            .then(res =>{setPost(res.data);
            })
        .catch(err => {console.log(err)});
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