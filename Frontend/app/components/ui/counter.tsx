"use client";

interface CounterProps {
  quantity: number;
  setQuantity: (quantity: number) => void;
}

export const Counter = ({ quantity, setQuantity }: CounterProps) => {
  return (
    <div className="flex h-full items-center rounded-lg border border-black p-1.5 outline-none">
      <button
        onClick={() => quantity > 0 && setQuantity(quantity - 1)}
        className="px-2 text-xl font-semibold text-secondary-text"
      >
        -
      </button>
      <input
        value={quantity}
        onChange={(e) => setQuantity(Number(e.target.value))}
        type="number"
        className={`h-full w-full text-center outline-none placeholder:text-secondary-text`}
      />
      <button
        onClick={() => setQuantity(quantity + 1)}
        className="px-2 text-xl font-semibold text-secondary-text"
      >
        +
      </button>
    </div>
  );
};
