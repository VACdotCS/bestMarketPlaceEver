"use client";

import OrderService from "@/app/api/order-service";
import { DeliveryItem } from "@/components/delivery/delivery-item";
import { IDeliveryItem } from "@/types";
import { useQuery } from "@tanstack/react-query";
import { Loading } from "../layout/loading";

interface DeliveryPanelProps {
  codeForReceive: string;
  items?: IDeliveryItem[];
}

export const DeliveryList = ({ codeForReceive, items }: DeliveryPanelProps) => {
  const { data, isLoading } = useQuery({
    queryKey: ["orders"],
    queryFn: async () => {
      const response = await OrderService.getAllUserOrders();
      return response.data;
    },
  });
  const allOrders = data;
  console.log(allOrders);
  return (
    <div className="flex flex-col rounded-xl bg-white p-6 shadow-lg">
      <div className="flex items-center justify-between">
        <h2 className="text-lg font-semibold text-black">
          Информация о доставках
        </h2>
      </div>
      {isLoading && (
        <div className="flex h-[62vh] flex-col">
          <Loading />
        </div>
      )}
      {!allOrders?.length && !isLoading ? (
        <div className="flex min-h-[500px] w-full items-center justify-center text-center">
          Информация о доставках отсутствует
        </div>
      ) : (
        allOrders?.map((order) => (
          <p>
            {order.products.map((product) => (
              <DeliveryItem
                title={product.ordered_product.title}
                price={product.price}
                unit={product.ordered_product.unit}
                count={product.quantity}
                status={product.delivery_status}
                dateDelivery={product.completion_date}
              />
            ))}
          </p>
        ))
      )}
    </div>
  );
};
