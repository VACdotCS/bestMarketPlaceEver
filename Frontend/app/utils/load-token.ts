export const loadToken = () => {
  if (typeof window === "undefined") {
    return null;
  }

  const tokenDataString = localStorage.getItem("token");
  if (!tokenDataString) {
    return null;
  }

  try {
    const tokenData = JSON.parse(tokenDataString);
    const now = new Date().getTime();
    if (now > tokenData.expires_in) {
      localStorage.removeItem("token");
      return null;
    }
    return tokenData;
  } catch (error) {
    console.error("Ошибка получения токена: ", error);
    return null;
  }
};
