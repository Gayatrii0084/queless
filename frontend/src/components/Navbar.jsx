import { NavLink, Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/dashboard" className="navbar-brand">
          <div className="navbar-brand-icon">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.2">
              <path d="M12 6v12M6 12h12" />
            </svg>
          </div>
          <span className="navbar-brand-title">QueLess</span>
        </Link>

        <ul className="navbar-links">
          <li>
            <NavLink
              to="/dashboard"
              className={({ isActive }) => (isActive ? 'nav-item-link active' : 'nav-item-link')}
            >
              Dashboard
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/patient-registration"
              className={({ isActive }) => (isActive ? 'nav-item-link active' : 'nav-item-link')}
            >
              Patient Registration
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/queue"
              className={({ isActive }) => (isActive ? 'nav-item-link active' : 'nav-item-link')}
            >
              Queue Management
            </NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
