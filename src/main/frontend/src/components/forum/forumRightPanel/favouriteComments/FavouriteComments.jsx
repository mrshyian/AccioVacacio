import React, {useEffect, useState} from 'react';

import axios from "axios";
import ForumLeftPanel from "../../forumleftpanel/ForumLeftPanel";
import SinglePost from "../singlePost/SinglePost";
import {Card} from "react-bootstrap";
import {getResponseFromAxiosGet} from "../../../../axios";

const FavouriteComments = () => {
    const [favouriteComments, setFavouriteComments] = useState([]);
    const [favouriteCommentsPosts, setFavouriteCommentsPosts] = useState([]);

    const fetchMyComments = () => {
        getResponseFromAxiosGet(`http://localhost:8080/favouriteComments`, 2).then(res =>{setFavouriteComments(res.data);
            console.log(res.data)})
    };

    const fetchMyPosts = () => {
        getResponseFromAxiosGet(`http://localhost:8080/favouriteCommentsPosts`, 2).then(res =>{setFavouriteCommentsPosts(res.data);
            console.log(res.data)})
    };

    useEffect(() => {
        fetchMyComments();
        fetchMyPosts();
    }, [])

    return (
        <div>
            <ForumLeftPanel/>
            <Card
                bg={"dark"}
                key={"favourite-comments-dark"}
                text={'white'}
                className="mb-2 bg-opacity"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Favourite comments</h2></Card.Header>
                <Card.Body>
                    <Card.Text style={{paddingLeft: "60px", paddingRight: "60px"}}>
                        <div>
                        {favouriteCommentsPosts.map((singlePost, index) => {

                            return (
                                <SinglePost post={singlePost} comments={favouriteComments} key={index}/>
                            )
                        })}
                        </div>
                    </Card.Text>
                </Card.Body>
            </Card>

        </div>
    );
};

export default FavouriteComments;