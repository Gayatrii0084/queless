import { useState } from 'react';
import api from '../api/axios';
import './PatientRegistration.css';

function PatientRegistration() {
  const queueId = 1;

  const [patientName, setPatientName] = useState('');
  const [age, setAge] = useState('');
  const [gender, setGender] = useState('Male');
  const [phone, setPhone] = useState('');

  const [submitting, setSubmitting] = useState(false);
  const [successData, setSuccessData] = useState(null);
  const [errorMsg, setErrorMsg] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Form Validation
    if (!patientName.trim()) {
      setErrorMsg('Please enter a valid patient name.');
      return;
    }
    const numAge = Number(age);
    if (!age || isNaN(numAge) || numAge <= 0) {
      setErrorMsg('Please enter a valid positive age.');
      return;
    }
    if (!gender) {
      setErrorMsg('Please select a gender.');
      return;
    }
    if (!phone.trim()) {
      setErrorMsg('Please enter a valid phone number.');
      return;
    }

    setSubmitting(true);
    setErrorMsg(null);
    setSuccessData(null);

    const payload = {
      patientName: patientName.trim(),
      age: numAge,
      gender: gender,
      phone: phone.trim()
    };

    try {
      const response = await api.post(`/patient/queue/${queueId}`, payload);
      setSuccessData(response.data);
      // Reset form fields
      setPatientName('');
      setAge('');
      setGender('Male');
      setPhone('');
    } catch (err) {
      console.error('Registration failed:', err);
      setErrorMsg(err.response?.data?.message || 'Failed to register patient. Please check your backend server.');
    } finally {
      setSubmitting(false);
    }
  };

  const handleRegisterAnother = () => {
    setSuccessData(null);
    setErrorMsg(null);
  };

  return (
    <div className="reg-container">
      {/* Header */}
      <header className="reg-header">
        <div className="brand-section">
          <div className="brand-icon">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.2">
              <path d="M12 6v12M6 12h12" />
            </svg>
          </div>
          <div>
            <h1 className="brand-name">QueLess</h1>
            <p className="brand-tagline">Hospital Queue Management System</p>
          </div>
        </div>
        <span className="header-badge">Patient Registration</span>
      </header>

      {/* Main Registration Card */}
      <div className="reg-card">
        <h2 className="reg-card-title">New Patient Registration</h2>

        {/* Queue Info Summary */}
        <div className="queue-summary-banner">
          <div className="summary-item">
            <span className="summary-label">Queue</span>
            <span className="summary-value">General OPD</span>
          </div>
          <div className="summary-item">
            <span className="summary-label">Department</span>
            <span className="summary-value">General Medicine</span>
          </div>
          <div className="summary-item">
            <span className="summary-label">Doctor</span>
            <span className="summary-value">Dr. Anjali Sharma</span>
          </div>
        </div>

        {/* Success Screen */}
        {successData && (
          <div className="success-banner">
            <div className="success-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5">
                <path d="M20 6L9 17l-5-5" strokeLinecap="round" strokeLinejoin="round"/>
              </svg>
            </div>
            <h3 className="success-title">Patient Registered Successfully!</h3>
            
            <div className="token-badge-container">
              <span className="token-badge-label">Generated Token Number</span>
              <span className="token-badge-value">#{successData.tokenNumber}</span>
            </div>

            <div className="patient-info-summary">
              Patient: <strong>{successData.patientName}</strong> ({successData.gender}, {successData.age} yrs)
            </div>

            <button type="button" className="btn-reset" onClick={handleRegisterAnother}>
              Register Another Patient
            </button>
          </div>
        )}

        {/* Error Message */}
        {errorMsg && (
          <div className="error-alert">
            {errorMsg}
          </div>
        )}

        {/* Registration Form */}
        <form className="reg-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="patientName">
              Patient Name <span className="required">*</span>
            </label>
            <input
              type="text"
              id="patientName"
              className="form-input"
              placeholder="e.g. Test Patient"
              value={patientName}
              onChange={(e) => setPatientName(e.target.value)}
              disabled={submitting}
              required
            />
          </div>

          <div className="form-row">
            <div className="form-group">
              <label htmlFor="age">
                Age <span className="required">*</span>
              </label>
              <input
                type="number"
                id="age"
                className="form-input"
                placeholder="e.g. 25"
                min="1"
                max="120"
                value={age}
                onChange={(e) => setAge(e.target.value)}
                disabled={submitting}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="gender">
                Gender <span className="required">*</span>
              </label>
              <select
                id="gender"
                className="form-select"
                value={gender}
                onChange={(e) => setGender(e.target.value)}
                disabled={submitting}
                required
              >
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </select>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="phone">
              Phone Number <span className="required">*</span>
            </label>
            <input
              type="tel"
              id="phone"
              className="form-input"
              placeholder="e.g. 9876543210"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
              disabled={submitting}
              required
            />
          </div>

          <button type="submit" className="btn-submit" disabled={submitting}>
            {submitting ? 'Registering Patient...' : 'Register Patient'}
          </button>
        </form>
      </div>
    </div>
  );
}

export default PatientRegistration;
