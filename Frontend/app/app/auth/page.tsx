"use client";

import type { IRole } from "@/store/authorizedUser";
import Cookies from "js-cookie";
import AuthService from "@/app/api/auth-service";
import { EmailIcon } from "@/components/icons/email-icon";
import { PasswordIcon } from "@/components/icons/password-icon";
import { PhoneIcon } from "@/components/icons/phone-icon";
import { RepeatPasswordIcon } from "@/components/icons/repeat-password-icon";
import { UserIcon } from "@/components/icons/user-icon";
import { Button } from "@/components/ui/button";
import { Field } from "@/components/ui/field";
import { useState } from "react";
import { useRouter } from "next/navigation";
import toast from "react-hot-toast";
import { saveToken } from "@/utils/save-token";
import { useMutation } from "@tanstack/react-query";
import { AuthRequest, RegisterRequest } from "../api/[models]/authRequests";
import { IUserRole } from "@/types";
import authorizedUserStore from "@/store/authorizedUser";

const AuthPage = () => {
  const [stage, setStage] = useState("login");

  const [formData, setFormData] = useState({
    username: "",
    phone: "",
    email: "",
    password: "",
    repeatPassword: "",
  });

  const router = useRouter();

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  const loginMutation = useMutation({
    mutationKey: ["login"],
    mutationFn: async (loginData: AuthRequest) => {
      try {
        const response = await AuthService.login(loginData);
        saveToken(response.data.token);
        authorizedUserStore.setUser({
          userId: response.data.user_id,
          role: response.data.role as IRole,
        });
        Cookies.set("token", response.data.token, { expires: 1, path: "/" });
        return response;
      } catch (error: any) {
        throw new Error(error.response.data);
      }
    },
    onSuccess: () => {
      toast.success("Вход выполнен успешно!");
      router.push("/");
    },
    onError: (error: Error) => {
      toast.error(error.message);
    },
  });

  const registerMutation = useMutation({
    mutationKey: ["register"],
    mutationFn: async (registerData: RegisterRequest) => {
      try {
        await AuthService.register(registerData);
      } catch (error: any) {
        throw new Error(error.response.data);
      }
    },
    onSuccess: () => {
      setStage("login");
      toast.success(
        "Вы успешно создали аккаунт. Теперь вы можете войти в аккаунт.",
      );
    },
    onError: (error: Error) => {
      toast.error(error.message);
    },
  });

  const handleLogin = (event: any) => {
    event.preventDefault();
    loginMutation.mutate({
      phone: "+7" + formData.phone,
      password: formData.password,
    });
  };

  const handleRegister = (event: any) => {
    event.preventDefault();
    registerMutation.mutate({
      username: formData.username,
      phone: "+7" + formData.phone,
      email: formData.email,
      password: formData.password,
    });
  };

  return (
    <div
      style={{ minHeight: "calc(100dvh - 240px)" }}
      className="flex items-center justify-center bg-[#F6F6F6]"
    >
      <title>Авторизация</title>
      <form className="my-20 flex w-[30em] flex-col items-center gap-8 rounded-xl bg-white p-10 shadow-2xl">
        <h2 className="text-2xl font-bold text-black">
          {stage === "login" ? "Войти в профиль" : "Регистрация"}
        </h2>
        <div className="flex w-full flex-col gap-3">
          {stage === "login" ? (
            <>
              <Field
                icon={<PhoneIcon />}
                startContent="+7"
                placeholder="000 000 00-00"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
              />
              <Field
                icon={<PasswordIcon />}
                placeholder="Введите пароль"
                name="password"
                type="password"
                value={formData.password}
                onChange={handleChange}
              />
            </>
          ) : (
            <>
              <Field
                icon={<UserIcon />}
                placeholder="Имя"
                name="username"
                value={formData.username}
                onChange={handleChange}
              />
              <Field
                icon={<PhoneIcon />}
                startContent="+7"
                placeholder="000 000 00-00"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
              />
              <Field
                icon={<EmailIcon />}
                placeholder="Введите e-mail"
                name="email"
                value={formData.email}
                onChange={handleChange}
              />
              <Field
                type="password"
                icon={<PasswordIcon />}
                placeholder="Введите пароль"
                name="password"
                value={formData.password}
                onChange={handleChange}
              />
              <Field
                type="password"
                icon={<RepeatPasswordIcon />}
                placeholder="Повторите пароль"
                name="repeatPassword"
                value={formData.repeatPassword}
                onChange={handleChange}
              />
            </>
          )}
        </div>
        <div className="flex w-full flex-col gap-4">
          {stage === "login" ? (
            <>
              <Button
                disabled={loginMutation.isPending}
                isLoading={loginMutation.isPending}
                color="dark"
                type="button"
                onClick={(e) => handleLogin(e)}
              >
                Войти
              </Button>
              <span className="w-full text-center">
                <Button
                  variant="link"
                  href="#"
                  onClick={() => setStage("register")}
                >
                  Зарегистрироваться
                </Button>
              </span>
            </>
          ) : (
            <>
              <Button
                color="dark"
                type="button"
                onClick={(e) => handleRegister(e)}
              >
                Зарегистрироваться
              </Button>
              <span className="w-full text-center">
                <Button
                  variant="link"
                  href="#"
                  onClick={() => setStage("login")}
                >
                  Есть аккаунт? Войти
                </Button>
              </span>
            </>
          )}
        </div>
      </form>
    </div>
  );
};

export default AuthPage;
