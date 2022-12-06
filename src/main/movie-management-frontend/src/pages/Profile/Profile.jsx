import React from 'react';
import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab'
import RecentActivityJsx from './Tabs/RecentActivity';
import SettingsJsx from './Tabs/Settings';
import UserMovieFavoritesJsx from './Tabs/UserMovieFavorites';
import './Profile.css';

function ProfileJsx() {
  return (
    <div className="signup">
        <Tabs defaultActiveKey="recent-activity"
        id="uncontrolled-tab-example"
        className="mb-3 tab-nav">
            <Tab eventKey="recent-activity" title="Recent Activity">
                <RecentActivityJsx />
            </Tab>
            <Tab eventKey="movie-favorites" title="Favorites">
                <UserMovieFavoritesJsx />
            </Tab>
            <Tab eventKey="settings" title="Settings">
                <SettingsJsx />
            </Tab>
        </Tabs>
    </div>
  );
}

export default ProfileJsx;