package com.example.myapplication.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class TodoAdapter (
    private val todos: MutableList<Todo>

) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
     class TodoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )

    }

    fun addTodo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1 );
    }

    fun deleteDoneTodos(){
        todos.removeAll{todo->
            todo.isChecked
        }
        notifyDataSetChanged();
    }

    override fun getItemCount(): Int {
       return todos.size;
    }

    private fun ToggleStrikeThrough(tvTodoTitle: TextView, isChecked:Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        val it = holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
        val cb = holder.itemView.findViewById<CheckBox>(R.id.cdDone)
        holder.itemView.apply {
            it.text = curTodo.title
            cb.isChecked = curTodo.isChecked
            ToggleStrikeThrough(it,curTodo.isChecked)
            cb.setOnCheckedChangeListener { _, isChecked ->
                ToggleStrikeThrough(it,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }
}
