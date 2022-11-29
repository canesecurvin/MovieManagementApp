import React, {useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import UserService from '../../services/UserService';
import {useNavigate} from 'react-router-dom';

export const SignupJsx = () => {
    const navigate = useNavigate();
    const [values, setValues] = useState({
        email: '',
        firstName: '',
        lastName: '',
        displayName: '',
        password: '',
        confirmPassword: ''
      });
    const [formErrors, setFormErrors] = useState({});

    function handleFieldChange(event) {
        setValues((values)=> ({
            ...values,
            [event.target.id]: event.target.value
        }));
    }

    function validateFormValues(){
        let errors = {};
        let emaillRgx = /\S+@\S+\.\S+/;
        if (!emaillRgx.test(values.email)){
            errors.email = 'Email is invalid';
        }
        if (values.password && values.confirmPassword !== values.password){
            errors.confirmPassword = 'Passwords do not match.'
        }

        setFormErrors(errors);

        if (Object.keys(errors).length === 0) {
            return true;
        } else {
            return false;
        }
    }

    function handleFormSubmit(event) {
        if (event) event.preventDefault();
        if (validateFormValues(values)){
            UserService.createUser(values).then(res => {
            }).catch(err => {
                alert(err);
            }).finally(()=> {
                loginAfterRegister();
            })
        }
    }

    function loginAfterRegister(){
        UserService.loginUser({email: values.email, password: values.password}).then(res => {
            navigate('/movies');
        }).catch(err => {
            alert('login failed', err);
        });
    }


    return (
    <div className="container">
        <h1>Create Account</h1>
        <Form onSubmit={handleFormSubmit}>
            <Form.Group className="mb-3 form-field" controlId="email" onChange={handleFieldChange}>
                <Form.Label>Email</Form.Label>
                <Form.Control required type="email" placeholder="Email"/>
                {formErrors.email && (
                      <p className="text-danger">{formErrors.email}</p>
                    )}
            </Form.Group>
            <Form.Group className="mb-3 form-field" controlId="firstName" onChange={handleFieldChange}>
                <Form.Label>First Name</Form.Label>
                <Form.Control required type="text" placeholder="First Name"/>
            </Form.Group>
            <Form.Group className="mb-3 form-field" controlId="lastName" onChange={handleFieldChange}>
                <Form.Label>Last Name</Form.Label>
                <Form.Control required type="text" placeholder="Last Name"/>
            </Form.Group>
            <Form.Group className="mb-3 form-field" controlId="displayName" onChange={handleFieldChange}>
                <Form.Label>Display Name</Form.Label>
                <Form.Control required type="text" placeholder="Display Name"/>
            </Form.Group>
            <Form.Group className="mb-3 form-field" controlId="password" onChange={handleFieldChange}>
                <Form.Label>Password</Form.Label>
                <Form.Control required type="password" placeholder="Password"/>
            </Form.Group>
            <Form.Group className="mb-3 form-field" controlId="confirmPassword" onChange={handleFieldChange}>
                <Form.Label>Confirm Password</Form.Label>
                <Form.Control required type="password" placeholder="Confirm Password"/>
                {formErrors.confirmPassword && (
                      <p className="text-danger">{formErrors.confirmPassword}</p>
                    )}
            </Form.Group>
            <Button className="submit-button" variant="primary" type="submit">Submit</Button>
        </Form>
    </div>
    )
}