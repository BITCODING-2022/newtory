import React from "react";
import { Link } from 'react-router-dom';
import "../../css/todolist/template.css";

const Template = ({ children }) => {
  return (
    <div className="Template">
      <div className="tilteContainer">
        <div className="title">
          <Link to="/todolist/daily">Daily</Link>
        </div>
        <div className="title">
          <Link to="/todolist/monthly">Monthly</Link>
        </div>
      </div>
      <div>{children}</div>
    </div>
  );
};

export default Template;