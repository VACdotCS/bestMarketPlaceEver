"use client";

import { DownArrowIcon } from "@/components/icons/down-arrow-icon";
import { useState, useEffect, useRef } from "react";

interface SelectProps {
  textByDefault?: string;
  options: string[];
  size?: "default" | "small";
}

export const Select = ({
  textByDefault,
  options,
  size = "default",
}: SelectProps) => {
  const [isOpened, setOpened] = useState<boolean>(false);
  const [currentOption, setCurrentOption] = useState<string>(
    textByDefault || options[0] || "",
  );

  const selectRef = useRef<HTMLDivElement>(null);

  const handleClickOutside = (event: MouseEvent) => {
    if (
      selectRef.current &&
      !selectRef.current.contains(event.target as Node)
    ) {
      setOpened(false);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <div
      className="relative"
      onClick={() => setOpened(!isOpened)}
      ref={selectRef}
    >
      <div
        className={`relative w-full bg-white ${
          size === "default" ? "py-2.5" : "py-1"
        } border-d-gray-500 cursor-pointer rounded-lg border outline-offset-0 ${
          isOpened && "rounded-b-none"
        }`}
      >
        <span
          className={`text-d-gray-500 rounded-lg pr-11 outline-none ${
            size === "default" ? "" : "text-sm"
          } select-none pl-3`}
        >
          {currentOption}
        </span>
        <DownArrowIcon
          className={`text-d-gray-500 absolute right-3 top-1/2 h-3.5 w-3.5 -translate-y-[50%] transition-all ${
            isOpened && "origin-center rotate-180"
          }`}
        />
      </div>
      {isOpened && (
        <ul
          className={`absolute w-full bg-white ${
            size === "default" ? "top-11" : "top-8"
          } rounded-b-lg border border-black/10 border-t-black/10 text-sm`}
        >
          {options &&
            options.map((option) => (
              <li
                key={option}
                className={`hover:bg-d-red-400 cursor-pointer text-black transition-all hover:bg-button-dark-accent hover:text-white ${
                  size === "default" && "px-2"
                } ${
                  size === "small" && "px-1 text-sm"
                } -z-10 last:rounded-b-lg`}
                onClick={() => {
                  setCurrentOption(option);
                  setOpened(false);
                }}
              >
                {option}
              </li>
            ))}
        </ul>
      )}
    </div>
  );
};
