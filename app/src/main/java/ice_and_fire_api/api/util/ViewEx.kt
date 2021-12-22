package ice_and_fire_api.api.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(msgId: Int, length: Int) {
    showSnackbar(context.getString(msgId), length)
}

fun View.showSnackbar(msg: String, length: Int) {
    showSnackbar(msg, length, null, {})
}

fun View.showSnackbar(msgId: Int, length: Int, actionMessegeId: Int, action: (View) -> Unit) {
    showSnackbar(context.getString(msgId), length, context.getString(actionMessegeId), action)
}

fun View.showSnackbar(
    msg: String,
    length: Int,
    actioMessege: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actioMessege != null) {
        snackbar.setAction(actioMessege) {
            action(this)
        }.show()
    }
}