import React from 'react';
import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab'
import RecentActivityJsx from './Tabs/RecentActivity';
import SettingsJsx from './Tabs/Settings';
import './Profile.css';

function ProfileJsx(props) {
  const {updateNavigate} = props;
  return (
    <div className="signup">
        <Tabs defaultActiveKey="recent-activity"
        id="uncontrolled-tab-example"
        className="mb-3 tab-nav">
            <Tab eventKey="recent-activity" title="Recent Activity">
                <RecentActivityJsx />
            </Tab>
            <Tab eventKey="settings" title="Settings">
                <SettingsJsx updateNavigate={updateNavigate}/>
            </Tab>
        </Tabs>
    </div>
  );
}

export default ProfileJsx;