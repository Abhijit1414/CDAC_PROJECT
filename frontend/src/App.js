import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import FarmerRegister from "./Component/FarmerRegister";
import FarmerLogin from "./Component/FarmerLogin";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from "./Component/Navbar";
import Homepage from "./Component/Homepage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/register" element={<FarmerRegister />} />
        <Route path="/login" element={<FarmerLogin />} />
        <Route path="/" element={<Homepage />} /> {/* Default route */}
      </Routes>
    </Router>
  );
}

export default App;
