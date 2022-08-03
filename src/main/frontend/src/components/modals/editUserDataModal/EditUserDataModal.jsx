import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, Form, Modal} from "react-bootstrap";
import ReCAPTCHA from "react-google-recaptcha";
import AddImage from "../../userPage/userMainBar/userMainBarComponents/addImage/AddImage";

const EditUserDataModal = (props) => {

    const setToPropsModalClose = () => {
        props.close(false)
    }


    useEffect(() => {
        setNickName(props.myUser.nickName);
        setFullName(props.myUser.fullName);
        setAboutMe(props.myUser.aboutMe);
        setInstagram(props.myUser.instagram);
        setFacebook(props.myUser.facebook);
        setEMail(props.myUser.userEMail);
        setShowEditUserDataModal(props.visible);
        setBirthday(props.myUser.birthday)
    }, [])

    const [nickName, setNickName] = useState("")
    const [fullName, setFullName] = useState("")
    const [aboutMe, setAboutMe] = useState("")
    const [instagram, setInstagram] = useState("")
    const [facebook, setFacebook] = useState("")
    const [eMail, setEMail] = useState("")
    const [birthday, setBirthday] = useState("")

    const [disabledBtn, setDisabledBtn] = useState(true)

    const [showEditUserDataModal, setShowEditUserDataModal] = useState(false);
    const handleCloseLoginModal = () => {
        setToPropsModalClose();
        setShowEditUserDataModal(false);
    };


    const sendDataToServer = () => {
        const url = "http://localhost:8080/usermainbar";
        axios.post(url, {
            nickName: nickName,
            fullName: fullName,
            aboutMe: aboutMe,
            instagram: instagram,
            facebook: facebook,
            eMail: eMail,
            birthday: birthday
        })
            .then(res => {
                console.log(res);
                window.location.reload();
            })
        handleCloseLoginModal()

    }


    return (
        <Modal show={showEditUserDataModal} onHide={handleCloseLoginModal}
               style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
            <Modal.Header closeButton style={{background: "rgb(40,40,40)"}}>
                <Modal.Title>Edit my information</Modal.Title>
            </Modal.Header>
            <Modal.Body style={{background: "rgb(20,20,20)"}}>
                <Form style={{background: "rgb(20,20,20)"}}>
                    {/*-------------------------------------*/}
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>Full name</Form.Label>
                        <Form.Control
                            value={fullName}
                            onChange={e => setFullName(e.target.value)}
                            type="text"
                            autoFocus
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group className="mb-3">
                        <Form.Label style={{color: "orange"}}>Nickname</Form.Label>
                        <Form.Control
                            value={nickName}
                            onChange={e => setNickName(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group className="mb-3">
                        <Form.Label style={{color: "orange"}}>E-mail</Form.Label>
                        <Form.Control
                            value={eMail}
                            onChange={e => setEMail(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>About me</Form.Label>
                        <Form.Control
                            value={aboutMe}
                            onChange={e => setAboutMe(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>Facebook link</Form.Label>
                        <Form.Control
                            value={facebook}
                            onChange={e => setFacebook(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group
                        className="mb-3"
                    >
                        <Form.Label style={{color: "orange"}}>Instagram link</Form.Label>
                        <Form.Control
                            value={instagram}
                            onChange={e => setInstagram(e.target.value)}
                            type="text"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <Form.Group
                        className="mb-3"
                        controlId="textarea-2"
                    >
                        <Form.Label style={{color: "orange"}}>Birthday</Form.Label>
                        <Form.Control
                            type="date"
                            placeholder="Birthday"
                            value={birthday}
                            onChange={e=> setBirthday(e.target.value)}
                            min="1940-01-01" max="2010-01-01"
                        />
                    </Form.Group>
                    {/*-------------------------------------*/}
                    <AddImage/>
                </Form>
            </Modal.Body>
            <Modal.Footer style={{background: "rgb(40,40,40)"}}>
                <ReCAPTCHA
                    size="normal"
                    sitekey="6Let98ogAAAAAH3niinH0n8_di4vhssvE5YL_AuF"
                    onChange={() => setDisabledBtn(false)}
                />
                <Button variant="outline-secondary" onClick={handleCloseLoginModal}>
                    Close
                </Button>
                <Button disabled={disabledBtn} variant="outline-warning" onClick={sendDataToServer}>
                    Edit
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default EditUserDataModal;