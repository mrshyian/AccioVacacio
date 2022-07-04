import React, {useEffect, useState} from 'react';
import SinglePost from "./singlePost/SinglePost";
import axios from "axios";


const FilterComments = () => {

    const [sort, setSort] = useState([]);

    const fetchSorting = () => {
        axios.get(`http://localhost:8080/sort_by`)
            .then(res =>{setSort(res.data);
                console.log("data "+ res.data)})
            .catch(err => {console.log(err)});
    };

    useEffect(()=>
        fetchSorting()
    ,[])

    return (
        <div>
            {sort.map((post, index)=>
                <SinglePost post={post} key={index}/>
            )}
        </div>
    );
};

export default FilterComments;