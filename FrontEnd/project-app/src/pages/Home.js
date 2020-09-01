import React, { Component } from 'react'
import { Link } from "react-router-dom";

export default class Home extends Component {
    render() {
        return (
            <div>
            <section className="home-main-section">
            <div>
            <h1 className="title">General Booking Services</h1>
            <span className="subtitle">Book your favourite service today!
            </span>
            <Link to="/booking" className="btn">
            Book Now
            </Link>
            </div>
          </section>
          <div className ="home-page-circle-1"></div>
          <div className ="home-page-circle-2"></div>
          <div className ="home-page-circle-3"></div>
          </div>
        )
    }
}
