import React, { Component } from 'react'

export default class ContactUs extends Component {
    render() {
        return (
            <div>
            <h1>Contact Us</h1>
            <form>

            <label >First Name</label>
            <input type="text" name="firstname" placeholder="Your name..">
            </input>
        
            <label >Last Name</label>
            <input type="text" name="lastname" placeholder="Your last name..">
            </input>

            <label >Email address</label>
            <input type="email" name="email" placeholder="Your email address..">
            </input>
        
            <label >Subject</label>
            <textarea  name="subject" placeholder="Write something.."/>
        
            <input type="submit" value="Submit" />
        
          </form>
          </div>
        )
    }
}
