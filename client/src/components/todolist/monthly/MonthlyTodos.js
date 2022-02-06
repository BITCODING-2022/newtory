import React from 'react';
import { useState, useEffect } from 'react';
import axios from 'axios';
import TodoItem from '../TodoItem';

const MonthlyTodos = ({ baseUrl }) => {

  const [isLoading, setLoading] = useState(true);
  const [todos, setTodos] = useState();

  const getMonthlyTodos = () => {
    console.log("Monthly 조회 시작");
    const request = async () => {
      await axios
        .get(baseUrl + "monthly")
        .then((response) => {
          console.log(response.data);
          setLoading(false);
          setTodos(response.data);
        })
      }
    request();
  }

  useEffect( () => {
    getMonthlyTodos();
  }, []);

  return (
    <div>
    { isLoading ? (
      <div></div>
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
    </div>
  );

}

export default MonthlyTodos;
