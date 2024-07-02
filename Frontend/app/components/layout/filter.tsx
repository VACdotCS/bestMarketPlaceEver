"use client";

import { useState } from "react";
import { CrossIcon } from "@/components/icons/cross-icon";
import { Field } from "@/components/ui/field";
import { useQuery } from "@tanstack/react-query";
import ProductService from "@/app/api/product-service";
import { Loading } from "./loading";

interface FilterProps {
  minPrice: number;
  maxPrice: number;
  categories: Category[];
  isLoading: boolean;
}

type Category = {
  name: string;
  count: number;
};

export const Filter = ({
  minPrice,
  maxPrice,
  categories,
  isLoading,
}: FilterProps) => {
  const [selectedCategories, setSelectedCategories] = useState<string[]>([]);

  const toggleCategory = (categoryName: string) => {
    setSelectedCategories((prevSelected) =>
      prevSelected.includes(categoryName)
        ? prevSelected.filter((name) => name !== categoryName)
        : [...prevSelected, categoryName],
    );
  };

  return (
    <div className="flex max-w-64 flex-col gap-6">
      <div className="flex flex-col gap-3">
        <h3 className="text-xl font-semibold uppercase">Подкатегории</h3>
        <div className="flex flex-col gap-2">
          {isLoading &&
            [0, 1, 2].map((i) => (
              <div className="flex h-10 w-full animate-pulse items-center justify-between rounded-lg bg-field-bg p-2">
                <div className="h-2.5 w-20 rounded-xl bg-secondary-text/40"></div>
                <div className="h-2.5 w-8 rounded-xl bg-secondary-text/40"></div>
              </div>
            ))}
          {!categories && !isLoading ? (
            <div className="text-center opacity-50">Нет подкатегорий...</div>
          ) : (
            categories?.map((category) => {
              const isSelected = selectedCategories.includes(category.name);
              return (
                <div
                  key={category.name}
                  className={`flex cursor-pointer items-center justify-between rounded-lg px-4 py-2 transition-colors ${
                    isSelected ? "bg-accent/35" : "bg-field-bg"
                  }`}
                  onClick={() => toggleCategory(category.name)}
                >
                  <span className="flex items-center gap-2 font-semibold">
                    {isSelected && <CrossIcon className="h-4 w-4" />}
                    {category.name}
                  </span>
                  <span className="font-semibold">{category.count}</span>
                </div>
              );
            })
          )}
        </div>
      </div>
      <div className="flex flex-col gap-3">
        <h3 className="text-xl font-semibold uppercase">Цена</h3>
        <div className="flex items-center justify-between gap-4">
          <span>от</span>
          <Field
            variant="numeric"
            startContent="₽"
            placeholder={minPrice.toString()}
          />
          <span>до</span>
          <Field
            variant="numeric"
            startContent="₽"
            placeholder={maxPrice.toString()}
          />
        </div>
      </div>
    </div>
  );
};
