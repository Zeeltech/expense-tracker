import axios from "axios";
import { useEffect, useState } from "react";
import { createContext } from "react";
import { authenticateEmployee, loginUser } from "./UserService";

export const UserContext = createContext({});

export function UserContextProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    if (!user) {
      authenticateEmployee()
        .then((response) => {
          if (response.data != null) {
            setUser(response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, []);

  return (
    <UserContext.Provider
      value={{
        user,setUser
      }}
    >
      {children}
    </UserContext.Provider>
  );
}
