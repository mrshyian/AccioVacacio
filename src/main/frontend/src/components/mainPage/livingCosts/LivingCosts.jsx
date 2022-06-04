import React from 'react';
import SingleLivingCosts from "./SingleLivingCosts";

const LivingCosts = (props) => {
    return (
        // <div className="livingCosts">
        //     <img src="https://cdn.icon-icons.com/icons2/3456/PNG/512/clipboard_paper_file_correct_done_list_document_icon_219518.png" alt="file"/>
        //         <h1 >{livingCosts.itemName}</h1>
        // </div>
        <div className="livingCosts">
            <h1 style={{textAlign: "center", color: "coral"}}>Living Costs</h1>
            {props.livingCosts.map((livingCosts, index) => {
                return (
                    <SingleLivingCosts key={index} livingCosts={livingCosts} />
                )

            })}
        </div>
    );
};
export default LivingCosts;