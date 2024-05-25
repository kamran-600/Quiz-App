package com.kamran.assignment.utils

import android.content.Context
import cn.pedant.SweetAlert.SweetAlertDialog

class Dialog(context: Context, title : String) {

    val successDialog : SweetAlertDialog by lazy {
        val successDialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
        successDialog.apply {
            titleText = title
            setCancelable(false)
            setConfirmText("OK")
        }
    }


    val warningDialog : SweetAlertDialog by lazy {
        val warningDialog = SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
        warningDialog.apply {
            titleText = title
            setCancelable(false)
            setConfirmText("OK")
        }
    }
}