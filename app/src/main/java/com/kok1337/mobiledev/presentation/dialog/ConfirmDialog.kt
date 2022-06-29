package com.kok1337.mobiledev.presentation.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.DialogConfirmBinding

class ConfirmDialog(
    private val title: String,
    private val onConfirmed: () -> Unit,
) : DialogFragment(R.layout.dialog_confirm) {

    private val binding by viewBinding(DialogConfirmBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.text = title
        binding.acceptButton.setOnClickListener { confirm() }
        binding.cancelButton.setOnClickListener { closeDialog() }
    }

    private fun confirm() {
        onConfirmed()
        closeDialog()
    }

    private fun closeDialog() { dismiss() }

}