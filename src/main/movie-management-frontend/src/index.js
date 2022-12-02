import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Signup from './pages/Signup/Signup';
import MovieList from './pages/MovieList/MovieList'
import Movie from './pages/Movie/Movie';
import Profile from './pages/Profile/Profile';
import NavigationJsx from './components/Navigation';
import reportWebVitals from './reportWebVitals';
import {
  createBrowserRouter,
  RouterProvider,
  Route,
} from "react-router-dom";
import "./index.css";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/register",
    element: <Signup />,
  },
  {
    path: "/movies",
    element: <MovieList />,
  },
  {
    path: "/movie/:id",
    element: <Movie />
  },
  {
    path: "/profile",
    element: <Profile />
  }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <>
    {/* <NavigationJsx /> */}
    <React.StrictMode className="container">
      <RouterProvider router={router} />
    </React.StrictMode>
  </>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
