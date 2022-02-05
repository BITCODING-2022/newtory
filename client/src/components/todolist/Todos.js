import React, { useEffect, useState } from "react";
import TodoItem from "./TodoItem";
import "../../css/todolist/todos.css";
import axios from 'axios';

const Todos = ({
  typeStatus
}) => {
  const [todos, setTodos] = useState();
  const [isLoading, setLoading] = useState(true);

  const baseUrl = "http://localhost:8080/todolist/";

  function getTypeTodos() {
    console.log("조회 시작");
    const request = async () => {
      await axios
        .get(baseUrl + typeStatus)
        .then((response) => {
          console.log(response.data);
          setLoading(false);
          setTodos(response.data);
        })
      }
    request();
  }

  useEffect( () => {
    getTypeTodos();
  }, []);

  return (
    <div className="TodoList">
      {isLoading ? (
        <div className="loading">Loading...</div>
        ) : (
        <div className="TodoList"> {
          todos.map(todo => (
            <TodoItem 
              key={todo.id} 
              todo={todo}
            />
            ))
          }
        </div>
        )
      }
    </div>
  );
};

export default Todos;