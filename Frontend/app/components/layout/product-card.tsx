"use client";

import { Button } from "@/components/ui/button";
import { Counter } from "@/components/ui/counter";
import cartStore from "@/store/cart";
import { IProduct } from "@/types";
import { formatNumber } from "@/utils";
import { observer } from "mobx-react-lite";
import { useState } from "react";
import toast from "react-hot-toast";
import Modal from "./modal";

export const ProductCard = observer(
  ({
    id,
    title,
    price,
    discountPrice,
    description,
    unit,
    availableQuantity,
    image = "./products/no-product.png",
    category = "Без категории",
  }: IProduct) => {
    const [quantity, setQuantity] = useState(1);
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleOpenModal = () => {
      setIsModalOpen(true);
    };

    const handleCloseModal = () => {
      setIsModalOpen(false);
    };

    return (
      <div className="group flex h-[30em] cursor-pointer flex-col justify-center rounded-lg border border-transparent p-4 transition-all hover:border-black">
        <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
          <div className="flex flex-col gap-10">
            <div className="grid grid-cols-5 items-start gap-10">
              <div className="col-span-2">
                <img
                  src={image}
                  alt={title}
                  className="h-full w-full object-contain"
                />
              </div>
              <div className="col-span-3 flex items-start justify-end">
                <div className="flex w-full flex-col gap-3">
                  <h3 className="text-3xl font-semibold">{title}</h3>
                  <div className="flex flex-col gap-1 font-medium leading-none">
                    <div className="flex items-center gap-3">
                      <span className="text-2xl font-bold text-green-600">
                        ₽{formatNumber(price)}
                      </span>
                      <s className="text-xl text-secondary-text">
                        ₽{formatNumber(discountPrice)}
                      </s>
                    </div>
                    <div className="flex items-center gap-2 text-secondary-text">
                      <span>1{unit}</span>| {category}
                    </div>
                    <span className="text-secondary-text/80">
                      {availableQuantity}
                      {unit} в наличии
                    </span>
                  </div>
                  {description}
                </div>
              </div>
            </div>
            <div className="grid w-full grid-cols-5 gap-2">
              <div className="col-span-1">
                <Counter quantity={quantity} setQuantity={setQuantity} />
              </div>
              <div className="col-span-4">
                <Button
                  size="small"
                  onClick={() => {
                    cartStore.addItem(id, quantity);
                    handleCloseModal();
                    setQuantity(1);
                    toast("Товар добавлен в корзину!");
                  }}
                >
                  Добавить
                </Button>
              </div>
            </div>
          </div>
        </Modal>
        <div
          className="mx-auto flex h-72 w-full items-center justify-center p-2"
          onClick={handleOpenModal}
        >
          <img
            src={image}
            alt={title}
            className="h-full w-full object-contain"
          />
        </div>
        <span className="text-lg font-bold">{title}</span>
        <div className="flex items-center gap-1.5 font-semibold">
          <span>1{unit}</span>
          <span className="hidden group-hover:flex">|</span>
          <span className="hidden text-secondary-text group-hover:flex">
            {availableQuantity}
            {unit} в наличии
          </span>
        </div>
        <div className="flex flex-col gap-2">
          <span className="text-xl font-bold text-green-600 transition-all group-hover:text-3xl">
            ₽{formatNumber(price)}
          </span>
          <div className="flex group-hover:hidden">
            <s className="text-xl font-bold text-secondary-text/50">
              ₽{formatNumber(discountPrice)}
            </s>
          </div>
          <div className="hidden items-center gap-2 group-hover:flex">
            <Counter quantity={quantity} setQuantity={setQuantity} />
            <Button
              size="small"
              onClick={() => {
                cartStore.addItem(id, quantity);
                setQuantity(1);
                toast.success(`Товар "${title}" добавлен в корзину!`, {
                  style: {
                    border: "1px solid #8A2525",
                    padding: "16px",
                    color: "#8A2525",
                  },
                  iconTheme: {
                    primary: "#8A2525",
                    secondary: "#FFFAEE",
                  },
                });
              }}
            >
              Добавить
            </Button>
          </div>
        </div>
      </div>
    );
  },
);
