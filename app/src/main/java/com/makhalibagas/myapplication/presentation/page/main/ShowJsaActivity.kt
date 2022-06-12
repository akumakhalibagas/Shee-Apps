package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.data.source.remote.response.JsaItem
import com.makhalibagas.myapplication.databinding.ActivityShowJsaBinding
import com.makhalibagas.myapplication.presentation.page.adapter.ItemJsaAdapter
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.PdfConverterJsa
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import com.makhalibagas.myapplication.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ShowJsaActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityShowJsaBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    private lateinit var itemJsaAdapter: ItemJsaAdapter
    private lateinit var list: List<JsaItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        initView()
        initListener()
        initObserver()
    }

    private fun initView(){
        binding.apply {
            itemJsaAdapter = ItemJsaAdapter()
            included.rvJsa.adapter = itemJsaAdapter
        }
    }

    private fun initListener() {
        binding.apply {
            btPdf.setOnClickListener {
                val pdfConverter = PdfConverterJsa()
                pdfConverter.createPdf(this@ShowJsaActivity, list, this@ShowJsaActivity)
            }

            btExcel.setOnClickListener {
                createExcelJsaFile()
            }
        }
    }

    private fun initObserver() {
        viewModel.getIbpr()

        collectLifecycleFlow(viewModel.jsa) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {}
                is UiStateWrapper.Success -> {
                    list = state.data
                    itemJsaAdapter.setData(list)
                    binding.apply {
                        btPdf.isEnabled = true
                        btExcel.isEnabled = true
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
        cell.setCellValue("Perusahaan")
        cell = row.createCell(2)
        cell.setCellValue("Pekerja")
        cell = row.createCell(3)
        cell.setCellValue("Pekerjaan")
        cell = row.createCell(4)
        cell.setCellValue("Tanggal")
        cell = row.createCell(5)
        cell.setCellValue("Supervisor")
        cell = row.createCell(6)
        cell.setCellValue("HSE")
        cell = row.createCell(7)
        cell.setCellValue("Tahap")
        cell = row.createCell(8)
        cell.setCellValue("Bahaya")
        cell = row.createCell(9)
        cell.setCellValue("Pengendalian")
        cell = row.createCell(10)
        cell.setCellValue("Tanggung Jawab")
        cell = row.createCell(11)
        cell.setCellValue("Anggota1")

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
        sheet.setColumnWidth(9, 30 * 200)
        sheet.setColumnWidth(10, 30 * 200)
        sheet.setColumnWidth(11, 30 * 200)

        for (i in list.indices) {

            val row1: Row = sheet.createRow(i + 1)
            cell = row1.createCell(0)
            cell.setCellValue("${i + 1}")
            cell = row1.createCell(1)
            cell.setCellValue(list[i].perusahaan)
            cell = row1.createCell(2)
            cell.setCellValue(list[i].pekerja)
            cell = row1.createCell(3)
            cell.setCellValue(list[i].pekerjaan)
            cell = row1.createCell(4)
            cell.setCellValue(list[i].tanggal)
            cell = row1.createCell(5)
            cell.setCellValue(list[i].supervisor)
            cell = row1.createCell(6)
            cell.setCellValue(list[i].hse)
            cell = row1.createCell(7)
            cell.setCellValue(list[i].tahap)
            cell = row1.createCell(8)
            cell.setCellValue(list[i].bahaya)
            cell = row1.createCell(9)
            cell.setCellValue(list[i].pengendalian)
            cell = row1.createCell(10)
            cell.setCellValue(list[i].tanggungJawab)
            cell = row1.createCell(11)
            cell.setCellValue(list[i].anggota1)

            sheet.setColumnWidth(0, 20 * 200)
            sheet.setColumnWidth(1, 30 * 200)
            sheet.setColumnWidth(2, 30 * 200)
            sheet.setColumnWidth(3, 20 * 200)
            sheet.setColumnWidth(4, 30 * 400)
            sheet.setColumnWidth(5, 30 * 400)
            sheet.setColumnWidth(6, 20 * 400)
            sheet.setColumnWidth(7, 30 * 200)
            sheet.setColumnWidth(8, 30 * 200)
            sheet.setColumnWidth(9, 30 * 200)
            sheet.setColumnWidth(10, 30 * 200)
            sheet.setColumnWidth(11, 30 * 200)
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
}