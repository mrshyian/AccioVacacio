import React, {useEffect, useState} from 'react';
import axios from "axios";
import MyComment from "./MyComment";
import ForumLeftPanel from "../../forumleftpanel/ForumLeftPanel";
import SingleComment from "../singleComment/SingleComment";
import SinglePost from "../singlePost/SinglePost";


const MyComments = () => {
    const [myComments, setMyComments] = useState([]);
    const [myCommentsPosts, setMyCommentsPosts] = useState([]);

    const fetchMyComments = () => {
        axios.get(`http://localhost:8080/myComments`)
            .then(res =>{setMyComments(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };


    const fetchMyCommentsPosts = () => {
        axios.get(`http://localhost:8080/myCommentsPosts`)
            .then(res =>{setMyCommentsPosts(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    useEffect(() => {
        fetchMyCommentsPosts();
        fetchMyComments();
    }, [])


    return (
        <div>
            <ForumLeftPanel/>
            {myCommentsPosts.map((posts,  index) => {
                return(
                <SinglePost post={posts} comments={myComments} key={index}/>
                )
            })}

        </div>
    );
};

export default MyComments;