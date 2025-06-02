export interface SmsRequest {
  message: string;
  phoneNumber: string;
}

export interface SmsResponse {
  parts: string[];
}

export interface ValidationError {
  timestamp: string;
  status: number;
  error: string;
  message: string;
  fieldErrors: Record<string, string>;
} 