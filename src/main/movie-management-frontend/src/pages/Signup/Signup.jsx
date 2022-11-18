import React from 'react';
import Button from 'react-bootstrap/Button';
import FormGroup from 'react-bootstrap/esm/FormGroup';
import FormLabel from 'react-bootstrap/esm/FormLabel';
import Form from 'react-bootstrap/Form';

export const SignupJsx = () => {
    return (
    <div className="container">
        <h1>Create Account</h1>
        <Form.Group className="mb-3 form-field" controlId="email">
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" placeholder="Email"/>
        </Form.Group>
        <Form.Group className="mb-3 form-field" controlId="display-name">
            <Form.Label>Display Name</Form.Label>
            <Form.Control type="text" placeholder="Display Name"/>
        </Form.Group>
        <Form.Group className="mb-3 form-field" controlId="first-name">
            <Form.Label>First Name</Form.Label>
            <Form.Control type="text" placeholder="First Name"/>
        </Form.Group>
        <Form.Group className="mb-3 form-field" controlId="last-name">
            <Form.Label>Last Name</Form.Label>
            <Form.Control type="text" placeholder="Last Name"/>
        </Form.Group>
        <Form.Group className="mb-3 form-field" controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password"/>
        </Form.Group>
        <Button className="submit-button" variant="primary" type="submit">Submit</Button>
    </div>
    )
}