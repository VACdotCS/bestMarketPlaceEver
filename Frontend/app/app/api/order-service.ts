import { AxiosResponse } from "axios";
import api from "@/app/api/index";
import { OrderResponse } from "@/app/api/[models]/orderResponses";
import {
  OrderConfirmationRequest,
  OrderRequest,
} from "@/app/api/[models]/orderRequests";

export default class OrderService {
  /** Доступно всем*/
  static async getAllUserOrders(): Promise<AxiosResponse<OrderResponse[]>> {
    return api.get<OrderResponse[]>("/orders/user");
  }

  /** Доступно менеджеру*/
  static async getOrderForManager(
    orderId: number,
  ): Promise<AxiosResponse<OrderResponse>> {
    return api.get<OrderResponse>(`/orders/${orderId}`);
  }

  /** Доступно всем*/
  static async createOrder(order: OrderRequest): Promise<AxiosResponse> {
    return api.post(`/orders`, order);
  }

  /** Доступно менеджеру*/
  static async confirmOrder(
    orderId: number,
    data: OrderConfirmationRequest,
  ): Promise<AxiosResponse> {
    return api.post(`/orders/${orderId}`, data);
  }
}
