import React, { useState, useEffect } from "react";
import "../../css/todolist/todolist.css";
import DailyTodos from './daily/DailyTodos';
import MonthlyTodos from './monthly/MonthlyTodos';

const Todolist = () => {

  const [typeStatus, setTypeStatus] = useState("daily");
  const baseUrl = "http://localhost:8080/todolist/";
  
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
        {typeStatus === "daily" ? (
          <DailyTodos baseUrl={baseUrl}/>
          ) : (
            <MonthlyTodos baseUrl={baseUrl}/>
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