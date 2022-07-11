import React, {useMemo} from 'react';
import { GoogleMap, useLoadScript, Marker } from "@react-google-maps/api";

const MyGoogleMap = () => {
    const { isLoaded } = useLoadScript({
        googleMapsApiKey: "AIzaSyB9QXIKAMjIG5jkZzmZdoWEJNtwuDL4Lsc",
    });

    if (!isLoaded) return <div>Loading...</div>;
    return <Map />;
};



function Map() {
    const center = useMemo(() => ({ lat: 44, lng: -80 }), []);

    return (
        <GoogleMap zoom={10} center={center} mapContainerClassName="map-container">
            <Marker position={center} />
        </GoogleMap>
    );
}

export default MyGoogleMap;