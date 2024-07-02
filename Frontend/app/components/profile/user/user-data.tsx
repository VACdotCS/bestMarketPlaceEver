import { EmailIcon } from "@/components/icons/email-icon";
import { PhoneIcon } from "@/components/icons/phone-icon";
import { UserIcon } from "@/components/icons/user-icon";
import { Button } from "@/components/ui/button";
import { Field } from "@/components/ui/field";
import { useEffect, useState } from "react";
import { useAuth } from "@/hooks/useAuth";
import { useMutation } from "@tanstack/react-query";
import UserService from "@/app/api/user-service";
import toast from "react-hot-toast";
import authorizedUserStore from "@/store/authorizedUser";

export const UserData = () => {
  const { data, isLoading } = useAuth();
  const userId = authorizedUserStore.user?.userId;
  const [userData, setUserData] = useState({
    username: isLoading ? "" : data?.username,
    phone: isLoading ? "" : data?.phone.slice(2),
    email: isLoading ? "" : data?.email,
  });

  useEffect(() => {
    setUserData({
      username: data?.username,
      phone: data?.phone.slice(2),
      email: data?.email,
    });
  }, [data, isLoading]);

  const updateMutation = useMutation({
    mutationKey: ["updateUser", userId],
    mutationFn: async () => {
      return await UserService.changeUserInfo(userId, {
        username: userData.username as string,
        phone: userData.phone ? (("+7" + userData.phone) as string) : "",
        email: userData.email as string,
      });
    },
    onSuccess: () => {
      toast.success("Данные обновлены успешно");
    },
    onError: () => {
      toast.error("Произошла ошибка при обновлении данных");
    },
  });

  const handleUpdate = (event: any) => {
    event.preventDefault();
    updateMutation.mutate();
  };

  return (
    <div className="rounded-xl bg-white p-6 shadow-lg">
      <form className="flex flex-col gap-2">
        <h2 className="text-center text-lg font-semibold text-black">
          Данные профиля
        </h2>
        <Field
          icon={isLoading ? <img src="/loader.svg" /> : <UserIcon />}
          placeholder="Имя_пользователя"
          value={userData.username}
          onChange={(e) => {
            setUserData({ ...userData, username: e.target.value });
          }}
        />
        <Field
          icon={isLoading ? <img src="/loader.svg" /> : <PhoneIcon />}
          placeholder="000 000 00-00"
          startContent="+7"
          value={userData.phone}
          onChange={(e) => {
            setUserData({ ...userData, phone: e.target.value });
          }}
        />
        <Field
          icon={isLoading ? <img src="/loader.svg" /> : <EmailIcon />}
          value={userData.email}
          onChange={(e) => {
            setUserData({ ...userData, email: e.target.value });
          }}
          placeholder="example@kubmarket.ru"
        />
        <div className="mt-4">
          <Button
            disabled={updateMutation.isPending}
            isLoading={updateMutation.isPending}
            color="dark"
            onClick={(e) => handleUpdate(e)}
          >
            Сохранить
          </Button>
        </div>
      </form>
    </div>
  );
};
