import React, { useContext } from "react";
import { UserContext } from "../service/UserContext";
import Typewriter from "typewriter-effect";
import HomePage from "../assets/HomePage2.svg";

const IndexPage = () => {
  const { user, setUser } = useContext(UserContext);
  return (
    <div className="give_height bg-my_bg flex ">
      <div className="flex flex-grow justify-evenly">
        <img className="w-1/3" src={HomePage} alt="" />
        <div className="flex flex-col gap-4 w-1/3">
          <div className="text-my_font1 text-6xl mt-28 flex flex-col gap-3">
            <div>
              Track your
            </div>
            <Typewriter
              onInit={(typewriter) => {
                typewriter.typeString("Expenses Daily ").pauseFor(1000).start();
              }}
            >
              Track your
            </Typewriter>
          </div>
          <div className="text-my_bg_silver">
            The idea of tracking your expenses can feel overwhelming especially
            if you've been avoiding it for a while or have never done it before.
            But once you get started really looking at your budget and finances,
            you'll feel a sense of relief and control. Finally getting on top of
            your money and debts comes with huge pay offs: peace of mind and no
            more debts!
            <br />
            <br />
            When it comes to tracking your spending, there can be different
            reasons for doing it. Maybe you're curious about where your money is
            going, are working towards a specific goal, or want to deal with
            your debt once and for all. Whatever your reasons, we've got the
            tools and resources to help you get started.
          </div>
        </div>
      </div>
    </div>
  );
};

export default IndexPage;
