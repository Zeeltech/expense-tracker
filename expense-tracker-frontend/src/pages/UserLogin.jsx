import React, { useContext, useState } from "react";
import Loader from "../components/Loader";
import { Navigate, useNavigate } from "react-router-dom";
import { UserContext } from "../service/UserContext";
import { authenticateEmployee, loginUser, registerUser } from "../service/UserService";
import { toast } from "react-toastify";

const UserLogin = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const [errors, setErrors] = useState({
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState(false);

  const { user, setUser } = useContext(UserContext);

  if (user) {
    return <Navigate to="/dashboard" />;
  }

  async function logInUser(event) {
    event.preventDefault();

    if (validateForm()) {
      await loginUser({ email, password })
        .then((response) => {
          localStorage.setItem("_token", response.data.token);
        })
        .catch((error) => {
          toast.error("User does not exists");
        });
      await authenticateEmployee()
        .then((response) => {
          if (response.data != null) {
            setUser(response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }

  function validateForm() {
    let valid = true;
    const errorsCopy = { ...errors };

    if (email.trim()) {
      errorsCopy.email = "";
    } else {
      errorsCopy.email = "Email is required";
      valid = false;
    }

    if (password.trim()) {
      errorsCopy.password = "";
    } else {
      errorsCopy.password = "Password is required";
      valid = false;
    }

    setErrors(errorsCopy);
    return valid;
  }

  return (
    <>
      <div className="give_height bg-my_bg">
        <div className="flex justify-center items-center text-my_font1 font-semibold text-sm">
          {loading && <Loader height={"h-[70vh]"} />}
          {!loading && (
            <form
              onSubmit={logInUser}
              className=" mt-24 bg-my_box rounded-md border-my_bg_silver p-12 flex flex-col justify-center items-center gap-2"
            >
              <div className="text-xl text-my_light_green ">Log In</div>

              <div className="w-80 flex flex-col gap-3">
                <div className="w-full">
                  <div className="text-xs font-normal pb-1">Email : </div>
                  <input
                    type="text"
                    value={email}
                    className={`form-control ${
                      errors.email ? "is-invalid" : ""
                    }`}
                    onChange={(ev) => {
                      setEmail(ev.target.value);
                    }}
                    name="email"
                    placeholder="Enter your email"
                  />
                </div>

                <div className="w-full">
                  <div className="text-xs font-normal pb-1">Password : </div>
                  <input
                    type="password"
                    value={password}
                    className={`form-control ${
                      errors.password ? "is-invalid" : ""
                    }`}
                    onChange={(ev) => {
                      setPassword(ev.target.value);
                    }}
                    name="password"
                    placeholder="Enter password "
                  />
                </div>

                <button className="mt-2 green-btn w-full h-12">Log In</button>
              </div>
            </form>
          )}
        </div>
      </div>
    </>
  );
};

export default UserLogin;
