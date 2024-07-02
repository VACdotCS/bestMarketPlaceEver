"use client";

import Link from "next/link";
import clsx from "clsx";
import Image from "next/image";

interface IButton {
  variant?: "default" | "icon" | "link";
  icon?: React.ReactNode;
  color?: "light" | "dark" | "gray" | "";
  href?: string;
  size?: "default" | "small";
  onClick?: React.MouseEventHandler<HTMLButtonElement | HTMLAnchorElement>;
  children?: React.ReactNode;
  type?: string;
  disabled?: boolean;
  isLoading?: boolean;
}

export const Button = ({
  variant = "default",
  icon,
  color = icon ? "" : "light",
  href,
  size = "default",
  onClick,
  children,
  type,
  disabled = false,
  isLoading,
}: IButton) => {
  return (
    <>
      {variant === "default" && (
        <button
          disabled={disabled}
          onClick={onClick}
          className={clsx(
            "flex w-full items-center justify-center gap-2 rounded-lg border border-transparent font-semibold text-white transition-colors",
            size === "small" ? "px-1.5 py-3 text-sm" : "p-3",
            {
              "bg-button-accent hover:bg-button-accent/85": color === "light",
              "bg-button-gray-accent hover:bg-button-gray-accent/85":
                color === "gray",
              "bg-button-dark-accent hover:bg-button-dark-accent/85":
                color === "dark",
              "active:border active:border-red-400 active:bg-accent/30":
                !disabled,
              "bg-button-dark-accent/50": disabled,
            },
          )}
        >
          {isLoading && (
            <Image src="/loader.svg" width={20} height={20} alt="loader" />
          )}
          {children}
        </button>
      )}

      {variant === "icon" && icon && (
        <button
          onClick={onClick}
          className={clsx(
            "flex w-max items-center justify-center rounded-lg p-2.5 transition-colors",
            {
              "bg-button-dark-accent text-white hover:bg-button-dark-accent/85":
                color === "dark",
              "bg-button-accent text-white hover:bg-button-accent/85":
                color === "light",
              "bg-transparent hover:bg-transparent/5 hover:text-icon/90":
                !color,
            },
          )}
        >
          {icon}
        </button>
      )}

      {variant === "link" && (
        <Link
          className="font-medium text-link underline underline-offset-2 transition-all hover:text-link/80 hover:underline-offset-4"
          href={href!}
          onClick={onClick}
        >
          {children}
        </Link>
      )}
    </>
  );
};
