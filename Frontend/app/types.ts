export interface IProduct {
  id: number;
  title: string;
  price: number;
  discountPrice: number;
  description: string;
  unit: string;
  availableQuantity: number;
  image: string;
  category?: string;
}
export interface IDefaultBanner {
  title: string;
  description: string;
}

export interface IPickupPoint {
  id?: number;
  address?: string;
  manager_name?: string;
  income?: number;
}

export interface IUser {
  code: number;
  name: string;
  role: IUserRole;
  redemptionSum: number;
  redemptionPercentage: number;
  discount: number;
}

export interface IDeliveryItem extends Partial<IProduct> {
  count: number;
  dateOrder: string;
  dateDelivery: string;
  status?: "в пути" | "отказ" | "доставлено" | "получено";
}

export type IPositionItem = Partial<IProduct>;

export type IBannerProduct = Pick<IProduct, "title" | "image" | "price" | "discountPrice">;

export type IUserRole = "USER" | "ADMIN" | "MANAGER" | undefined;
