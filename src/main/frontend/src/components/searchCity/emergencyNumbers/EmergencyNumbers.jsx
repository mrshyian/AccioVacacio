import React from 'react';
import {Card} from "react-bootstrap";
import "./EmergencyNumbers.css"
import police from "../../../images/police.png"
import aid from "../../../images/aid.png"
import fireguard from "../../../images/fireguard.png"
import dispatch from "../../../images/dispatch.png"

const EmergencyNumbers = (props) => {
    if (props.emergencyNumber.dispatch !== ""){
        return (
            <Card
                bg={"dark"}
                key={"emergency-numbers-dark"}
                text={'white'}
                style={{ width: '35rem', borderColor: "orange" }}
                className="mb-2"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Emergency Number</h2></Card.Header>
                <Card.Body>
                    <Card.Text>
                        <table>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src={dispatch}
                                         alt="random image"/>
                                </td>
                                <td>
                                    <h2>     Dispatch: {props.emergencyNumber.dispatch}</h2>
                                </td>
                            </tr>
                        </table>
                    </Card.Text>
                </Card.Body>
            </Card>
        );
    } else {
        return (
            <Card
                bg={"dark"}
                key={"emergency-numbers-dark-key"}
                text={'white'}
                style={{ width: '35rem', borderColor: "orange" }}
                className="mb-2"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Emergency Number</h2></Card.Header>
                <Card.Body>
                    <Card.Text style={{marginLeft: "20%"}}>
                        <table>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src={fireguard}
                                         alt="random image"/>
                                </td>
                                <td>
                                    <h2>     Fire Guard: {props.emergencyNumber.fireGuard}</h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src={police}
                                         alt="random image"/>
                                </td>
                                <td>
                                    <h2>     Police: {props.emergencyNumber.police}</h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src={aid}
                                         alt="random image"/>
                                </td>
                                <td>
                                    <h2>     Ambulance: {props.emergencyNumber.ambulance}</h2>
                                </td>
                            </tr>
                        </table>
                    </Card.Text>
                </Card.Body>
            </Card>
        );
    }

};

export default EmergencyNumbers;