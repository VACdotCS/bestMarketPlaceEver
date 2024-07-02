export const formatNumber = (number: any): string => {
  if (typeof number !== 'number' || isNaN(number)) {
    return "0.0";
  }
  
  return Intl.NumberFormat("ru", {
    style: "decimal",
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  }).format(number);
};
