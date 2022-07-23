import React, {useState} from 'react';
import {Button, Card} from "react-bootstrap";
import "./SingleAlbum.css"
import {FaTimes} from "react-icons/fa";
import DeleteAlbumModal from "../../../../../modals/deleteAlbumModal/DeleteAlbumModal";
import {useNavigate} from "react-router-dom";


const SingleAlbum = (props) => {
    const navigate = useNavigate();
    const [modalOpen, setModalOpen] = useState(false);
    return (
        <Card
            bg="dark"
            text={'white'}
            className="album-card"
        >
            <div className="btn-on-image">
                <Button onClick={() => {
                    setModalOpen(true)
                }} variant="dark" className="delete-album-btn">{<FaTimes/>}</Button>
                <div>
                    <Card.Img className="album-image" variant="top"
                              src={`http://localhost:8080/image/download/photo/${props.album.albumId}`}/>
                    <Card.Body>
                        <Card.Title>
                            <div>{props.album.albumName}</div>
                            <div
                                className="album-date-and-place">{props.album.city}/{props.album.country} - {props.album.tripDate}</div>
                        </Card.Title>
                        {props.album.aboutAlbum.length < 55 ? (
                            <Card.Text>
                                {props.album.aboutAlbum}
                            </Card.Text>
                        ) : (
                            <Card.Text>
                                {props.album.aboutAlbum.slice(0, 55)}...
                            </Card.Text>
                        )}

                        <Button onClick={() => navigate("/userpage/albums_from_trips/album", {
                            state: {
                                album: props.album,
                            }
                        })}
                                variant="outline-warning">Go to album
                        </Button>
                    </Card.Body>
                </div>
            </div>
            {modalOpen && <DeleteAlbumModal visible={modalOpen} albumId={props.album.albumId} close={setModalOpen}/>}
        </Card>
    );
};

export default SingleAlbum;