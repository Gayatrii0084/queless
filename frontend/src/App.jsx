import { useEffect } from 'react'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import api from './api/axios'
import Layout from './components/Layout'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import QueueManagement from './pages/QueueManagement'
import PatientRegistration from './pages/PatientRegistration'
import './App.css'

function App() {
  useEffect(() => {
    api.get('/patient/getAll')
      .then((response) => {
        console.log('Patients data:', response.data);
      })
      .catch((error) => {
        console.error('API call failed:', error);
      });
  }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />} />
        
        {/* Layout with Navbar */}
        <Route element={<Layout />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/queue" element={<QueueManagement />} />
          <Route path="/patient-registration" element={<PatientRegistration />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
