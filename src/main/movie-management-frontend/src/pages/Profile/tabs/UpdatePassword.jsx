import React, {useState} from 'react';
import UserService from '../../../services/UserService';
import AuthService from '../../../services/AuthService';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function UpdatePasswordJsx(){
    const currentUser = AuthService.getCurrentUser();
    const [passwordValues, setPasswordValues] = useState({
        oldPassword: '',
        newPassword: '',
        confirmNewPassword: ''
      });
    const [formErrors, setFormErrors] = useState({});
    const [passwordUpdated, setPasswordUpdated] = useState(false);
    function handlePasswordFieldChange(event) {
        setPasswordValues((passwordValues)=> ({
            ...passwordValues,
            [event.target.id]: event.target.value
        }));
    }
    function validateFormValues(){
        let errors = {};
        if (passwordValues.newPassword && passwordValues.confirmNewPassword !== passwordValues.newPassword){
            errors.confirmNewPassword = 'Passwords do not match.'
        }

        setFormErrors(errors);

        if (Object.keys(errors).length === 0) {
            return true;
        } else {
            return false;
        }
    }
    const handlePasswordUpdate = (event) => {
        if (event) event.preventDefault();
        if (validateFormValues(passwordValues)){
            UserService.updateUserPassword(currentUser.id ,passwordValues).then(res => {
                setPasswordUpdated(true);
                setTimeout(() => {
                    setPasswordUpdated(false);
                }, "3000")
            }).catch(error => {console.log(error);})
        }
        event.target.reset();
    }
    return (
        <>
            <h3>Update Password</h3>
            <Form onSubmit={handlePasswordUpdate}>
                <Form.Group className="mb-3 form-field" controlId="oldPassword" onChange={handlePasswordFieldChange}>
                    <Form.Label>Previous Password</Form.Label>
                    <Form.Control type="password" placeholder="Previous Password"/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="newPassword" onChange={handlePasswordFieldChange}>
                    <Form.Label>New Password</Form.Label>
                    <Form.Control type="password" placeholder="New Password"/>
                </Form.Group>
                <Form.Group className="mb-3 form-field" controlId="confirmNewPassword" onChange={handlePasswordFieldChange}>
                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control type="password" placeholder="Confirm Password"/>
                    {formErrors.confirmNewPassword && (
                      <p className="text-danger">{formErrors.confirmNewPassword}</p>
                    )}
                </Form.Group>
                {passwordUpdated ? (<div className="success-message"><p>Password Updated</p></div>) : (<></>)}
                <Button className="submit-button" variant="primary" type="submit">Update Password</Button>
            </Form>
        </>
    )
}

export default UpdatePasswordJsx