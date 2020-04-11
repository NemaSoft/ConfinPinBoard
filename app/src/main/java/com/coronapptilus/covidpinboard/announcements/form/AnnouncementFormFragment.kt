package com.coronapptilus.covidpinboard.announcements.form

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.DisplayMetrics
import android.view.LayoutInflater
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
import androidx.navigation.findNavController
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
        setupPickersViews()
        setupSpinnerView()

        addForm_addButton.setOnClickListener {
            getAnnouncementInfo()
        }

        addForm_categories.setOnClickListener {
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

        val dialogView = LayoutInflater.from(this).inflate(R.layout.filter_dialog, null)
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

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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

    override fun navigateToBoardFragment() {
        view?.findNavController()?.navigate(R.id.action_form_fragment_to_list_fragment)
        // TODO. investigar porqué se quedan las tabs activas
    }

    companion object {
        private const val DATE_FORMAT = "%s / %s / %s"
        private const val TIME_FORMAT = "%s : %s"
    }
}
