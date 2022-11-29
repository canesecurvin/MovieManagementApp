import React, {useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Login.css';

function LoginJsx(){
    return (
        <div className="container">
            <h1>Login</h1>
            <Form>
                <Form.Group className="mb-3 form-field" controlId="email">
                    <Form.Label>Email</Form.Label>
                    <Form.Control required type="email" placeholder="Email"/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control required type="password" placeholder="Password"/>
                </Form.Group>
                <Button className="submit-button" variant="primary" type="submit">Submit</Button>
            </Form>
        </div>
    )
}

export default LoginJsx;