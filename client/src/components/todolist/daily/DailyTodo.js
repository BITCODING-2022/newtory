import React, { useState } from "react";
import { MdCheckBox, MdCheckBoxOutlineBlank } from "react-icons/md";
import "../../css/todolist/todoitem.css";


const TodoItem = ({
  todo
}) => {
  const {id} = todo;
  const [title, setTitle] = useState(todo.title);
  const [status, setStatus] = useState(todo.status);
  const [description, setDescription] = useState(todo.description);



  const baseUrl = "http://localhost:8080/todolist/";

  // const onInsertToggle = () => {
  //   if (selectedTodo) {
  //     setSelectedTodo(null);
  //   }
  //   setInsertToggle(prev => !prev);
  // };

  // const onInsertTodo = text => {
  //   if (text === "") {
  //     return alert("할 일을 입력해주세요.");
  //   } else {
  //     const todo = {
  //       id: nextId,
  //       text,
  //       checked: false
  //     };
  //     setTodos(todos => todos.concat(todo));
  //     nextId++;
  //   }
  // };

  const onCheckToggle = (id, status) => {
    // const request = () => {
    //   axios
    //     .get(baseUrl + "daily")
    //     .then((response) => {
    //       console.log(response.data);
    //       setMonthlyLoading(false);
    //       setMonthlyTodos(response.data);
    //     })
    //   }
    // request();
    if (status === "DONE") {
      setStatus("ON_GOING");
    } else if (status === "ON_GOING") {
      setStatus("DONE");
    } 
  };

  // const onChangeSelectedTodo = todo => {
  //   setSelectedTodo(todo);
  // };

  // const onRemove = id => {
  //   onInsertToggle();
  //   setTodos(todos => todos.filter(todo => todo.id !== id));
  // };

  // const onUpdate = (id, text) => {
  //   onInsertToggle();
  //   setTodos(todos =>
  //     todos.map(todo => (todo.id === id ? { ...todo, text } : todo))
  //   );
  // };

  return (
    <div className="TodoItem">
      <div className={"content " + (status === "DONE" ? "checked" : "")}>
        {status === "DONE" ? (
          <MdCheckBox
            onClick={() => {
              onCheckToggle(id, status);
            }}
          />
        ) : (
          <MdCheckBoxOutlineBlank
            onClick={() => {
              onCheckToggle(id, status);
            }}
          />
        )}
        <div
          className="text"
        >
          {description}
        </div>
      </div>
    </div>
  );
};

export default TodoItem;