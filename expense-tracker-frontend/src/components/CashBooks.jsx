import React, { useEffect, useState } from "react";
import { getAllCashBooks } from "../service/CashBookService";

const CashBooks = () => {
  const [cashBooks, setcashBooks] = useState([]);

  useEffect(() => {
    getAllCashBooks().then((response) => {
      setcashBooks(response.data.cashBooks);
      console.log(response.data);
    });
  }, []);

  return (
    <div>
      {cashBooks.length > 0 &&
        cashBooks.map((cashBook) => (
          <div key={cashBook.name}>{cashBook.name}</div>
        ))}
    </div>
  );
};

export default CashBooks;
