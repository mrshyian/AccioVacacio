import React, {useState, useEffect} from 'react';
import {Button, Card} from "react-bootstrap";
import SocialMedia from "../socialMedia/SocialMedia";
import "./ProfileImage.css"
import "./InformationAboutUser.css"
import EditUserDataModal from "../../../../modals/editUserDataModal/EditUserDataModal";
import user from "../../../../../images/user.png"
import CountryCounter from "../countryCounter/CountryCounter";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import userImage from "../../../../../images/user.png"
import {FaComments, FaTrashAlt, FaUserPlus} from "react-icons/fa";

const InformationAboutUser = (props) => {
    const navigate = useNavigate();
    const [modalOpen, setModalOpen] = useState(false);
    const [allFriends, setAllFriends] = useState([]);
    const [myId, setMyId] = useState("");
    const [friendsList, setFriendsList] = useState([]);
    const [boolka, setBoolka] = useState(false);

    useEffect(() => {
        searchAllFriends();
    }, [props])

    const addFriend = () => {
        axios.get(`http://localhost:8080/add_friend/${props.myUser.id}`, { headers: {"Authorization" : `Bearer ${sessionStorage.getItem("token")}`}})
            .then(() => window.location.reload())
            .catch(err => {
                console.log(err)
            });
    };

    const removeFriend = () => {
        axios.get(`http://localhost:8080/remove_friend/${props.myUser.id}`, { headers: {"Authorization" : `Bearer ${sessionStorage.getItem("token")}`}})
            .then(() => window.location.reload())
            .catch(err => {
                console.log(err)
            });
    };

    const searchAllFriends = () => {
        axios.get(`http://localhost:8080/search_friend/id/${props.myUser.id}`, { headers: {"Authorization" : `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res => {
                setAllFriends(res.data);
                setMyId(props.myUser.id.toString());
                friendOrNot();
            })
            .catch(err => {
                console.log(err)
            });
    };

    const sayHello = () => {
        axios.get(`http://localhost:8080/mail_to_friend/${props.myUser.id}`, { headers: {"Authorization" : `Bearer ${sessionStorage.getItem("token")}`}})
            .then(() => {
                navigate("/userpage/friends/mail_box", {
                    state: {
                        friend: props.myUser
                    }})})
            .catch(err => {
                console.log(err)
            });
    }

    const friendOrNot = () => {

        axios.get(`http://localhost:8080/search_friend/id/${sessionStorage.getItem("userId")}`, { headers: {"Authorization" : `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res => {
                let friendsList = res.data;
                friendsList.map(friend => {
                    if(friend.id===props.myUser.id){
                        setBoolka(true);
                    }
                })
            })
            .catch(err => {
                console.log(err)
            });
    };

    return (
        <Card
            bg={"dark"}
            key={"dark"}
            text={'white'}
            className="mb-2"
        >
            <Card.Body className="user-info-body">
                <div style={{border: "1px solid orange", display: "flex", padding: 30, width: "100%"}}>
                    <div>
                        <img
                            className="profile-image"
                            src={`http://localhost:8080/image/download/user/${props.myUser.id}`}
                            onError={({currentTarget}) => {
                                currentTarget.onerror = null; // prevents looping
                                currentTarget.src = userImage;
                            }}
                        />
                        <SocialMedia myUser={props.myUser}/>
                        <div className="birthday">Birthday: {props.myUser.birthday}</div>
                    </div>
                    <div className="user-info-second-div">
                        <h3>{props.myUser.fullName}</h3>
                        <div className="user-info-second-div-nickname">
                            (<img className="user-info-nickname-img" src={user}/>
                            <h4 className="user-info-nickname">{props.myUser.nickName}</h4>)
                        </div>
                        <Card.Text className="user-info-aboutme-text">
                            <br/>
                            <h5 style={{color: "orange"}}>About me:</h5>
                            <div className="user-info-aboutme-text-div">
                                {props.myUser.aboutMe}
                            </div>
                            <br/>

                            <div style={{marginTop: "2%", display: "flex"}}>
                                {myId === sessionStorage.getItem("userId") ?
                                    <h5 className="friends" onClick={() => {

                                        navigate("/userpage/friends");
                                    }}>
                                        Friends ({allFriends.length}):
                                    </h5>
                                    :
                                    <h5 className="friends-of-friend">
                                        Friends ({allFriends.length}):
                                    </h5>
                                }
                                &nbsp;
                                {allFriends.map((friend, index) => {
                                    if (index < 2 && index !== allFriends.length - 1) {
                                        return <div style={{display: "flex"}}>
                                            <div
                                                key={index}
                                                className="navigate-to-friend-page"
                                                onClick={() => {
                                                    navigate(`/userpage/friend/${friend.nickName}`)
                                                }}
                                            > {friend.nickName}</div>
                                            <div className="normal-coma">,&nbsp;</div>
                                        </div>
                                    } else if (index === 2) {
                                        return <div
                                            key={index}
                                            className="navigate-to-friend-page"
                                            onClick={() => {
                                                navigate(`/userpage/friend/${friend.nickName}`)
                                            }}
                                        > {friend.nickName} </div>
                                    } else if (index === 3) {
                                        return <div>...</div>
                                    } else {
                                        return <div
                                            key={index}
                                            className="navigate-to-friend-page"
                                            onClick={() => {
                                                navigate(`/userpage/friend/${friend.nickName}`)
                                            }}
                                        > {friend.nickName} </div>
                                    }

                                })}
                            </div>
                        </Card.Text>
                    </div>
                    {myId === sessionStorage.getItem("userId") ?
                        <Button onClick={() => setModalOpen(true)} className="user-info-edit-btn"
                                variant={"outline-warning"}>Edit my details</Button>
                        :
                        <div className="user-start-chat-btn">
                            {boolka ?
                                <Button style={{marginRight: "5px"}} variant="outline-warning" onClick={() => removeFriend()}>{<FaTrashAlt/>}</Button>
                                :
                                <Button style={{marginRight: "5px"}} variant="outline-warning" onClick={() => addFriend()}>{<FaUserPlus/>}</Button>
                            }
                                <Button variant="warning"
                                     onClick={() => sayHello()}>Say Hello {<FaComments/>}</Button>
                        </div>

                    }
                </div>
            </Card.Body>
            {/*<CountryCounter/>*/}
            {modalOpen && <EditUserDataModal visible={modalOpen} close={setModalOpen} myUser={props.myUser}/>}
        </Card>
    );
};

export default InformationAboutUser;