import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import ContactUs from './pages/ContactUs';
import Login from './pages/Login'
import Booking from './components/Booking'
import AboutUs from './pages/AboutUs'
import AddPerson from './components/AddEmployee';
import Schedule from './components/Schedule';
import {Route, Switch} from 'react-router-dom';
import {Provider} from "react-redux";
import store from "./store";

function App() {
  return (
    <Provider store={store}>
      <div>
        <Navbar/>
        <Switch>
          <Route exact path = "/" component={Home}/>
          <Route exact path = "/contact-us" component={ContactUs}/>
          <Route exact path = "/login" component={Login}/>
          <Route exact path = "/booking" component={Booking}/>
          <Route exact path = "/about-us" component={AboutUs}/>
          <Route exact path = "/addEmployee" component={AddPerson}/>
          <Route exact path = "/schedule" component={Schedule}/>
          <Route component={Error}/>
        </Switch>
      </div>
    </Provider>
  
  );
}

export default App;
