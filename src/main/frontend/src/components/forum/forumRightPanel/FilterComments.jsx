import React, {useEffect, useState} from 'react';
import SinglePost from "./singlePost/SinglePost";
import axios from "axios";
import ForumLeftPanel from "../forumleftpanel/ForumLeftPanel";
import {Card} from "react-bootstrap";


const FilterComments = () => {

    const [sortedPosts, setSortedPosts] = useState([]);
    const [sortedComments, setSortedComments] = useState([]);

    const fetchSortedPosts = () => {
        axios.get(`http://localhost:8080/get_sorted_posts`)
            .then(res =>{setSortedPosts(res.data);})
            .catch(err => {console.log(err)});
    };

    const fetchSortedComments = () => {
        axios.get(`http://localhost:8080/get_sorted_comments`)
            .then(res =>{setSortedComments(res.data);})
            .catch(err => {console.log(err)});
    };

    useEffect(()=>{
        fetchSortedPosts();
        fetchSortedComments();
        },[])

    return (
        <div>
            <ForumLeftPanel/>
            <Card
                bg={"dark"}
                key={"filter-comments-dark"}
                text={'white'}
                className="mb-2 bg-opacity"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Sorted posts</h2></Card.Header>
                <Card.Body>
                    <Card.Text style={{paddingLeft: "60px", paddingRight: "60px"}}>
                        <div>
                            {sortedPosts.map((post, index) => {
                                return(
                                    <SinglePost post={post} comments={sortedComments} key={index}/>
                                )
                            })}
                        </div>
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    );
};

export default FilterComments;