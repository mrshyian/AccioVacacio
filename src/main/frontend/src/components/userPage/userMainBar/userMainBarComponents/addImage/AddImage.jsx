import React, {useCallback, useEffect, useState} from 'react';
import axios from "axios";
import {useDropzone} from "react-dropzone";


const UserProfiles = () => {

    const [userProfiles, setUserProfile] = useState([]);

    const fetchUserProfiles = () => {
        axios.get("http://localhost:8080/user-profile").then(res =>{
            setUserProfile(res.data);
        });
    };

    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return (
        <div className='post'>
            {userProfiles.id ?
                <img className="myimg" src={`http://localhost:8080/image/download`}
                     alt={"example"}/>
                : null}
            <Dropzone {...userProfiles}/>
            <h1>{userProfiles.username}</h1>
            <p>{userProfiles.id}</p>
        </div>
    )

};

function Dropzone({userProfileId}) {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];

        console.log(acceptedFiles);

        const formData = new FormData();
        formData.append("file", file);


        axios.post(`http://localhost:8080/image/upload`,
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }).then(() => {
            console.log("file upload successfully");
        }).catch(err => {
            console.log(err);
        });

    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p className='after-drag'>Drop the files here ...</p> :
                    <p className='for-drag'>Drag 'n' drop some files here, or click to select files</p>
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