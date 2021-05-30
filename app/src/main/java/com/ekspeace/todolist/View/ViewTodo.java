package com.ekspeace.todolist.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekspeace.todolist.R;

public class ViewTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todo);
        String list = getIntent().getStringExtra("TODO_LIST");
        String title = getIntent().getStringExtra("TODO_TITLE");
        int id = getIntent().getIntExtra("KEY_ID", 0);

        ImageView edit_button = findViewById(R.id.update_todo);
        ImageView back_button = findViewById(R.id.view_back);
        TextView view_list = findViewById(R.id.view_list);
        TextView view_title = findViewById(R.id.view_title);

        view_list.setText(list);
        ManipulateText(title, view_title);

        back_button.setOnClickListener(view -> onBackPressed());
        edit_button.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpdateTodo.class);
            intent.putExtra("ID", id);
            intent.putExtra("TITLE", title);
            intent.putExtra("LIST", list);
            startActivity(intent);
        });
    }
    private void ManipulateText(String title, TextView view_title ){
        String Title;
        if(title.length() > 15){
           Title = title.substring(0, 15);
            Title = Title.concat(" ...");
            view_title.setText(Title);
        }else {view_title.setText(title);}


    }
}