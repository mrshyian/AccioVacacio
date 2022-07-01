import React from "react";
import {Button, Card, Image} from "react-bootstrap";
import "./SingleComment.css"
import {FaHeart} from "react-icons/fa";

const SingleComment = (props) => {
    return (
        <Card
            key={"dark"}
            text={'white'}
            className="mb-2 comment-card">
            <Card.Header className="comment-card-header">
                <div>
                    <Image fluid="true" className="user-avatar-for-comment" src={props.comments.commentImage}
                           alt="user photo"/>
                    <div>Sebastian Ryndak</div>
                </div>
                <div>{props.comments.commentDateTime}</div>
            </Card.Header>
            <Card.Body className="comment-card-body">
                <Card.Text>
                    <div className="comment-content-div">
                        <Image rounded="true" fluid="true" className="addImage"
                               src="https://cdn.icon-icons.com/icons2/2184/PNG/512/healthy_strength_strong_health_heart_icon_133538.png"
                               alt=""
                        />
                        <div className="comment-text">{props.comments.commentText}</div>
                    </div>
                </Card.Text>
            </Card.Body>
            <Card.Footer className="comment-footer">
                <div style={{textAlign: "right"}}>
                    <Button variant="outline-warning">{<FaHeart/>}</Button>
                </div>
            </Card.Footer>
        </Card>
    );
};

export default SingleComment;