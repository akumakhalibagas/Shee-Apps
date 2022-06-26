package com.she.apps.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

class PdfUtils {

    private fun convertBitmapToPdf(bitmap: Bitmap, context: Context, fileName: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitmap, 0F, 0F, null)
        pdfDocument.finishPage(page)
        val filePath =
            File(context.externalMediaDirs[0], fileName)
        Toast.makeText(context, "created at $filePath", Toast.LENGTH_SHORT).show()
        pdfDocument.writeTo(FileOutputStream(filePath))
        pdfDocument.close()
    }

    private fun createBitmap(v: View, height: Int, width: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        v.draw(canvas)
        return bitmap
    }

    fun createPdf(view: HorizontalScrollView, activity: Activity, fileName: String) {
        convertBitmapToPdf(
            createBitmap(view, view.getChildAt(0).height, view.getChildAt(0).width),
            activity, fileName
        )
    }
}