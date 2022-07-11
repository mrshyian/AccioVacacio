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
    const center = useMemo(() => ({ lat: 52.409538, lng: 16.931992 }), []);

    return (
        <div style={{width: "100%"}}>
            <GoogleMap zoom={12} center={center} mapContainerClassName="map-container">
                <div style={{height: "839px"}}>
                <Marker
                    title={'The marker`s title will appear as a tooltip.'}
                    name={'SOMA'}
                    key={1}
                    position={center}
                />
                </div>
            </GoogleMap>
        </div>
    );
}

export default MyGoogleMap;