import React from 'react';
import {Card, Image} from "react-bootstrap";

const FavouriteComment = (props) => {
    return (
        <div>
            <Card
                key={"dark"}
                text={'white'}
                style={{maxWidth: '90%', margin: "10px", marginLeft: "2.5%"}}
                className="mb-2 ">
                <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex", backgroundColor: "rgb(35, 35, 35)"}}>

                    <p style={{ marginBottom: -30 }}>
                        <Image fluid="true" className="imgForPost" src={props.favouriteComments.commentImage} alt="user photo"/>
                        <p>{props.favouriteComments.userName}</p></p>
                    <p>{props.favouriteComments.commentDateTime}</p>
                    {/*<p>{props.user.role}</p>*/}
                </Card.Header>
                <Card.Body style={{ backgroundColor: "rgb(55, 55, 55)"}}>
                    <Card.Text style={{color: "white"}}>
                        {props.favouriteComments.commentText}

                        <p><Image rounded="true" fluid="true" className="addImage" src="https://cdn.icon-icons.com/icons2/2184/PNG/512/healthy_strength_strong_health_heart_icon_133538.png" alt=""/></p>
                    </Card.Text>
                </Card.Body>
                <Card.Footer style={{ backgroundColor: "rgb(45, 45, 45)"}}>
                </Card.Footer>
            </Card>
        </div>
    );
};

export default FavouriteComment;