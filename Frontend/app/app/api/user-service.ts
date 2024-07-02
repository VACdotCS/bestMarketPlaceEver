import api from "@/app/api/index";
import { AdminPanelUserInfo, UserData } from "@/app/api/[models]/userResponses";
import { AxiosResponse } from "axios";
import {
  UserInfoRequest,
  CardInfoRequest,
} from "@/app/api/[models]/userRequests";

export default class UserService {
  /** Доступно всем*/
  static async getAllUserInfo(
    userId: number,
  ): Promise<AxiosResponse<UserData>> {
    return api.get<UserData>(`/users/${userId}`);
  }

  /** Доступно всем*/
  static async changeUserInfo(
    userId: number,
    userData: UserInfoRequest,
  ): Promise<AxiosResponse> {
    return api.patch(`/users/${userId}`, userData);
  }

  /** Доступно всем*/
  static async changePassword(
    currentPassword: string,
    confirmationPassword: string,
    newPassword: string,
  ): Promise<AxiosResponse> {
    return api.patch("/users/password", {
      currentPassword,
      newPassword,
      confirmationPassword,
    });
  }

  /** Доступно всем */
  static async changeUserCardData(
    card: CardInfoRequest,
  ): Promise<AxiosResponse> {
    return api.patch("/users/card", card);
  }

  /** Вытянуть всех юзеров (только админ) */
  static async getAllUsers(): Promise<AxiosResponse<AdminPanelUserInfo[]>> {
    return api.get<AdminPanelUserInfo[]>("/users");
  }
}
