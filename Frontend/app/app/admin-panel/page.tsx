import { PickupPointsList } from "@/components/admin-panel/pickup-points-list";
import { PositionsList } from "@/components/admin-panel/positions-list";
import { UsersList } from "@/components/admin-panel/users-list";
import authStore from "@/store/auth";

const AdminPanelPage = () => {
  return (
    <div
      style={{ minHeight: "calc(100dvh - 240px)" }}
      className="flex bg-[#F6F6F6] py-10"
    >
      <title>Админ-панель</title>
      <div className="grid w-full grid-cols-3 gap-6 px-20">
        <div className="col-span-1">
          <PositionsList />
        </div>
        <div className="col-span-1">
          <PickupPointsList />
        </div>
        <div className="col-span-1">
          <UsersList />
        </div>
      </div>
    </div>
  );
};

export default AdminPanelPage;
