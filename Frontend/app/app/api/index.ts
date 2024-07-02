import axios, { InternalAxiosRequestConfig } from "axios";
import { loadToken } from "@/utils/load-token";

const api = axios.create({
  withCredentials: true,
  baseURL: process.env.API_URL,
});

api.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  const tokenData = loadToken();
  if (tokenData) config.headers.Authorization = `Bearer ${loadToken().token}`;
  return config;
});

export default api;
