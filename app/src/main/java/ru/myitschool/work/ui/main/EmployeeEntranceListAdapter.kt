package ru.myitschool.work.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.myitschool.work.databinding.ItemVisitBinding
import ru.myitschool.work.entities.EmployeeEntranceEntity
import ru.myitschool.work.utils.dateConverter

class EmployeeEntranceListAdapter : PagingDataAdapter<EmployeeEntranceEntity, EmployeeEntranceListAdapter.ViewHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        return ViewHolder(
            ItemVisitBinding.inflate(
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
        private val binding: ItemVisitBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmployeeEntranceEntity) {
            binding.readerName.text = item.readerName
            binding.timeVisit.text = dateConverter(item.scanTime)
        }

    }
    object DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<EmployeeEntranceEntity>() {
        override fun areItemsTheSame(oldItem: EmployeeEntranceEntity, newItem: EmployeeEntranceEntity): Boolean {
            return oldItem.scanTime ==  newItem.scanTime
        }
        override fun areContentsTheSame(oldItem: EmployeeEntranceEntity, newItem: EmployeeEntranceEntity): Boolean {
            return oldItem == newItem
        }
    }
}