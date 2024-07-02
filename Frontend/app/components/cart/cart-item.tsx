import cartStore from "@/store/cart";
import { IProduct } from "@/types";
import { formatNumber } from "@/utils";
import { observer } from "mobx-react-lite";
import { TrashIcon } from "../icons/trash-icon";
import { Button } from "../ui/button";
import { CartItemCounter } from "./cart-item-counter";

interface ICartItem extends IProduct {
  quantity: number;
}

export const CartItem = observer(
  ({
    id,
    title,
    price,
    unit,
    availableQuantity,
    image,
    quantity,
  }: ICartItem) => {
    const handleCheckboxChange = (id: number) => {
      cartStore.toggleItemSelection(id);
    };

    return (
      <div className="flex flex-col gap-3">
        <div className="mt-2 h-1 w-full border-t" />
        <div className="relative flex items-center justify-between py-2">
          <div className="absolute left-2 top-2">
            <input
              type="checkbox"
              className="custom-checkbox h-6 w-6 appearance-none rounded border border-dark-accent bg-white shadow-inner checked:bg-dark-accent"
              checked={cartStore.selectedItems.has(id)}
              onChange={() => handleCheckboxChange(id)}
            />
          </div>
          <div className="flex w-[60%] items-start justify-between">
            <div className="flex items-center gap-3">
              <div className="h-16 w-16">
                <img
                  src={image}
                  alt={title}
                  className="h-full w-full object-contain"
                />
              </div>
              <div className="flex items-center justify-between">
                <div className="flex flex-col">
                  <h3 className="text-lg font-medium">{title}</h3>
                  <div className="flex gap-2 font-medium">
                    <span className="text-green-600">
                      ₽{formatNumber(price)}
                    </span>
                    <span className="text-secondary-text">1{unit}</span>
                  </div>
                  <span className="text-secondary-text/80">
                    {availableQuantity}
                    {unit} в наличии
                  </span>
                </div>
              </div>
            </div>
            <CartItemCounter
              id={id}
              quantity={quantity}
              availableQuantity={availableQuantity}
            />
          </div>
          <div></div>
          <div className="flex flex-col items-end gap-1">
            <div className="flex items-center gap-3">
              <h4 className="text-2xl font-bold">
                ₽
                <span className="text-dark-accent">
                  {Intl.NumberFormat("ru", {
                    style: "decimal",
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2,
                  }).format(price * quantity)}
                </span>
              </h4>
            </div>
            <Button
              variant="icon"
              icon={<TrashIcon />}
              onClick={() => cartStore.removeItem(id)}
            />
          </div>
        </div>
      </div>
    );
  },
);
