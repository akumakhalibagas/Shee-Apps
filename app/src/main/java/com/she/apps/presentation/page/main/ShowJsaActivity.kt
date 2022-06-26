package com.she.apps.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.she.apps.data.source.remote.response.JsaItem
import com.she.apps.databinding.ActivityShowJsaBinding
import com.she.apps.databinding.LayoutFilterBinding
import com.she.apps.presentation.page.adapter.ItemJsaAdapter
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
class ShowJsaActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityShowJsaBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    private lateinit var itemJsaAdapter: ItemJsaAdapter
    private lateinit var list: List<JsaItem>
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
            itemJsaAdapter = ItemJsaAdapter(viewModel.getUsers()!!.tipeUser.equals("1"))
            included.rvJsa.adapter = itemJsaAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {
            btPdf.setOnClickListener {
                val pdfConverter = PdfUtils()
                pdfConverter.createPdf(scroll, this@ShowJsaActivity, "SheeDemoJsa${System.currentTimeMillis()}.pdf")
            }

            btExcel.setOnClickListener {
                createExcelJsaFile()
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

            itemJsaAdapter.onItemClick = { data ->
                val intent = Intent(this@ShowJsaActivity, JsaActivity::class.java)
                intent.putExtra("jsa", data)
                startActivity(intent)
            }
        }
    }

    private fun initObserver() {
        viewModel.getJsa("","")

        collectLifecycleFlow(viewModel.jsa) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {}
                is UiStateWrapper.Success -> {
                    list = state.data
                    itemJsaAdapter.setData(list)
                    binding.apply {
                        btPdf.isEnabled = true
                        btExcel.isEnabled = true
                        etFilter.isEnabled = true
                        btAll.isEnabled = true
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }
    }

    private fun createExcelJsaFile() {
        val wb: Workbook = HSSFWorkbook()
        var cell: Cell?
        val sheet: Sheet? = wb.createSheet("Demo Excel Sheet Ibpr")

        val row: Row = sheet!!.createRow(0)

        cell = row.createCell(0)
        cell.setCellValue("NO")
        cell = row.createCell(1)
        cell.setCellValue("TANGGAL")
        cell = row.createCell(2)
        cell.setCellValue("JAM")
        cell = row.createCell(3)
        cell.setCellValue("NAMA PEKERJA")
        cell = row.createCell(4)
        cell.setCellValue("NAMA PERUSAHAAN")
        cell = row.createCell(5)
        cell.setCellValue("DEPARTEMEN")
        cell = row.createCell(6)
        cell.setCellValue("TAHAP PEKERJA")
        cell = row.createCell(7)
        cell.setCellValue("POTENSI BAHAYA")
        cell = row.createCell(8)
        cell.setCellValue("UPAYA PENGENDALIAN")
        cell = row.createCell(9)
        cell.setCellValue("TANGGUNG JAWAB")
        cell = row.createCell(10)
        cell.setCellValue("SUPERVISOR")

        //column width
        sheet.setColumnWidth(0, 20 * 200)
        sheet.setColumnWidth(1, 30 * 200)
        sheet.setColumnWidth(2, 30 * 200)
        sheet.setColumnWidth(3, 30 * 200)
        sheet.setColumnWidth(4, 30 * 400)
        sheet.setColumnWidth(5, 30 * 400)
        sheet.setColumnWidth(6, 30 * 400)
        sheet.setColumnWidth(7, 30 * 200)
        sheet.setColumnWidth(8, 30 * 200)
        sheet.setColumnWidth(9, 30 * 200)
        sheet.setColumnWidth(10, 30 * 200)

        for (i in list.indices) {

            val row1: Row = sheet.createRow(i + 1)
            cell = row1.createCell(0)
            cell.setCellValue("${i + 1}")
            cell = row1.createCell(1)
            cell.setCellValue(changeDateFormat(list[i].tanggal.toString(), "yyyy-MM-dd"))
            cell = row1.createCell(2)
            cell.setCellValue(changeDateFormat(list[i].tanggal.toString(), "HH:mm:ss"))
            cell = row1.createCell(3)
            cell.setCellValue(list[i].pekerja)
            cell = row1.createCell(4)
            cell.setCellValue(list[i].perusahaan)
            cell = row1.createCell(5)
            cell.setCellValue(list[i].department)
            cell = row1.createCell(6)
            cell.setCellValue(list[i].pekerjaan)
            cell = row1.createCell(7)
            cell.setCellValue(list[i].bahaya)
            cell = row1.createCell(8)
            cell.setCellValue(list[i].pengendalian)
            cell = row1.createCell(9)
            cell.setCellValue(list[i].tanggungJawab)
            cell = row1.createCell(10)
            cell.setCellValue(list[i].supervisor)

            sheet.setColumnWidth(0, 20 * 200)
            sheet.setColumnWidth(1, 30 * 200)
            sheet.setColumnWidth(2, 30 * 200)
            sheet.setColumnWidth(3, 30 * 200)
            sheet.setColumnWidth(4, 30 * 400)
            sheet.setColumnWidth(5, 30 * 400)
            sheet.setColumnWidth(6, 30 * 400)
            sheet.setColumnWidth(7, 30 * 200)
            sheet.setColumnWidth(8, 30 * 200)
            sheet.setColumnWidth(9, 30 * 200)
            sheet.setColumnWidth(10, 30 * 200)
        }

        val filePath = File(externalMediaDirs[0], "SheeDemoJsa${System.currentTimeMillis()}.xls")
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

            etShift.visibility = View.GONE
            etStatus.visibility = View.GONE
            tvShift.visibility = View.GONE
            tvStatus.visibility = View.GONE

            btnSave.setOnClickListener {
                textfilter = etTglMulai.text.toString() + etTglEnd.text.toString()
                viewModel.getJsa(etTglMulai.text.toString(), etTglEnd.text.toString())
                dialogBottom.dismiss()
            }
        }
        dialogBottom.show()
    }
}