import { useState, useEffect, useCallback } from 'react';
import api from '../api/axios';
import './Dashboard.css';

function Dashboard() {
  const queueId = 1;

  const [currentPatient, setCurrentPatient] = useState(null);
  const [nextPatient, setNextPatient] = useState(null);
  const [waitingCount, setWaitingCount] = useState(0);
  const [completedCount, setCompletedCount] = useState(0);
  const [skippedCount, setSkippedCount] = useState(0);
  const [totalCount, setTotalCount] = useState(0);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [actionProcessing, setActionProcessing] = useState(false);
  const [actionError, setActionError] = useState(null);

  const fetchDashboardData = useCallback(async (showFullLoading = false) => {
    if (showFullLoading) {
      setLoading(true);
    }
    setError(null);

    try {
      const [
        currentRes,
        nextRes,
        waitingRes,
        completedRes,
        skippedRes,
        totalRes
      ] = await Promise.all([
        api.get(`/patient/queue/${queueId}/current`),
        api.get(`/patient/queue/${queueId}/next`),
        api.get(`/patient/queue/${queueId}/waiting-count`),
        api.get(`/patient/queue/${queueId}/completed-count`),
        api.get(`/patient/queue/${queueId}/skipped-count`),
        api.get(`/patient/queue/${queueId}/total-count`)
      ]);

      setCurrentPatient(currentRes.data || null);
      setNextPatient(nextRes.data || null);
      setWaitingCount(waitingRes.data ?? 0);
      setCompletedCount(completedRes.data ?? 0);
      setSkippedCount(skippedRes.data ?? 0);
      setTotalCount(totalRes.data ?? 0);
    } catch (err) {
      console.error('Error fetching dashboard data:', err);
      setError('Failed to fetch dashboard data from server. Please check backend connection.');
    } finally {
      setLoading(false);
    }
  }, [queueId]);

  useEffect(() => {
    fetchDashboardData(true);
  }, [fetchDashboardData]);

  // Action Handlers
  const handleCallNext = async () => {
    if (actionProcessing) return;
    setActionProcessing(true);
    setActionError(null);

    try {
      await api.put(`/patient/queue/${queueId}/call-next`);
      await fetchDashboardData(false);
    } catch (err) {
      console.error('Error calling next patient:', err);
      setActionError('Failed to call next patient. ' + (err.response?.data?.message || err.message));
    } finally {
      setActionProcessing(false);
    }
  };

  const handleComplete = async () => {
    if (!currentPatient?.id || actionProcessing) return;
    setActionProcessing(true);
    setActionError(null);

    try {
      await api.put(`/patient/complete/${currentPatient.id}`);
      await fetchDashboardData(false);
    } catch (err) {
      console.error('Error completing patient consultation:', err);
      setActionError('Failed to complete patient consultation. ' + (err.response?.data?.message || err.message));
    } finally {
      setActionProcessing(false);
    }
  };

  const handleSkip = async () => {
    if (!currentPatient?.id || actionProcessing) return;
    setActionProcessing(true);
    setActionError(null);

    try {
      await api.put(`/patient/skip/${currentPatient.id}`);
      await fetchDashboardData(false);
    } catch (err) {
      console.error('Error skipping patient:', err);
      setActionError('Failed to skip patient. ' + (err.response?.data?.message || err.message));
    } finally {
      setActionProcessing(false);
    }
  };

  if (loading) {
    return (
      <div className="state-container">
        <div className="loading-text">Loading...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="state-container">
        <div className="error-box">
          <h3>Connection Error</h3>
          <p>{error}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="dashboard-container">
      {/* Header */}
      <header className="dashboard-header">
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
        <span className="header-badge">Dashboard</span>
      </header>

      {/* Queue Information Banner */}
      <section className="queue-info-banner">
        <div className="queue-info-item">
          <span className="info-label">Queue</span>
          <span className="info-value">General OPD</span>
        </div>
        <div className="queue-info-item">
          <span className="info-label">Department</span>
          <span className="info-value">General Medicine</span>
        </div>
        <div className="queue-info-item">
          <span className="info-label">Attending Doctor</span>
          <span className="info-value">Dr. Anjali Sharma</span>
        </div>
      </section>

      {/* Current and Next Patient Cards */}
      <section className="patient-spotlight-grid">
        {/* CURRENT PATIENT */}
        <div className="patient-card current">
          <h2 className="card-title">Current Patient</h2>
          {currentPatient ? (
            <div>
              <div className="token-display">
                <span className="info-label">Token</span>
                <span className="token-number">#{currentPatient.tokenNumber}</span>
              </div>
              <div className="patient-name">{currentPatient.patientName}</div>
              <span className={`patient-status-pill ${currentPatient.status}`}>
                {currentPatient.status}
              </span>
            </div>
          ) : (
            <div>
              <div className="patient-name" style={{ color: '#64748b' }}>No patient currently being attended</div>
            </div>
          )}
        </div>

        {/* NEXT PATIENT */}
        <div className="patient-card next">
          <h2 className="card-title">Next Patient</h2>
          {nextPatient ? (
            <div>
              <div className="token-display">
                <span className="info-label">Token</span>
                <span className="token-number">#{nextPatient.tokenNumber}</span>
              </div>
              <div className="patient-name">{nextPatient.patientName}</div>
              <span className={`patient-status-pill ${nextPatient.status}`}>
                {nextPatient.status}
              </span>
            </div>
          ) : (
            <div>
              <div className="patient-name" style={{ color: '#64748b' }}>No waiting patient in queue</div>
            </div>
          )}
        </div>
      </section>

      {/* QUEUE ACTIONS */}
      <section className="action-controls-section">
        <h2 className="action-section-title">Queue Actions</h2>
        <div className="action-buttons-group">
          <button
            type="button"
            className="action-btn action-btn-call"
            onClick={handleCallNext}
            disabled={actionProcessing}
          >
            {actionProcessing ? 'Processing...' : 'CALL NEXT'}
          </button>

          <button
            type="button"
            className="action-btn action-btn-complete"
            onClick={handleComplete}
            disabled={!currentPatient || !currentPatient.id || actionProcessing}
          >
            COMPLETE
          </button>

          <button
            type="button"
            className="action-btn action-btn-skip"
            onClick={handleSkip}
            disabled={!currentPatient || !currentPatient.id || actionProcessing}
          >
            SKIP
          </button>
        </div>

        {actionError && (
          <div className="action-error-alert">
            {actionError}
          </div>
        )}
      </section>

      {/* STATISTICS CARDS */}
      <section>
        <h2 className="stats-header">Queue Summary Statistics</h2>
        <div className="stats-grid">
          <div className="stat-card waiting">
            <div className="info-label">Waiting Patients</div>
            <div className="stat-value">{waitingCount}</div>
          </div>
          <div className="stat-card completed">
            <div className="info-label">Completed Patients</div>
            <div className="stat-value">{completedCount}</div>
          </div>
          <div className="stat-card skipped">
            <div className="info-label">Skipped Patients</div>
            <div className="stat-value">{skippedCount}</div>
          </div>
          <div className="stat-card total">
            <div className="info-label">Total Patients</div>
            <div className="stat-value">{totalCount}</div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Dashboard;