import React, { Component } from 'react'

export default class Schedule extends Component {
    render() {
        return (

            <div className="row">
                <div className="col m12" >
                    <div className="section">
                        <h5>Schedule Editor</h5>

                        <table className="highlight">
                            <thead>
                                <tr>
                                    <th data-field="name">Name</th>
                                    <th data-field="name">Mon</th>
                                    <th data-field="name">Tues</th>
                                    <th data-field="name">Wed</th>
                                    <th data-field="name">Thurs</th>
                                    <th data-field="name">Fri</th>
                                </tr>
                            </thead>
                            <tbody>
                                        <tr>
                                            <td className="fullName">
                                            </td>
                                            <td className="">
                                                <div className="input-field schedule">
                                                    <select className="browser-default" name="monday">
                                                    <option value=""></option>
                                                    <option value="8am-5pm">8am-5pm</option>
                                                    <option value="9am-6pm">9am-6pm</option>
                                                    <option value="10am-7pm">10am-7pm</option>
                                                </select>
                                                </div>
                                            </td>
                                            <td>
                                                <div className="input-field schedule">
                                                    <select className="browser-default" name="tuesday">
                                                    <option value=""></option>
                                                    <option value="8am-5pm">8am-5pm</option>
                                                    <option value="9am-6pm">9am-6pm</option>
                                                    <option value="10am-7pm">10am-7pm</option>
                                                </select>
                                                </div>
                                            </td>
                                            <td>
                                                <div className="input-field schedule">
                                                    <select className="browser-default" name="wednesday">
                                                    <option value=""></option>
                                                    <option value="8am-5pm">8am-5pm</option>
                                                    <option value="9am-6pm">9am-6pm</option>
                                                    <option value="10am-7pm">10am-7pm</option>
                                                </select>
                                                </div>
                                            </td>
                                            <td>
                                                <div className="input-field schedule">
                                                    <select className="browser-default" name="thursday">
                                                    <option value=""></option>
                                                    <option value="8am-5pm">8am-5pm</option>
                                                    <option value="9am-6pm">9am-6pm</option>
                                                    <option value="10am-7pm">10am-7pm</option>
                                                </select>
                                                </div>
                                            </td>
                                            <td>
                                                <div className="input-field schedule">
                                                    <select className="browser-default" name="friday" >
                                                    <option value=""></option>
                                                    <option value="8am-5pm">8am-5pm</option>
                                                    <option value="9am-6pm">9am-6pm</option>
                                                    <option value="10am-7pm">10am-7pm</option>
                                                </select>
                                                </div>
                                            </td>
                                           
                                            <td>
                                                <button  className="addSchedule"  className="btn btn-small waves-effect waves-light green accent-3">Add</button>
                                            </td>
                                            <td>
                                                <button className="clearSchedule" className="btn btn-small waves-effect waves-light green accent-3">Clear</button>
                                            </td>
                                        </tr>
                                    
                            </tbody>
                        </table>
                      </div>
                    </div>
                </div>
        );
    }
}
