export interface AuthResponse {
  user_id: number;
  role: string;
  token: string;
}

export interface RegisterResponse extends AuthResponse {}
