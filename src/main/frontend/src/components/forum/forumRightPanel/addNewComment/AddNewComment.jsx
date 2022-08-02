import React, {useCallback, useState} from 'react';
import Axios from "axios";
import axios from "axios";
import "./AddNewComment.css"
import {Button, Card} from "react-bootstrap";
import {useDropzone} from "react-dropzone";


function AddNewComment(props) {

    const url = "http://localhost:8080/comments"
    const [image, setImage] = useState(false);
    const [dataa, setDataa] = useState(new FormData());
    const [name, setName] = useState({
        name: ""
    })

    function handle(e) {
        const newName = {...name}
        newName[e.target.id] = e.target.value
        setName(newName)

    }




    function submit(e) {
        e.preventDefault();
        console.log(dataa)

        {
            !image ?
                Axios.post(url, {
                        name: name.name,
                        postId: props.postId

                    },
                    {headers:
                            {"Authorization": `Bearer ${sessionStorage.getItem("token")}`,
                                'X-XSRF-TOKEN': props.tokenCsrf}})
                    .then()
                :
                axios.post(`http://localhost:8080/image/upload/comment/${name.name}/${props.postId}`,
                    dataa,
                    {
                        headers: {
                            "Content-Type": "multipart/form-data",
                            "Authorization": `Bearer ${sessionStorage.getItem("token")}`,
                            'X-XSRF-TOKEN': props.tokenCsrf}})
                    .then(() => {

                }).catch(err => {
                    console.log(err);
                });
        }
    }

    function Dropzone() {
        const onDrop = useCallback(acceptedFiles => {
            const file = acceptedFiles[0];
            setImage(true);

            console.log(acceptedFiles);

            const formData = new FormData();
            formData.append("file", file);
            setDataa(formData);

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
                                  key={"dark"}
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
                    key={"dark"}
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
                            <Button  variant="outline-warning" type="submit">Add comment</Button>
                        </div>
                    </Card.Body>
                </Card>
            </form>
        </div>
    );
}

export default AddNewComment;