import React, { useContext } from "react";
import { Link, Navigate } from "react-router-dom";
import { UserContext } from "../service/UserContext";
import logo from "../assets/logo.png";

const Header = () => {
  const { user, setUser } = useContext(UserContext);

  async function logOut(event) {
    event.preventDefault();
    setUser(null);
    localStorage.removeItem("_token");
  }

  return (
    <div className="sticky py-2 shadow-md bg-my_box">
      <header className="flex justify-between items-center">
        <div className="flex justify-between items-center my-1">
          <Link
            to={"/"}
            className="flex items-center gap-2 cursor-pointer px-2 no-underline"
          >
            <img
              className=" h-10 w-10 border-1 rounded-full"
              alt="C"
              src={logo}
            />
            <span className="text-lg font-semibold text-my_font1">
              ExpenseTrack
            </span>
          </Link>
        </div>

        {!user && (
          <div className="mx-3 flex gap-2">
            <a className="green-btn" href={"/signup"}>
              Sign Up
            </a>
            <a className="white-btn" href={"/login"}>
              Log In
            </a>
          </div>
        )}
        {user && (
          <div className="mx-3 flex gap-2">
            <div>
              <a className="white-btn cursor-pointer" href={"/dashboard"}>
                Cash Book
              </a>
            </div>
            <div>
              <a className="green-btn cursor-pointer" onClick={logOut}>
                Log out
              </a>
            </div>
          </div>
        )}
      </header>
    </div>
  );
};

export default Header;
