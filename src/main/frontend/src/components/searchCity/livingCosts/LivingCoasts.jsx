import React from 'react';
import "./livingCosts.css"

const LivingCosts = (props) => {
    return (
        <div className="living-costs">
            <h1 style={{textAlign: "center", color: "coral"}}>Living Costs</h1>
            <div style={{display: "flex"}}>
                <div className="dupa-for-living-cost">
                    <div className="back-for-living-cost">
                        {props.livingCosts.slice(0, 8).map((livingCosts, index) => {
                            return (
                                <div key={index} className="space-between">
                                    <div>{livingCosts.itemName}</div>
                                    <div>{livingCosts.averagePrice} {livingCosts.cost}</div>
                                </div>
                            )
                        })}

                    </div>
                </div>
                <div className="dupa-for-living-cost">
                    <div className="back-for-living-cost">
                        {props.livingCosts.slice(8, 16).map((livingCosts, index) => {
                            return (
                                <div key={index} className="space-between">
                                    <div>{livingCosts.itemName}</div>
                                    <div>{livingCosts.averagePrice} {livingCosts.cost}</div>
                                </div>
                            )
                        })}
                    </div>
                </div>
            </div>
        </div>
    )
};

export default LivingCosts;