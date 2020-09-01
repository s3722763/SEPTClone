import React, { Component } from 'react'
import logo from '../logo.svg'
import { Link } from "react-router-dom";

export default class Navbar extends Component {
    render() {
        return (
            <header className="main-header">
            <a href ="/" className="brand-logo">
               <img src = {logo} />
               <div className= "brand-logo-name" >BookingApp</div>
            </a>
            <nav className="main-nav">
               <ul>
                    <li>
                    <Link to="/about-us">About Us</Link>
                    </li>
                    <li>
                    <Link to="/contact-us">Contact Us</Link>
                    </li>
                    <li>
                    <Link to="/login">Login</Link>
                    </li>
               </ul>
            </nav>
            </header>
        )
    }
}
