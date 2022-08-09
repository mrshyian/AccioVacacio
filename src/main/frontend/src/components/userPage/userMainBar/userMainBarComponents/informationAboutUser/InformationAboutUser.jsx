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


const InformationAboutUser = () => {

    const [myFriend, setMyFriend] = useState({})
    const witchUrl = () => {
        if (window.location.href.toString().startsWith("http://localhost:3000/userpage/friend")) {
            const userNickName = (window.location.href.toString().split("/")[window.location.href.toString().split("/").length - 1]);
            const friendByNickUrl = `http://localhost:8080/get_friend_by_nick/${userNickName}`;
            getResponseFromAxiosGet(friendByNickUrl, 0)
                .then(res => {
                    setMyFriend(res.data);
                    alert("if")
                });
        } else {
            const friendByNickUrl = `http://localhost:8080/usermainbar/${sessionStorage.getItem('userId')}`;
            getResponseFromAxiosGet(friendByNickUrl, 0)
                .then(res => {
                    setMyFriend(res.data);
                    alert("else")
                });
        }
    }

    const navigate = useNavigate();
    const [modalOpen, setModalOpen] = useState(false);
    const [allFriends, setAllFriends] = useState([]);
    const idFromProps = myFriend.id;
    const [boolka, setBoolka] = useState(false);
    const addFriendUrl = `http://localhost:8080/add_friend/${idFromProps}`;
    const removeFriendUrl = `http://localhost:8080/remove_friend/${idFromProps}`;
    const searchAllFriendsUrl = `http://localhost:8080/search_friend/id/${idFromProps}`;
    const sayHelloUrl = `http://localhost:8080/mail_to_friend/${idFromProps}`;

    useEffect(() => {
        witchUrl();
    }, [])

    useEffect(() => {
        searchAllFriends();
        friendOrNot();
    }, [myFriend])

    const addFriend = () => {
        getResponseFromAxiosGet(addFriendUrl, 2).then(() => window.location.reload());
    };

    const removeFriend = () => {
        getResponseFromAxiosGet(removeFriendUrl, 2).then(() => window.location.reload());
    };

    const searchAllFriends = () => {
        getResponseFromAxiosGet(searchAllFriendsUrl, 0)
            .then(res => {
                setAllFriends(res.data);
            });
    };

    const sayHello = () => {
        getResponseFromAxiosGet(sayHelloUrl, 2)
            .then(() => {
                navigate("/userpage/friends/mail_box", {
                    state: {
                        friend: myFriend
                    }
                })
            })
    }

    const friendOrNot = () => {
        allFriends.map(friend => {
            if (friend.id === myFriend.id) {
                setBoolka(true);
            }
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
                            src={`http://localhost:8080/image/download/user/${myFriend.id}`}
                            onError={({currentTarget}) => {
                                currentTarget.onerror = null; // prevents looping
                                currentTarget.src = userImage;
                            }}
                        />
                        <SocialMedia myUser={myFriend}/>
                        <div className="birthday">Birthday: {myFriend.birthday}</div>
                    </div>
                    <div className="user-info-second-div">
                        <h3>{myFriend.fullName}</h3>
                        <div className="user-info-second-div-nickname">
                            (<img className="user-info-nickname-img" src={user}/>
                            <h4 className="user-info-nickname">{myFriend.nickName}</h4>)
                        </div>
                        <Card.Text className="user-info-aboutme-text">
                            <br/>
                            <h5 style={{color: "orange"}}>About me:</h5>
                            <div className="user-info-aboutme-text-div">
                                {myFriend.aboutMe}
                            </div>
                            <br/>

                            <div style={{marginTop: "2%", display: "flex"}}>
                                {myFriend.id === sessionStorage.getItem("userId") ?
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
                    {myFriend.id === sessionStorage.getItem("userId") ?
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
            {modalOpen && <EditUserDataModal visible={modalOpen} close={setModalOpen} myUser={myFriend}/>}
        </Card>
    );
};

export default InformationAboutUser;