import React from 'react';

const SearchingPlaceBar = (props) => {
    return (
        <div className="newsInline2">
            <h1>{props.country} / {props.city}</h1>
        </div>
    );
};

export default SearchingPlaceBar;