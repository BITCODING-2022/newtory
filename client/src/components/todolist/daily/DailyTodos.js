import React from 'react';
import { useState, useEffect } from 'react';
import axios from 'axios';
import TodoItem from '../TodoItem';
import { MdAddCircle } from "react-icons/md";

const DailyTodos = ({ baseUrl }) => {

  const [isLoading, setLoading] = useState(true);
  const [todos, setTodos] = useState();

  const onRemove = (id) => {
    setTodos(todos.filter(todo => todo.id !== id))
    axios
      .delete(baseUrl + id)
      .then(() => {
        console.log("Delete!");
      })
    }

  const onAdd = () => {
    console.log("Clicked!");
  }


  const getDailyTodos = () => {
    console.log("Daily 조회 시작");
    const request = async () => {
      await axios
        .get(baseUrl + "daily")
        .then((response) => {
          console.log(response.data);
          setLoading(false);
          setTodos(response.data);
        })
      }
    request();
  }

  useEffect( () => {
    getDailyTodos();
  }, []);

  return (
    <div>
    { isLoading ? (
      <div>  </div>
    ) : (
      <div className="TodoList">
        {todos &&
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
    <div className="add-todo-button" onClick={onAdd}>
      <MdAddCircle />
    </div>
    </div>
  );
}

export default DailyTodos;
