"use client";
import authStore from "@/store/auth";
import { UserItem } from "./user-item";
import UserService from "@/app/api/user-service";
import { useEffect, useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { Loading } from "../layout/loading";

export const UsersList = () => {
  const { data, isLoading } = useQuery({
    queryKey: ["usersList"],
    queryFn: async () => {
      const response = await UserService.getAllUsers();
      return response.data;
    },
  });

  const allUsers = data?.sort((a, b) => a.id - b.id);

  return (
    <div className="flex h-[84vh] flex-col gap-2 rounded-xl bg-white p-6 shadow-lg">
      <h2 className="flex-none text-lg font-semibold">Пользователи</h2>
      <div className="flex-grow overflow-auto">
        {isLoading && <Loading />}
        {!allUsers?.length && !isLoading ? (
          <div className="flex h-full w-full items-center justify-center">
            <p className="opacity-50">Позиции отстутствуют...</p>
          </div>
        ) : (
          <div className="flex flex-col gap-4">
            {allUsers?.map((user) => (
              <UserItem
                id={user?.id}
                key={user?.id}
                username={user.username}
                role={user.role}
                user_discount={user.user_discount}
                amount_spent={user.amount_spent}
              />
            ))}
          </div>
        )}
      </div>
    </div>
  );
};
