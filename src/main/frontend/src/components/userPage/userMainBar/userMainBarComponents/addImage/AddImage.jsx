import React, {useCallback, useEffect, useState} from 'react';
import axios from "axios";
import {useDropzone} from "react-dropzone";


const UserProfiles = () => {

    const [userProfiles, setUserProfile] = useState([]);

    const fetchUserProfiles = () => {
        axios.get("http://localhost:8080/user-profile",
            {headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res =>{
            setUserProfile(res.data);
        });
    };

    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return (
        <div className='post'>
            <Dropzone {...userProfiles}/>
            <h1>{userProfiles.username}</h1>
            <p>{userProfiles.id}</p>
        </div>
    )

};

function Dropzone({userProfileId}) {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];

        const formData = new FormData();
        formData.append("file", file);


        axios.post(`http://localhost:8080/image/upload`,
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data",
                    "Authorization": `Bearer ${sessionStorage.getItem("token")}`
                }
            }).then(() => {
            console.log("file upload successfully");
        }).catch(err => {
            console.log(err);
        });

    }, [])
    const {getRootProps, getInputProps} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                <div>
                <p>
                    Drag n drop image here
                </p>
                </div>
            }
        </div>
    )
}


const AddImage = () => {
    return (
            <UserProfiles/>
    );
};

export default AddImage;