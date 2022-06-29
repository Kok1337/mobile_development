package com.kok1337.mobiledev.presentation.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.DialogPreviewBinding

class PreviewDialog(
    private val title: String,
    private val value: Any?,
) : DialogFragment(R.layout.dialog_preview) {

    private val binding by viewBinding(DialogPreviewBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.text = title
        binding.editText.setText(value?.toString() ?: "")
        binding.editText.isEnabled = false
        binding.cancelButton.setOnClickListener { closeDialog() }
    }

    private fun closeDialog() { dismiss() }

}