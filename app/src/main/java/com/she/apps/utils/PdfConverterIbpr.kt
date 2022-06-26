//package com.makhalibagas.myapplication.utils
//
//import android.app.Activity
//import android.content.ActivityNotFoundException
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.Canvas
//import android.graphics.pdf.PdfDocument
//import android.os.Build
//import android.util.DisplayMetrics
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Toast
//import androidx.core.content.FileProvider
//import com.makhalibagas.myapplication.R
//import com.makhalibagas.myapplication.data.source.remote.response.IbprItem
//import com.makhalibagas.myapplication.databinding.LayoutPdfIbprBinding
//import com.makhalibagas.myapplication.presentation.page.adapter.ItemIbprAdapter
//import java.io.File
//import java.io.FileOutputStream
//
//class PdfConverterIbpr {
//
//    private fun createBitmapFromView(
//        context: Context,
//        view: View,
//        adapter: ItemIbprAdapter,
//        activity: Activity
//    ): Bitmap {
//        val binding = LayoutPdfIbprBinding.bind(view)
//        binding.rvIbpr.adapter = adapter
//        return createBitmap(context, view, activity)
//    }
//
//    private fun createBitmap(
//        context: Context,
//        view: View,
//        activity: Activity,
//    ): Bitmap {
//        val displayMetrics = DisplayMetrics()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            context.display?.getRealMetrics(displayMetrics)
//            displayMetrics.densityDpi
//        } else {
//            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
//        }
//        view.measure(
//            View.MeasureSpec.makeMeasureSpec(
//                displayMetrics.widthPixels, View.MeasureSpec.EXACTLY
//            ),
//            View.MeasureSpec.makeMeasureSpec(
//                displayMetrics.heightPixels, View.MeasureSpec.EXACTLY
//            )
//        )
//        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
//        val bitmap = Bitmap.createBitmap(
//            view.measuredWidth,
//            view.measuredHeight, Bitmap.Config.ARGB_8888
//        )
//
//        val canvas = Canvas(bitmap)
//        view.draw(canvas)
//        return Bitmap.createScaledBitmap(bitmap, view.width, view.height, true)
//    }
//
//    private fun convertBitmapToPdf(bitmap: Bitmap, context: Context) {
//        val pdfDocument = PdfDocument()
//        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
//        val page = pdfDocument.startPage(pageInfo)
//        page.canvas.drawBitmap(bitmap, 0F, 0F, null)
//        pdfDocument.finishPage(page)
//        val filePath =
//            File(context.externalMediaDirs[0], "SheeDemoIbpr${System.currentTimeMillis()}.pdf")
//        Toast.makeText(context, "created at $filePath", Toast.LENGTH_SHORT).show()
//        pdfDocument.writeTo(FileOutputStream(filePath))
//        pdfDocument.close()
//    }
//
//    fun createPdf(
//        context: Context,
//        list: List<IbprItem>,
//        activity: Activity
//    ) {
//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.layout_pdf_ibpr, null)
//
//        val adapter = ItemIbprAdapter(true)
//        adapter.setData(list)
//        val bitmap = createBitmapFromView(context, view, adapter, activity)
//        convertBitmapToPdf(bitmap, activity)
//    }
//
//
//    private fun renderPdf(context: Context, filePath: File) {
//        val uri = FileProvider.getUriForFile(
//            context,
//            context.applicationContext.packageName + ".provider",
//            filePath
//        )
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        intent.setDataAndType(uri, "application/pdf")
//
//        try {
//            context.startActivity(intent)
//        } catch (e: ActivityNotFoundException) {
//
//        }
//    }
//}