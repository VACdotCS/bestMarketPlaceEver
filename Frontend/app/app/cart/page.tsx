"use client";

import { CartList } from "@/components/cart/cart-list";
import { Order } from "@/components/cart/order";
import { observer } from "mobx-react-lite";

const CartPage = observer(() => {
  return (
    <div
      style={{ minHeight: "calc(100dvh - 240px)" }}
      className="bg-[#F6F6F6] px-20 py-10"
    >
      <title>Корзина</title>
      <div className="grid grid-cols-3 gap-6">
        <div className="col-span-2">
          <CartList />
        </div>
        <div className="col-span-1">
          <Order />
        </div>
      </div>
    </div>
  );
});

export default CartPage;
