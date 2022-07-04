// import React from 'react';
// import {Card} from "react-bootstrap";
// import SingleComment from "../singleComment/SingleComment";
//
//
// const FavouriteComment = (props) => {
//
//
//     return (
//             <Card
//                 bg="dark"
//                 key={"dark"}
//                 text={'white'}
//
//                 style={{width: '80%', margin: "10px", marginLeft: "auto", marginRight: "auto"}}
//                 className="mb-2"
//             >
//                 <Card.Header style={{justifyContent: "space-between", color: "orange", display: "flex"}}>
//
//                     <p style={{marginBottom: -20}}>
//                         <img className="imgForForum"
//                              src="https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE"
//                              alt="user photo"/>
//                         <p> {props.favouriteComments.userName} </p></p>
//                     <h2 style={{marginTop: "auto", marginBottom: "auto"}}>{props.favouriteComments.topic}</h2>
//                     <p>{props.favouriteComments.postDateTime}</p>
//
//                 </Card.Header>
//                 <Card.Body>
//                     <Card.Text>
//                         <h4>{props.favouriteComments.postText}</h4>
//                     </Card.Text>
//
//                 </Card.Body>
//                 <Card.Footer>
//
//                     <p>Comments:</p>
//                     <div>
//                         {props.favouriteComments.comments.map((comment, index) => {
//
//                             return (
//                                 <SingleComment key={index} comments={comment}/>
//                             )
//                         })}
//                     </div>
//                 </Card.Footer>
//             </Card>
//
//     );
// };
//
// export default FavouriteComment;