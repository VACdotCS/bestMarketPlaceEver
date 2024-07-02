import { loadToken } from "@/utils/load-token";
import { useQuery } from "@tanstack/react-query";
import UserService from "@/app/api/user-service";
import authorizedUserStore from "@/store/authorizedUser";

export const useAuth = () => {
  const userId = authorizedUserStore.user?.userId;

  const { data, isLoading, isError, error, isStale } = useQuery({
    queryKey: ["user", userId],
    queryFn: async () => {
      if (!userId) {
        throw new Error("User ID не найден");
      }
      const { data } = await UserService.getAllUserInfo(userId);
      return data;
    },
    enabled: !!userId,
    staleTime: 2000,
    refetchOnWindowFocus: true,
  });

  return { data, isLoading, isError, error, isStale };
};
