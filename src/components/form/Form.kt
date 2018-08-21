package components.form

import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.js.onSubmitFunction
import kotlinx.html.onSubmit
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*
import react.dom.form

interface FormProps: RProps {
    var handleSubmit: (e: Event) -> Unit
}

class FormComponent(props: FormProps): RComponent<FormProps, RState>(props) {
    override fun RBuilder.render() {
        div(classes = "form") {
             form {
                attrs {
                    onSubmitFunction = props.handleSubmit
                }

                input(name = "title", type = InputType.text) {
                    attrs {

                        value = "reactの勉強"
                        placeholder = "タイトル ※必須"
                    }
                }
                br{}

                textArea {
                    attrs {
                        name = "desc"
                        placeholder="説明を入力"
                        value="todoアプリを作っています！"
                    }
                }
                br{}
                button(type = ButtonType.submit) {
                    +"todoを作成"
                }
            }
        }
    }
}

fun RBuilder.form(handleSubmit: (e: Event) -> Unit) = child(FormComponent::class) {
    attrs.handleSubmit = handleSubmit
}