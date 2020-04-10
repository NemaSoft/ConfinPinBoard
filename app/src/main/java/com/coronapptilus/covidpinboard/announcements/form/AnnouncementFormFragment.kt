package com.coronapptilus.covidpinboard.announcements.form

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_announcement_form.*
import org.koin.android.ext.android.inject
import java.util.*

class AnnouncementFormFragment : Fragment(R.layout.fragment_announcement_form),
    AnnouncementFormContract.View {

    private val presenter: AnnouncementFormContract.Presenter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbar?.init(ToolbarView.FORM)

        this.presenter.attachView(this)
        setupPickersViews()
        // TODO retocar formatos hora/día
        setupSpinnerView()

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

    override fun setupSpinnerView() {
        val targetListNames = presenter.getSpinnerTargetList(context!!)

        val adapter = object :
            ArrayAdapter<String>(context!!, R.layout.spinner_layout_color, targetListNames) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: View = super.getDropDownView(position, convertView, parent)
                val item: TextView = view as TextView

                if (position == 0) {
                    item.setTextColor(Color.GRAY)
                } else {
                    item.setTextColor(Color.BLACK)
                }

                return view
            }
        }

        addForm_targetSpinner.adapter = adapter
        adapter.notifyDataSetChanged()

        addForm_targetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // TODO cambiar el color del texto original en el textview una vez se clicka
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //TODO("Not yet implemented")
            }
        }
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