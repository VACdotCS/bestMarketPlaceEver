type DeliveryStatus = "Выдан" | "Готов" | "Отказ" | "В пути";

export interface OrderedProduct {
  id: number;
  title: string;
  image: string;
  description: string;
  price: number;
  unit: string;
  delivery_days: number;
  delivery_status: DeliveryStatus;
  completion_date: string;
}

export interface OrderResponse {
  order_id: number;
  products: OrderedProduct[];
  order_date: string;
  completed: boolean;
}
