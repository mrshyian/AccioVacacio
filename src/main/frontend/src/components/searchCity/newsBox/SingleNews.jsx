import React from 'react';
import {Button, Card} from "react-bootstrap";

const SingleNews = (props) => {

    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            style={{ width: '100%' }}
            className="mb-2"
        >
            <Card.Header style={{textAlign: "center", color: "orange"}}>
                <table>
                    <tr>
                        <td>
                            <img  src="https://cdn.icon-icons.com/icons2/203/PNG/128/diagram-01_24516.png" alt="random image"/>
                        </td>
                        <td>
                            <h2>{props.news.title}</h2>
                        </td>
                    </tr>
                </table>
            </Card.Header>
            <Card.Body>
                <Card.Text>
                    {props.news.summary}
                </Card.Text>
                    <Button variant={"warning"} href={props.news.link}>Read more</Button>
            </Card.Body>
        </Card>
    );
};

export default SingleNews;