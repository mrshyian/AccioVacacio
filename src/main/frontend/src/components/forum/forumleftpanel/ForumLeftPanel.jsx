import React, {useEffect, useState} from 'react';
import {Menu, MenuItem, ProSidebar, SubMenu} from "react-pro-sidebar";
import {
    FaComment,
    FaCommentDots,
    FaFilter,
    FaGlobeAmericas,
    FaGlobeEurope,
    FaHeart,
    FaHourglassHalf
} from "react-icons/fa";
import AddNewPost from "../forumRightPanel/addNewPost/AddNewPost";
import {Link} from "react-router-dom";
import {BiFileFind} from "react-icons/bi";
import {Form} from "react-bootstrap";
import Axios from "axios";
import {AiOutlineFire} from "react-icons/ai";
import SinglePost from "../forumRightPanel/singlePost/SinglePost";
import axios from "axios";

const ForumLeftPanel = (props) => {

    const [NewModalOpen, setNewModalOpen] = useState(false);
    const [popularPosts, setPopularPosts] = useState([]);
    const url = `http://localhost:8080/sort_by`;
    const [date, setDate] = useState("");
    const [data, setData] = useState({
        country: "",
        city: "",
        time: date
    })

    const openModal = () =>{
        setNewModalOpen(true)
    }

    function handleSelect(e){
        setDate(e)
    }

    function handle(e){
        const newData = {...data}
        newData[e.target.id] = e.target.value
        setData(newData)
    }

    function submit(e){
        e.preventDefault();
        Axios.post(url,
            {
            country: data.country,
            city: data.city,
            time : date},
            {headers:
                    {"Authorization": `Bearer ${sessionStorage.getItem("token")}`,
                        'X-XSRF-TOKEN': props.tokenCsrf}}
        ).then(()=> refreshPage())

    }

    const fetchMostPopularPosts = () => {
        axios.get(`http://localhost:8080/get_most_popular_posts`,
            {headers: {"Authorization": `Bearer ${sessionStorage.getItem("token")}`}})
            .then(res =>{setPopularPosts(res.data);
                console.log(res.data)})
            .catch(err => {console.log(err)});
    };

    function refreshPage(){
        window.location.reload();
    }

    useEffect(()=>{
        fetchMostPopularPosts();
    }, [])

    if (sessionStorage.getItem("userId") === null){
        return (
            <header>
                <ProSidebar className="sidebar" style={{height: "1000px"}}>
                    <Menu iconShape="square">
                        <SubMenu title="Filter" icon={<FaFilter />}>
                            <Form>
                                <Form.Select as="select"
                                             value={data.time}
                                             id="submit"
                                             size="sm"
                                             onChange={(e) => handleSelect(e.target.value)}
                                             style={{ marginLeft: "15%", width: "72%", height: "30px"}}
                                             icon={<FaHourglassHalf />}
                                >
                                    <option id="latest" value={"Latest"}>Select</option>
                                    <option id="oldest" value={"Oldest"}>Oldest</option>
                                    <option id="latest" value={"Latest"}>Latest</option>
                                </Form.Select >

                                <MenuItem icon={<FaGlobeAmericas />}><input onChange={(e) => handle(e)} id="country" value={data.country} type="text" placeholder="Country"/></MenuItem>
                                <MenuItem icon={<FaGlobeEurope />}><input onChange={(e) => handle(e)} id="city" value={data.city} type="text" placeholder="City"/></MenuItem>
                                <MenuItem icon={<BiFileFind />}><button onClick={(e)=> submit(e)} type="submit"><Link to="/sort_by"> Submit </Link></button></MenuItem>
                            </Form>
                        </SubMenu>
                        <SubMenu title="Most Popular" icon={<AiOutlineFire />}>
                            <div>
                                {popularPosts.map((post, index) => {
                                    return (
                                        <MenuItem key={index}><a href={`#${post.id}`}>{post.topic}</a></MenuItem>
                                    )
                                })}
                            </div>
                        </SubMenu>
                    </Menu>
                </ProSidebar>
            </header>
        );
    } else {
        return (
            <header>
                <ProSidebar className="sidebar" style={{height: "1000px"}}>
                    <Menu iconShape="square">
                        <MenuItem icon={<FaComment />}> <Link to="/forum/my_comments"> My comments </Link></MenuItem>
                        <SubMenu title="Filter" icon={<FaFilter />}>
                            <Form>
                                <Form.Select as="select"
                                             value={data.time}
                                             id="submit"
                                             size="sm"
                                             onChange={(e) => handleSelect(e.target.value)}
                                             style={{ marginLeft: "15%", width: "72%", height: "30px"}}
                                             icon={<FaHourglassHalf />}
                                >
                                    <option id="latest" value={"Latest"}>Select</option>
                                    <option id="oldest" value={"Oldest"}>Oldest</option>
                                    <option id="latest" value={"Latest"}>Latest</option>
                                </Form.Select >

                                <MenuItem icon={<FaGlobeAmericas />}><input onChange={(e) => handle(e)} id="country" value={data.country} type="text" placeholder="Country"/></MenuItem>
                                <MenuItem icon={<FaGlobeEurope />}><input onChange={(e) => handle(e)} id="city" value={data.city} type="text" placeholder="City"/></MenuItem>
                                <MenuItem icon={<BiFileFind />}><button onClick={(e)=> submit(e)} type="submit"><Link to="/sort_by"> Submit </Link></button></MenuItem>
                            </Form>
                        </SubMenu>

                        <MenuItem icon={<FaHeart />}><Link to="/forum/favourite_comments">Favourite comments</Link></MenuItem>
                        <MenuItem variant="outline-warning" onClick={() => openModal()} icon={<FaCommentDots />}>Add Post</MenuItem>
                        {NewModalOpen && <AddNewPost close={setNewModalOpen} open={NewModalOpen}/>}
                        <SubMenu title="Most Popular" icon={<AiOutlineFire />}>
                            <div>
                            {popularPosts.map((post, index) => {
                                return (
                                    <MenuItem key={index}><a href={`#${post.id}`}>{post.topic}</a></MenuItem>
                                )
                            })}
                        </div>
                        </SubMenu>
                    </Menu>
                </ProSidebar>
            </header>
        );
    }
};

export default ForumLeftPanel;