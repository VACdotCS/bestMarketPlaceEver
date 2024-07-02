"use client";

// TODO: адаптировать поле ввода под разные экраны

import "@/components/ui/field-no-snippers.css";
import { useState } from "react";

interface IField {
  variant?: "default" | "numeric" | "textarea" | "card";
  type?: "text" | "email" | "password";
  placeholder?: string;
  icon?: React.ReactNode;
  startContent?: string;
  value?: string;
  name?: string;
  color?: "default" | "white";
  onChange?: React.ChangeEventHandler<HTMLInputElement | HTMLTextAreaElement>;
  disabled?: boolean;
  maxLength?: number;
}

export const Field = ({
  variant = "default",
  type = "text",
  placeholder,
  icon,
  name,
  startContent,
  value,
  onChange,
  color,
  disabled,
  maxLength,
}: IField) => {
  const [cardNumber, setCardNumber] = useState("1010101010101010");

  const handleCardNumberChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    let value = e.target.value.replace(/\D/g, "");
    if (value.length > 16) {
      value = value.slice(0, 16);
    }
    let formattedValue = value.replace(/(.{4})/g, "$1 ").trim();
    setCardNumber(formattedValue);
    if (onChange) {
      const event = { ...e, target: { ...e.target, value: formattedValue } };
      onChange(event);
    }
  };

  return (
    <>
      {variant === "default" && (
        <label className="relative flex w-full items-center">
          {icon && (
            <span className="absolute left-3 top-1/2 flex h-5 w-5 -translate-y-[55%] transform items-center text-black">
              {icon}
            </span>
          )}
          {startContent && (
            <span
              className={`absolute top-1/2 flex -translate-y-1/2 transform select-none items-center text-sm font-medium ${
                icon ? "left-10" : "left-3"
              }`}
            >
              {startContent}
            </span>
          )}
          <input
            name={name}
            disabled={disabled}
            type={type}
            placeholder={placeholder}
            value={value}
            onChange={onChange}
            maxLength={maxLength}
            className={`flex w-full items-center bg-field-bg p-3 ${
              color === "white" && "bg-white focus:bg-white/60"
            } rounded-lg border border-transparent text-sm text-black outline-none transition-colors placeholder:text-sm placeholder:text-secondary-text focus:bg-field-bg/80 ${
              disabled && "cursor-not-allowed text-center"
            } ${
              startContent
                ? icon
                  ? "pl-[calc(2.5rem+1.2rem)]"
                  : "pl-[calc(2.5rem+1rem)]"
                : icon
                  ? "pl-11"
                  : ""
            }`}
          />
        </label>
      )}

      {variant === "numeric" && (
        <label className="relative w-28">
          {startContent && (
            <span className="absolute left-3 top-1/2 -translate-y-1/2 transform font-medium">
              {startContent}
            </span>
          )}
          <input
            type="number"
            name={name}
            placeholder={placeholder}
            className={`w-full rounded-lg border border-black p-3 text-sm outline-none placeholder:text-sm placeholder:text-secondary-text ${
              !startContent && "text-center"
            } ${startContent && "pl-8"}`}
          />
        </label>
      )}

      {variant === "textarea" && (
        <textarea
          className="w-full rounded-lg bg-white p-3 text-sm outline-none placeholder:text-sm placeholder:text-secondary-text"
          placeholder={placeholder}
          value={value}
          onChange={onChange}
          maxLength={maxLength}
        />
      )}

      {variant === "card" && (
        <label className="relative flex w-full items-center">
          {icon && (
            <span className="absolute left-3 top-1/2 h-5 w-5 -translate-y-1/2 transform text-black">
              {icon}
            </span>
          )}
          <input
            name={name}
            type="text"
            placeholder="1234 5678 9012 3456"
            value={cardNumber}
            onChange={handleCardNumberChange}
            maxLength={19}
            className={`w-full rounded-lg border border-transparent bg-field-bg p-3 text-sm text-black outline-none transition-colors placeholder:text-sm placeholder:text-secondary-text focus:bg-field-bg/80 ${
              disabled && "cursor-not-allowed text-center"
            } ${icon ? "pl-12" : ""}`}
          />
        </label>
      )}
    </>
  );
};
