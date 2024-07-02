export const formatNumber = (number: number): string => {
  return Intl.NumberFormat("ru", {
    style: "decimal",
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  }).format(number);
};
