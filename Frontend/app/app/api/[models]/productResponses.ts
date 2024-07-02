export interface ProductResponse {
  product_id: number;
  title: string;
  image: string;
  description: string;
  price: number;
  discount_price: number;
  quantity_available: number;
  category: string;
  unit: string;
  delivery_days: number;
}
