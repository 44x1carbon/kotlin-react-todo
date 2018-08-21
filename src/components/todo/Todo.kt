package components.todo

import kotlinx.html.js.onClickFunction
import model.Todo
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.li
import react.dom.p
import react.dom.span

interface TodoProps: RProps {
    var todo: Todo
    var setTodoStatus: (Todo) -> Unit
}

class TodoComponent(props: TodoProps): RComponent<TodoProps, RState>(props) {
    override fun RBuilder.render() {
        val className = if(props.todo.done) "undone" else "done"
        val link = if(props.todo.done) "元に戻す" else "完了！"
        li(classes = className) {
            span { +props.todo.id.toString() }
            span { +":${props.todo.title}" }
            a(href = "#") {
                +link
                attrs {
                    onClickFunction = { e ->
                        e.preventDefault()
                        this@TodoComponent.props.setTodoStatus(this@TodoComponent.props.todo)
                    }
                }
            }
            p { +props.todo.desc }
        }
    }
}

fun RBuilder.todo(todo: Todo, setTodoStatus: (Todo) -> Unit) = child(TodoComponent::class) {
    attrs.todo = todo
    attrs.setTodoStatus = setTodoStatus
}

