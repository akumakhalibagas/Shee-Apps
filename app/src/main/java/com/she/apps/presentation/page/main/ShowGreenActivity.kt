package com.she.apps.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.she.apps.R
import com.she.apps.data.source.remote.response.GreenItem
import com.she.apps.databinding.ActivityShowGreenBinding
import com.she.apps.databinding.LayoutFilterBinding
import com.she.apps.presentation.page.adapter.ItemGreenAdapter
import com.she.apps.presentation.state.UiStateWrapper
import com.she.apps.utils.*
import dagger.hilt.android.AndroidEntryPoint
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ShowGreenActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityShowGreenBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    private lateinit var itemGreenAdapter: ItemGreenAdapter
    private lateinit var list: List<GreenItem>
    private var textfilter : String = "All"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.apply {
            etFilter.setText(textfilter)
            itemGreenAdapter = ItemGreenAdapter(viewModel.getUsers()!!.tipeUser.equals("1"))
            included.rvGreen.adapter = itemGreenAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {
            btPdf.setOnClickListener {
                val pdfConverter = PdfUtils()
                pdfConverter.createPdf(scroll, this@ShowGreenActivity, "SheeDemoGreen${System.currentTimeMillis()}.pdf")
            }

            btExcel.setOnClickListener {
                createExcelFile()
            }

            etFilter.apply {
                setOnTouchListener { _, _ ->
                    showFilter()
                    return@setOnTouchListener false
                }
            }

            btAll.setOnClickListener {
                viewModel.getJsa("","")
            }

            itemGreenAdapter.onItemClick = { data ->
                val intent = Intent(this@ShowGreenActivity, GreenActivity::class.java)
                intent.putExtra("green", data)
                startActivity(intent)
            }
        }
    }

    private fun initObserver() {
        viewModel.getGreen("","","","")

        collectLifecycleFlow(viewModel.green) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {}
                is UiStateWrapper.Success -> {
                    binding.apply {
                        btPdf.isEnabled = true
                        btExcel.isEnabled = true
                        etFilter.isEnabled = true
                        btAll.isEnabled = true
                    }
                    list = state.data
                    itemGreenAdapter.setData(list)
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }

    private fun createExcelFile() {
        val wb: Workbook = HSSFWorkbook()
        var cell: Cell?
        val sheet: Sheet? = wb.createSheet("Demo Excel Sheet Green")

        val row: Row = sheet!!.createRow(0)

        cell = row.createCell(0)
        cell.setCellValue("NO")
        cell = row.createCell(1)
        cell.setCellValue("DATE")
        cell = row.createCell(2)
        cell.setCellValue("JAM")
        cell = row.createCell(3)
        cell.setCellValue("LOKASI")
        cell = row.createCell(4)
        cell.setCellValue("KONDISI TINDAKAN AMAN")
        cell = row.createCell(5)
        cell.setCellValue("SARAN PERBAIKAN")
        cell = row.createCell(6)
        cell.setCellValue("DIBICARAKAN DENGAN")
        cell = row.createCell(7)
        cell.setCellValue("STATUS")
        cell = row.createCell(8)
        cell.setCellValue("CATEGORY")
        cell = row.createCell(9)
        cell.setCellValue("SHIFT")
        cell = row.createCell(10)
        cell.setCellValue("SITE")
        cell = row.createCell(11)
        cell.setCellValue("DEPARTEMENT")
        cell = row.createCell(12)
        cell.setCellValue("PELAPOR")

        //column width
        sheet.setColumnWidth(0, 20 * 200)
        sheet.setColumnWidth(1, 30 * 200)
        sheet.setColumnWidth(2, 30 * 200)
        sheet.setColumnWidth(3, 20 * 200)
        sheet.setColumnWidth(4, 30 * 400)
        sheet.setColumnWidth(5, 30 * 400)
        sheet.setColumnWidth(6, 20 * 400)
        sheet.setColumnWidth(7, 30 * 200)
        sheet.setColumnWidth(8, 30 * 200)
        sheet.setColumnWidth(9, 20 * 400)
        sheet.setColumnWidth(10, 30 * 200)
        sheet.setColumnWidth(11, 30 * 200)
        sheet.setColumnWidth(12, 30 * 200)

        for (i in list.indices) {

            val row1: Row = sheet.createRow(i + 1)
            cell = row1.createCell(0)
            cell.setCellValue("${i + 1}")
            cell = row1.createCell(1)
            cell.setCellValue(changeDateFormat(list[i].date.toString(), "yyyy-MM-dd"))
            cell = row1.createCell(2)
            cell.setCellValue(changeDateFormat(list[i].date.toString(), "HH:mm:ss"))
            cell = row1.createCell(3)
            cell.setCellValue(list[i].lokasi)
            cell = row1.createCell(4)
            cell.setCellValue(list[i].kondisi)
            cell = row1.createCell(5)
            cell.setCellValue(list[i].saran)
            cell = row1.createCell(6)
            cell.setCellValue(list[i].dibicarakan)
            cell = row1.createCell(7)
            cell.setCellValue(list[i].status)
            cell = row1.createCell(8)
            cell.setCellValue(list[i].kategori)
            cell = row1.createCell(9)
            cell.setCellValue(list[i].shift)
            cell = row1.createCell(10)
            cell.setCellValue(list[i].site)
            cell = row1.createCell(11)
            cell.setCellValue(list[i].department)
            cell = row1.createCell(12)
            cell.setCellValue(list[i].pelapor)

            sheet.setColumnWidth(0, 20 * 200)
            sheet.setColumnWidth(1, 30 * 200)
            sheet.setColumnWidth(2, 30 * 200)
            sheet.setColumnWidth(3, 20 * 200)
            sheet.setColumnWidth(4, 30 * 400)
            sheet.setColumnWidth(5, 30 * 400)
            sheet.setColumnWidth(6, 20 * 400)
            sheet.setColumnWidth(7, 30 * 200)
            sheet.setColumnWidth(8, 30 * 200)
            sheet.setColumnWidth(9, 20 * 400)
            sheet.setColumnWidth(10, 30 * 200)
            sheet.setColumnWidth(11, 30 * 200)
            sheet.setColumnWidth(12, 30 * 200)
        }

        val filePath = File(externalMediaDirs[0], "SheeDemoGreen${System.currentTimeMillis()}.xls")
        try {
            if (!filePath.exists()) {
                filePath.createNewFile()
            }
            val fileOutputStream = FileOutputStream(filePath)
            wb.write(fileOutputStream)
            Toast.makeText(this, "created at $filePath", Toast.LENGTH_SHORT).show()

            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showFilter(){
        val layoutDialog = LayoutFilterBinding.inflate(layoutInflater, null, false)
        val dialogBottom = BottomSheetDialog(this)
        dialogBottom.setContentView(layoutDialog.root)
        layoutDialog.apply {
            etTglMulai.apply {
                onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        v?.showDatePicker {
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val dateSelected: String = dateFormat.format(it.time)
                            etTglMulai.setText(dateSelected)
                        }
                    }
                }
                setOnClickListener {
                    showDatePicker {
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val dateSelected: String = dateFormat.format(it.time)
                        etTglMulai.setText(dateSelected)
                    }
                }
            }

            etTglEnd.apply {
                onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        v?.showDatePicker {
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val dateSelected: String = dateFormat.format(it.time)
                            etTglEnd.setText(dateSelected)
                        }
                    }
                }
                setOnClickListener {
                    showDatePicker {
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val dateSelected: String = dateFormat.format(it.time)
                        etTglEnd.setText(dateSelected)
                    }
                }
            }

            etShift.apply {
                setAdapter(
                    ArrayAdapter(
                        this@ShowGreenActivity,
                        R.layout.item_dropdown,
                        Datas.shift
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }

            etStatus.apply {
                setAdapter(
                    ArrayAdapter(
                        this@ShowGreenActivity,
                        R.layout.item_dropdown,
                        Datas.status
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }
            }

            btnSave.setOnClickListener {
                textfilter = etTglMulai.text.toString() + etTglEnd.text.toString() + etShift.text.toString() + etStatus.text.toString()
                viewModel.getGreen(etTglMulai.text.toString(), etTglEnd.text.toString(), etShift.text.toString(), etStatus.text.toString())
                dialogBottom.dismiss()
            }
        }
        dialogBottom.show()
    }
}