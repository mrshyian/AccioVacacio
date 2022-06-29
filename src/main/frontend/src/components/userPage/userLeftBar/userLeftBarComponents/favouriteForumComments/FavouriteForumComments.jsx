import React from 'react';
import {Card} from "react-bootstrap";
import UserLeftBar from "../../UserLeftBar";

const FavouriteForumComments = () => {
    return (
        <div>
            <UserLeftBar/>
            <Card
                bg="dark"
                key={"dark"}
                text={'white'}
                className="mb-2 right">
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Favourite comments</h2></Card.Header>
                <Card.Body>
                    <Card.Text>
                        ULUBIONE KOMENTARZE
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    );
};

export default FavouriteForumComments;