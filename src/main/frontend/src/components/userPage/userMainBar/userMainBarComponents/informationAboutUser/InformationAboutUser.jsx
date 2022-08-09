import React, {useEffect, useState} from 'react';
import {Button, Card} from "react-bootstrap";
import SocialMedia from "../socialMedia/SocialMedia";
import "./ProfileImage.css"
import "./InformationAboutUser.css"
import EditUserDataModal from "../../../../modals/editUserDataModal/EditUserDataModal";
import user from "../../../../../images/user.png"
import userImage from "../../../../../images/user.png"
import {useNavigate} from "react-router-dom";
import {FaComments, FaTrashAlt, FaUserPlus} from "react-icons/fa";
import {getResponseFromAxiosGet} from "../../../../../axios";


const InformationAboutUser = (props) => {
    const navigate = useNavigate();
    const [modalOpen, setModalOpen] = useState(false);
    const [allFriends, setAllFriends] = useState([]);
    const idFromProps = sessionStorage.getItem("userId");
    const [boolka, setBoolka] = useState(false);
    const [userId, setUserId] = useState(sessionStorage.getItem("userId"));
    const addFriendUrl = `http://localhost:8080/add_friend/${idFromProps}`;
    const removeFriendUrl = `http://localhost:8080/remove_friend/${idFromProps}`;
    const searchAllFriendsUrl = `http://localhost:8080/search_friend/id/${userId}`;
    const sayHelloUrl = `http://localhost:8080/mail_to_friend/${idFromProps}`;
    const searchFriendByIdFromPropsUrl = `http://localhost:8080/search_friend/id/${idFromProps}`;

    useEffect(() => {
        (async () => {
            await searchAllFriends();
        })();
    }, [props])

    const addFriend = () => {
        getResponseFromAxiosGet(addFriendUrl, 2).then(() => window.location.reload());
    };

    const removeFriend = () => {
        getResponseFromAxiosGet(removeFriendUrl, 2).then(() => window.location.reload());
    };

    const searchAllFriends = async () => {
        if (sessionStorage.getItem("chosenFriendId") !== null) {
            setUserId(sessionStorage.getItem("chosenFriendId"));
        }
        getResponseFromAxiosGet(searchAllFriendsUrl, 2)
            .then(res => {
                setAllFriends(res.data);
                sessionStorage.removeItem("chosenFriendId");
                friendOrNot();
            });
    };

    const sayHello = () => {
        getResponseFromAxiosGet(sayHelloUrl, 2)
            .then(() => {
                navigate("/userpage/friends/mail_box", {
                    state: {
                        friend: props.myUser
                    }
                })
            })
    }

    const friendOrNot = () => {
        getResponseFromAxiosGet(searchFriendByIdFromPropsUrl, 2)
            .then(res => {
                let friendsList = res.data;
                friendsList.map(friend => {
                    if (friend.id === props.myUser.id) {
                        setBoolka(true);
                    }
                })
            })
    };

    return (
        <Card
            bg={"dark"}
            key={"information-about-user-dark"}
            text={'white'}
            className="mb-2"
        >
            <Card.Body className="user-info-body">
                <div style={{border: "1px solid orange", display: "flex", padding: 30, width: "100%"}}>
                    <div>
                        <img
                            className="profile-image"
                            src={`http://localhost:8080/image/download/user/${userId}`}
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
                                {userId === sessionStorage.getItem("userId") ?
                                    <h5 className="friends" onClick={() => {

                                        navigate("/userpage/friends");
                                    }}>
                                        Following ({allFriends.length}):
                                    </h5>
                                    :
                                    <h5 className="friends-of-friend">
                                        Following ({allFriends.length}):
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
                    {userId === sessionStorage.getItem("userId") ?
                        <Button onClick={() => setModalOpen(true)} className="user-info-edit-btn"
                                variant={"outline-warning"}>Edit my details</Button>
                        :
                        <div className="user-start-chat-btn">
                            {boolka ?
                                <Button style={{marginRight: "5px"}} variant="outline-warning"
                                        onClick={() => removeFriend()}>{<FaTrashAlt/>}</Button>
                                :
                                <Button style={{marginRight: "5px"}} variant="outline-warning"
                                        onClick={() => addFriend()}>{<FaUserPlus/>}</Button>
                            }
                            <Button variant="warning"
                                    onClick={() => sayHello()}>Say Hello {<FaComments/>}</Button>
                        </div>
                    }
                </div>
            </Card.Body>
            {modalOpen && <EditUserDataModal visible={modalOpen} close={setModalOpen} myUser={props.myUser}/>}
        </Card>
    );
};

export default InformationAboutUser;