// import React, {useState} from 'react';
// import Button from 'react-bootstrap/Button';
// import Form from 'react-bootstrap/Form';
// import Modal from 'react-bootstrap/Modal';
//
//
// const NewPostModal = () => {
//
//     const [show, setShow] = useState(true);
//
//     const handleClose = () => setShow(false);
//     const handleShow = () => setShow(true);
//
//     return (
//         <>
//             <Modal show={show} onHide={handleClose} style={{background: "rgba(0, 0, 0, 0.6)", color: "orange"}}>
//                 <Modal.Header closeButton style={{background: "rgb(40,40,40)"}} >
//                     <Modal.Title >Add Post</Modal.Title>
//                 </Modal.Header>
//                 <Modal.Body style={{background: "rgb(20,20,20)"}}>
//                     <Form style={{background: "rgb(20,20,20)"}}>
//                         <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
//                             <Form.Label style={{color: "orange"}}>Topic</Form.Label>
//                             <Form.Control
//                                 type="email"
//                                 placeholder="Topic"
//                                 autoFocus
//                             />
//                         </Form.Group>
//                         <Form.Group
//                             className="mb-2"
//                             controlId="exampleForm.ControlTextarea1"
//                         >
//                             <Form.Label style={{color: "orange"}}>Post Message</Form.Label>
//                             <Form.Control as="textarea" rows={3}/>
//                         </Form.Group>
//                     </Form>
//                 </Modal.Body>
//                 <Modal.Footer style={{background: "rgb(40,40,40)"}}>
//                     <Button variant="secondary" onClick={handleClose}>
//                         Close
//                     </Button>
//                     <Button variant="primary" onClick={handleClose}>
//                         Save Changes
//                     </Button>
//                 </Modal.Footer>
//             </Modal>
//         </>
//
//     );
// };
//
// export default NewPostModal;