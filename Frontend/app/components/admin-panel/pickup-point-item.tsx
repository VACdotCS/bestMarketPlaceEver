"use client";
import authStore from "@/store/auth";
import { IPickupPoint } from "@/types";
import { formatNumber } from "@/utils";
import { observer } from "mobx-react-lite";
import { TrashIcon } from "../icons/trash-icon";
import { Button } from "../ui/button";
import { formatId } from "@/utils/format-id";
import { EditIcon } from "../icons/edit-icon";
import Modal from "../layout/modal";
import { useState } from "react";
import { useMutation, useMutationState } from "@tanstack/react-query";
import PointsService from "@/app/api/points-service";
import { Field } from "../ui/field";
import { HouseIcon } from "../icons/house-icon";
import { PrimeReactProvider, PrimeReactContext } from "primereact/api";
import "primereact/resources/themes/lara-light-cyan/theme.css";
import { InputOtp } from "primereact/inputotp";
import { OtpField } from "../ui/otp-field";
import toast from "react-hot-toast";

export const PickupPointItem = observer(
  ({ id, address, manager_name, income }: IPickupPoint) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const handleOpenModal = () => {
      setIsModalOpen(true);
    };

    const handleCloseModal = () => {
      setIsModalOpen(false);
    };

    const [addressData, setAddressData] = useState(address);
    const [managerIdData, setManagerIdData] = useState<string[]>(
      new Array(2).fill(""),
    );

    const handleManagerIdData = (otp: string[]) => {
      setManagerIdData(otp);
    };

    const updateMutation = useMutation({
      mutationKey: ["updatePickupPoint", id],
      mutationFn: async () => {
        try {
          const response = await PointsService.updatePoint(
            {
              address: addressData,
              manager_id: parseInt(managerIdData.join("")),
            },
            id,
          );
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

    const handleUpdate = (e) => {
      e.preventDefault;
      updateMutation.mutate();
    };
    return (
      <>
        <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
          <div className="flex w-full flex-col">
            <div className="flex flex-col gap-3">
              <h3 className="text-center text-xl font-semibold">
                Редактор пункта выдачи
              </h3>
              <div className="flex flex-col gap-3">
                <div className="flex items-center gap-1">
                  <Field
                    icon={<HouseIcon />}
                    placeholder="Адрес пункта выдачи"
                    value={addressData}
                    onChange={(e) => setAddressData(e.target.value)}
                  />
                  <div className="flex items-center justify-center rounded-lg bg-button-dark-accent p-0.5">
                    <Button color="dark" variant="icon" icon={<TrashIcon />} />
                  </div>
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
                <Button color="dark" onClick={handleUpdate}>
                  Сохранить
                </Button>
              </div>
            </div>
          </div>
        </Modal>
        <div className="relative rounded-lg bg-secondary-bg p-4">
          <div className="absolute right-3 top-3 text-secondary-text">
            <Button
              variant="icon"
              icon={<EditIcon />}
              onClick={handleOpenModal}
            />
          </div>
          <div className="flex flex-col gap-2">
            <div className="flex flex-col gap-0.5 leading-none">
              <h3 className="text-xl font-medium">КРД-{id && formatId(id)}</h3>
              <p className="text-sm text-secondary-text">{address}</p>
            </div>
            <div className="flex flex-col gap-0.5 leading-none">
              <span className="font-semibold">Менеджер</span>
              <span className="text-sm">{manager_name}</span>
              <div className="flex items-center justify-between">
                <span className="font-semibold">Доход</span>
                <span className="text-xl text-secondary-text">
                  ₽{income && formatNumber(income)}
                </span>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  },
);
