import React from 'react';

const SingleLivingCosts = (props) => {
    return (
        <div className="livingCosts">
            <h1 style={{textAlign: "center", color: "coral"}}>Living Costs</h1>
            <div className="wrapper">
                <div className="blog_post">
                    <div className="img_pod">
                        <img
                            src="https://cdn.icon-icons.com/icons2/3456/PNG/512/clipboard_paper_file_correct_done_list_document_icon_219518.png"
                            alt="file"/>
                    </div>
                    <div className="minus-margin">
                    <span className="container_copy">
                        <h1>{props.livingCosts.item_name}</h1>
                    </span>
                        <p>{props.livingCosts.item_name} <br/> {props.livingCosts.average_price} <br/> {props.livingCosts.currency}
                            {/*<br/> {airportDetail.street} <br/> {airportDetail.street_number} <br/> {airportDetail.phone}*/}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
};
export default SingleLivingCosts;