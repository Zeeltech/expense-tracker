import { Route, Routes } from "react-router-dom";
import { REST_API_BASE_URL } from "./service/UserService";
import Layout from "./Layout";
import axios from "axios";
import { UserContextProvider } from "./service/UserContext";
import IndexPage from "./pages/IndexPage";
import UserRegister from "./pages/UserRegister";
import UserLogin from "./pages/UserLogin";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";
import Dashboard from "./pages/Dashboard";

axios.defaults.baseURL = REST_API_BASE_URL;
axios.defaults.withCredentials = true;

function App() {
  return (
    <UserContextProvider>
      <ToastContainer />
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/" element={<IndexPage />} />
          <Route path="/signup" element={<UserRegister />} />
          <Route path="/login" element={<UserLogin />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Route>
      </Routes>
    </UserContextProvider>
  );
}

export default App;
