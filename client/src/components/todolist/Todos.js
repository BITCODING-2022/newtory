import TodoItem from "./TodoItem";
import "../../css/todolist/todos.css";
import { useState } from 'react';

const Todos = (props) => {

  const [todos, setTodos] = useState(props.todos);

  return (
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
  );
};

export default Todos;