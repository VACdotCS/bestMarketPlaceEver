import Image from "next/image";

export const MinScreen = () => {
  return (
    <div className="flex h-screen items-center justify-center bg-footer-bg text-white base:hidden">
      <div className="flex flex-col items-center gap-4 p-12">
        <h1 className="text-center text-2xl font-bold">
          Ой! Ваш экран слишком маленький.
        </h1>
        <p className="text-center">
          Чтобы продолжить работу с нашим интернет-магазином на экранах с
          маленьким разрешением, пожалуйста, скачайте приложение:
        </p>
        <div className="flex gap-6">
          <div className="flex items-center gap-4">
            <Image
              src="/qr-codes/android-app.png"
              width={80}
              height={80}
              alt="Скачайте приложение KubMarket для Android"
            />
            <h4 className="text-lg font-semibold">Приложение для Android</h4>
          </div>
        </div>
      </div>
    </div>
  );
};
