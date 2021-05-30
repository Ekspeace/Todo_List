package com.ekspeace.todolist.Model;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ekspeace.todolist.R;
import com.ekspeace.todolist.ViewModel.TodoViewModel;

import java.util.List;

public class PopUp {

    public static void Toast(Context context, View layout, String message, int duration)
    {
        TextView tvDesc = layout.findViewById(R.id.toast_message);

        tvDesc.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 40);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }

    public static void DeleteDialog(Context context, String title, String message, TodoViewModel noteViewModel, List<Todo> List, int Position, View layout) {
        Dialog alertDialog = new Dialog(context);
        alertDialog.setContentView(R.layout.pop_up);

        TextView tvTitle = alertDialog.findViewById(R.id.dialog_title);
        TextView tvDesc = alertDialog.findViewById(R.id.dialog_desc);
        TextView btnClose = alertDialog.findViewById(R.id.no_button);
        TextView btnConfirm = alertDialog.findViewById(R.id.yes_button);

        tvTitle.setText(title);
        tvDesc.setText(message);
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }

        btnClose.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = new Todo(List.get(Position).getMessage(), List.get(Position).getId(), List.get(Position).getDate(), List.get(Position).getTitle());
                noteViewModel.delete(todo);
                Toast(context, layout,"Task deleted successfully", Toast.LENGTH_SHORT);
                alertDialog.dismiss();

            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }


}
