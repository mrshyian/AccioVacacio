import React from 'react';
import "./livingCosts.css"
import { Card} from "react-bootstrap";

const LivingCosts = (props) => {
    return (
        <div className="living-costs">
            <h1 style={{textAlign: "center", color: "orange"}}>Living Costs</h1>
            <div style={{display: "flex"}}>
                <Card
                    bg={"dark"}
                    key={"dark"}
                    text={'white'}
                    style={{width: '100%', margin: "5px", borderColor: "orange" }}
                    className="mb-2"
                >
                    <Card.Body>
                        <Card.Text>
                            {props.livingCosts.slice(0, 8).map((livingCosts, index) => {
                                return (
                                    <div key={index} className="space-between">
                                        <div>{livingCosts.itemName}</div>
                                        <div>{livingCosts.averagePrice} {livingCosts.cost}</div>
                                    </div>
                                )
                            })}
                        </Card.Text>
                    </Card.Body>
                </Card>

                <Card
                    bg={"dark"}
                    key={"dark"}
                    text={'white'}
                    style={{width: '100%', margin: "5px", borderColor: "orange" }}
                    className="mb-2"
                >
                    <Card.Body>
                        <Card.Text>
                            {props.livingCosts.slice(8, 16).map((livingCosts, index) => {
                                return (
                                    <div key={index} className="space-between">
                                        <div>{livingCosts.itemName}</div>
                                        <div>{livingCosts.averagePrice} {livingCosts.cost}</div>
                                    </div>
                                )
                            })}
                        </Card.Text>
                    </Card.Body>
                </Card>
            </div>
        </div>
    )
};

export default LivingCosts;