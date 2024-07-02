"use client";

import cartStore from "@/store/cart";
import { observer } from "mobx-react-lite";
import { CartItem } from "./cart-item";

export const CartList = observer(() => {
  return (
    <div className="flex flex-col rounded-xl bg-white p-6 shadow-lg">
      <div className="flex w-full items-center justify-between">
        <h3 className="text-lg font-semibold">Корзина</h3>
        {cartStore.getTotalItems() > 0 && (
          <p
            onClick={() => cartStore.clearCart()}
            className="text-sm font-medium text-accent underline hover:cursor-pointer"
          >
            Очистить корзину
          </p>
        )}
      </div>
      {cartStore.items.length === 0 && <hr className="my-4" />}
      <div>
        {cartStore.items.length ? (
          <ul className="flex flex-col gap-2 overflow-y-auto">
            {cartStore.items.map((item) => (
              <CartItem
                id={item.id}
                title={item.title}
                image={item.image}
                quantity={item.quantity}
                price={item.price}
                discountPrice={item.discountPrice}
                unit={item.unit}
                availableQuantity={item.availableQuantity}
                description={item.description}
              />
            ))}
          </ul>
        ) : (
          <div className="flex h-48 w-full items-center justify-center">
            <p className="text-black/40">В Вашей корзине ничего нет</p>
          </div>
        )}
      </div>
    </div>
  );
});
