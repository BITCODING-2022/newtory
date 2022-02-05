import React, { useState } from "react";
import "../../css/todolist/todolist.css";
import Template from "./Template";
import Todos from "./Todos";
import { MdAddCircle } from "react-icons/md";
import TodoInsert from "./TodoInsert";

let nextId = 4;

const Todolist = () => {
  const [selectedTodo, setSelectedTodo] = useState(null);
  const [insertToggle, setInsertToggle] = useState(false);

  const [typeStatus, setTypeStatus] = useState("Daily");

  const onInsertToggle = () => {
    if (selectedTodo) {
      setSelectedTodo(null);
    }
    setInsertToggle(prev => !prev);
  };

  const onInsertTodo = text => {
    if (text === "") {
      return alert("할 일을 입력해주세요.");
    } else {
      const todo = {
        id: nextId,
        text,
        checked: false
      };
      setTodos(todos => todos.concat(todo));
      nextId++;
    }
  };

  const onCheckToggle = id => {
    setTodos(todos =>
      todos.map(todo =>
        todo.id === id ? { ...todo, checked: !todo.checked } : todo
      )
    );
  };

  const onChangeSelectedTodo = todo => {
    setSelectedTodo(todo);
  };

  const onRemove = id => {
    onInsertToggle();
    setTodos(todos => todos.filter(todo => todo.id !== id));
  };

  const onUpdate = (id, text) => {
    onInsertToggle();
    setTodos(todos =>
      todos.map(todo => (todo.id === id ? { ...todo, text } : todo))
    );
  };
return (
  <div className="Template">
      <div className="tilteContainer">
        <button className={"title" + (typeStatus === "Daily" ? " currentType" : "")}
          onClick={() => {setTypeStatus("daily")}}>
          Daily
        </button>
        <button className={"title" + (typeStatus === "Monthly" ? " currentType" : "")}
          onClick={() => {setTypeStatus("monthly")}}>
          Monthly
        </button>
      </div>
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