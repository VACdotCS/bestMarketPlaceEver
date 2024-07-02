import { IBannerProduct } from "@/types";
import { formatNumber } from "@/utils";
import { observer } from "mobx-react-lite";
import Link from "next/link";
import { Button } from "../ui/button";

export const Banner = observer(
  ({ id, title, description, price, discountPrice, image }: IBannerProduct) => {
    return (
      <div className="z-90 absolute bottom-10 left-20 right-20 top-10 flex items-center justify-between rounded-xl bg-white px-20">
        <div className="flex items-start gap-10">
          <div className="flex flex-col gap-6">
            <h1 className="text-5xl font-bold uppercase text-black">{title}</h1>
            <p className="line-clamp-3 w-80 overflow-hidden text-ellipsis text-secondary-text">
              {description}
            </p>
            <span className="w-[12em]">
              <Link href="/#products">
                <Button>Перейти к товарам</Button>
              </Link>
            </span>
          </div>
          <div className="mr-[10em] hidden flex-col gap-2 1.5xl:flex">
            <div className="relative">
              <h3 className="text-end text-3xl font-bold text-green-600">
                ₽{formatNumber(price)}
              </h3>
              <s className="absolute -right-16 -top-4 text-xl font-semibold text-secondary-text">
                ₽{formatNumber(discountPrice)}
              </s>
            </div>
            <span className="w-[10em] text-end text-base font-bold uppercase leading-none text-secondary-text">
              Ограниченное предложение
            </span>
          </div>
        </div>
        <img src={image} className="h-[20em]" alt="Product" />
      </div>
    );
  },
);
