import React, { useContext } from "react";
import { UserContext } from "../service/UserContext";
import { Navigate } from "react-router-dom";

const CashBook = () => {
  const { user, setUser } = useContext(UserContext);

  if (!user) {
    return <Navigate to="/" />;
  }
  return (
    <div className="give_height bg-my_bg flex">
      <div className="w-1/2 bg-my_box">
        <div className="text-my_light_green font-semibold text-2xl text-center mt-4">
          Cashbook
        </div>
      </div>
      <div></div>
    </div>
  );
};

export default CashBook;
