import React from 'react';
import NewsBox from "./newsBox/NewsBox";


// PRZYKŁAD! ---------------------------------------------------------
const news = [
    {
        "NewsTitle": "Pierwszy tytul",
        "NewsText": "Pierwsza wiadomość",
        "NewsLink": "https://www.youtube.com/watch?v=3rzgrP7VA_Q"
    },
    {
        "NewsTitle": "Drugi tytul",
        "NewsText": "Druga wiadomość",
        "NewsLink": "https://www.youtube.com/watch?v=3rzgrP7VA_Q"
    },
    {
        "NewsTitle": "Trzeci tytul",
        "NewsText": "Trzecia wiadomość",
        "NewsLink": "https://www.youtube.com/watch?v=3rzgrP7VA_Q"
    }
]
// PRZYKŁAD! ---------------------------------------------------------

const MainPage = () => {
    return (
        <div>
            <NewsBox news={news}/>
        </div>
    );
};

export default MainPage;