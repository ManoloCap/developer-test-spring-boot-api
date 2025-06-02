import { API_CONFIG } from '../config/api';
import type { SmsRequest } from '../types/sms';

export class SmsService {
  private baseUrl = API_CONFIG.BASE_URL;

  async sendSms(request: SmsRequest, apiVersion: string = '1.1.0'): Promise<string[]> {
    const url = `${this.baseUrl}${API_CONFIG.ENDPOINTS.SMS_SEND}`;
    
    const headers: Record<string, string> = {
      'Content-Type': 'application/json',
    };
    
    if (apiVersion) {
      headers['X-API-Version'] = apiVersion;
    }

    try {
      const response = await fetch(url, {
        method: 'POST',
        headers,
        body: JSON.stringify(request),
      });

      if (!response.ok) {
        if (response.status === 400) {
          const errorData = await response.json();
          throw new SmsValidationError(errorData.message, errorData.fieldErrors);
        }
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data: string[] = await response.json();
      return data;
      
    } catch (error) {
      if (error instanceof SmsValidationError) {
        throw error;
      }
      throw new Error('Failed to send SMS. Please check your connection and try again.');
    }
  }
}

export class SmsValidationError extends Error {
  constructor(
    message: string,
    public fieldErrors: Record<string, string>
  ) {
    super(message);
    this.name = 'SmsValidationError';
  }
}

export const smsService = new SmsService(); 