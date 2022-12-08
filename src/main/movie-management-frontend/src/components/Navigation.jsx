import React, {useState, useEffect} from 'react';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import AuthService from '../services/AuthService';
import {useNavigate} from 'react-router-dom';
import Button from 'react-bootstrap/Button';

function NavigationJsx(props){
    const navigate = useNavigate();
    const currentUser = AuthService.getCurrentUser();
    const [userName, setUserName] = useState();
    let isLoggedIn = AuthService.isLoggedIn();
    const renderUsername = () => {
        if (!props.displayName){
            return (
                <><p className="welcome">, welcome {userName}!</p></>
            )
        }else {
            return (<><p className="welcome">, welcome {props.displayName}!</p></>)
        }
    }
    useEffect(()=>{
        if (isLoggedIn){
            setUserName(currentUser.displayName);
        }
        renderUsername();
    },[userName]);
    const handleLogout = ()=> {
        AuthService.logout();
        navigate('/')
    }
    const navigateProfile = () => {
        navigate('/profile')
    }
    const navigateMovies = () => {
        navigate('/movies')
    }
    return (
        <>
            <Navbar className="nav" bg="primary" variant="dark" sticky="top">
                <Navbar.Brand className="brand">
                    movie management
                    {isLoggedIn ? (
                        <>{renderUsername()}</>
                    ) : (<></>)}
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                {isLoggedIn ? 
                    (
                        <Navbar.Collapse className="nav-collapse" id="basic-navbar-nav">
                            <Nav className="me-auto nav-links">
                                <Button className="logout-button" variant="primary" onClick={navigateProfile}>profile</Button>
                                <Button className="logout-button" variant="primary" onClick={navigateMovies}>movies</Button>
                                <Button className="logout-button" variant="primary" onClick={handleLogout}>logout</Button>
                            </Nav>
                        </Navbar.Collapse>
                    ): (<></>)}
            </Navbar>
        </>
    )
}

export default NavigationJsx;