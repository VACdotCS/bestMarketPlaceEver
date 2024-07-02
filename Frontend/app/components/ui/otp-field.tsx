"use client";

import React, { useState, useRef, useEffect } from "react";

interface OtpFieldProps {
  onOtpChange: (otp: string[]) => void;
}

export const OtpField: React.FC<OtpFieldProps> = ({ onOtpChange }) => {
  const [otp, setOtp] = useState<string[]>(new Array(2).fill(""));
  const inputRefs = useRef<(HTMLInputElement | null)[]>([]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    index: number,
  ) => {
    const value = e.target.value;
    if (!isNaN(Number(value)) && value.length <= 3) {
      const newOtp = [...otp];
      newOtp[index] = value;
      setOtp(newOtp);
      if (value.length === 3 && index < otp.length - 1) {
        inputRefs.current[index + 1]?.focus();
      }
      onOtpChange(newOtp);
    }
  };

  const handleFocus = (index: number) => {
    inputRefs.current[index]?.select();
  };

  useEffect(() => {
    inputRefs.current = inputRefs.current.slice(0, otp.length);
  }, [otp.length]);

  return (
    <div className="flex items-center rounded-lg bg-field-bg p-2">
      {otp.map((data, index) => (
        <div key={index} className="flex items-center">
          <input
            className="mono flex w-8 items-center bg-field-bg text-center outline-none"
            placeholder="XXX"
            type="text"
            maxLength={3}
            value={data}
            onChange={(e) => handleChange(e, index)}
            onFocus={() => handleFocus(index)}
            ref={(el) => (inputRefs.current[index] = el)}
          />
          {index < otp.length - 1 && (
            <span className="mx-1 text-secondary-text">-</span>
          )}
        </div>
      ))}
    </div>
  );
};
