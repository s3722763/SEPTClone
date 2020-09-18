import React, { Component } from 'react'

export default class Booking extends Component {
    
  constructor(){
    super();

    this.state= {
    bookersName: "",
    workerName: "",
    bookingDate: "",
    service: ""
  }; 
  this.onChange = this.onChange.bind(this);
  this.onSubmit = this.onSubmit.bind(this);

    }

  onChange(e){
      this.setState({[e.target.name]: e.target.value});
  }
  onSubmit(e){
      e.preventDefault();
      const newBooking = {
        
        bookersName: this.state.bookersName,
        workerName: this.state.workerName,
        bookingDate: this.state.bookingDate,
        service: this.state.service 
      }

    console.log(newBooking);
  }
  
  render() {
        return (
          <div>
            <h2>New Booking Form</h2>
            <form onSubmit={this.onSubmit}>
                <label>
                Full Name:
                <input type="text" placeholder="Full Name" name="bookersName" value = {this.state.bookersName}
                onChange = {this.onChange}/>
                </label>
                <label>
                Select Service:
                <select name="service" value = {this.state.service}
                onChange = {this.onChange}>
                 
                  <option value="service 1">Service 1</option>
                  <option value="service 2">Service 2</option>
                  <option value="service 3">Service 3</option>
                  <option value="service 4">Service 4</option>
              </select>
            </label>
            <label>
              Preferred Staff:
                <select name="workerName" value = {this.state.workerName}
                onChange = {this.onChange}>
        
                  <option value="sandra">Sandra</option>
                  <option value="angelique">Angelique</option>
                  <option value="daniel">Daniel</option>
              </select>
            </label>
            <div>
              <label>Select Date & Time: </label>
              <input type="datetime-local" name="bookingDate" value = {this.state.bookingDate}
              onChange = {this.onChange}></input>
            </div>
                <input type="submit" value="Submit" />
        </form>
          </div>
        )
    }
}