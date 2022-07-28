import React, {useEffect, useState} from 'react';
import {Card} from "react-bootstrap";
import SinglePost from "../../../../forum/forumRightPanel/singlePost/SinglePost";
import axios from "axios";
import UserLeftBar from "../../UserLeftBar";

const UserPageFavouriteComments = () => {

    const [favouriteComments, setFavouriteComments] = useState([]);
    const [favouriteCommentsPosts, setFavouriteCommentsPosts] = useState([]);

    const fetchMyComments = () => {
        axios.get(`http://localhost:8080/favouriteComments`,
            {headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res =>{setFavouriteComments(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    const fetchMyPosts = () => {
        axios.get(`http://localhost:8080/favouriteCommentsPosts`,
            {headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}})
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
            <UserLeftBar/>
            <Card
                bg={"dark"}
                key={"dark"}
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