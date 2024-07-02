import { PasswordIcon } from "@/components/icons/password-icon";
import { RepeatPasswordIcon } from "@/components/icons/repeat-password-icon";
import { Button } from "@/components/ui/button";
import { Field } from "@/components/ui/field";
import { useState } from "react";
import UserService from "@/app/api/user-service";
import toast from "react-hot-toast";
import { useMutation } from "@tanstack/react-query";

export const ChangePasswordPanel = () => {
  const [oldPassword, setOldPassword] = useState<string>("");
  const [newPassword, setNewPassword] = useState<string>("");
  const [repeatedNewPassword, setRepeatedNewPassword] = useState<string>("");

  const clearFields = () => {
    setOldPassword("");
    setNewPassword("");
    setRepeatedNewPassword("");
  };
  const updateMutation = useMutation({
    mutationKey: ["password"],
    mutationFn: async () => {
      try {
        const response = await UserService.changePassword(
          oldPassword,
          newPassword,
          repeatedNewPassword,
        );
        return response;
      } catch (error: any) {
        throw new Error(error.response.data);
      }
    },
    onSuccess: () => {
      toast.success("Пароль успешно обновлен");
      clearFields();
    },
    onError: (error: Error) => {
      toast.error(error.message);
      clearFields();
    },
  });
  const handleUpdate = async (event: any) => {
    event.preventDefault();
    updateMutation.mutate();
  };
  return (
    <div className="rounded-xl bg-white p-6 shadow-lg">
      <form className="flex flex-col gap-2">
        <h2 className="text-center text-lg font-semibold text-black">
          Изменить пароль
        </h2>
        <Field
          icon={<PasswordIcon className="text-dark-accent" />}
          placeholder="Старый пароль"
          value={oldPassword}
          type="password"
          onChange={(e) => setOldPassword(e.target.value)}
        />
        <Field
          icon={<PasswordIcon />}
          placeholder="Новый пароль"
          value={newPassword}
          type="password"
          onChange={(e) => setNewPassword(e.target.value)}
        />
        <Field
          icon={<RepeatPasswordIcon />}
          placeholder="Повторите пароль"
          value={repeatedNewPassword}
          type="password"
          onChange={(e) => setRepeatedNewPassword(e.target.value)}
        />
        <div className="mt-4">
          <Button color="dark" onClick={(event) => handleUpdate(event)}>
            Обновить пароль
          </Button>
        </div>
      </form>
    </div>
  );
};
