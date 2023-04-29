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
                // MaterialDatePicker is used to pick a date.
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText(R.string.pick_date)
                    .setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds()).build()

                // Add a listener to the date picker, so that when the user selects a date, the date
                // will be displayed in the dateText TextView.
                datePicker.addOnPositiveButtonClickListener {
                    dateText.text = getString(R.string.date, datePicker.headerText)
                }

                datePicker.show(parentFragmentManager, "datePicker")
            }

            timeButton.setOnClickListener {
                // MaterialTimePicker is used to pick a time.
                val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build()

                // Add a listener to the time picker, so that when the user selects a time, the time
                // will be displayed in the timeText TextView.
                timePicker.addOnPositiveButtonClickListener {
                    timeText.text =
                        getString(R.string.time, "${timePicker.hour}:${timePicker.minute}")
                }

                timePicker.show(parentFragmentManager, "timePicker")
            }

            saveButton.setOnClickListener {
                // Create a bundle to pass the date and time to the CustomDialogFragment.
                val bundle = Bundle().apply {
                    putString("date", dateText.text.toString())
                    putString("time", timeText.text.toString())
                }
                // Create a CustomDialogFragment and pass the bundle to it.
                val dialogFragment = CustomDialogFragment()
                dialogFragment.arguments = bundle
                // Set the parent of the CustomDialogFragment to this DialogScreensFragment.
                dialogFragment.parent = this@DialogScreensFragment
                dialogFragment.show(parentFragmentManager, "customDialog")
            }
        }


    }

    // Set the initial data for the TextViews.
    private fun setInitialData() {
        val noChoice = "No choice" // This is used to display "No choice" in the TextViews.
        binding.apply {
            dateText.text = getString(R.string.date, noChoice)
            timeText.text = getString(R.string.time, noChoice)
            savedDateText.text = getString(R.string.date, noChoice)
            savedTimeText.text = getString(R.string.time, noChoice)
        }
    }

    /**
     * This function is called when the user clicks the "Save" button in the CustomDialogFragment.
     */
    override fun onDataSaved(date: String, time: String) {
        binding.savedDateText.text = date
        binding.savedTimeText.text = time
    }

}

class CustomDialogFragment : DialogFragment() {

    /**
     * This interface is used to pass data from the CustomDialogFragment to the DialogScreensFragment.
     */
    interface CustomDialogListener {
        fun onDataSaved(date: String, time: String)
    }

    // This variable is used to set the parent of the CustomDialogFragment.
    lateinit var parent: Fragment

    // These variables are used to store the date and time.
    private lateinit var date: String
    private lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the date and time from the bundle.
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
            .setPositiveButton(R.string.save) { _, _ -> // When the user clicks the "Save" button, call the onDataSaved function of the parent.
                (parent as CustomDialogListener).onDataSaved(
                    date,
                    time
                ) // This is how we call the onDataSaved function of the parent.
            }
            .create()
    }
}