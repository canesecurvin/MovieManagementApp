import React from 'react';
import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab'
import RecentActivityJsx from './Tabs/RecentActivity';
import SettingsJsx from './Tabs/Settings';

function ProfileJsx() {
  return (
    <div className="signup">
        <Tabs defaultActiveKey="recent-activity"
        id="uncontrolled-tab-example"
        className="mb-3">
            <Tab eventKey="recent-activity" title="Recent Activity">
                <RecentActivityJsx />
            </Tab>
            <Tab eventKey="settings" title="Settings">
                <SettingsJsx />
            </Tab>
        </Tabs>
    </div>
  );
}

export default ProfileJsx;