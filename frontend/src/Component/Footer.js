import React from 'react'
import { Link } from 'react-router-dom';

function Footer() {
    return (
        <footer className="footer">
            <p>
                &copy; 2024 Farmers Marketplace.{' '}
                <Link to="/terms">Terms & Conditions</Link>
            </p>
        </footer>
    )
}

export default Footer