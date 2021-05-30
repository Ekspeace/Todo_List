package com.ekspeace.todolist.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.ekspeace.todolist.Model.Todo;
import com.ekspeace.todolist.Repository.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private TodoRepository repository;
    private LiveData<List<Todo>> allTodo;
    public TodoViewModel(Application application) {
        super(application);
        repository = new TodoRepository(application);
        allTodo = repository.getAllTodo();
    }
    public void insert(Todo todo) {
        repository.insert(todo);
    }
    public void delete(Todo todo) {
        repository.delete(todo);
    }
    public void update(Todo todo) {
        repository.update(todo);
    }
    public LiveData<List<Todo>> getAllTodo() {
        return allTodo;
    }
}
