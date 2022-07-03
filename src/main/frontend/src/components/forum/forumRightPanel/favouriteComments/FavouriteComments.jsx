import React, {useEffect, useState} from 'react';

import axios from "axios";
import FavouriteComment from "./FavouriteComment";
import ForumLeftPanel from "../../forumleftpanel/ForumLeftPanel";
import MyComment from "../myComments/MyComment";

const FavouriteComments = () => {

    const [favouriteComments, setFavouriteComments] = useState([]);

    const fetchMyComments = () => {
        axios.get(`http://localhost:8080/favouriteComments`)
            .then(res =>{setFavouriteComments(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    useEffect(() => {
        fetchMyComments();
    }, [])

    return (
        <div>
            <ForumLeftPanel/>
            {favouriteComments.map((favouriteComments, index) => {
                return(
                    <FavouriteComment Comments={favouriteComments.comments} favouriteComments={favouriteComments} key={index}/>
                )
            })}

        </div>
    );
};

export default FavouriteComments;