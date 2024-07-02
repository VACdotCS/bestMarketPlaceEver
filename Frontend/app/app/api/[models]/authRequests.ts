export interface AuthRequest {
  phone: string;
  password: string;
}

export interface RegisterRequest extends AuthRequest {
  email: string;
  username: string;
}
