import React, {useEffect, useState} from 'react';
import ForumLeftPanel from "../../forumleftpanel/ForumLeftPanel";
import SinglePost from "../singlePost/SinglePost";
import {Card} from "react-bootstrap";
import {getResponseFromAxiosGet} from "../../../../axios";


const MyComments = () => {
    const [myComments, setMyComments] = useState([]);
    const [myCommentsPosts, setMyCommentsPosts] = useState([]);

    const fetchMyComments = () => {
        getResponseFromAxiosGet(`http://localhost:8080/myComments`, 2).then(res => {
            setMyComments(res.data);
        })
    };


    const fetchMyCommentsPosts = () => {
        getResponseFromAxiosGet(`http://localhost:8080/myCommentsPosts`, 2).then(res => {
            setMyCommentsPosts(res.data);
        })
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