"use client";

import { DeliveryList } from "@/components/delivery/delivery-panel";
import { ChangePasswordPanel } from "@/components/profile/user/change-password-panel";
import { PaymentData } from "@/components/profile/user/payment-data";
import { StatisticsPanel } from "@/components/profile/user/statistics-panel";
import { UserData } from "@/components/profile/user/user-data";
import deliveryStore from "@/store/delivery";
import { useAuth } from "@/hooks/useAuth";

const ProfilePage = () => {
  const { data, isLoading } = useAuth();
  return (
    <>
      {isLoading ? (
        <div className="flex h-[88.5vh] w-full items-center justify-center">
          <img className="h-8 w-8" src="/red-loader.svg" alt="loader" />
        </div>
      ) : (
        <div
          style={{ minHeight: "calc(100dvh - 240px)" }}
          className="flex bg-[#F6F6F6] py-10"
        >
          <title>Профиль</title>
          <div className="grid h-full w-full grid-cols-4 gap-6 px-20">
            <div className="col-span-1 flex flex-col gap-6">
              <UserData />
              <ChangePasswordPanel />
            </div>
            <div className="col-span-1 flex flex-col gap-6">
              <StatisticsPanel
                title="Сумма выкупа"
                stats={data?.amount_spent || 0}
                unit="₽"
                unitPosition="left"
              />
              <StatisticsPanel
                title="Процент выкупа"
                stats={
                  data && isNaN(data.redemption_percent)
                    ? 0
                    : data?.redemption_percent
                }
                unit="%"
              />
              <StatisticsPanel
                title="Ваша скидка"
                stats={data?.user_discount || 0}
                unit="%"
              />
              <PaymentData
                cardNumberData={data?.card_number || ""}
                cardDateData={data?.validity || ""}
                CVCData={data?.cvc || ""}
              />
            </div>
            <div className="col-span-2">
              {deliveryStore.deliveryItems.length ? (
                <DeliveryList
                  codeForReceive="123-123"
                  items={deliveryStore.deliveryItems}
                />
              ) : (
                <p>Вам ничего не должны доставлять</p>
              )}
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default ProfilePage;
