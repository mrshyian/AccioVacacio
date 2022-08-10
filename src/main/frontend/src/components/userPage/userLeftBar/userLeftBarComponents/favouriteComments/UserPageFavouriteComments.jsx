import React, {useEffect, useState} from 'react';
import {Card} from "react-bootstrap";
import SinglePost from "../../../../forum/forumRightPanel/singlePost/SinglePost";
import UserLeftBar from "../../UserLeftBar";
import {getResponseFromAxiosGet} from "../../../../../axios";


const UserPageFavouriteComments = () => {
    const favouriteCommentsUrl = `http://localhost:8080/favouriteComments`;
    const favouriteCommentsPostsUrl = `http://localhost:8080/favouriteCommentsPosts`;
    const [favouriteComments, setFavouriteComments] = useState([]);
    const [favouriteCommentsPosts, setFavouriteCommentsPosts] = useState([]);

    const fetchMyComments = () => {
        getResponseFromAxiosGet(favouriteCommentsUrl, 2).then(resp => setFavouriteComments(resp.data));
    };

    const fetchMyPosts = () => {
        getResponseFromAxiosGet(favouriteCommentsPostsUrl, 2).then(resp => setFavouriteCommentsPosts(resp.data));
    };

    useEffect(() => {
        fetchMyComments();
        fetchMyPosts();
    }, [])


    return (
        <div>
            <UserLeftBar/>
            <Card
                bg={"dark"}
                key={"user-page-favourite-comments-dark"}
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

export default UserPageFavouriteComments;