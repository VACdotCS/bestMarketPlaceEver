import { IBannerProduct } from "@/types";
import { observer } from "mobx-react-lite";
import { useEffect, useState } from "react";
import { Banner } from "./banner";

interface IDefaultBanner {
  title: string;
  description: string;
  image: string;
}

interface CarouselProps {
  banners: IBannerProduct[] | IDefaultBanner;
}

export const Carousel = observer(({ banners }: CarouselProps) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % banners.length);
    }, 6000);

    return () => clearInterval(interval);
  }, [banners.length]);

  return (
    <div className="relative h-[25em] w-full overflow-hidden">
      <img
        src="/under-banner.png"
        alt="Under Banner"
        className="absolute h-full w-full object-cover"
      />
      {banners.length === 0 && (
        <div className="z-90 absolute bottom-10 left-20 right-20 top-10 flex items-center justify-between rounded-xl bg-white px-20">
          <div className="flex items-start gap-10">
            <div className="flex flex-col gap-6">
              <h1 className="text-5xl font-bold uppercase text-black">
                Кубмаркет
              </h1>
              <p className="line-clamp-3 w-80 overflow-hidden text-ellipsis text-secondary-text">
                Здесь будут отображаться баннеры с продуктами
              </p>
            </div>
          </div>
          <img src="/logos/Main_logo.png" className="h-[6em]" alt="Product" />
        </div>
      )}
      {banners.map((banner, index) => {
        return (
          <div
            key={banner.id}
            className={`absolute h-full w-full transition-opacity duration-1000 ${index === currentIndex ? "opacity-100" : "opacity-0"}`}
          >
            <Banner
              id={banner.id}
              title={banner.title}
              description={banner.description}
              price={banner.price}
              discountPrice={banner.discountPrice}
              image={banner.image}
            />
          </div>
        );
      })}
    </div>
  );
});
