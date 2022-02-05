import TodoItem from "./TodoItem";
import "../../css/todolist/todos.css";

const Todos = ({todos}) => {

  return (
    <div className="TodoList">
        <div className="TodoList"> {todos &&
          todos.map(todo => (
            <TodoItem 
              key={todo.id} 
              todo={todo}
            />
            ))
          }
        </div>
    </div>
  );
};

export default Todos;