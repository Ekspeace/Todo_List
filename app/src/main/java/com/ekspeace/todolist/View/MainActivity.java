package com.ekspeace.todolist.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ekspeace.todolist.Adapter.RecyclerViewTodo;
import com.ekspeace.todolist.Custom.CustomAnalogClock;
import com.ekspeace.todolist.Model.PopUp;
import com.ekspeace.todolist.Model.Todo;
import com.ekspeace.todolist.R;
import com.ekspeace.todolist.ViewModel.TodoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView add_button = findViewById(R.id.add_todo);
        TextView no_task_text = findViewById(R.id.no_todos);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        TodoViewModel todoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TodoViewModel.class);

        PopulateRecyclerView(todoViewModel, recyclerView, no_task_text);
        add_button.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, NewTodo.class)));
    }

    @Override
    public void onBackPressed() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            PopUp.Toast(MainActivity.this, layout,"Please press again to exit...", Toast.LENGTH_SHORT);
        } else {
            System.exit(0);
        }
    }

    private void PopulateRecyclerView(TodoViewModel todoViewModel, RecyclerView recyclerView, TextView textView){
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            todoViewModel.getAllTodo().observe(this, (Observer<List<Todo>>) todos -> {
                if(todos.isEmpty()){
                    textView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }else{
                    textView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
                RecyclerViewTodo adapter = new RecyclerViewTodo(this, todos, todoViewModel, layout);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener((view, position) -> {
                    String Title = todos.get(position).getTitle();
                    String Message = todos.get(position).getMessage();
                    String Date = todos.get(position).getDate();
                    int Id = todos.get(position).getId();
                    Intent intent = new Intent(getBaseContext(), ViewTodo.class);
                    intent.putExtra("KEY_ID", Id);
                    intent.putExtra("TODO_LIST", Message);
                    intent.putExtra("TODO_DATE", Date);
                    intent.putExtra("TODO_TITLE", Title);
                    startActivity(intent);
                });
            });
    }
}