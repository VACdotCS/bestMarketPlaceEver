export const saveToken = (token: string) => {
  const now = new Date();
  const tokenData = {
    token: token,
    expires_in: now.getTime() + 24 * 3600 * 1000,
  };
  localStorage.setItem("token", JSON.stringify(tokenData));
};
