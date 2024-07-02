export const Loading = () => {
  return (
    <div
      className="flex h-full w-full flex-col items-center justify-center"
      draggable={false}
    >
      <div className="flex items-center gap-3">
        <img
          src="/red-loader.svg"
          alt="loader"
          className="h-10 w-10 select-none"
          draggable={false}
        />
        <p className="select-none text-sm font-semibold text-accent">
          Идет загрузка...
        </p>
      </div>
    </div>
  );
};
