import React, { useContext, useState } from "react";
import Loader from "../components/Loader";
import { Navigate } from "react-router-dom";
import { UserContext } from "../service/UserContext";
import { registerUser } from "../service/UserService";
import { toast } from "react-toastify";

const UserRegister = () => {
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [errors, setErrors] = useState({
    email: "",
    firstName: "",
    lastName: "",
    password: "",
    confirmPassword: "",
  });

  const [loading, setLoading] = useState(false);

  const { user, setUser } = useContext(UserContext);

  if (user) {
    return <Navigate to="/dashboard" />;
  }

  async function signupUser(event) {
    event.preventDefault();

    if (validateForm()) {
      setLoading(true);
      registerUser({ email, firstName, lastName, password }).then(
        (response) => {
          console.log(response.data);
        }
      ).catch((error) => {
        toast.error("User already exists");
        setLoading(false);
      })
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

    if (firstName.trim()) {
      errorsCopy.firstName = "";
    } else {
      errorsCopy.firstName = "First name is required";
      valid = false;
    }

    if (lastName.trim()) {
      errorsCopy.lastName = "";
    } else {
      errorsCopy.lastName = "Last name is required";
      valid = false;
    }

    if (password.trim()) {
      errorsCopy.password = "";
    } else {
      errorsCopy.password = "Password is required";
      valid = false;
    }

    if (confirmPassword.trim()) {
      errorsCopy.confirmPassword = "";
    } else {
      errorsCopy.confirmPassword = "Confirm Password is required";
      valid = false;
    }

    if(password.trim() != confirmPassword.trim()) {
      errorsCopy.password = "Password is required";
      errorsCopy.confirmPassword = "Confirm Password is required"
      valid = false;
      toast.error("Passwords do not match");
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
              onSubmit={signupUser}
              className=" mt-14 bg-my_box rounded-md border-my_bg_silver p-12 flex flex-col justify-center items-center gap-2"
            >
              <div className="text-xl text-my_light_green ">Sign Up</div>

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
                  <div className="text-xs font-normal pb-1">First Name : </div>
                  <input
                    type="text"
                    value={firstName}
                    className={`form-control ${
                      errors.firstName ? "is-invalid" : ""
                    }`}
                    onChange={(ev) => {
                      setFirstName(ev.target.value);
                    }}
                    name="firstName"
                    placeholder="Enter your first name "
                  />
                </div>

                <div className="w-full">
                  <div className="text-xs font-normal pb-1">Last Name : </div>
                  <input
                    type="text"
                    value={lastName}
                    className={`form-control ${
                      errors.lastName ? "is-invalid" : ""
                    }`}
                    onChange={(ev) => {
                      setLastName(ev.target.value);
                    }}
                    name="lastName"
                    placeholder="Enter your last name "
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
                <div className="w-full">
                  <div className="text-xs font-normal pb-1">
                    Confirm Password :
                  </div>
                  <input
                    type="password"
                    value={confirmPassword}
                    className={`form-control ${
                      errors.confirmPassword ? "is-invalid" : ""
                    }`}
                    onChange={(ev) => {
                      setConfirmPassword(ev.target.value);
                    }}
                    name="confirmPassword"
                    placeholder="Re-enter password "
                  />
                </div>

                <button className="mt-2 green-btn w-full h-12">Sign Up</button>
              </div>
            </form>
          )}
        </div>
      </div>
    </>
  );
};

export default UserRegister;
