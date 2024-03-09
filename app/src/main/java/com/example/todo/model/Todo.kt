package com.example.todo.model

import android.icu.text.CaseMap.Title
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
data class Success(val todos: List<Todo>)
data class Todo(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean
)
const val Base_URL = "https://jsonplaceholder.typicode.com"
interface TodosApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
    companion object{
        var todosService: TodosApi? = null
        fun getInstance(): TodosApi {
            if(todosService === null){
                todosService = Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(TodosApi::class.java)
            }
            return todosService!!
        }
    }
}