import React from 'react';
import {Carousel} from 'react-bootstrap';

const AllCarousel = () => {
    return (
        <Carousel >
            <Carousel.Item style={{height: "400px"}}>
                <img
                    className="d-block w-100"
                    src="https://images.unsplash.com/photo-1513026705753-bc3fffca8bf4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
                    alt="First slide"
                />
                <Carousel.Caption>
                    <h3 style={{color: "orange", background: "rgba(0, 0, 0, 0.5)"}}>Are you a traveler?</h3>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item style={{height: "400px"}}>
                <img
                    className="d-block w-100"
                    src="https://images.unsplash.com/photo-1591289009723-aef0a1a8a211?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80"
                    alt="Second slide"
                />

                <Carousel.Caption>
                    <h3 style={{color: "orange", background: "rgba(0, 0, 0, 0.5)"}}>Looking for a travel app?</h3>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item style={{height: "400px"}}>
                <img
                    className="d-block w-100"
                    src="https://images.unsplash.com/photo-1575460519308-b221d02bc008?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1548&q=80"
                    alt="Third slide"
                />

                <Carousel.Caption>
                    <h3 style={{color: "orange", background: "rgba(0, 0, 0, 0.5)"}}>Congratulations! You found it!</h3>
                </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item style={{height: "400px"}}>
                <img
                    className="d-block w-100"
                    src="https://images.unsplash.com/photo-1456428199391-a3b1cb5e93ab?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1932&q=80"
                    alt="Third slide"
                />

                <Carousel.Caption>
                    <h3 style={{color: "orange", background: "rgba(0, 0, 0, 0.5)"}}>Click on "Search City" and begin your trip.</h3>
                </Carousel.Caption>
            </Carousel.Item>
        </Carousel>
    );
};

export default AllCarousel;