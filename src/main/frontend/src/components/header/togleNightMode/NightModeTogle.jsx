import React from 'react';
import "./NightModelTogle.css"

const NightModeTogle = () => {
    return (
        <div>
            <input type="checkbox" id="toggle" className="toggle--checkbox"/>
                <label htmlFor="toggle" className="toggle--label">
                    <span className="toggle--label-background"></span>
                </label>
                <div className="background"></div>
        </div>
    );
};

export default NightModeTogle;