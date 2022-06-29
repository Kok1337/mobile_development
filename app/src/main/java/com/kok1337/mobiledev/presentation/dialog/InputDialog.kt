package com.kok1337.mobiledev.presentation.dialog

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.DialogInputBinding
import com.kok1337.mobiledev.presentation.util.AppTextWatcher

private enum class Input(val inputType: Int) {
    TEXT(InputType.TYPE_CLASS_TEXT),
    INT(InputType.TYPE_CLASS_NUMBER),
    DOUBLE(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL),
}

sealed class InputDialog<T>(
    private val title: String,
    private val startValue: T? = null,
    private val input: Input = Input.TEXT,
    private val valueChecker: ((T) -> Boolean)?,
    private val onValueIntroduced: (T) -> Unit
) : DialogFragment(R.layout.dialog_input) {

    private val binding by viewBinding(DialogInputBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.text = title
        binding.editText.isEnabled = false
        binding.editText.inputType = input.inputType
        binding.editText.setText(startValue?.toString() ?: "")
        binding.editText.addTextChangedListener(AppTextWatcher { it?.let { onTextChanged(it) } })
        binding.acceptButton.setOnClickListener { confirm() }
        binding.cancelButton.setOnClickListener { closeDialog() }
    }

    abstract fun castInput(inputText: String): T?

    private fun onTextChanged(inputText: String) {
        val value: T? = castInput(inputText)
        if (value == null) {
            binding.acceptButton.isEnabled = false
            return
        }
        if (valueChecker == null) {
            binding.acceptButton.isEnabled = true
            return
        }
        binding.acceptButton.isEnabled = valueChecker.invoke(value)
    }

    private fun confirm() {
        val inputText = binding.editText.text.toString()
        onValueIntroduced(castInput(inputText)!!)
        closeDialog()
    }

    private fun closeDialog() {
        dismiss()
    }

}

class InputStringDialog(
    title: String,
    startValue: String? = null,
    valueChecker: ((String) -> Boolean)?,
    onValueIntroduced: (String) -> Unit
) : InputDialog<String>(
    title, startValue, Input.TEXT, valueChecker, onValueIntroduced
) {
    override fun castInput(inputText: String): String {
        return inputText
    }
}

class InputIntDialog(
    title: String,
    startValue: Int? = null,
    valueChecker: ((Int) -> Boolean)?,
    onValueIntroduced: (Int) -> Unit
) : InputDialog<Int>(
    title, startValue, Input.INT, valueChecker, onValueIntroduced
) {
    override fun castInput(inputText: String): Int? {
        return inputText.toIntOrNull()
    }
}

class InputDoubleDialog(
    title: String,
    startValue: Double? = null,
    valueChecker: ((Double) -> Boolean)?,
    onValueIntroduced: (Double) -> Unit
) : InputDialog<Double>(
    title, startValue, Input.DOUBLE, valueChecker, onValueIntroduced
) {
    override fun castInput(inputText: String): Double? {
        return inputText.toDoubleOrNull()
    }
}