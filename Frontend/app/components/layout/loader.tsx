import React from "react";
import "./loader.css"; // Create a CSS file for styling the preloader

const Loader = () => {
  return (
    <div className="preloader">
      <div className="spinner"></div>
      <p>Идет загрузка...</p>
    </div>
  );
};

export default Loader;
