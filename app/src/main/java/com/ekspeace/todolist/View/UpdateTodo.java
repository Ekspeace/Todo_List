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

public class UpdateTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);
        String title_update = getIntent().getStringExtra("TITLE");
        String list_update = getIntent().getStringExtra("LIST");

        ImageView save_button = findViewById(R.id.update_confirm);
        ImageView back_button = findViewById(R.id.update_back);
        EditText update_list = findViewById(R.id.update_list);
        EditText update_title = findViewById(R.id.update_title);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        TodoViewModel todoViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TodoViewModel.class);

        update_title.setText(title_update);
        update_list.setText(list_update);

        save_button.setOnClickListener(v -> UpdateNote(update_title, update_list, todoViewModel, layout));
        back_button.setOnClickListener(view -> onBackPressed());
    }

    private void UpdateNote(EditText title, EditText list, TodoViewModel todoViewModel, View view){
        int id = getIntent().getIntExtra("ID", 1);
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

        Todo todo = new Todo(List, id, Date, Title);
        todoViewModel.update(todo);
        PopUp.Toast(this, view, "A task successfully updated", Toast.LENGTH_SHORT);
        startActivity(new Intent(this, MainActivity.class));
    }
}