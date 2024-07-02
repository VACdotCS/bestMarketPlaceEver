import type { Config } from "tailwindcss";

const config: Config = {
  plugins: [require("@tailwindcss/line-clamp")],
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      screens: {
        base: "992px",
        "base-xl": "1280px",
        "1.5lg": "1024px",
        "1.5xl": "1350px",
      },
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },
      colors: {
        "primary-text": "#000000",
        "secondary-text": "#6E6E6E",
        "field-bg": "#EBEBEB",
        accent: "#C92E2E",
        "dark-accent": "#8A2525",
        "selected-field": "#FFC3C3",
        "footer-bg": "#8A2525",
        "button-accent": "#F93F3F",
        "button-dark-accent": "#B20000",
        "button-gray-accent": "#727272",
        icon: "#252525",
        link: "#6B6B6B",
        "on-the-way": "#4E4E4E",
        received: "#007312",
        delivered: "#7D9C00",
        cancelled: "#7F0000",
        "secondary-bg": "#F0F0F0",
      },
    },
  },
};
export default config;
