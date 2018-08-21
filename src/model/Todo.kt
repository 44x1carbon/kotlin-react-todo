package model

data class Todo(
        var id: Int,
        var title: String,
        var desc: String,
        var done: Boolean = false
)