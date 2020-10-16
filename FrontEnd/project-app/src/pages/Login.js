import React, { Component } from "react";

export default class Login extends Component {
    render() {
        return (
            <div className = "login-container">
            <form>
                <h2>Login</h2>

                <div className="form-group">
                    <label>Username</label>
                    <input type="text" className="form-control" placeholder="Enter username" />
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input type="password" className="form-control" placeholder="Enter password" />
                </div>

                <input type="submit" value="Login" /> 
                <p className="forgot-password text-right">
                    Forgot <a href="#">password?</a>
                </p>
                <p className="sign-up text-right">
                    No existing account? <a href="/signUp">Sign Up!</a>
                </p>
            </form>
            </div>
        );
    }
}