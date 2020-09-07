import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import ContactUs from './pages/ContactUs';
import Login from './pages/Login'
import Booking from './pages/Booking'
import AboutUs from './pages/AboutUs'
import AddPerson from './pages/AddPerson';
import {Route, Switch} from 'react-router-dom';


function App() {
  return (

    <div>
      <Navbar/>
      <Switch>
        <Route exact path = "/" component={Home}/>
        <Route exact path = "/contact-us" component={ContactUs}/>
        <Route exact path = "/login" component={Login}/>
        <Route exact path = "/booking" component={Booking}/>
        <Route exact path = "/about-us" component={AboutUs}/>
        <Route exact path = "/addPerson" component={AddPerson}/>
        <Route component={Error}/>
      </Switch>
    </div>
    
  );
}

export default App;
