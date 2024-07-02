import { IDeliveryItem } from "@/types";

export const DeliveryItem = ({
  title,
  price,
  unit,
  count,
  image = "/products/no-product.png",
  status,
  dateOrder,
  dateDelivery,
}: IDeliveryItem) => {
  return (
    <div className="flex items-center justify-between py-2">
      <div className="flex items-center gap-3">
        <div className="h-16 w-16">
          <img src={image} alt="" className="h-full w-full object-contain" />
        </div>
        <div className="flex flex-col">
          <h3 className="text-lg font-medium first-letter:uppercase">
            {title}
          </h3>
          <div className="flex gap-2 font-medium">
            <span className="text-green-600">
              ₽
              {Intl.NumberFormat("ru", {
                style: "decimal",
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
              }).format(price!)}
            </span>
            <span className="text-secondary-text">1{unit}</span>
          </div>
          <span>Дата заказа: {dateOrder}</span>
        </div>
      </div>
      <div className="flex flex-col items-end gap-1">
        <div className="flex items-center gap-3">
          <span>
            {count}
            {unit}
          </span>
          <h4 className="text-2xl font-bold">
            ₽
            <span className="text-dark-accent">
              {Intl.NumberFormat("ru", {
                style: "decimal",
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
              }).format(count * price!)}
            </span>
          </h4>
        </div>
        <div
          className={`rounded-full px-4 py-0.5 font-bold italic text-white ${
            status === "в пути"
              ? "bg-on-the-way"
              : status === "доставлено"
                ? "bg-delivered"
                : status === "получено"
                  ? "bg-received"
                  : "bg-cancelled"
          }`}
        >
          {status}
        </div>
        <span className="text-secondary-text">до: {dateDelivery}</span>
      </div>
    </div>
  );
};
