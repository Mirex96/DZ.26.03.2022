package com.example.dz26032022

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.dz10032022.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val REQUEST_KEY_REMOTE = "REQUEST_KEY_REMOTE"
const val BUNDLE_KEY_REMOTE = "BUNDLE_KEY_REMOTE"
const val REQUEST_KEY_EDIT = "REQUEST_KEY_EDIT"
const val BUNDLE_KEY_EDIT = "BUNDLE_KEY_EDIT"
const val REQUEST_KEY_CLONE = "REQUEST_KEY_CLONE"
const val BUNDLE_KEY_CLONE = "BUNDLE_KEY_CLONE"
const val REQUEST_KEY_SAVE = "REQUEST_KEY_SAVE"
const val BUNDLE_KEY_SAVE = "BUNDLE_KEY_SAVE"
const val REQUEST_KEY_NUMBER_1 = "REQUEST_KEY_NUMBER_1"
const val BUNDLE_KEY_NUMBER_1 = "BUNDLE_KEY_NUMBER_1"
const val REQUEST_KEY_NUMBER_2 = "REQUEST_KEY_NUMBER_2"
const val BUNDLE_KEY_NUMBER_2 = "BUNDLE_KEY_NUMBER_2"
const val REQUEST_KEY_APPLY = "REQUEST_KEY_APPLY"
const val BUNDLE_KEY_APPLY = "BUNDLE_KEY_APPLY"


class BottomSheetDialog: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.remoteButtonSheet).setOnClickListener {
            val remote = "remote"
            setFragmentResult(REQUEST_KEY_REMOTE, bundleOf(BUNDLE_KEY_REMOTE to remote))
            dismiss()
        }

        view.findViewById<Button>(R.id.editButtonSheet).setOnClickListener {
            val edit = "edit"
            setFragmentResult(REQUEST_KEY_EDIT, bundleOf(BUNDLE_KEY_EDIT to edit ))
            dismiss()
        }

        view.findViewById<Button>(R.id.cloneButtonSheet).setOnClickListener {
            val clone = "clone"
            setFragmentResult(REQUEST_KEY_CLONE, bundleOf(BUNDLE_KEY_CLONE to clone))
            dismiss()
        }
        view.findViewById<Button>(R.id.saveButtonSheet).setOnClickListener {
            val save = "save"
            setFragmentResult(REQUEST_KEY_SAVE, bundleOf(BUNDLE_KEY_SAVE to save))
            dismiss()
        }

        view.findViewById<EditText>(R.id.editText1).setOnClickListener {
            val number1 = "number1"
            setFragmentResult(REQUEST_KEY_NUMBER_1, bundleOf(BUNDLE_KEY_NUMBER_1 to number1))

        }

        view.findViewById<EditText>(R.id.editText2).setOnClickListener {
            val number2 = "number2"
            setFragmentResult(REQUEST_KEY_NUMBER_2, bundleOf(BUNDLE_KEY_NUMBER_2 to number2))

        }

        view.findViewById<Button>(R.id.applyButton).setOnClickListener {
            val apply = "apply"
            setFragmentResult(REQUEST_KEY_APPLY, bundleOf(BUNDLE_KEY_APPLY to apply))
            dismiss()
        }

        view.findViewById<Button>(R.id.exitButtonSheet).setOnClickListener {
            dismiss()
        }


    }






    companion object {
        const val TAG = "DIALOG"

        fun show (fragmentManager: FragmentManager) {
            BottomSheetDialog()
                .show(fragmentManager, TAG)
        }
    }


}