import { IPickupPoint } from "@/types";

export const PickupPoint = ({ point }: { point: IPickupPoint | undefined }) => {
  return (
    <div className="rounded-xl bg-white p-6 shadow-lg">
      <div className="flex flex-col gap-3">
        <h3 className="text-lg font-semibold">Пункт выдачи</h3>
        <div className="flex flex-col gap-1">
          <h4 className="font-semibold">Адрес пункта выдачи</h4>
          <p>{point?.address}</p>
        </div>
        <div className="flex flex-col">
          <h4 className="font-semibold">Идентификатор пункта выдачи</h4>
          <p>{point?.code}</p>
        </div>
        <div className="flex flex-col">
          <h4 className="font-semibold">Менеджер пункта выдачи</h4>
          <p>{point?.manager.name}</p>
        </div>
      </div>
    </div>
  );
};
