import { HouseIcon } from "../icons/house-icon";
import { Field } from "../ui/field";

export const AddingPickupPoint = () => {
  return (
    <div className="flex flex-col gap-3 rounded-lg bg-secondary-bg p-4">
      <div className="flex items-center gap-12 rounded-lg bg-white p-4">
        <HouseIcon />
        <p className="line-clamp-2 w-1/3">
          г. Краснодар, ул. Ставропольская, 149
        </p>
      </div>
      <div className="flex items-center justify-between">
        <b>ID менеджера</b>
        <div className="w-1/2">
          <Field color="white" placeholder="XXX-XXX" />
        </div>
      </div>
    </div>
  );
};
