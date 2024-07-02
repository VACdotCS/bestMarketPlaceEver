import { makeAutoObservable } from "mobx";
import authStore from "./auth";
import { IDeliveryItem } from "@/types";

class Delivery {
  selectedDeliveryItems: Set<number> = new Set();
  deliveryItems: IDeliveryItem[] = authStore.productsAdmin.map((product) => ({
    ...product,
    count: 1,
    status: "в пути",
    dateOrder: new Date().toLocaleDateString(),
    dateDelivery: new Date().toLocaleDateString(),
  }));

  constructor() {
    makeAutoObservable(this);
  }

  toggleItemSelection(id: number) {
    if (this.selectedDeliveryItems.has(id)) {
      this.selectedDeliveryItems.delete(id);
    } else {
      this.selectedDeliveryItems.add(id);
    }
  }

  get selectedItems() {
    return this.deliveryItems.filter((item) =>
      this.selectedDeliveryItems.has(item.id!),
    );
  }

  get totalPrice() {
    return this.selectedItems.reduce(
      (total, item) => total + item.price! * item.count,
      0,
    );
  }

  get returnsCount() {
    return this.deliveryItems.length - this.selectedItems.length;
  }
}

const deliveryStore = new Delivery();
export default deliveryStore;
