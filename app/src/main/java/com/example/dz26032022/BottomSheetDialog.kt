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


const val REQUEST_KEY_BS = "REQUEST_KEY_BS"
const val BUNDLE_KEY_ACTION_TYPE = "BottomSheetDialogAction"
const val BUNDLE_KEY_POS1 = "BUNDLE_KEY_POS1"
const val BUNDLE_KEY_POS2 = "BUNDLE_KEY_POS2"


enum class BottomSheetDialogAction {
    CLONE,
    SAVE,
    EDIT,
    REMOVE,
    POSITION_SWAP
}


class BottomSheetDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    override fun getTheme() = R.style.AppBottomSheetDialogTheme


    private fun onClickReturnResult(action: BottomSheetDialogAction) {
        setFragmentResult(REQUEST_KEY_BS, bundleOf(BUNDLE_KEY_ACTION_TYPE to action))
        dismiss()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText1 = view.findViewById<EditText>(R.id.editText1)
        val editText2 = view.findViewById<EditText>(R.id.editText2)

        view.findViewById<Button>(R.id.remoteButtonSheet).setOnClickListener {
            onClickReturnResult(BottomSheetDialogAction.REMOVE)
            dismiss()
        }

        view.findViewById<Button>(R.id.editButtonSheet).setOnClickListener {
            onClickReturnResult(BottomSheetDialogAction.EDIT)
            dismiss()
        }

        view.findViewById<Button>(R.id.cloneButtonSheet).setOnClickListener {
            onClickReturnResult(BottomSheetDialogAction.CLONE)
            dismiss()
        }
        view.findViewById<Button>(R.id.saveButtonSheet).setOnClickListener {
            onClickReturnResult(BottomSheetDialogAction.SAVE)
            dismiss()
        }


        view.findViewById<Button>(R.id.applyButton).setOnClickListener {
            setFragmentResult(
                REQUEST_KEY_BS, bundleOf(
                    BUNDLE_KEY_ACTION_TYPE to BottomSheetDialogAction.POSITION_SWAP,
                    BUNDLE_KEY_POS1 to editText1.text.toString(),
                    BUNDLE_KEY_POS2 to editText2.text.toString()
                )
            )
            dismiss()
        }


        view.findViewById<Button>(R.id.exitButtonSheet).setOnClickListener {
            dismiss()
        }


    }


    companion object {
        const val TAG = "DIALOG"

        fun show(fragmentManager: FragmentManager) {
            if (fragmentManager.findFragmentByTag(TAG) == null)
                BottomSheetDialog()
                    .show(fragmentManager, TAG)
        }
    }


}