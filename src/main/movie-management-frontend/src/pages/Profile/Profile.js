import React from 'react';
import ProfileJsx from './Profile.jsx';
import './Profile.css';
import AuthService from '../../services/AuthService';
import NavigationJsx from '../../components/Navigation.jsx';

function Profile() {
  // if (AuthService.getCurrentUser() == null){
  //   return (
  //     <>
  //       <h1>NOT LOGGED IN!</h1>
  //     </>
  //   )
  // }
  return (
    <div>
      <NavigationJsx />
      <div className="profile">
        <ProfileJsx/>
      </div>
    </div>
  );
}

export default Profile;