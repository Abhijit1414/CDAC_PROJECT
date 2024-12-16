import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";


function FarmerLogin() {
  const [formData, setFormData] = useState({
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
    console.log("Login Data:", formData);
    navigate("/dashboard");
  };

  return (
    <>
      {/* <Navbar /> */}
      <div style={styles.container}>
        <div style={styles.formContainer}>
          <h2>Login to Farmer's Market</h2>
          <form onSubmit={handleSubmit} style={styles.form}>
            <div style={styles.formGroup}>
              <label>Email</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                style={styles.input}
              />
            </div>
            <div style={styles.formGroup}>
              <label>Password</label>
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
                style={styles.input}
              />
            </div>
            <button type="submit" style={styles.button}>
              Login
            </button>
          </form>
          <p style={styles.redirect}>
            Don't have an account?{" "}
            <span
              onClick={() => navigate("/register")}
              style={styles.registerLink}
            >
              Register Here
            </span>
          </p>
        </div>
      </div>
    </>
  );
}

const styles = {
  container: {
    position: "relative",
    width: "100%",
    height: "100vh", 
    backgroundImage: `url(${process.env.PUBLIC_URL + '/images/farmer-login.jpg'})`, 
    backgroundSize: "cover",
    backgroundPosition: "center",
    opacity: 1.1, 
    overflow: "hidden"
  },
  formContainer: {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    padding: "30px",
    border: "1px solid #ddd",
    borderRadius: "8px",
    backgroundColor: "rgba(112, 240, 77, 0.9)" ,
    opacity: 0.8, 
    
    boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
    textAlign: "center",
    width: "450px",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  formGroup: {
    marginBottom: "15px",
  },
  input: {
    width: "100%",
    padding: "10px",
    margin: "5px 0",
    border: "1px solid #ccc",
    borderRadius: "5px",
  },
  button: {
    padding: "10px",
    backgroundColor: "#28a745",
    color: "black",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  redirect: {
    marginTop: "15px",
    textAlign: "center",
  },
  registerLink: {
    color: "#28a745",
    cursor: "pointer",
    textDecoration: "underline",
  },
};

export default FarmerLogin;



