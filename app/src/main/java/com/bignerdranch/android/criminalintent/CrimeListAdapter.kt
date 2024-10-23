package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemSeriousCrimeBinding

abstract class Holder (
    private val binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(crime: Crime)
}

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : Holder(binding) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class SeriousCrimeHolder(
    private val binding: ListItemSeriousCrimeBinding
) : Holder(binding) {
    override fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Crime is serious!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<Holder>() {

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return when (crime.requiresPolice) {
            true -> 1
            else -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        if (viewType == 1) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemSeriousCrimeBinding.inflate(inflater, parent, false)
            return SeriousCrimeHolder(binding)
        }
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size

}