package ru.myitschool.work.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.myitschool.work.R
import ru.myitschool.work.databinding.ItemVisitBinding
import ru.myitschool.work.entities.EmployeeEntranceEntity
import ru.myitschool.work.utils.monthConverter
import ru.myitschool.work.utils.timeConverter

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
            binding.visitReaderId.text = item.readerName
            binding.visitDate.text = monthConverter(item.scanTime)
            binding.visitTime.text = timeConverter(item.scanTime)
            binding.visitDirection.text = item.entryType
            if(item.type == "smartphone"){
                binding.visitType.setImageResource(R.drawable.logo_visit_scan)
            }
            else{
                binding.visitType.setImageResource(R.drawable.logo_visit_card)
            }

        }

    }
    object DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<EmployeeEntranceEntity>() {
        override fun areItemsTheSame(oldItem: EmployeeEntranceEntity, newItem: EmployeeEntranceEntity): Boolean {
            return oldItem.id ==  newItem.id
        }
        override fun areContentsTheSame(oldItem: EmployeeEntranceEntity, newItem: EmployeeEntranceEntity): Boolean {
            return oldItem == newItem
        }
    }
}