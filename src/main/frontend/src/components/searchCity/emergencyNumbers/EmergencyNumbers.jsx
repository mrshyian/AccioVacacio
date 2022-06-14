import React from 'react';
import "./EmergencyNumbers.css"
import {Card} from "react-bootstrap";

const EmergencyNumbers = (props) => {
    if (props.emergencyNumber.dispatch !== ""){
        return (
            <Card
                bg={"dark"}
                key={"dark"}
                text={'white'}
                style={{ width: '35rem' }}
                className="mb-2"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Emergency Number</h2></Card.Header>
                <Card.Body>
                    <Card.Text>
                        <table>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src="https://cdn.icon-icons.com/icons2/2276/PNG/512/covid_corona_protect_ambulance_hospital_emergency_call_medical_icon_140797.png"
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
                key={"dark"}
                text={'white'}
                style={{ width: '35rem' }}
                className="mb-2"
            >
                <Card.Header style={{textAlign: "center", color: "orange"}}><h2>Emergency Number</h2></Card.Header>
                <Card.Body>
                    <Card.Text>
                        <table>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src="https://cdn.icon-icons.com/icons2/577/PNG/256/FireTruck_Red_icon-icons.com_54899.png"
                                         alt="random image"/>
                                </td>
                                <td>
                                    <h2>     Fire Guard: {props.emergencyNumber.fireGuard}</h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src="https://cdn.icon-icons.com/icons2/1448/PNG/512/42548oncomingpolicecar_99125.png"
                                         alt="random image"/>
                                </td>
                                <td>
                                    <h2>     Police: {props.emergencyNumber.police}</h2>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img className="img-for-emergency-number"
                                         src="https://cdn.icon-icons.com/icons2/577/PNG/256/Ambulance_Red_icon-icons.com_54900.png"
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