// import React, {useState} from 'react';
// import {Button, Card, FormControl, Image, InputGroup} from "react-bootstrap";
// import axios from "axios";
// import SingleComment from "../singleComment/SingleComment";
//
// const FavouriteCommentSingleComment = (props) => {
//     let text = props.comments.commentText;
//     const [commentText, setCommentText] = useState(text)
//     const [editable, setEditable] = useState(false)
//
//     const url = "http://localhost:8080/comment_edit"
//
//     function submit(e){
//         e.preventDefault();
//         axios.put(url, {
//             commentText: commentText,
//             commentId: props.comments.id
//         }).then(r => console.log(r.data))
//         window.location.reload()
//     }
//
//
//     function editText(e) {
//         e.preventDefault();
//         setEditable(true);
//
//     }
//
//     return (
//         <div>
//             <SingleComment/>
//         </div>
//     );
// };
//
// export default FavouriteCommentSingleComment;