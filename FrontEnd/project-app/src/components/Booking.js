import React, { Component } from 'react'
import axios from 'axios';
// import EmployeeDataService from '../service/EmployeeDataService';

export default class Booking extends Component {

  constructor() {
    super();

    this.state = {
      bookersName: "",
      employees: [],
      assignedEmployee: "",
      bookingDate: "",
      service: ""
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.handleDropdownChange = this.handleDropdownChange.bind(this);

  }

componentDidMount() {
 axios.get("http://localhost:8080/api/employee")
    .then(response => {
      console.log("Making request");
      return response.json;
    })
    .then(console.log("Made request"))
    .then(data => {
      let employeesFromApi = data.map(employee => {
        return { value: employee, display: employee };
      });
      this.setState({
        employees: [
          {
            value: "",
            display:
              "(Select your preferred staff)"
          }
        ].concat(employeesFromApi)
      });
    })
    .catch(error => {
      console.log(error);
    });
}

  handleDropdownChange(e) {
    this.setState({ service: e.target.value });
  }


  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  onSubmit(e) {
    e.preventDefault();
    const newBooking = {

      bookersName: this.state.bookersName,
      assignedEmployee: this.state.assignedEmployee,
      bookingDate: this.state.bookingDate,
      service: this.state.service
    }

    console.log(newBooking);
  }

  render() {

    return (
      <div>
        <h2>New Booking Form</h2>
        <form onSubmit={this.onSubmit} data-testid="form">
          <label>
            Full Name:
                <input type="text" placeholder="Full Name" name="bookersName" value={this.state.bookersName}
              onChange={this.onChange} />
          </label>

          <label>Select Service: </label>
          <select name="service"
            onChange={this.handleDropdownChange}>

            <option value="service 1">Service 1</option>
            <option value="service 2">Service 2</option>
            <option value="service 3">Service 3</option>
            <option value="service 4">Service 4</option>
          </select>


          <label> Preferred Staff: </label>
          <select value={this.state.assignedEmployee}
            onChange={e =>
              this.setState({
                assignedEmployee: e.target.value,
                validationError:
                  e.target.value === ""
                    ? "You must select your preferred staff"
                    : ""
              })
            }
          >
            {this.state.employees.map(employee => (
              <option
                key={employee.value.id}
                value={employee.value.id}
              >
                {employee.value.name}
              </option>
            ))}
          </select>

          <div>
            <label>Select Date & Time: </label>
            <input type="datetime-local" name="bookingDate" value={this.state.bookingDate}
              onChange={this.onChange}></input>
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    )
  }
}