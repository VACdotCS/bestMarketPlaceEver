"use client";

import { Footer } from "@/components/layout/footer";
import { Header } from "@/components/layout/header";
import { Inter } from "next/font/google";
import { Toaster } from "react-hot-toast";
import "./globals.css";
import { MinScreen } from "@/components/layout/min-screen";
import { ReactQueryProvider } from "../providers/react-query-provider";
import { useAuth } from "@/hooks/useAuth";
import { useRouter } from "next/navigation";

const inter = Inter({ subsets: ["latin"] });

const RootLayout = ({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) => {
  return (
    <html lang="ru">
      <title>Загрузка...</title>
      <body className={`${inter.className} h-full`}>
        <ReactQueryProvider>
          <Toaster position="bottom-right" />
          <MinScreen />
          <div className="hidden base:block">
            <Header />
            <div style={{ minHeight: "calc(100vh - 240px)" }}>{children}</div>
            <Footer />
          </div>
        </ReactQueryProvider>
      </body>
    </html>
  );
};

export default RootLayout;
