import { formatNumber } from "@/utils";
import { useAuth } from "@/hooks/useAuth";

interface StatisticsPanelProps {
  title: string;
  stats?: number; // ключевое число в статистике
  unit: string; // ключевой символ статистики (%, ₽, шт.)
  unitPosition?: "left" | "right"; // положение ключевого символа
}

export const StatisticsPanel = ({
  title,
  stats,
  unit,
  unitPosition = "right",
}: StatisticsPanelProps) => {
  const user = useAuth();
  return (
    <div className="flex flex-col items-center justify-center gap-4 rounded-xl bg-white p-6 shadow-lg">
      <h2 className="text-center text-lg font-semibold text-black">{title}</h2>
      <span className="text-4xl font-semibold">
        {unitPosition === "left" ? <span className="mr-1">{unit}</span> : ""}
        <span className="text-dark-accent">{formatNumber(stats)}</span>
        {unitPosition === "right" ? <span className="ml-1">{unit}</span> : ""}
      </span>
    </div>
  );
};
