"use client";

import cartStore from "@/store/cart";
import { observer } from "mobx-react-lite";
import { useState } from "react";
import { Button } from "../ui/button";
import { PickupPointModal } from "./pickup-point-modal";

interface OrderProps {
  pickupPointAddress?: string;
  deliveryDate?: string;
}

export const Order = observer(
  ({
    pickupPointAddress = "г. Краснодар, ул. Ставропольская, 149",
    deliveryDate = "2 июня",
  }: OrderProps) => {
    const [selectedAddress, setSelectedAddress] = useState(pickupPointAddress);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const handleOpenModal = () => {
      setIsModalOpen(true);
    };

    const handleCloseModal = () => {
      setIsModalOpen(false);
    };

    const handleSelectAddress = (address: string) => {
      setSelectedAddress(address);
      setIsModalOpen(false);
    };

    return (
      <div className="flex flex-col rounded-xl bg-white p-6 shadow-lg">
        <PickupPointModal
          isModalOpen={isModalOpen}
          handleCloseModal={handleCloseModal}
          handleSelectAddress={handleSelectAddress}
        />
        <h3 className="text-lg font-semibold">Оформление заказа</h3>
        <hr className="my-4" />
        <div className="flex flex-col gap-4">
          <div className="flex flex-col gap-2">
            <div className="flex flex-col gap-1">
              <p className="font-semibold">Доставка в пункт выдачи</p>
              <p
                className="cursor-pointer text-dark-accent underline decoration-dotted"
                onClick={() => handleOpenModal()}
              >
                {selectedAddress}
              </p>
            </div>
            <div className="flex flex-col gap-1">
              <p className="font-semibold">Дата ближайшей доставки</p>
              <p>{deliveryDate}</p>
            </div>
          </div>
          <div className="flex items-center justify-between text-lg font-bold">
            <span>Итого</span>
            <span className="flex items-center">
              ₽
              <span className="text-dark-accent">
                {Intl.NumberFormat("ru", {
                  style: "decimal",
                  minimumFractionDigits: 2,
                  maximumFractionDigits: 2,
                }).format(cartStore.getTotalPrice())}
              </span>
            </span>
          </div>
          <Button color="dark">Заказать</Button>
        </div>
      </div>
    );
  },
);
