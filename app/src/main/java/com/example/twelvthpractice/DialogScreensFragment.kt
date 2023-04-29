package com.example.twelvthpractice

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.twelvthpractice.databinding.FragmentDialogScreensBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class DialogScreensFragment : Fragment(), CustomDialogFragment.CustomDialogListener {

    private lateinit var binding: FragmentDialogScreensBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogScreensBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setInitialData()
        binding.apply {
            dateButton.setOnClickListener {
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText(R.string.pick_date)
                    .setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds()).build()

                datePicker.addOnPositiveButtonClickListener {
                    dateText.text = getString(R.string.date, datePicker.headerText)
                }

                datePicker.show(parentFragmentManager, "datePicker")
            }

            timeButton.setOnClickListener {
                val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build()

                timePicker.addOnPositiveButtonClickListener {
                    timeText.text =
                        getString(R.string.time, "${timePicker.hour}:${timePicker.minute}")
                }

                timePicker.show(parentFragmentManager, "timePicker")
            }

            saveButton.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("date", dateText.text.toString())
                    putString("time", timeText.text.toString())
                }
                val dialogFragment = CustomDialogFragment()
                dialogFragment.arguments = bundle
                dialogFragment.parent = this@DialogScreensFragment
                dialogFragment.show(parentFragmentManager, "customDialog")
            }
        }


    }

    private fun setInitialData() {
        val noChoice = "No choice"
        binding.apply {
            dateText.text = getString(R.string.date, noChoice)
            timeText.text = getString(R.string.time, noChoice)
            savedDateText.text = getString(R.string.date, noChoice)
            savedTimeText.text = getString(R.string.time, noChoice)
        }
    }

    override fun onDataSaved(date: String, time: String) {
        binding.savedDateText.text = date
        binding.savedTimeText.text = time
    }

}

class CustomDialogFragment : DialogFragment() {

    interface CustomDialogListener {
        fun onDataSaved(date: String, time: String)
    }

    lateinit var parent: Fragment

    private lateinit var date: String
    private lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            date = it.getString("date") ?: ""
            time = it.getString("time") ?: ""
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        return builder
            .setTitle(R.string.save_data)
            .setMessage(getString(R.string.save_message, date, time))
            .setPositiveButton(R.string.save) { _, _ ->
                (parent as CustomDialogListener).onDataSaved(date, time)
            }
            .create()
    }
}