import React, {useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import UserService from '../../services/UserService';
import AuthService from '../../services/AuthService';
import NavigationJsx from '../../components/Navigation';
import {useNavigate, Link} from 'react-router-dom';
import './Login.css';

function LoginJsx(){
    const navigate = useNavigate();
    const [values, setValues] = useState({
        email: '',
        password: '',
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
            UserService.loginUser(values).then(res => {
                AuthService.setCurrentUser(res.data);
            }).then(()=> {
                navigate('/movies');
            })
            .catch(error => {
                let errors = {};
                console.log(error);
                errors.password = 'Email or Password in incorrect';
                setFormErrors(errors);
            })
        }
    }

    return (
        <div className="containers">
            <NavigationJsx />
            <div className="log-container">
                <h1>Login</h1>
                <Form onSubmit={handleFormSubmit}>
                    <Form.Group className="mb-3 form-field" controlId="email" onChange={handleFieldChange}>
                        <Form.Label>Email</Form.Label>
                        <Form.Control required type="email" placeholder="Email"/>
                        {formErrors.email && (
                        <p className="text-danger">{formErrors.email}</p>
                        )}
                    </Form.Group>
                    <Form.Group className="mb-3 form-field" controlId="password" onChange={handleFieldChange}>
                        <Form.Label>Password</Form.Label>
                        <Form.Control required type="password" placeholder="Password"/>
                        {formErrors.password && (
                      <p className="text-danger">{formErrors.password}</p>
                    )}
                    </Form.Group>
                    <p>Dont have an account? <Link to="/register">Create Account</Link></p>
                    <Button className="submit-button" variant="primary" type="submit">Submit</Button>
                </Form>
            </div>
        </div>
    )
}

export default LoginJsx;