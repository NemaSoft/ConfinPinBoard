package com.coronapptilus.covidpinboard.announcements.form

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.announcements.commons.filter.adapter.FilterGridAdapter
import com.coronapptilus.covidpinboard.commons.components.ToolbarView
import com.coronapptilus.covidpinboard.domain.models.AnnouncementModel
import com.coronapptilus.covidpinboard.utils.CalendarUtils
import com.coronapptilus.covidpinboard.utils.CategoryUtils
import com.coronapptilus.covidpinboard.utils.CategoryUtils.getCategoryString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_announcement_form.*
import kotlinx.android.synthetic.main.spinner_layout_color.*
import org.koin.android.ext.android.inject
import java.util.*

class AnnouncementFormFragment : Fragment(R.layout.fragment_announcement_form),
    AnnouncementFormContract.View {

    private val presenter: AnnouncementFormContract.Presenter by inject()
    private val categories = mutableListOf<AnnouncementModel.Category>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbar?.init(ToolbarView.FORM)

        this.presenter.attachView(this)

        setUpViews()
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

    private fun setUpViews() {
        setupEditTexts()
        setupPickersViews()
        setupSpinnerView()

        addForm_addButton.setOnClickListener {
            getAnnouncementInfo()
        }

        addForm_categories.setOnClickListener {
            categoriesEditTextParent.error = null
            context?.apply {
                showFilterDialog { list ->
                    categories.clear()
                    categories.addAll(list)
                    addForm_categories.text =
                        list.joinToString { getCategoryString(it) }.toEditable()
                }
            }
        }
    }

    private fun setupEditTexts() {
        // Title
        addForm_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                titleEditTextParent.error = null
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // Announcer
        addForm_announcer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                announcerEditTextParent.error = null
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // Place
        addForm_place.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                placeEditTextParent.error = null
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        // Description
        addForm_description.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                descriptionEditTextParent.error = null
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setupPickersViews() {

        val pickersListener: View.OnClickListener = View.OnClickListener {
            when (it) {
                addForm_startingDate -> showDatePickerDialog(it)
                addForm_endingDate -> showDatePickerDialog(it)
                addForm_startingTime -> showTimePickerDialog(it)
                addForm_endingTime -> showTimePickerDialog(it)
            }
        }

        addForm_startingDate.setOnClickListener(pickersListener)
        addForm_endingDate.setOnClickListener(pickersListener)
        addForm_startingTime.setOnClickListener(pickersListener)
        addForm_endingTime.setOnClickListener(pickersListener)
    }

    private fun setupSpinnerView() {

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

                when (item.text) {
                    context.resources.getString(R.string.adults) -> item.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_target_adults,
                        0,
                        0,
                        0
                    )
                    context.resources.getString(R.string.children) -> item.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_target_kids,
                        0,
                        0,
                        0
                    )
                    context.resources.getString(R.string.families) -> item.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_target_families,
                        0,
                        0,
                        0
                    )
                }
                item.compoundDrawablePadding = 8

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
            override fun onNothingSelected(parent: AdapterView<*>?) {
                clearTargetError()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                clearTargetError()
                targetSpinnerLayout?.setTextColor(Color.BLACK)
            }
        }
    }

    private fun showDatePickerDialog(view: View) {
        // Current date
        val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        clearDateTimeError()

        // Picker launch
        if (context != null) {
            val datePickerDialog = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { _, dYear, dMonth, dDay ->
                    val formattedDate = String.format(
                        DATE_FORMAT,
                        CalendarUtils.twoDigits(dDay),
                        CalendarUtils.twoDigits(dMonth + 1),
                        dYear
                    )

                    when (view) {
                        addForm_startingDate -> addForm_startingDate.text =
                            formattedDate.toEditable()
                        addForm_endingDate -> addForm_endingDate.text = formattedDate.toEditable()
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

        clearDateTimeError()

        // Picker launch
        if (context != null) {
            val timePickerDialog = TimePickerDialog(
                context!!,
                TimePickerDialog.OnTimeSetListener { _, dHour, dMinute ->
                    val formattedHour = String.format(
                        TIME_FORMAT,
                        CalendarUtils.twoDigits(dHour),
                        CalendarUtils.twoDigits(dMinute)
                    )

                    when (view) {
                        addForm_startingTime -> addForm_startingTime.text =
                            formattedHour.toEditable()
                        addForm_endingTime -> addForm_endingTime.text = formattedHour.toEditable()
                    }
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }
    }

    private fun getAnnouncementInfo() {

        val announcer = addForm_announcer.text.toString()
        val title = addForm_title.text.toString()
        val description = addForm_description.text.toString()
        val place = addForm_place.text.toString()

        val targetPosition = addForm_targetSpinner.selectedItemPosition
        val target = presenter.getTargetType(targetPosition)

        val startingDate = addForm_startingDate.text.toString()
        val startingTime = addForm_startingTime.text.toString()
        val endingDate = addForm_endingDate.text.toString()
        val endingTime = addForm_endingTime.text.toString()

        this.presenter.submitForm(
            announcer,
            title,
            description,
            place,
            categories,
            target,
            startingDate,
            startingTime,
            endingDate,
            endingTime
        )
    }

    private fun Context.showFilterDialog(callBack: (List<AnnouncementModel.Category>) -> Unit) {

        val dialogView = View.inflate(this, R.layout.filter_dialog, null)
        val closeButton =
            dialogView.findViewById<AppCompatImageView>(R.id.filter_dialog_close_button)
        val okButton = dialogView.findViewById<AppCompatButton>(R.id.filter_dialog_ok_button)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.filter_dialog_recyclerview)


        val filterGridAdapter = FilterGridAdapter(this, CategoryUtils.getAllCategories())
        filterGridAdapter.setCheckedCategories(categories)

        val displayMetrics: DisplayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density

        recyclerView.adapter = filterGridAdapter
        recyclerView.layoutManager =
            GridLayoutManager(context, (screenWidthDp / ToolbarView.COLUMN_WIDTH + 0.5).toInt())


        val mBuilder = AlertDialog.Builder(this).setView(dialogView).create()

        closeButton.setOnClickListener {
            mBuilder.dismiss()
        }

        okButton.setOnClickListener {
            callBack.invoke(filterGridAdapter.getCheckedCategories())
            mBuilder.dismiss()
        }

        mBuilder.show()
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    override fun showMessage(message: Int) {
        context?.let { Toast.makeText(it, getString(message), Toast.LENGTH_LONG).show() }
    }

    override fun setErrorMessage(message: Int, formItem: FormItem) {
        when (formItem) {
            FormItem.ANNOUNCER -> announcerEditTextParent.error = getString(message)
            FormItem.TITLE -> titleEditTextParent.error = getString(message)
            FormItem.PLACE -> placeEditTextParent.error = getString(message)
            FormItem.DATE -> {
                dateForm_error.text = getString(message)
                dateForm_error.visibility = View.VISIBLE
            }
            FormItem.TARGET -> {
                targetForm_error.text = getString(message)
                targetForm_error.visibility = View.VISIBLE
            }
            FormItem.CATEGORIES -> categoriesEditTextParent.error = getString(message)
            FormItem.DESCRIPTION -> descriptionEditTextParent.error = getString(message)
        }
    }

    private fun clearDateTimeError() {
        dateForm_error.visibility = View.GONE
    }

    private fun clearTargetError() {
        targetForm_error.visibility = View.GONE
    }

    override fun clearForm() {
        addForm_title.setText("")
        addForm_announcer.setText("")
        addForm_place.setText("")
        addForm_startingDate.setText("")
        addForm_startingTime.setText("")
        addForm_endingDate.setText("")
        addForm_endingTime.setText("")
        addForm_categories.setText("")
        addForm_targetSpinner.setSelection(0)
        addForm_description.setText("")
    }

    companion object {
        private const val DATE_FORMAT = "%s / %s / %s"
        private const val TIME_FORMAT = "%s : %s"
    }
}
