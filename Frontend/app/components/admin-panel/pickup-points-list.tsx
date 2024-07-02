"use client";

import { useState } from "react";
import { Button } from "../ui/button";
import Modal from "../layout/modal";
import { PositionItem } from "./position-item";
import { useMutation, useQuery } from "@tanstack/react-query";
import ProductService from "@/app/api/product-service";
import { Loading } from "../layout/loading";
import PointsService from "@/app/api/points-service";
import { PickupPointItem } from "./pickup-point-item";
import toast from "react-hot-toast";
import { HouseIcon } from "../icons/house-icon";
import { Field } from "../ui/field";
import { OtpField } from "../ui/otp-field";

export const PickupPointsList = () => {
  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["points"],

    queryFn: async () => {
      const response = await PointsService.getAllPoints();
      return response.data;
    },
  });

  const allPoints = data?.sort((a, b) => a.id - b.id);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const handleOpenModal = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const [addressData, setAddressData] = useState("");
  const [managerIdData, setManagerIdData] = useState<string[]>(
    new Array(2).fill(""),
  );

  const handleManagerIdData = (otp: string[]) => {
    setManagerIdData(otp);
  };

  const handleUpdate = (e) => {
    e.preventDefault;
    addMutation.mutate();
  };

  const addMutation = useMutation({
    mutationKey: ["updatePickupPoint"],
    mutationFn: async () => {
      try {
        const response = await PointsService.createNewPoint({
          address: addressData,
          manager_id: parseInt(managerIdData.join("")),
        });
        return response;
      } catch (error: any) {
        throw new Error(error.response.data);
      }
    },
    onSuccess: () => {
      toast.success("Пункт выдачи обновлен");
    },
    onError: (error) => {
      toast.error(error.message);
    },
  });

  return (
    <>
      <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
        <div className="flex w-full flex-col">
          <div className="flex flex-col gap-3">
            <h3 className="text-center text-xl font-semibold">
              Добавление нового пункта выдачи
            </h3>
            <div className="flex flex-col gap-3">
              <div className="flex items-center gap-1">
                <Field
                  icon={<HouseIcon />}
                  placeholder="Адрес пункта выдачи"
                  value={addressData}
                  onChange={(e) => setAddressData(e.target.value)}
                />
              </div>
              <div className="flex w-full items-center justify-between">
                <p className="font-semibold">ID менеджера</p>
                <div className="">
                  <OtpField onOtpChange={handleManagerIdData} />
                </div>
              </div>
            </div>
            <div className="flex w-full items-center gap-3">
              <Button color="gray" onClick={() => handleCloseModal()}>
                Отмена
              </Button>
              <Button
                color="dark"
                onClick={handleUpdate}
                isLoading={addMutation.isPending}
                disabled={addMutation.isPending}
              >
                Сохранить
              </Button>
            </div>
          </div>
        </div>
      </Modal>

      <div className="flex h-[84vh] flex-col gap-2 rounded-xl bg-white p-6 shadow-lg">
        <h2 className="flex-none text-lg font-semibold">Пункты выдачи</h2>
        <div className="flex-grow overflow-auto">
          {isLoading && <Loading />}
          {!allPoints?.length && !isLoading ? (
            <div className="flex h-full w-full items-center justify-center">
              <p className="select-none opacity-50">
                Пункты выдачи отстутствуют...
              </p>
            </div>
          ) : (
            <div className="flex flex-col gap-4">
              {allPoints?.map((point) => (
                <PickupPointItem
                  id={point?.id}
                  address={point?.address}
                  manager_name={point?.manager_name}
                  income={point && parseInt(point.income, 10)}
                />
              ))}
            </div>
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
