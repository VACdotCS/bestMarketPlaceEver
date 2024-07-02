import { IProduct } from "@/types";
import { makeAutoObservable } from "mobx";
import authStore from "./auth";

class ProductStore {
  allProducts: IProduct[] = [];

  constructor() {
    makeAutoObservable(this);
    this.initializeProducts();
  }

  initializeProducts() {
    this.allProducts = authStore.productsAdmin.filter(
      (product): product is IProduct => this.isValidProduct(product),
    );
  }

  isValidProduct(product: Partial<IProduct>): product is IProduct {
    return (
      product.id !== undefined &&
      product.title !== undefined &&
      product.price !== undefined
    );
  }

  getProductById(id: number): IProduct | null {
    const product = this.allProducts.find((product) => product.id === id);
    console.log("getProductById:", id, product);
    return product || null;
  }
}

const productStore = new ProductStore();
export default productStore;
