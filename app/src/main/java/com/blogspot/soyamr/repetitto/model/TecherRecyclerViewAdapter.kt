package com.blogspot.soyamr.repetitto.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.repetitto.R
import com.blogspot.soyamr.repetitto.RetrofitPojo.User

class TecherRecyclerViewAdapter // If the list needs an update, call a notification method on the RecyclerView.Adapter object,
// such as notifyItemChanged().
// The layout manager then rebinds any affected view holders, allowing their data to be updated.
(private val dataSet: List<User>, private val listener: (View?, Int) -> Unit) : RecyclerView.Adapter<TecherRecyclerViewAdapter.ViewHolder>() {
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.teacher_recycler_view_item, parent, false)
        val vh = ViewHolder(v, listener)
        //setting listener
        //v.setOnClickListener { v -> listener(v, vh.adapterPosition) }
        return vh
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // - get element from your dataset at this position
// - replace the contents of the view with that element
        val data = dataSet[position]
        holder.teacherName.text = data.firstName
        holder.lastName.text = data.lastName
        holder.costForSubject.text = data.price.toString()
        //holder.teacherPhoto.setImageResource(data.getPhotoRecouseId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(itemView: View, private val clickListener: (View?, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var teacherName: TextView
        var lastName: TextView
        var costForSubject: TextView
        var teacherPhoto: ImageView? = null

        init {
            itemView.setOnClickListener {
                clickListener(it, adapterPosition)
            }
            teacherName = itemView.findViewById(R.id.name)
            lastName = itemView.findViewById(R.id.last_name)
            costForSubject = itemView.findViewById(R.id.cost)
            //teacherPhoto = itemView.findViewById(R.id.circular_photo);
        }
    } //TODO: some info

}