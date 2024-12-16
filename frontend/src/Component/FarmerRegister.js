import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";
import "./FarmerRegister.css";

function FarmerRegister() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form Data:", formData);
    navigate("/login");
  };

  return (
    <>
      {/* <Navbar /> */}
      <div className="container">
        <div className="form-container">
          <h2>Register for Farmer's Market</h2>
          <form onSubmit={handleSubmit} className="form">
            <div className="form-group">
              <label>Name</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
                className="input"
              />
            </div>
            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                className="input"
              />
            </div>
            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
                className="input"
              />
            </div>
            <button type="submit" className="button">
              Register
            </button>
          </form>
          <p className="redirect">
            Already have an account?{" "}
            <span
              onClick={() => navigate("/login")}
              className="register-link"
            >
              Login Here
            </span>
          </p>
        </div>
      </div>
    </>
  );
}

export default FarmerRegister;
