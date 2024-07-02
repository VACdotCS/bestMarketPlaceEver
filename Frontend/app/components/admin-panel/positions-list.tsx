"use client";

import { useState } from "react";
import { Button } from "../ui/button";
import Modal from "../layout/modal";
import { PositionItem } from "./position-item";
import { useQuery } from "@tanstack/react-query";
import ProductService from "@/app/api/product-service";
import { Loading } from "../layout/loading";

export const PositionsList = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpenModal = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["positions"],

    queryFn: async () => {
      const response = await ProductService.getFullAssortment();
      return response.data;
    },
  });

  const allProducts = data;

  return (
    <>
      <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
        <div className="flex w-full flex-col">
          <div className="flex flex-col gap-3">
            <h3 className="text-center text-xl font-semibold">
              Добавить позицию
            </h3>
            <PositionItem />
            <div className="flex w-full items-center gap-3">
              <Button color="gray" onClick={() => handleCloseModal()}>
                Отмена
              </Button>
              <Button color="dark">Добавить</Button>
            </div>
          </div>
        </div>
      </Modal>

      <div className="flex h-[84vh] flex-col gap-2 rounded-xl bg-white p-6 shadow-lg">
        <h2 className="flex-none text-lg font-semibold">Позиции</h2>
        <div className="flex-grow overflow-auto">
          {isLoading && <Loading />}
          {!allProducts?.length && !isLoading ? (
            <div className="flex h-full w-full items-center justify-center">
              <p className="opacity-50">Позиции отстутствуют...</p>
            </div>
          ) : (
            allProducts?.map((position) => (
              <PositionItem
                key={position.id}
                id={position.id}
                title={position.title}
                price={position.price}
                discountPrice={position.discountPrice}
                unit={position.unit}
                availableQuantity={position.availableQuantity}
                description={position.description}
                image={position.image}
                category={position.category}
              />
            ))
          )}
        </div>
        <div className="flex-none">
          <Button
            color="dark"
            onClick={() => handleOpenModal()}
            disabled={isLoading}
          >
            Добавить
          </Button>
        </div>
      </div>
    </>
  );
};
