import React, {useState} from 'react';
import Axios from "axios";
import app from "../../../App";
import {logDOM} from "@testing-library/react";
import {availiablePages} from "../../../types";
import {Link} from "react-router-dom";


function AddNewComment(props) {

    const url = "http://localhost:8080/comments"
    const [name, setName] = useState({
        name: ""
    })

    function handle(e){
        const newName = {...name}
        newName[e.target.id] = e.target.value
        setName(newName)
        console.log(newName)
    }

    function submit(e){
        refreshPage();
        e.preventDefault();
        Axios.post(url, {
            name: name.name
        }).then(r => console.log(r.data))
    }

    function refreshPage(){
        console.log("działa")
        window.location.reload();
    }

    return (
        <div>
            <form onSubmit={(e) => submit(e)}>
                <input onChange={(e) => handle(e)} id="name" value={name.name} placeholder="name" type="text"/>
                <button  type="submit">submit</button>
            </form>
        </div>
    );
}

export default AddNewComment;