import React from 'react';
import {Card} from "react-bootstrap";
import UserLeftBar from "../../UserLeftBar";
import SingleAlbum from "./singleAlbum/SingleAlbum";
import "./AlbumsFromTrips.css"

const AlbumsFromTrips = () => {
    return (
        <div>
            <UserLeftBar/>
            <Card
                bg="dark"
                key={"dark"}
                text={'white'}
                className="mb-2 right">
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Photo albums</h2></Card.Header>
                <Card.Body>
                    <Card.Text className="albums-box">
                        <SingleAlbum
                            city="Rome"
                            country="Italy"
                            tripDate="11/06/2020"
                            albumName="My trip to Rome"
                            tripDescription="It's was an amazing trip"
                            urlForImage="https://cdn.theculturetrip.com/wp-content/uploads/2015/11/eur_italy_rome.JPG"
                        />
                        <SingleAlbum
                            city="Barcelona"
                            country="Spain"
                            tripDate="31/10/2019"
                            albumName="My trip to Barcelona"
                            tripDescription="Barcelona is beautiful!!"
                            urlForImage="https://www.cunard.com/content/dam/cunard/inventory-assets/ports/BCN/BCN.jpg.1614954820779.image.750.563.low.jpg"
                        />
                        <SingleAlbum
                            city="Warsaw"
                            country="Poland"
                            tripDate="21/11/2014"
                            albumName="My trip to Warsaw"
                            tripDescription="Unforgettable trip"
                            urlForImage="https://www.polska.travel/images/pl-PL/glowne-miasta/warszawa/warszawa_plac_zamkowy_1170.jpg"
                        />
                        <SingleAlbum
                            city="Paris"
                            country="France"
                            tripDate="3/02/2016"
                            albumName="My trip to Paris"
                            tripDescription="I want to live here now."
                            urlForImage="https://cdn.sortiraparis.com/images/80/83517/753564-visuel-paris-tour-eiffel-rue.jpg"
                        />
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    );
};

export default AlbumsFromTrips;