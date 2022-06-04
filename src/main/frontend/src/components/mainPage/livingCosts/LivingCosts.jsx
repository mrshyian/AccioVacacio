import React from 'react';
import SingleLivingCosts from "./SingleLivingCosts";

const LivingCosts = (props) => {
    return (
        <div className="livingCosts">
            <h1 style={{textAlign: "center", color: "coral"}}>Living Costs</h1>
            {props.livingCosts.map((livingCosts, index) => {
                return (
                    <div className="dupa">
                        <div className="back">
                    <p>{livingCosts.itemName}</p>
                    <p>{livingCosts.averagePrice}</p>
                    <p>{livingCosts.cost}</p></div>
                    </div>
                )

            })}
        </div>
    )
};

export default LivingCosts;