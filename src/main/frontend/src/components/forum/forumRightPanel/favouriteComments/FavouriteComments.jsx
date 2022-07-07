import React, {useEffect, useState} from 'react';

import axios from "axios";
import ForumLeftPanel from "../../forumleftpanel/ForumLeftPanel";
import SinglePost from "../singlePost/SinglePost";

const FavouriteComments = () => {

    const [favouriteComments, setFavouriteComments] = useState([]);
    const [favouriteCommentsPosts, setFavouriteCommentsPosts] = useState([]);

    const fetchMyComments = () => {
        axios.get(`http://localhost:8080/favouriteComments`)
            .then(res =>{setFavouriteComments(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    const fetchMyPosts = () => {
        axios.get(`http://localhost:8080/favouriteCommentsPosts`)
            .then(res =>{setFavouriteCommentsPosts(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    useEffect(() => {
        fetchMyComments();
        fetchMyPosts();
    }, [])


    return (
        <div>
            <ForumLeftPanel/>
            {favouriteCommentsPosts.map((singlePost, index) => {

                return (
                    <SinglePost post={singlePost} comments={favouriteComments} key={index}/>
                )
            })}
        </div>
    );
};

export default FavouriteComments;