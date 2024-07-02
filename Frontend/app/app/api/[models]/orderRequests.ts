type OrderedProduct = {
  product_id: number;
  count: number;
};

type ReceivedProductId = number;
type ReturnedProductId = number;

export interface OrderRequest {
  pickup_point_id: number;
  products: OrderedProduct[];
}

export interface OrderConfirmationRequest {
  received: ReceivedProductId[];
  returned: ReturnedProductId[];
}
