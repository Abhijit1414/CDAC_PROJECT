// Importing required dependencies and components
import React, { useRef } from 'react'; // React for component creation, useRef for creating references to DOM elements.
import Navbar from './Navbar'; // Importing the Navbar component for navigation.
import Footer from './Footer'; // Importing the Footer component for the footer section.
import "./Homepage.css"; // Importing the CSS file for styling.

const Homepage = () => {
  // Using useRef to create references for each section. These will allow smooth scrolling to specific sections.
  const homeRef = useRef(null);
  const whyUsRef = useRef(null);
  const howItWorksRef = useRef(null);
  const aboutRef = useRef(null);
  const contactRef = useRef(null);
  const orderRef = useRef(null);

  return (
    <div className="homepage">
      {/* Rendering the Navbar component and passing scroll functions as props */}
      <Navbar 
        scrollToHome={() => homeRef.current.scrollIntoView({ behavior: 'smooth' })} // Scrolls to the home section smoothly.
        scrollToWhyUs={() => whyUsRef.current.scrollIntoView({ behavior: 'smooth' })} // Scrolls to the "Why Us" section.
        scrollToHowItWorks={() => howItWorksRef.current.scrollIntoView({ behavior: 'smooth' })} // Scrolls to "How It Works" section.
        scrollToAbout={() => aboutRef.current.scrollIntoView({ behavior: 'smooth' })} // Scrolls to the "About Us" section.
        scrollToContact={() => contactRef.current.scrollIntoView({ behavior: 'smooth' })} // Scrolls to the "Contact Us" section.
        scrollToOrder={() => orderRef.current.scrollIntoView({ behavior: 'smooth' })} // Scrolls to the "Order" section.
      />
      
      {/* Home Section */}
      <div className="home-section" ref={homeRef}> {/* Ref for scrolling */}
        <div className="home-content">
          <h1>Grains, Fresh Fruits, Vegetables & Much more.....</h1> {/* Section title */}
          <p>Directly from Farmers to Your Doorstep</p> {/* Section description */}
          <button 
            className="btn-order" 
            onClick={() => orderRef.current.scrollIntoView({ behavior: 'smooth' })} // Smooth scroll to the "Order" section on button click.
          >
            Order Now
          </button>
        </div>
      </div>

      {/* Why Us Section */}
      <section className="section why-us" ref={whyUsRef}> {/* Ref for scrolling */}
        <div className="section-content">
          <h2>Why Choose Us?</h2> {/* Section title */}
          <p>
            We provide high-quality, Grains, fresh fruits and vegetables that are sourced directly from local farmers.
            Our goal is to support sustainable agriculture and bring farm-fresh produce to your doorstep.
            You can trust us to deliver the best products while promoting local farmers and reducing food miles.
          </p>
        </div>
      </section>

      {/* How It Works Section */}
      <section className="section how-it-works" ref={howItWorksRef}> {/* Ref for scrolling */}
        <div className="section-content">
          <h2>How It Works</h2> {/* Section title */}
          <p>
            Our process is simple and easy:
            <ol> {/* Ordered list to outline the process */}
              <li>Browse our wide range of Grains, fresh fruits and vegetables.</li>
              <li>Add your items to your cart and check out.</li>
              <li>We deliver the products right to your doorstep, directly from the farm.</li>
            </ol>
            Enjoy fresh produce without the hassle!
          </p>
        </div>
      </section>

      {/* About Us Section */}
      <section className="section about-us" ref={aboutRef}> {/* Ref for scrolling */}
        <div className="section-content">
          <h2>About Us</h2> {/* Section title */}
          <p>
            We are a marketplace designed to connect consumers with local farmers. Our platform enables
            you to buy the freshest produce directly from farmers in your region. By eliminating intermediaries,
            we ensure better prices for farmers and fresher produce for you.
          </p>
        </div>
      </section>

      {/* Contact Us Section */}
      <section className="section contact-us" ref={contactRef}> {/* Ref for scrolling */}
        <div className="section-content">
          <h2>Contact Us</h2> {/* Section title */}
          <p>
            Have questions or feedback? Our team is here to help. Whether you're looking for more information
            about our products or need support with your order, don't hesitate to reach out to us.
          </p>
          {/* Contact form */}
          <form>
            <div className="form-group">
              <label htmlFor="name">Your Name:</label>
              <input 
                type="text" 
                id="name" 
                className="form-control" 
                placeholder="Enter your name" 
              />
            </div>
            <div className="form-group">
              <label htmlFor="email">Your Email:</label>
              <input 
                type="email" 
                id="email" 
                className="form-control" 
                placeholder="Enter your email" 
              />
            </div>
            <div className="form-group">
              <label htmlFor="message">Your Message:</label>
              <textarea 
                id="message" 
                className="form-control" 
                placeholder="Write your message here"
              ></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Send Message</button>
          </form>
        </div>
      </section>

      {/* Order Section */}
      <section className="section order" ref={orderRef}> {/* Ref for scrolling */}
        <div className="section-content">
          <h2>Order Fresh Produce</h2> {/* Section title */}
          <p>Select your favorite fruits and vegetables and have them delivered to your doorstep!</p>
          <button className="btn-order">Start Ordering</button> {/* Button to initiate the order process */}
        </div>
      </section>

      {/* Footer Section */}
      <Footer /> {/* Render the Footer component */}
    </div>
  );
};

export default Homepage; // Export the Homepage component for use in other parts of the application.
