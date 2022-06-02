import React from 'react';
import "./ErrorModal.css"

const ErrorModal = ({ error, setErrorModalOpen }) => {
    return (
        <div className="errorModalBackground">
            <div className="errorModalContainer">
                <div className="errorTitle">
                    <h1>ERROR</h1>
                </div>
                <div className="errorBody">
                    <h4>
                        {error}
                    </h4>
                </div>
                <div className="errorFooter">
                    <button onClick={() => {setErrorModalOpen(false);}}>OK</button>
                </div>
            </div>
        </div>
    );
};

export default ErrorModal;