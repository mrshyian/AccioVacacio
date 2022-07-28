import React, {useEffect, useState} from 'react';
import UserLeftBar from "../../../UserLeftBar";
import {Button, Card} from "react-bootstrap";
import {useLocation} from "react-router-dom";
import "./SingleAlbumView.css"
import AddPhotoModal from "../../../../../modals/addPhotoModal/AddPhotoModal";
import axios from "axios";

const SingleAlbumView = () => {
    const [showAddPhotoModal, setShowAddPhotoModal] = useState(false);
    const location = useLocation()
    const album = location.state.album;


    const [photo, setPhoto] = useState([]);
    const getPhotosFromDB = () => {
        axios.get(`http://localhost:8080/photos`,
            {headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res => {
                console.log(res.data)
                setPhoto(res.data);
            })
            .catch(err => {
                console.log(err)
            });
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

                                            {/*<img className="view-image"  src={`http://localhost:8080/image/download/photo/${photoId}`}/>*/}

                                            {/*<img className="view-image"  src="https://i.gifer.com/BgY2.gif"/>*/}

                                            {/*<img className="view-image"  src="https://25.media.tumblr.com/4dcf710acadfbe90ce2c619f9e1559c0/tumblr_mstadgMyLS1st90nio1_400.gif"/>*/}

                                            {/*<img className="view-image"  src="https://i.gifer.com/CJqO.gif"/>*/}

                                            {/*<img className="view-image" src="https://www.wykop.pl/cdn/c3201142/comment_YntKj2x0Wi364cmRAcy6FVppJD9uZT18.gif"/>*/}

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