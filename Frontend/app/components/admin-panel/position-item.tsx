"use client";

import { IPositionItem } from "@/types";
import { useState, useRef } from "react";
import { BoxIcon } from "../icons/box-icon";
import { RubleIcon } from "../icons/ruble-icon";
import { RubleXPercentIcon } from "../icons/ruble-x-percent-icon";
import { TitleIcon } from "../icons/title-icon";
import { TrashIcon } from "../icons/trash-icon";
import { UnitIcon } from "../icons/unit-icon";
import { Button } from "../ui/button";
import { Field } from "../ui/field";

export const PositionItem = ({
  title,
  price,
  unit,
  discountPrice,
  availableQuantity,
  description,
  image = "./products/no-product.png",
  category,
}: IPositionItem) => {
  const [titleValue, setTitleValue] = useState(title || "");
  const [priceValue, setPriceValue] = useState(
    (price && price.toString()) || "",
  );
  const [unitValue, setUnitValue] = useState((unit && unit.toString()) || "");
  const [discountPriceValue, setDiscountPrice] = useState(
    (discountPrice && discountPrice.toString()) || "",
  );
  const [availableQuantityValue, setAvailableQuantityValue] = useState(
    (availableQuantity && availableQuantity.toString()) || "",
  );
  const [descriptionValue, setDescriptionValue] = useState(description || "");
  const [imageValue, setImageValue] = useState(image || "");
  const [categoryValue, setCategoryValue] = useState(category || "");

  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleImageClick = () => {
    fileInputRef.current?.click();
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setImageValue(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <form className="flex flex-col gap-3 rounded-lg bg-secondary-bg p-4">
      <div className="grid grid-cols-3 gap-3">
        <div
          className="col-span-1 cursor-pointer rounded-xl bg-field-bg"
          onClick={handleImageClick}
        >
          <img src={imageValue} alt="" className="h-32 w-full object-contain" />
          <input
            type="file"
            ref={fileInputRef}
            style={{ display: "none" }}
            onChange={handleFileChange}
          />
        </div>
        <div className="col-span-2">
          <div className="flex flex-col gap-2">
            <Field
              color="white"
              icon={<TitleIcon className="h-4 w-4 text-secondary-text" />}
              placeholder="Название товара"
              value={titleValue}
              onChange={(e) => setTitleValue(e.target.value)}
            />
            <div className="grid grid-cols-2 gap-2">
              <div className="flex flex-col gap-2">
                <Field
                  color="white"
                  icon={<RubleIcon className="h-4 w-4 text-secondary-text" />}
                  placeholder="Цена"
                  value={priceValue}
                  onChange={(e) => setPriceValue(e.target.value)}
                />
                <Field
                  color="white"
                  icon={
                    <RubleXPercentIcon className="h-4 w-4 text-secondary-text" />
                  }
                  placeholder="250"
                  value={discountPriceValue}
                  onChange={(e) => setDiscountPrice(e.target.value)}
                />
              </div>
              <div className="flex flex-col gap-2">
                <Field
                  color="white"
                  icon={<UnitIcon className="h-4 w-4 text-secondary-text" />}
                  placeholder="кг"
                  value={unitValue}
                  onChange={(e) => setUnitValue(e.target.value)}
                />
                <Field
                  color="white"
                  icon={<BoxIcon className="h-4 w-4 text-secondary-text" />}
                  placeholder="Кол-во в налчии"
                  value={availableQuantityValue}
                  onChange={(e) => setAvailableQuantityValue(e.target.value)}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="flex w-full gap-1">
        <Field
          color="white"
          placeholder="Категория"
          value={categoryValue}
          onChange={(e) => setCategoryValue(e.target.value)}
        />
      </div>
      <div className="flex w-full gap-2">
        <Field
          variant="textarea"
          placeholder="Описание"
          value={descriptionValue}
          onChange={(e) => setDescriptionValue(e.target.value)}
        />
        <Button color="dark" variant="icon" icon={<TrashIcon />} />
      </div>
    </form>
  );
};
