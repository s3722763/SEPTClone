import React, { Component } from 'react'

export default class AddPerson extends Component {

  constructor(){
    super();

    this.state= {
      name:"",
      dob:"",
      gender:"",
      email:"",
      phone: "",
      tfn: "",
      super:""
  }; 
  this.onChange = this.onChange.bind(this);
  this.onSubmit = this.onSubmit.bind(this);

    }

  onChange(e){
      this.setState({[e.target.name]: e.target.value});
  }
  onSubmit(e){
      e.preventDefault();
      const newEmployee = {
        name:this.state.name,
        dob:this.state.dob,
        gender:this.state.gender,
        email:this.state.email,
        phone: this.state.phone,
        tfn: this.state.tfn,
        super:this.state.super
      }

    console.log(newEmployee);
  }  
    render() {
        return (
            <div>
            <h2>New Employee Form</h2>
            <form onSubmit={this.onSubmit}>
            <label>
              Full Name:
              <input type="text" name="name" value = {this.state.name}
              onChange = {this.onChange} />
            </label>
            <label>
              D.O.B:
              <input type="date" name="dob" value = {this.state.dob}
              onChange = {this.onChange} />
            </label>
            <div>
            <label>
            Gender:
            <div>
                <input type="radio" id="male" name="gender" value="male" onChange = {this.onChange}/>
                <label for="male">Male</label><br/>
                <input type="radio" id="female" name="gender" value="female" onChange = {this.onChange}/>
                <label for="female">Female</label><br/>
                <input type="radio" id="other" name="gender" value="other" onChange = {this.onChange}/>
                <label for="other">Other</label>
            </div>
            </label>
            </div>

            <label>
            Email:
            <input type="text" name="email"  name="email" value = {this.state.email}
            onChange = {this.onChange} />
          </label>

          <label>
          Phone:
          <input type="text"  name="phone" value = {this.state.phone}
          onChange = {this.onChange}  />
        </label>

        <label>
          TFN:
          <input type="text" name="tfn" value = {this.state.tfn}
          onChange = {this.onChange}  />
        </label>

        <label>
          Super:
          <input type="text" name="super" value = {this.state.super}
          onChange = {this.onChange} />
        </label>

            <input type="submit" value="Create Employee" />  
            </form>
          </div>
        )
    }
}
