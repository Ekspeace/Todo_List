package com.ekspeace.todolist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekspeace.todolist.Model.PopUp;
import com.ekspeace.todolist.R;
import com.ekspeace.todolist.Model.Todo;
import com.ekspeace.todolist.ViewModel.TodoViewModel;

import java.util.List;

public class RecyclerViewTodo extends RecyclerView.Adapter<RecyclerViewTodo.MyViewHolder> {
    private Context context;
    private List<Todo> List;
    private OnItemClickListener onItemClickListener;
    private int Position;
    private TodoViewModel todoViewModel;
    private View layout;

    public RecyclerViewTodo(Context context, java.util.List<Todo> List, TodoViewModel todoViewModel, View layout) {
        this.context = context;
        this.List = List;
        this.todoViewModel = todoViewModel;
        this.layout = layout;
    }

 @NonNull
    @Override
    public RecyclerViewTodo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_recycler_view_todo, parent, false);
        return new RecyclerViewTodo.MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTodo.MyViewHolder holder, int position) {
        holder.todo_title.setText(List.get(position).getTitle());
        holder.todo_date.setText(List.get(position).getDate());

        holder.todo_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Position = holder.getLayoutPosition();
                PopUp.DeleteDialog(context,"Delete Task", "Are you sure, you want to delete this task ?", todoViewModel, List, Position, layout);
            }
        });
    }


    @Override
    public int getItemCount() {
        return List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView todo_title, todo_date;
        private ImageView todo_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            todo_title = itemView.findViewById(R.id.title_task);
            todo_date = itemView.findViewById(R.id.task_date);
            todo_delete = itemView.findViewById(R.id.button_delete);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                int position = MyViewHolder.this.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(itemView, position);
                }
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
