package ru.myitschool.work.ui.admin.usersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.myitschool.work.R
import ru.myitschool.work.databinding.ItemEmployeeBinding
import ru.myitschool.work.databinding.ItemVisitBinding
import ru.myitschool.work.entities.EmployeeEntity
import ru.myitschool.work.entities.EmployeeEntranceEntity
import ru.myitschool.work.utils.monthConverter
import ru.myitschool.work.utils.timeConverter

class EmployeeListAdapter : PagingDataAdapter<EmployeeEntity, EmployeeListAdapter.ViewHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        return ViewHolder(
            ItemEmployeeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

    }
    inner class ViewHolder(
        private val binding: ItemEmployeeBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmployeeEntity) {
            binding.userName.text = item.name
            binding.position.text = item.position



        }

    }
    object DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<EmployeeEntity>() {
        override fun areItemsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity): Boolean {
            return oldItem.id ==  newItem.id
        }
        override fun areContentsTheSame(oldItem: EmployeeEntity, newItem: EmployeeEntity): Boolean {
            return oldItem == newItem
        }
    }
}