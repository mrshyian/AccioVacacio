import React, {useEffect, useState} from 'react';
import SinglePost from "./singlePost/SinglePost";
import axios from "axios";
import ForumLeftPanel from "../forumleftpanel/ForumLeftPanel";


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
            {sortedPosts.map((post, index) => {
                return(
                <SinglePost post={post} comments={sortedComments} key={index}/>
                )
            })}
        </div>
    );
};

export default FilterComments;