import React, {useEffect, useState} from 'react';
import UserLeftBar from "../../../UserLeftBar";
import {Button, Card} from "react-bootstrap";
import {useLocation} from "react-router-dom";
import "./SingleAlbumView.css"
import AddPhotoModal from "../../../../../modals/addPhotoModal/AddPhotoModal";
import {getResponseFromAxiosGet} from "../../../../../../axios";

const SingleAlbumView = () => {
    const [showAddPhotoModal, setShowAddPhotoModal] = useState(false);
    const location = useLocation()
    const album = location.state.album;
    const [photo, setPhoto] = useState([]);
    const getPhotoUrl = `http://localhost:8080/photos`;

    const getPhotosFromDB = () => {
        getResponseFromAxiosGet(getPhotoUrl, 2).then(resp => setPhoto(resp.data));
    };

    useEffect(()=>{
        getPhotosFromDB();
    }, [])

    return (
        <div>
            <UserLeftBar/>
            <Card
                bg="dark"
                text={'white'}
                className="mb-2 right">
                <Card.Header style={{textAlign: "center", color: "orange"}}>
                    <h2>{album.albumName}</h2>
                </Card.Header>
                <Card.Body>
                    <Card.Text>
                        <Card
                            bg="dark"
                            text={'white'}
                        >
                            <Card.Body>
                                <Card.Title>
                                    <div className="view-album-date-and-place">
                                        <div>
                                            {album.city}/{album.country}
                                        </div>
                                        <div>
                                            {album.tripDate}
                                        </div>
                                    </div>
                                    <div className="view-title">{album.aboutAlbum}</div>
                                </Card.Title>
                                <hr/>
                                <Card.Text>
                                    <Button
                                        style={{marginLeft: "85%"}}
                                        variant={"warning"}
                                        onClick={() => setShowAddPhotoModal(true)}
                                    >Add new photo
                                    </Button>
                                    <div className="view-images-box">

                                        {photo.map((photo, index) => {
                                            if(photo.albumFromTripsTable.id === album.albumId){
                                                return (
                                                    <img className="view-image"  src={`http://localhost:8080/image/download/photo/${photo.id}/${album.albumId}`}/>
                                                )
                                            }
                                        })}
                                    </div>
                                </Card.Text>
                            </Card.Body>
                        </Card>

                    </Card.Text>
                </Card.Body>
            </Card>
            {showAddPhotoModal && <AddPhotoModal visible={showAddPhotoModal} albumId={album.albumId} albumName={album.albumName} close={setShowAddPhotoModal}/>}
        </div>
    );
};

export default SingleAlbumView;