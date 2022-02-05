import React, { useState, useEffect } from "react";
import "../../css/todolist/todolist.css";
import axios from 'axios';
import Todos from "./Todos";

const Todolist = () => {
  const [isMonthlyLoading, setMonthlyLoading] = useState(true);
  const [isDailyLoading, setDailyLoading] = useState(true);

  const [monthlyTodos, setMonthlyTodos] = useState();
  const [dailyTodos, setDailyTodos] = useState();

  const [typeStatus, setTypeStatus] = useState("daily");

  const baseUrl = "http://localhost:8080/todolist/";

  const getMonthlyTodos = () => {
    console.log("Monthly 조회 시작");
    const request = async () => {
      await axios
        .get(baseUrl + "daily")
        .then((response) => {
          console.log(response.data);
          setMonthlyLoading(false);
          setMonthlyTodos(response.data);
        })
      }
    request();
  }

  const getDailyTodos = () => {
    console.log("Daily 조회 시작");
    const request = async () => {
      await axios
        .get(baseUrl + "monthly")
        .then((response) => {
          console.log(response.data);
          setDailyLoading(false);
          setDailyTodos(response.data);
        })
      }
    request();
  }

  useEffect( () => {
    getMonthlyTodos();
    getDailyTodos();
  }, []);
  
return (
  <div className="Template">
      <div className="tilteContainer">
        <button className={"title" + (typeStatus === "daily" ? " currentType" : "")}
          onClick={() => {setTypeStatus("daily")}}>
          Daily
        </button>
        <button className={"title" + (typeStatus === "monthly" ? " currentType" : "")}
          onClick={() => {setTypeStatus("monthly")}}>
          Monthly
        </button>
      </div>
      {isDailyLoading || isMonthlyLoading ? (
        <div className="loading">Loading...</div>
        ) : (
          <Todos todos={typeStatus === "daily" ? dailyTodos : monthlyTodos} />
        )}
  </div>


    // <Template todoLength={todos.length}>
    //   <Todos
    //     todos={todos}
    //     onCheckToggle={onCheckToggle}
    //     onInsertToggle={onInsertToggle}
    //     onChangeSelectedTodo={onChangeSelectedTodo}
    //   />
    //   <div className="add-todo-button" onClick={onInsertToggle}>
    //     <MdAddCircle />
    //   </div>
    //   {insertToggle && (
    //     <TodoInsert
    //       selectedTodo={selectedTodo}
    //       onInsertToggle={onInsertToggle}
    //       onInsertTodo={onInsertTodo}
    //       onRemove={onRemove}
    //       onUpdate={onUpdate}
    //     />
    //   )}
    // </Template>
  );
};

export default Todolist;