import React, {useEffect, useState} from 'react';
import ProfileJsx from './Profile.jsx';
import './Profile.css';
import AuthService from '../../services/AuthService';
import NavigationJsx from '../../components/Navigation.jsx';

function Profile() {
  const [profileDisplayName, setProfileDisplayName] = useState();
  const updateNavigate = (displayName) => {
    setProfileDisplayName(displayName);
  }
  useEffect(()=>{
  }, [profileDisplayName])
  return (
    <div>
      <NavigationJsx displayName={profileDisplayName}/>
      <div className="profile">
        <ProfileJsx updateNavigate={updateNavigate}/>
      </div>
    </div>
  );
}

export default Profile;