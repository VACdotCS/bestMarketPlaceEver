import { formatNumber } from "@/utils";

export const PointIncome = () => {
  return (
    <div className="flex w-full justify-between rounded-lg bg-white p-6 shadow-lg">
      <h3 className="text-lg font-semibold">Доход точки</h3>
      <h2 className="text-xl font-bold">
        ₽<span className="text-dark-accent">{formatNumber(1449250)}</span>
      </h2>
    </div>
  );
};
