"use client";

import { Carousel } from "@/components/layout/carousel";
import { Filter } from "@/components/layout/filter";
import { ProductCard } from "@/components/layout/product-card";
import { Select } from "@/components/ui/select";
import product from "@/store/product";
import { observer } from "mobx-react-lite";

import AuthService from "@/app/api/auth-service";
import authorizedUser from "@/store/authorizedUser";
import authorizedUserStore from "@/store/authorizedUser";
import { useRouter } from "next/navigation";
import { useQuery } from "@tanstack/react-query";
import ProductService from "./api/product-service";
import { Loading } from "@/components/layout/loading";

const HomePage = observer(() => {
  const allProductsData = useQuery({
    queryKey: ["products"],
    queryFn: async () => {
      const response = await ProductService.getFullAssortment();
      return response.data;
    },
  });
  const allProducts = allProductsData.data;

  const allCategoriesData = useQuery({
    queryKey: ["categories"],
    queryFn: async () => {
      const response = await ProductService.getAvailableCategories();
      return response.data;
    },
  });
  const allCategories = allCategoriesData.data;
  return (
    <div>
      <title>Главная</title>
      <Carousel banners={allProductsData?.data || []} />
      <div className="px-20 py-12">
        <div className="mb-6 flex items-center justify-between">
          <h3 className="text-2xl">Витрина магазина</h3>
          <span className="flex items-center gap-2 text-sm">
            Сортировать по:
            <div className="w-48">
              <Select
                options={["Популярности", "Цене", "Алфавиту"]}
                size="small"
              />
            </div>
          </span>
        </div>
        <div className="flex gap-20">
          <Filter
            minPrice={123}
            maxPrice={6000}
            categories={allCategories}
            isLoading={allCategoriesData.isLoading}
          />
          {allProductsData?.isLoading && (
            <div className="flex h-[400px] w-full flex-col items-center justify-center">
              <Loading />
            </div>
          )}
          {!allProducts?.length && !allProductsData.isLoading ? (
            <div className="flex h-[400px] w-full flex-col items-center justify-center opacity-50">
              На данный момент ассортимент пуст...
            </div>
          ) : (
            !allProductsData.isLoading && (
              <div
                className="grid w-full grid-cols-2 gap-5 base-xl:grid-cols-3 2xl:grid-cols-5"
                id="products"
              >
                {allProducts?.map((product) => (
                  <ProductCard
                    key={product.id}
                    id={product.id}
                    title={product.title}
                    image={product.image || "./no-product.png"}
                    price={product.price}
                    discountPrice={product.discountPrice}
                    unit={product.unit}
                    availableQuantity={product.availableQuantity}
                    description={product.description}
                    category={product.category}
                  />
                ))}
              </div>
            )
          )}
        </div>
      </div>
    </div>
  );
});
export default HomePage;
