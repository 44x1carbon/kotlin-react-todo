package components.app.todolist

import components.todo.todo
import model.Todo
import react.*
import react.dom.ul


interface TodoListState : RState {
    var todoList: MutableList<Todo>
}

interface TodoListProps : RProps {
    var todoList: List<Todo>
    var setTodoStatus: (Todo) -> Unit
}

class TodoListComponent(props: TodoListProps): RComponent<TodoListProps, TodoListState>(props) {
    override fun TodoListState.init(props: TodoListProps) {
        todoList = props.todoList.toMutableList()
    }

    override fun RBuilder.render() {
        ul {
            for (todo in props.todoList.listIterator()) {
                todo(todo, props.setTodoStatus)
            }
        }
    }
}

fun RBuilder.todoList(todoList: List<Todo>, setTodoStatus: (Todo) -> Unit) = child(TodoListComponent::class) {
    attrs.todoList = todoList
    attrs.setTodoStatus = setTodoStatus
}