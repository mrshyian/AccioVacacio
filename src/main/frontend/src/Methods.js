import React from 'react';
import axios from "axios";

    export async function getResponseFromAxiosGet(url, repeatTimes){
        let resp = {};
        let i = 0;

        do {
            resp = await axios.get(url);
            i++;
        } while (i < repeatTimes || resp.status !== 200)

        return resp;
    }

    export async function postDataToServerByAxiosPost(url, data, repeatTimes){
        let resp = {};
        let i = 0;

        do {
            resp = await axios.post(url, data);
            i++;
        } while (i < repeatTimes || resp.status !== 200)

        alert("You've been sent data to server");
    }

    export async function putDataToServerByAxiosPut(url, data, repeatTimes){
        let resp = {};
        let i = 0;

        do {
            resp = await axios.put(url, data);
            i++;
        } while (i < repeatTimes || resp.status !== 200)

        alert("You've been sent data to server");
    }

    export async function postImageToServerByAxiosPost(url, image, repeatTimes){
        let resp = {};
        let i = 0;

        do {
            resp = await axios.post(url, image,
                {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                })
            i++;
            alert(resp.status)
        } while (i < repeatTimes || resp.status !== 200)

        alert("You've been sent image to server");
    }


