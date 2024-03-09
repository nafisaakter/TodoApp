package com.example.todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.ui.theme.TodoTheme
import com.example.todo.viewmodel.TodoViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.model.Todo
import com.example.todo.viewmodel.TodoUIState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoApp(todoViewModel: TodoViewModel= viewModel()){
    TodoScreen(uiState = todoViewModel.todoUIState)
    Scaffold (
        topBar={
            TopAppBar(title = { Text("Todos") })

        },
        content={
            TodoScreen(uiState =todoViewModel.todoUIState)
        }
    )
    }


@Composable
fun LoadingScreen(){
    Text("Loading...")
}
@Composable
fun ErrorScreen(){
    Text("Error retrieving data from API.")
}

@Composable
fun TodoList(todos:List<Todo>){
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ){
        items(todos){todo->
            Text(
                text =todo.title,
                modifier = Modifier.padding(top=4.dp, bottom = 4.dp)
            )
            Divider(color = Color.LightGray, thickness = 1.dp)

        }
    }
}
@Composable
fun TodoScreen(uiState: TodoUIState){
    when(uiState){
        is TodoUIState.Loading -> LoadingScreen()
        is TodoUIState.Success -> TodoList(uiState.todos)
        is TodoUIState.Error -> ErrorScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoTheme {
       TodoApp()
    }
}