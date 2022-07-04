import React, {useEffect, useState} from 'react';
import axios from "axios";
import MyComment from "./MyComment";
import ForumLeftPanel from "../../forumleftpanel/ForumLeftPanel";


const MyComments = () => {
    const [myComments, setMyComments] = useState([]);

    const fetchMyComments = () => {
        axios.get(`http://localhost:8080/myComments`)
            .then(res =>{setMyComments(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    useEffect(() => {
        fetchMyComments();
    }, [])


    return (
        <div>
            <ForumLeftPanel/>
            {myComments.map((comment, index) => {
                return(
                <MyComment myComments={comment} key={index}/>
                )
            })}

        </div>
    );
};

export default MyComments;