package com.coronapptilus.covidpinboard.announcements.form

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import kotlinx.android.synthetic.main.fragment_announcement_form.*
import java.util.*

class AnnouncementFormFragment : Fragment(R.layout.fragment_announcement_form),
    AnnouncementFormContract.View {

    private var presenter: AnnouncementFormContract.Presenter = AnnouncementFormPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter.attachView(this)
        setupPickersViews()

        addForm_addButton.setOnClickListener {
            this.presenter.submitForm()
        }
    }

    override fun setupPickersViews() {

        val pickersListener: View.OnClickListener = View.OnClickListener {
            when (it) {
                addForm_startDatePickerButton -> showDatePickerDialog(it)
                addForm_endDatePickerButton -> showDatePickerDialog(it)
                addForm_startTimePickerButton -> showTimePickerDialog(it)
                addForm_endTimePickerButton -> showTimePickerDialog(it)
            }
        }

        addForm_startDatePickerButton.setOnClickListener(pickersListener)
        addForm_endDatePickerButton.setOnClickListener(pickersListener)
        addForm_startTimePickerButton.setOnClickListener(pickersListener)
        addForm_endTimePickerButton.setOnClickListener(pickersListener)
    }

    override fun onPause() {
        super.onPause()
        this.presenter.detachView()
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    private fun showDatePickerDialog(view: View) {
        // Current date
        val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Picker launch
        if (context != null) {
            val datePickerDialog = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { _, dYear, dMonth, dDay ->
                    val formattedDate = String.format(
                        getString(R.string.form_formattedDate),
                        dDay,
                        dMonth + 1,
                        dYear
                    )

                    when (view) {
                        addForm_startDatePickerButton -> addForm_startingDate.text = formattedDate
                        addForm_endDatePickerButton -> addForm_endingDate.text = formattedDate
                    }
                },
                year,
                month,
                day
            )

            datePickerDialog.datePicker.minDate = calendar.timeInMillis

            datePickerDialog.show()
        }
    }

    private fun showTimePickerDialog(view: View) {
        // Current time
        val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Picker launch
        if (context != null) {
            val timePickerDialog = TimePickerDialog(
                context!!,
                TimePickerDialog.OnTimeSetListener { _, dHour, dMinute ->
                    val formattedHour = String.format(
                        getString(R.string.form_formattedHour),
                        dHour,
                        dMinute
                    )

                    when (view) {
                        addForm_startTimePickerButton -> addForm_startingTime.text = formattedHour
                        addForm_endTimePickerButton -> addForm_endingTime.text = formattedHour
                    }
                },
                hour,
                minute,
                true
            )

            timePickerDialog.show()
        }
    }

}