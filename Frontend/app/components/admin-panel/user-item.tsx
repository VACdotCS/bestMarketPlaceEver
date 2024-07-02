import { IUser } from "@/types";
import { formatNumber } from "@/utils";

interface IUserItem {
  id: number;
  username: string;
  role: "USER" | "ADMIN" | "MANAGER";
  amount_spent: number;
  user_discount: number;
}

export const UserItem = ({
  id,
  username,
  role,
  amount_spent,
  user_discount,
}: IUserItem) => {
  const formatId = (id: number) => {
    return id.toString().padStart(6, "0");
  };
  const generateId = (role: string, id: number): string => {
    if (role === "ADMIN") return `AD-${formatId(id)}`;
    else if (role === "MANAGER") return `MG-${formatId(id)}`;
    return `US-${formatId(id)}`;
  };
  return (
    <div className="flex items-center justify-between rounded-lg bg-secondary-bg p-4">
      <div className="flex flex-col gap-1">
        <h3 className="text-lg font-semibold">{username}</h3>
        <div className="flex flex-col leading-none">
          <span>{generateId(role ? role : "unknown", id)}</span>
          <span>{role.toLocaleLowerCase()}</span>
        </div>
      </div>
      <div className="flex flex-col items-end justify-between">
        <span className="text-xl font-medium">{user_discount}%</span>
        <span className="text-xl font-medium">
          ₽{formatNumber(amount_spent)}
        </span>
      </div>
    </div>
  );
};
