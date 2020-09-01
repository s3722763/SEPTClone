import React, { Component } from 'react'

export default class Booking extends Component {
    render() {
        return (
            <div>
            <h2>New Booking Form</h2>
            <label>
           Select Category:
            <select>
            <option value="category 1">Category 1</option>
            <option value="category 2">Category 2</option>
            <option value="category 3">Category 3</option>
            <option value="category 4">Category 4</option>
          </select>
        </label>
        <label>
        Select Service:
         <select>
         <option value="service 1">Service 1</option>
         <option value="service 2">Service 2</option>
         <option value="service 3">Service 3</option>
         <option value="service 4">Service 4</option>
       </select>
     </label>
     <label>
       Preferred Staff:
         <select>
         <option value="sandra">Sandra</option>
         <option value="angelique">Angelique</option>
         <option value="daniel">Daniel</option>
       </select>
     </label>
     <div>
     <label>Select Date & Time: </label>
     </div>
     <label>
      Notes:
      <textarea  placeholder="Type your message.." styles="height:200px"></textarea>
    </label>


        <input type="submit" value="Submit" />
          </div>
        )
    }
}