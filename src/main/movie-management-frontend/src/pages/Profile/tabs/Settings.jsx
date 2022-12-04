import React from "react";
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/esm/Button";
import AuthService from "../../../services/AuthService";
import './Tabs.css';

function SettingsJsx(){
    const currentUser = AuthService.getCurrentUser();
    const handleBasicSubmit = () => {
        //
    }
    return (
        <div className="container">
            <div className="basic-info">
                <h3>Basic Info</h3>
                <Form onSubmit={handleBasicSubmit}>
                    <Form.Group className="mb-3 form-field" controlId="email">
                    <Form.Label>Email</Form.Label>
                    <Form.Control type="email" placeholder="Email" defaultValue={currentUser.email}/>
                    </Form.Group>
                    <Form.Group className="mb-3 form-field" controlId="display-name">
                        <Form.Label>Display Name</Form.Label>
                        <Form.Control type="text" placeholder="Display Name" defaultValue={currentUser.displayName}/>
                    </Form.Group>
                    <Form.Group className="mb-3 form-field" controlId="first-name">
                        <Form.Label>First Name</Form.Label>
                        <Form.Control type="text" placeholder="First Name" defaultValue={currentUser.firstName}/>
                    </Form.Group>
                    <Form.Group className="mb-3 form-field" controlId="last-name">
                        <Form.Label>Last Name</Form.Label>
                        <Form.Control type="text" placeholder="Last Name" defaultValue={currentUser.lastName}/>
                    </Form.Group>
                    <Button className="submit-button" variant="primary" type="submit">Update Basic Info</Button>
                </Form>
            </div>
            <div className="update-password">
                <h3>Update Password</h3>
                <Form.Group className="mb-3 form-field" controlId="prev-password">
                    <Form.Label>Previous Password</Form.Label>
                    <Form.Control type="password" placeholder="Previous Password"/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="new-password">
                    <Form.Label>New Password</Form.Label>
                    <Form.Control type="password" placeholder="New Password"/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="confirm-password">
                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control type="password" placeholder="Confirm Password"/>
                </Form.Group>
                <Button className="submit-button" variant="primary" type="submit">Update Password</Button>
            </div>
        </div>
    );
}

export default SettingsJsx;