import React, {useState} from "react";
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/esm/Button";
import UpdatePasswordJsx from "./UpdatePassword";
import AuthService from "../../../services/AuthService";
import UserService from "../../../services/UserService";
import './Tabs.css';

function SettingsJsx(){
    const currentUser = AuthService.getCurrentUser();
    const [basicValues, setBasicValues] = useState({
        id: currentUser.id,
        email: currentUser.email,
        firstName: currentUser.firstName,
        lastName: currentUser.lastName,
        displayName: currentUser.displayName,
      });
    const [formErrors, setFormErrors] = useState({});
    const [userUpdated, setUserUpdated] = useState(false);
    function handleBasicFieldChange(event) {
        setBasicValues((basicValues)=> ({
            ...basicValues,
            [event.target.id]: event.target.value
        }));
    }
    function validateFormValues(){
        let errors = {};
        let emaillRgx = /\S+@\S+\.\S+/;
        if (!emaillRgx.test(basicValues.email)){
            errors.email = 'Email is invalid';
        }

        setFormErrors(errors);

        if (Object.keys(errors).length === 0) {
            return true;
        } else {
            return false;
        }
    }
    const handleBasicSubmit = (event) => {
        if (event) event.preventDefault();
        if (validateFormValues(basicValues)){
            UserService.updateUserBasicInfo(basicValues).then(res => {
                setUserUpdated(true);
                setTimeout(() => {
                    setUserUpdated(false);
                }, "3000")
                AuthService.updateCurrentUser(res.data);
            }).catch(error => {console.log(error);})
        }
    }
    return (
        <div className="container">
            <div className="basic-info">
                <h3>Basic Info</h3>
                <Form onSubmit={handleBasicSubmit}>
                    <Form.Group className="mb-3 form-field" controlId="email" onChange={handleBasicFieldChange}>
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" placeholder="Email" defaultValue={currentUser.email}/>
                        {formErrors.email && (
                            <p className="text-danger">{formErrors.email}</p>
                        )}
                    </Form.Group>
                    <Form.Group className="mb-3 form-field" controlId="displayName" onChange={handleBasicFieldChange}>
                        <Form.Label>Display Name</Form.Label>
                        <Form.Control type="text" placeholder="Display Name" defaultValue={currentUser.displayName}/>
                    </Form.Group>
                    <Form.Group className="mb-3 form-field" controlId="firstName" onChange={handleBasicFieldChange}>
                        <Form.Label>First Name</Form.Label>
                        <Form.Control type="text" placeholder="First Name" defaultValue={currentUser.firstName}/>
                    </Form.Group>
                    <Form.Group className="mb-3 form-field" controlId="lastName" onChange={handleBasicFieldChange}>
                        <Form.Label>Last Name</Form.Label>
                        <Form.Control type="text" placeholder="Last Name" defaultValue={currentUser.lastName}/>
                    </Form.Group>
                    <Button className="submit-button" variant="primary" type="submit">Update Basic Info</Button>
                </Form>
                {userUpdated ? (<div className="success-message"><p>User Info Updated</p></div>) : (<></>)}
            </div>
            <div className="update-password">
                <UpdatePasswordJsx />
            </div>
        </div>
    );
}

export default SettingsJsx;