import React, { Component } from 'react'

export default class SignUp extends Component {
    render() {
        return (
            <div>
                <form>
                <h1>Sign up</h1>
            
                <label>Username</label>
                <input type="text" name="username" placeholder="Username" value="" required/>
                <label>Password</label>
                <input type="password" name="password" placeholder="Password" value="" required/>
                
                <label>Email</label>
                <input type="Email" name="email" placeholder="Email eg email@gmail.com" value="" required/>
                <label>Given name</label>
                <input type="text" name="firstName" placeholder="Givenname" value="" required/>
                <label>Surname</label>
                <input type="text" name="lastName" placeholder="Surname" value="" required/>
                <label>Gender</label>
                <input type="text" name="gender" placeholder="Gender" value="" required/>
                <label>Address</label>
                <input type="text" name="address" placeholder="Address eg 10 Movie avenue" value="" required/>
                <label>Country</label>
                <input type="text" name="country" placeholder="Country eg Australia" value="" required/>
                
                <input type="submit" value="Sign Up" />
                </form>
            </div>
        )
    }
}
