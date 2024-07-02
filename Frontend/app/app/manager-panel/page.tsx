import { PickupPointsList } from "@/components/admin-panel/pickup-points-list";
import { PositionsList } from "@/components/admin-panel/positions-list";
import { UsersList } from "@/components/admin-panel/users-list";
import { IssuingOrder } from "@/components/manager-panel/issuing-order";
import { PickupPoint } from "@/components/manager-panel/pickup-point";
import { PointIncome } from "@/components/manager-panel/point-income";
import authStore from "@/store/auth";

const AdminPanelPage = () => {
  return (
    <div
      style={{ minHeight: "calc(100dvh - 240px)" }}
      className="flex bg-[#F6F6F6] py-10"
    >
      <div className="grid h-full w-full grid-cols-3 gap-6 px-20">
        <div className="col-span-1 flex flex-col gap-6">
          <PickupPoint point={authStore.getPickupPointByCode("000-001")} />
          <PointIncome />
        </div>
        <div className="col-span-2">
          <IssuingOrder />
        </div>
      </div>
    </div>
  );
};

export default AdminPanelPage;
