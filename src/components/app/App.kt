package components.app

import model.Todo
import react.*
import components.app.todolist.todoList
import components.form.form
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.get
import react.dom.div
import react.dom.h1

interface AppState: RState {
    var todoList: MutableList<Todo>
    var countTodo: Int
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        todoList = mutableListOf(
                Todo(1, "Hello, React!", "React始めました"),
                Todo(2, "Hello, Redux!", "Reduxも始めました")
        )
        countTodo = todoList.size + 1
    }

    fun handleSubmit(e: Event) {
        e.preventDefault()
        val target = e.target as HTMLFormElement
        val titleInput =  target.get("title").unsafeCast<HTMLInputElement>()
        val descInput =  target.get("desc").unsafeCast<HTMLInputElement>()

        val title = titleInput.value
        val desc = descInput.value
        val todo = Todo(state.countTodo, title, desc)

        setState {
            todoList = (state.todoList + todo).toMutableList()
            countTodo = state.countTodo + 1
        }

        titleInput.value = ""
        descInput.value = ""
    }

    fun setTodoStatus(clickTodo: Todo) {
        val todos = state.todoList
        val todo = todos.get(clickTodo.id - 1)
        todo.done = !todo.done
        todos[clickTodo.id - 1] = todo

        setState {
            todoList = todos
        }
    }

    override fun RBuilder.render() {
        div( classes = "app" ) {
            h1 { +"todoアプリを作ってみた" }
            form(handleSubmit = ::handleSubmit)
            todoList(state.todoList, ::setTodoStatus)
        }
    }
}

fun RBuilder.app()  = child(App::class) {}
