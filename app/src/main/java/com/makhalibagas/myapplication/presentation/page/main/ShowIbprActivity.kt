package com.makhalibagas.myapplication.presentation.page.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.makhalibagas.myapplication.R
import com.makhalibagas.myapplication.data.source.remote.response.IbprItem
import com.makhalibagas.myapplication.databinding.ActivityShowIbprBinding
import com.makhalibagas.myapplication.presentation.page.adapter.ItemIbprAdapter
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.Datas
import com.makhalibagas.myapplication.utils.PdfConverterIbpr
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
class ShowIbprActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityShowIbprBinding::inflate)
    private val viewModel: MainViewModel by viewModels()
    private lateinit var itemIbprAdapter: ItemIbprAdapter
    private lateinit var listIbpr: List<IbprItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        binding.apply {
            itemIbprAdapter = ItemIbprAdapter()
            included.rvIbpr.adapter = itemIbprAdapter
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.apply {
            btPdf.setOnClickListener {
                val pdfConverter = PdfConverterIbpr()
                pdfConverter.createPdf(this@ShowIbprActivity, listIbpr, this@ShowIbprActivity)
            }

            btExcel.setOnClickListener {
                createExcelIbprFile()
            }

            etFilter.apply {
                setAdapter(
                    ArrayAdapter(
                        this@ShowIbprActivity,
                        R.layout.item_dropdown,
                        Datas.filter
                    )
                )
                setOnTouchListener { _, _ ->
                    showDropDown()
                    return@setOnTouchListener false
                }

                doOnTextChanged { text, _, _, _ ->
                    viewModel.queryIbprDebounced(text.toString())
                }
            }

            itemIbprAdapter.onItemClick = { data ->
                val intent = Intent(this@ShowIbprActivity, IbprActivity::class.java)
                intent.putExtra("ibpr", data)
                startActivity(intent)
            }
        }
    }

    private fun initObserver() {
        viewModel.getIbpr()
        viewModel.queryIbprDebounced("All")

        collectLifecycleFlow(viewModel.ibpr) { state ->
            when (state) {
                is UiStateWrapper.Loading -> {}
                is UiStateWrapper.Success -> {
                    binding.apply {
                        btPdf.isEnabled = true
                        btExcel.isEnabled = true
                        etFilter.isEnabled = true
                    }
                }
                is UiStateWrapper.Error -> {}
            }
        }

        collectLifecycleFlow(viewModel.listIbpr){
            listIbpr = it
            itemIbprAdapter.setData(listIbpr)
        }
    }

    private fun createExcelIbprFile() {
        val wb: Workbook = HSSFWorkbook()
        var cell: Cell?
        val sheet: Sheet? = wb.createSheet("Demo Excel Sheet Ibpr")

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
        cell.setCellValue("RESIKO")
        cell = row.createCell(5)
        cell.setCellValue("KODE BAHAYA")
        cell = row.createCell(6)
        cell.setCellValue("STATUS")
        cell = row.createCell(7)
        cell.setCellValue("TEMUAN")
        cell = row.createCell(8)
        cell.setCellValue("PENGENDALIAN RESIKO")
        cell = row.createCell(9)
        cell.setCellValue("SHIFT")
        cell = row.createCell(10)
        cell.setCellValue("SITE")
        cell = row.createCell(11)
        cell.setCellValue("DEPARTEMENT")
        cell = row.createCell(12)
        cell.setCellValue("BAHAYA")
        cell = row.createCell(13)
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
        sheet.setColumnWidth(13, 30 * 200)

        for (i in listIbpr.indices) {

            val row1: Row = sheet.createRow(i + 1)
            cell = row1.createCell(0)
            cell.setCellValue("${i + 1}")
            cell = row1.createCell(1)
            cell.setCellValue(listIbpr[i].date)
            cell = row1.createCell(2)
            cell.setCellValue(listIbpr[i].date)
            cell = row1.createCell(3)
            cell.setCellValue(listIbpr[i].lokasi)
            cell = row1.createCell(4)
            cell.setCellValue(listIbpr[i].resiko)
            cell = row1.createCell(5)
            cell.setCellValue(listIbpr[i].kodeBahaya)
            cell = row1.createCell(6)
            cell.setCellValue(listIbpr[i].status)
            cell = row1.createCell(7)
            cell.setCellValue(listIbpr[i].temuan)
            cell = row1.createCell(8)
            cell.setCellValue(listIbpr[i].pengendalianResiko)
            cell = row1.createCell(9)
            cell.setCellValue(listIbpr[i].shift)
            cell = row1.createCell(10)
            cell.setCellValue(listIbpr[i].site)
            cell = row1.createCell(11)
            cell.setCellValue(listIbpr[i].department)
            cell = row1.createCell(12)
            cell.setCellValue(listIbpr[i].bahaya)
            cell = row1.createCell(13)
            cell.setCellValue(listIbpr[i].pelapor)

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
            sheet.setColumnWidth(13, 30 * 200)
        }

        val filePath = File(externalMediaDirs[0], "SheeDemoIbpr${System.currentTimeMillis()}.xls")
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