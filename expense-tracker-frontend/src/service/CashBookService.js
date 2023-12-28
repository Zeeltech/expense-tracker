import axios from "axios";

export const REST_API_BASE_URL = "http://localhost:8080/api/v1/user/cashbook";

export const getAllCashBooks = () => {
  return axios.get(REST_API_BASE_URL + "/all", {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("_token"),
    },
  });
};
