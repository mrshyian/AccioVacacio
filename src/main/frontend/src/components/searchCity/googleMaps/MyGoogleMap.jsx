import React, {useEffect, useMemo, useState} from 'react';
import { GoogleMap, useLoadScript, Marker } from "@react-google-maps/api";
import axios from "axios";

const MyGoogleMap = (props) => {

    const { isLoaded } = useLoadScript({
        googleMapsApiKey: "AIzaSyB9QXIKAMjIG5jkZzmZdoWEJNtwuDL4Lsc",
    });

    const [latitude, setLatitude] = useState(52.229)
    const [longitude, setLongitude] = useState(21.0118)

    useEffect(() => {
        getCoordinates();
    }, []);

    const getCoordinates = () => {
        axios.get(`http://localhost:8080/get_coordinates/${props.city}`)
            .then(res =>{
                setLatitude(res.data.lat);
                setLongitude(res.data.lon);
            })
            .catch(err => {console.log(err)});
    }
    if (!isLoaded) return <div>Loading...</div>;
    return <Map city={props.city} lon={longitude} lat={latitude} />;

};



function Map(props) {
    const center = useMemo(() => ({ lat: props.lat, lng: props.lon }), []);

    return (
        <div className="newsInline1" style={{width: 500}}>
            <div style={{display: "flex"}}>
                <div style={{width: 500}}>
                    <GoogleMap zoom={12} center={center} mapContainerClassName="map-container">
                        <div style={{height: "500px"}}>
                            <Marker
                                title={'The city center'}
                                key={1}
                                position={center}
                            />
                        </div>
                    </GoogleMap>
                </div>
            </div>
        </div>
    );
}

export default MyGoogleMap;