import api from "@/app/api/index";
import { ProductResponse } from "@/app/api/[models]/productResponses";
import { AxiosResponse } from "axios";

export default class ProductService {
  /** Доступно всем*/
  static async getFullAssortment(): Promise<AxiosResponse<ProductResponse[]>> {
    return api.get<ProductResponse[]>("/products/assortment");
  }

  /** Доступно всем*/
  static async getAvailableCategories(): Promise<AxiosResponse<string[]>> {
    return api.get<string[]>("/products/categories");
  }

  /** Доступно всем*/
  static async getSpecialOffers(): Promise<AxiosResponse<ProductResponse[]>> {
    return api.get<ProductResponse[]>("/products/special");
  }

  /** Доступно админу*/
  static async addProduct(newProduct: FormData): Promise<AxiosResponse> {
    return api.post("/products", newProduct, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }

  /** Доступно админу*/
  static async updateProduct(
    productId: number,
    productData: FormData,
  ): Promise<AxiosResponse> {
    return api.patch(`/products/${productId}`, productData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }
}
