import React, {useCallback, useEffect, useState} from 'react';
import Axios from "axios";
import axios from "axios";
import "./AddNewComment.css"
import {Button, Card} from "react-bootstrap";
import {useDropzone} from "react-dropzone";
import {getResponseFromAxiosGet, postDataToServerByAxiosPost, postImageToServerByAxiosPost} from "../../../../Methods";

function AddNewComment(props) {

    const url = "http://localhost:8080/comments"
    const [image, setImage] = useState(false);
    const [imageToComment, setImageToComment] = useState(new FormData());
    const [isDisabled, setIsDisabled] = useState(true);
    const [name, setName] = useState("")

    function handle(e) {
        if (e.target.id === "name"){
            setName(e.target.value);
        }
    }

    useEffect(() => {
        name !== "" ? setIsDisabled(false): setIsDisabled(true);
    }, [name]);


    function refreshPage() {
        window.location.reload();
    }

    async function submit(e) {
        e.preventDefault();
        const dataa =
            {
                name: name,
                postId: props.postId
            }

        console.log(dataa)
        {
            !image ?
                await postDataToServerByAxiosPost(url, dataa, 2).then(() => console.log(dataa))
                // Axios.post(url, {
                //         data
                //     })
                //     .then(() => refreshPage())
                :
                await postImageToServerByAxiosPost(`http://localhost:8080/image/upload/comment/${name}/${props.postId}`,
                    imageToComment, 2).then(() => console.log(dataa));
        }
        refreshPage();
    }

    function Dropzone() {
        const onDrop = useCallback(acceptedFiles => {
            const file = acceptedFiles[0];
            setImage(true);

            console.log(acceptedFiles);

            const formData = new FormData();
            formData.append("file", file);
            setImageToComment(formData);

        }, []);
        const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
        return (
            <div {...getRootProps()}>
                {
                    !image ?
                        <div>
                            <input {...getInputProps()} />
                            {
                                isDragActive ?
                                    <p style={{width: "600px ", height: "200px"}} className='after-drag'>Drop the files
                                        here ...</p> :

                                    <p style={{maxWidth: "45% "}} className='for-drag'>Drag 'n' drop some files here, or
                                        click to select files</p>
                            }
                        </div>
                        :
                        <div>
                            <Card bg="success"
                                  key={"add-new-comment-dark"}
                                  text={'warning'}
                                  style={{marginRight: "90%"}}
                            >
                                <Card.Text>
                                    Photo added
                                </Card.Text>
                            </Card>
                        </div>
                }
            </div>
        )
    }

    return (
        <div>
            <form onSubmit={(e) => submit(e)}>
                <Card
                    key={"add-new-comment-dark-key"}
                    text={'white'}
                    className="mb-2 add-comment-card">
                    <Card.Body className="add-comment-card-body">
                        <Card.Text>
                            <textarea className="add-comment-textarea" onChange={(e) => handle(e)} id="name"
                                      value={name.name}
                                      placeholder="Comment text" type="text"/>
                        </Card.Text>
                        <div style={{textAlign: "right"}}>
                            <div>
                                <Dropzone/>
                            </div>
                            <Button disabled={isDisabled} variant="outline-warning" type="submit">Add comment</Button>
                        </div>
                    </Card.Body>
                </Card>
            </form>
        </div>
    );
}

export default AddNewComment;