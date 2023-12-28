import React, { useContext, useEffect, useState } from "react";
import { UserContext } from "./service/UserContext";
import { Outlet } from "react-router-dom";
import Loader from "./components/Loader";
import Header from "./components/Header";

const Layout = () => {
  const { user, setUser } = useContext(UserContext);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setIsLoading(false);
    }, 500);

    return () => clearTimeout(timer);
  }, []);

  if (isLoading) {
    return <Loader />;
  }

  return (
    <>
      <div>
        <Header />
        <Outlet />
      </div>
    </>
  );
};

export default Layout;
