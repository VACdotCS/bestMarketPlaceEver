"use client";

import { Field } from "@/components/ui/field";
import cartStore from "@/store/cart";
import Image from "next/image";
import Link from "next/link";
import { ManagerPanelIcon } from "../icons/manager-panel-icon";
import { AdminPanelIcon } from "../icons/admin-panel-icon";
import { CartIcon } from "../icons/cart-icon";
import { LogoutIcon } from "../icons/logout-icon";
import { SearchIcon } from "../icons/search-icon";
import { UserIcon } from "../icons/user-icon";
import { Button } from "../ui/button";
import { useAuth } from "@/hooks/useAuth";
import { useRouter } from "next/navigation";
import toast from "react-hot-toast";
import { loadToken } from "@/utils/load-token";
import authorizedUserStore from "@/store/authorizedUser";
import { observer } from "mobx-react-lite";
import Cookies from "js-cookie";

export const Header = observer(() => {
  const user = authorizedUserStore.user;
  const token = loadToken();
  const router = useRouter();

  const handleLogout = () => {
    localStorage.removeItem("token");
    Cookies.remove("token");
    authorizedUserStore.removeUser();
    router.push("/auth");
    toast.success("Вы успешно вышли из аккаунта");
  };

  return (
    <header className="flex h-20 w-full items-center justify-between gap-24 px-20">
      <div className="ml-2 flex w-1/2 items-center gap-8">
        <Link href="/">
          <Image
            src="/logos/Main_logo.png"
            width={140}
            height={80}
            objectFit="contain"
            alt="Скачайте приложение KubMarket для Android"
          />
        </Link>
        {user ? (
          <Field icon={<SearchIcon />} placeholder="Поиск товаров..." />
        ) : (
          <Field
            disabled
            placeholder="Войдите или зарегистрируйтесь, чтобы продолжить"
          />
        )}
      </div>
      <div className="flex flex-wrap items-center justify-between">
        <Link href="tel:8800666311" className="mr-20 hidden 2xl:block">
          <div className="flex flex-col items-end">
            <b className="text-base font-extrabold leading-none">
              8 800 666 13-11
            </b>
            <span className="text-end text-xs font-medium leading-none text-secondary-text">
              Поддержка 24/7
            </span>
          </div>
        </Link>
        <div className="flex flex-row-reverse items-center justify-end gap-4">
          {user && (
            <Button
              variant="icon"
              icon={<LogoutIcon className="h-6 w-6" />}
              onClick={() => handleLogout()}
            />
          )}
          <Link
            href={user ? "/cart" : ""}
            className="flex items-center gap-4 rounded-lg p-2 transition-colors hover:bg-transparent/5"
          >
            <div className="relative">
              {cartStore.getTotalItems() > 0 && (
                <div className="absolute bottom-5 left-4 flex h-3 w-3 items-center justify-center rounded bg-dark-accent px-3 py-2 text-xs font-bold text-white">
                  {cartStore.getTotalItems()}
                </div>
              )}
              <CartIcon className="h-8 w-8 text-icon" />
            </div>
            <div className="flex flex-col items-start">
              <span className="text-end text-xs font-medium leading-none text-secondary-text">
                Ваша корзина
              </span>
              <b className="text-base font-extrabold leading-none">
                {user ? (
                  <p>
                    ₽
                    {Intl.NumberFormat("ru", {
                      style: "decimal",
                      minimumFractionDigits: 2,
                      maximumFractionDigits: 2,
                    }).format(cartStore.getTotalPrice())}
                  </p>
                ) : (
                  <p>недоступна</p>
                )}
              </b>
            </div>
          </Link>
          <Link href={user ? "/profile" : "/auth"}>
            <Button
              variant="icon"
              icon={<UserIcon className="h-6 w-6 text-icon" />}
            />
          </Link>
          {user?.role === "ADMIN" && (
            <Link href="/admin-panel">
              <Button
                variant="icon"
                icon={<AdminPanelIcon className="h-6 w-6 text-icon" />}
              />
            </Link>
          )}

          {user?.role === "MANAGER" && (
            <Link href="/manager-panel">
              <Button
                variant="icon"
                icon={<ManagerPanelIcon className="h-6 w-6 text-icon" />}
              />
            </Link>
          )}
        </div>
      </div>
    </header>
  );
});
