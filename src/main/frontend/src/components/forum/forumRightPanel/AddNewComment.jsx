import React, {useState} from 'react';
import Axios from "axios";
import app from "../../../App";


function AddNewComment() {
    const express = require("express")
    const cors = require("cors")
    require('dotenv').config()
    const axios = require('axios')
    const app = express()
    // const url = "http://localhost:8080/api/Create";

    app.use(
        cors({
            origin: "http://localhost:8080/api/Create"
        })
    )

    app.listen(8080, ()=> console.log('Server Running On Port 8080'))

//     const [name, setName] = useState({
//         name: ""
//     })
//
//     function handle(e){
//         const newName = {...name}
//         newName[e.target.id] = e.target.value
//         setName(newName)
//         console.log(newName)
//     }
//
//     function submit(e){
//         e.preventDefault();
//         app.get(url, (req,res) =>{
//             res.json(name)
//             console.log(res.data)
//         })
//     }
//
//     return (
//         <div>
//             <form onSubmit={(e) => submit(e)}>
//                 <input onChange={(e) => handle(e)} id="name" value={name.name} placeholder="name" type="text"/>
//                 <button type="submit">submit</button>
//             </form>
//         </div>
//     );
}

export default AddNewComment;