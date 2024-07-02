"use client";

import cartStore from "@/store/cart";
import { observer } from "mobx-react-lite";

interface CartItemCounterProps {
  id: number;
  quantity?: number;
  availableQuantity: number;
}

export const CartItemCounter = observer(
  ({ id, quantity = 1, availableQuantity }: CartItemCounterProps) => {
    return (
      <div className="flex h-full w-[5.5em] items-center justify-center rounded-lg border border-black p-0.5 outline-none">
        <button
          onClick={() => cartStore.decreaseQuantity(id)}
          className="px-1 text-xl font-semibold text-secondary-text"
        >
          -
        </button>
        <span className="px-2">{quantity}</span>
        <button
          onClick={() =>
            quantity <= availableQuantity && cartStore.increaseQuantity(id)
          }
          className="px-1 text-xl font-semibold text-secondary-text"
        >
          +
        </button>
      </div>
    );
  },
);
