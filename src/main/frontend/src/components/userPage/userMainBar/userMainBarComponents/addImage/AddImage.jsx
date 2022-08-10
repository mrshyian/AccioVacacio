import React, {useCallback, useEffect, useState} from 'react';
import {useDropzone} from "react-dropzone";
import {getResponseFromAxiosGet, postDataToServerByAxiosPost} from "../../../../../axios";

const uploadImageUrl = `http://localhost:8080/image/upload`;

const UserProfiles = () => {

    const [userProfiles, setUserProfile] = useState([]);
    const userProfileUrl = "http://localhost:8080/user-profile";


    const fetchUserProfiles = () => {
        getResponseFromAxiosGet(userProfileUrl, 2).then(res => setUserProfile(res.data));
    };

    useEffect(() => {
        fetchUserProfiles();
    }, []);

    return (
        <div className='post'>
            <br/>
            <Dropzone {...userProfiles}/>
            <br/>
        </div>
    )

};

function Dropzone({userProfileId}) {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];

        const formData = new FormData();
        formData.append("file", file);

        postDataToServerByAxiosPost(uploadImageUrl, formData, 0).then(() => console.log("file upload successfully"))

    }, [])
    const {getRootProps, getInputProps} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                <div>
                    Drag n drop image here
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