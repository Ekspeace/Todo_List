package com.ekspeace.todolist.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ekspeace.todolist.Model.PopUp;
import com.ekspeace.todolist.Model.Todo;
import com.ekspeace.todolist.R;
import com.ekspeace.todolist.ViewModel.TodoViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);

        ImageView back_button = findViewById(R.id.new_back);
        ImageView save_button = findViewById(R.id.new_confirm);
        EditText title = findViewById(R.id.new_title);
        EditText list = findViewById(R.id.new_list);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        TodoViewModel todoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TodoViewModel.class);

        save_button.setOnClickListener(view -> AddTodo(title,list,todoViewModel, layout));
        back_button.setOnClickListener(view -> onBackPressed());
    }

    private void AddTodo(EditText title, EditText list, TodoViewModel todoViewModel, View view){
        String Title = title.getText().toString().trim();
        String List = list.getText().toString().trim();

        if (TextUtils.isEmpty(List)) {
            list.setError("Please enter a task");
            return;
        }
        if (TextUtils.isEmpty(Title)) {
            title.setError("Please enter a task title");
            return;
        }
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");
        String Date = simpleDateFormat.format(date);

        Todo todo = new Todo();
        todo.setTitle(Title);
        todo.setMessage(List);
        todo.setDate(Date);
        todoViewModel.insert(todo);
        PopUp.Toast(this, view, "A task successfully added", Toast.LENGTH_SHORT);
        startActivity(new Intent(this, MainActivity.class));
    }
}