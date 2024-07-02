import { makeAutoObservable } from "mobx";

export type IRole = "ADMIN" | "MANAGER" | "USER";

interface IUser {
  userId: number;
  role: IRole;
}

class AuthorizedUser {
  user: IUser | null = null;

  constructor() {
    makeAutoObservable(this);
    this.loadUser();
  }

  setUser(user: IUser) {
    this.user = { ...user };
    this.saveUser(user);
  }

  saveUser(user: IUser) {
    localStorage.setItem("user", JSON.stringify(user));
  }

  loadUser() {
    if (typeof window !== "undefined") {
      const userData = localStorage.getItem("user");
    if (userData) {
      this.user = JSON.parse(userData);
    }
    }
  }

  removeUser() {
    this.user = null;
    localStorage.removeItem("user");
    this.loadUser();
  }
}

const authorizedUserStore = new AuthorizedUser();
export default authorizedUserStore;
