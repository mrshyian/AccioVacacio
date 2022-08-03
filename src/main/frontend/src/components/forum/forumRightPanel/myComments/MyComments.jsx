import React, {useEffect, useState} from 'react';
import axios from "axios";
import ForumLeftPanel from "../../forumleftpanel/ForumLeftPanel";
import SinglePost from "../singlePost/SinglePost";
import {Card} from "react-bootstrap";
import InformationAboutUser
    from "../../../userPage/userMainBar/userMainBarComponents/informationAboutUser/InformationAboutUser";


const MyComments = () => {
    const [myComments, setMyComments] = useState([]);
    const [myCommentsPosts, setMyCommentsPosts] = useState([]);

    const fetchMyComments = () => {
        axios.get(`http://localhost:8080/myComments`)
            .then(res =>{setMyComments(res.data);})
            .catch(err => {console.log(err)});
    };


    const fetchMyCommentsPosts = () => {
        axios.get(`http://localhost:8080/myCommentsPosts`)
            .then(res =>{setMyCommentsPosts(res.data);})
            .catch(err => {console.log(err)});
    };

    useEffect(() => {
        fetchMyCommentsPosts();
        fetchMyComments();
    }, [])


    return (
        <div>
        <ForumLeftPanel/>
        <Card
            bg={"dark"}
            key={"my-comments-dark"}
            text={'white'}
            className="mb-2 bg-opacity"
        >
            <Card.Body>
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>My Comments</h2></Card.Header>
                <Card.Text style={{paddingLeft: "60px", paddingRight: "60px"}}>
                    <div>

                        {myCommentsPosts.map((posts,  index) => {
                            return(
                                <SinglePost post={posts} comments={myComments} key={index}/>
                            )
                        })}

                    </div>
                </Card.Text>
            </Card.Body>
        </Card>
        </div>

    );
};

export default MyComments;