export const Badge = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="absolute bottom-5 left-4 flex h-3 w-3 items-center justify-center rounded bg-dark-accent px-3 py-2 text-xs font-bold text-white">
      {children}
    </div>
  );
};
