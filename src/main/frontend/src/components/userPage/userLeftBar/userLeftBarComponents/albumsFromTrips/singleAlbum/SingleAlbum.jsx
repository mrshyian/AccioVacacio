import React, {useState} from 'react';
import {Button, Card} from "react-bootstrap";
import "./SingleAlbum.css"
import {FaTimes} from "react-icons/fa";
import DeleteAlbumModal from "../../../../../modals/deleteAlbumModal/DeleteAlbumModal";


const SingleAlbum = (props) => {

    const [modalOpen, setModalOpen] = useState(false);


    return (
        <Card
            bg="dark"
            key={"dark"}
            text={'white'}
            className="album-card"
        >
            <div className="btn-on-image">
                <Button onClick={() => {
                    setModalOpen(true)
                }} variant="dark" className="delete-album-btn">{<FaTimes/>}</Button>
                <div>
                    <Card.Img className="album-image" variant="top" src="https://neapol.pl/wp-content/uploads/2022/03/wybrzeze-sorrento-wlochy_shutterstock_519559345-1.jpg" />
                    <Card.Body>
                        <Card.Title><div>{props.album.albumName}</div><div className="album-date-and-place">{props.album.city}/{props.album.country} - {props.album.tripDate}</div></Card.Title>
                        <Card.Text>
                            {props.album.aboutAlbum}
                        </Card.Text>
                        <Button variant="outline-warning">Go to album</Button>
                    </Card.Body>
                </div>
            </div>
            {modalOpen && <DeleteAlbumModal visible={modalOpen} albumId={props.album.albumId}/>}
        </Card>
    );
};

export default SingleAlbum;