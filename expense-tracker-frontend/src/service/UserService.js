import axios from "axios";

export const REST_API_BASE_URL = "http://localhost:8080/api/v1";

export const authenticateEmployee = () => {
  return axios.get(REST_API_BASE_URL + "/user/authenticate", {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("_token"),
    },
  });
};

export const loginUser = async ({ email, password }) => {
  return await axios.post(
    REST_API_BASE_URL + "/auth/login",
    {
      email,
      password,
    },
    { withCredentials: true }
  );
};

export const registerUser = async ({
  email,
  firstName,
  lastName,
  password,
}) => {
  return await axios.post(
    REST_API_BASE_URL + "/auth/register",
    {
      email,
      firstName,
      lastName,
      password,
    },
    { withCredentials: true }
  );
};
