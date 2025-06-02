export const API_CONFIG = {
    BASE_URL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080',
    ENDPOINTS: {
      SMS_SEND: '/api/sms/send',
      SMS_HEALTH: '/api/sms/health',
      SMS_VERSIONS: '/api/sms/versions'
    }
  };