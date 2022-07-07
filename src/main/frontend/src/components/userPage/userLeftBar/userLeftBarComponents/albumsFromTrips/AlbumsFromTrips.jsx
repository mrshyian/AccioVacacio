import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import UserLeftBar from "../../UserLeftBar";
import "./AlbumsFromTrips.css"
import AddNewAlbumModal from "../../../../modals/addNewAlbumModal/AddNewAlbumModal";
import axios from "axios";
import SingleAlbum from "./singleAlbum/SingleAlbum";
import MustBeLogIn from "../../../../mustBeLogIn/MustBeLogIn";

const AlbumsFromTrips = () => {

    const [modalOpen, setModalOpen] = useState(false);

    const [albums, setAlbums] = useState([]);

    const getAlbumsFromDB = () => {
        axios.get(`http://localhost:8080/albumsfromtrips`)
            .then(res => {
                setAlbums(res.data);
            })
            .catch(err => {
                console.log(err)
            });
    };

    useEffect(() => {
        if (sessionStorage.getItem("userId") !== null) {
            getAlbumsFromDB();
        }
    }, [])

    if (sessionStorage.getItem("userId") !== null) {
        return (
            <div>
                <UserLeftBar/>
                <Card
                    bg="dark"
                    key={"dark"}
                    text={'white'}
                    className="mb-2 right">
                    <Card.Header style={{textAlign: "center", color: "orange"}}>
                        <h2>Photo albums</h2>
                        <Button
                            style={{marginLeft: "80%"}}
                            variant={"warning"}
                            onClick={() => {
                                setModalOpen(true)
                            }}>Add new album</Button>
                    </Card.Header>
                    <Card.Body>
                        <Card.Text >
                            <div className="albums-box">
                                {albums.map((album, index) => {
                                    return (
                                        <SingleAlbum
                                            album={album}
                                            key={index}
                                        />
                                    )
                                })}
                            </div>
                        </Card.Text>
                    </Card.Body>
                </Card>
                {modalOpen && <AddNewAlbumModal visible={modalOpen} close={setModalOpen}/>}
            </div>
        );
    } else {
        return <MustBeLogIn/>;
    }


};

export default AlbumsFromTrips;