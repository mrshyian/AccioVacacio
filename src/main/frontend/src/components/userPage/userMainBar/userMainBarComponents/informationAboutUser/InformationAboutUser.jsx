import React from 'react';
import {Card} from "react-bootstrap";
import ProfileImage from "../profileImage/ProfileImage";
import SocialMedia from "../socialMedia/SocialMedia";


const InformationAboutUser = () => {
    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2"
            style={{width: "100%"}}
        >
            <Card.Header>
                <table>
                    <tr>
                        <td style={{width: "30%"}}>
                            <ProfileImage/>
                        </td>
                        <td>
                            <h3>User Name</h3>
                        </td>
                    </tr>
                </table>
            </Card.Header>
            <Card.Body>
                <Card.Text>
                    Change the underlying component CSS base class name and modifier class names prefix. This is an escape hatch for working with heavily customized bootstrap css.
                    Change the underlying component CSS base class name and modifier class names prefix. This is an escape hatch for working with heavily customized bootstrap css.
                </Card.Text>
            </Card.Body>
            <Card.Footer>
                <SocialMedia/>
            </Card.Footer>
        </Card>
    );
};

export default InformationAboutUser;