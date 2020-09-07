import React, { Component } from 'react'

export default class AddPerson extends Component {
    render() {
        return (
            <div>
            <h2>New Employee Form</h2>
            <label>
              Full Name:
              <input type="text" name="fullName" />
            </label>
            <label>
              D.O.B:
              <input type="date" name="dateOfBirth" />
            </label>
            <div>
            <label>
            Gender:
            <div>
                <input type="radio" id="male" name="gender" value="male"/>
                <label for="male">Male</label><br/>
                <input type="radio" id="female" name="gender" value="female"/>
                <label for="female">Female</label><br/>
                <input type="radio" id="other" name="gender" value="other"/>
                <label for="other">Other</label>
            </div>
            </label>
            </div>

            <label>
            Email:
            <input type="text" name="email" />
          </label>

          <label>
          Phone:
          <input type="text" name="phone" />
        </label>

        <label>
          TFN:
          <input type="text" name="tfn" />
        </label>

        <label>
          Super:
          <input type="text" name="super" />
        </label>

            <input type="submit" value="Create Employee" />  
          </div>
        )
    }
}
