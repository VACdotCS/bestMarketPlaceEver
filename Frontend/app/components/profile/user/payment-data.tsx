import { BankCardIcon } from "@/components/icons/bank-card-icon";
import { CalendarIcon } from "@/components/icons/calendar-icon";
import { Field } from "@/components/ui/field";
import { useState } from "react";

interface paymentDataProps {
  cardNumberData: string;
  cardDateData: Date | string;
  CVCData: number | string;
}

export const PaymentData = ({
  cardNumberData,
  cardDateData,
  CVCData,
}: paymentDataProps) => {
  const [cardNumber, setCardNumber] = useState<string>(cardNumberData || "");
  const [cardDate, setCardDate] = useState<Date | string>(cardDateData || "");
  const [CVC, setCVC] = useState<number | string>(CVCData || "");
  return (
    <div className="flex flex-col items-center justify-center gap-4 rounded-xl bg-white p-6 shadow-lg">
      <h2 className="text-center text-lg font-semibold text-black">
        Платежное средство
      </h2>
      <form className="flex flex-col gap-2">
        <Field
          icon={<BankCardIcon />}
          placeholder="Номер банковской карты"
          value={cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
        />
        <div className="grid grid-cols-2 gap-2">
          <Field
            icon={<CalendarIcon />}
            placeholder="Дата"
            value={cardDate}
            onChange={(e) => setCardDate(e.target.value)}
          />
          <Field
            startContent="CVC"
            placeholder="Код безопасности"
            value={CVC}
            type="password"
            maxLength={3}
            onChange={(e) => setCVC(e.target.value)}
          />
        </div>
      </form>
    </div>
  );
};
