package com.makhalibagas.myapplication.presentation.page.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.myapplication.data.source.remote.response.GreenItem
import com.makhalibagas.myapplication.databinding.ActivityShowGreenBinding
import com.makhalibagas.myapplication.presentation.page.adapter.ItemGreenAdapter
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.PDFConverter
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
class ShowGreenActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityShowGreenBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    private lateinit var itemGreenAdapter: ItemGreenAdapter
    private lateinit var list: List<GreenItem>

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
            itemGreenAdapter = ItemGreenAdapter()
            included.rvGreen.adapter = itemGreenAdapter
        }
    }

    private fun initListener() {
        binding.apply {
            btPdf.setOnClickListener {
                val pdfConverter = PDFConverter()
                pdfConverter.createPdf(this@ShowGreenActivity, list, this@ShowGreenActivity)
            }

            btExcel.setOnClickListener {
                createExcelFile()
            }
        }
    }

    private fun initObserver() {
        viewModel.getGreen()
        collectLifecycleFlow(viewModel.green) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {}
                is UiStateWrapper.Success -> {
                    list = state.data
                    itemGreenAdapter.setData(list)
                    binding.apply {
                        btPdf.isEnabled = true
                        btExcel.isEnabled = true
                    }
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

        for (i in list.indices) {

            val row1: Row = sheet.createRow(i + 1)
            cell = row1.createCell(0)
            cell.setCellValue("${i + 1}")
            cell = row1.createCell(1)
            cell.setCellValue(list[i].date)
            cell = row1.createCell(2)
            cell.setCellValue(list[i].date)
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

            sheet.setColumnWidth(0, 20 * 200)
            sheet.setColumnWidth(1, 30 * 200)
            sheet.setColumnWidth(2, 30 * 200)
            sheet.setColumnWidth(3, 20 * 200)
            sheet.setColumnWidth(4, 30 * 400)
            sheet.setColumnWidth(5, 30 * 400)
            sheet.setColumnWidth(6, 20 * 400)
            sheet.setColumnWidth(7, 30 * 200)
            sheet.setColumnWidth(8, 30 * 200)
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
}