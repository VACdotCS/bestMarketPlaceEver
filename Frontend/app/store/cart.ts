import productStore from "@/store/product"; // Импортируем экземпляр Product
import { IProduct } from "@/types";
import { makeAutoObservable } from "mobx";

class Cart {
  items: (IProduct & { quantity: number })[] = [];
  selectedItems: Set<number> = new Set();

  constructor() {
    makeAutoObservable(this);
  }

  addItem(id: number, quantity: number = 1) {
    const product = productStore.getProductById(id);
    console.log("Product found:", product);
    if (!product) {
      console.error(`Product with id ${id} not found`);
      return;
    }

    const existingItem = this.items.find((existing) => existing.id === id);
    if (existingItem) {
      existingItem.quantity += quantity;
    } else {
      this.items.push({ ...product, quantity });
      this.selectedItems.add(id);
    }
  }

  removeItem(id: number) {
    this.items = this.items.filter((item) => item.id !== id);
    this.selectedItems.delete(id);
  }

  clearCart() {
    this.items = [];
    this.selectedItems.clear();
  }

  increaseQuantity(id: number) {
    const item = this.items.find((item) => item.id === id);
    if (item && item.quantity < item.availableQuantity) {
      item.quantity++;
    }
  }

  decreaseQuantity(id: number) {
    const item = this.items.find((item) => item.id === id);
    if (item && item.quantity > 1) {
      item.quantity--;
    }
  }

  toggleItemSelection(id: number) {
    if (this.selectedItems.has(id)) {
      this.selectedItems.delete(id);
    } else {
      this.selectedItems.add(id);
    }
  }

  getTotalItems() {
    return this.items.reduce((acc, item) => acc + item.quantity, 0);
  }

  getTotalPrice() {
    return this.items.reduce((acc, item) => {
      if (this.selectedItems.has(item.id)) {
        return acc + item.price * item.quantity;
      }
      return acc;
    }, 0);
  }
}

const cartStore = new Cart();
export default cartStore;
