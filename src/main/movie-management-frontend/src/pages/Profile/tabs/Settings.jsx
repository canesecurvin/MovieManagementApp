import React from "react";
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/esm/Button";
import './Tabs.css';

const user = {
    email: 'janed@gmail.com',
    displayName: 'Jane',
    firstName: 'Jane',
    lastName: 'Doe'
}

function SettingsJsx(){
    return (
        <div className="container">
            <div className="basic-info">
                <h3>Basic Info</h3>
                <Form.Group className="mb-3 form-field" controlId="email">
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" placeholder="Email" defaultValue={user.email}/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="display-name">
                    <Form.Label>Display Name</Form.Label>
                    <Form.Control type="text" placeholder="Display Name" defaultValue={user.displayName}/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="first-name">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control type="text" placeholder="First Name" defaultValue={user.firstName}/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="last-name">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control type="text" placeholder="Last Name" defaultValue={user.lastName}/>
                </Form.Group>
                <Button className="submit-button" variant="primary" type="submit">Update Basic Info</Button>
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