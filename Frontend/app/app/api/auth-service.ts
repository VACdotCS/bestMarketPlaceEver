import api from "./index";
import { AuthRequest, RegisterRequest } from "@/app/api/[models]/authRequests";
import {
  AuthResponse,
  RegisterResponse,
} from "@/app/api/[models]/authResponses";
import { AxiosResponse } from "axios";

export default class AuthService {
  static async login(user: AuthRequest): Promise<AxiosResponse<AuthResponse>> {
    return api.post<AuthResponse>("/auth", user);
  }

  static async register(
    user: RegisterRequest,
  ): Promise<AxiosResponse<RegisterResponse>> {
    return api.post<RegisterResponse>("/auth/register", user);
  }
}
