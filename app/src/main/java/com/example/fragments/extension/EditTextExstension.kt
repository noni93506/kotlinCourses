package com.example.fragments.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData


fun EditText.bindToLiveData(
    lifecycleOwner: LifecycleOwner,
    data: MutableLiveData<String>,
    maxLength: Int? = null,
    onTextChanged: ((String) -> Unit)? = null
) {

    this.textChanges {
        val currentText = text.toString().trimStart()

        if (maxLength != null) {
            if (currentText.length <= maxLength) {
                data.value = currentText
                onTextChanged?.invoke(currentText)
            } else {
                val substring = currentText.substring(0, maxLength)
                this.setText(substring)
                this.setSelection(substring.length)
            }
        } else {
            data.value = currentText
            onTextChanged?.invoke(currentText)
        }
    }

    data.observe(lifecycleOwner) {
        if (text.toString() != it) {
            setText(it.orEmpty())
        }
    }
}
fun EditText.textChanges(listener: (text: CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
            listener.invoke(p0)
        }
    })
}

