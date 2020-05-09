package com.example.myhw_v1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDialDialogInteractionListener} interface
 * to handle interaction events.
 */
public class DialDialog extends DialogFragment {

    private OnDialDialogInteractionListener mListener;

    public DialDialog() {
        // Required empty public constructor
    }
    static DialDialog newInstance(String name)
    {
        DialDialog dialog = new DialDialog();
        Bundle arg = new Bundle();
        arg.putString("NAME",name);
        dialog.setArguments(arg);
        return dialog;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // AlertDialog.Builder will be used to create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the message displayed in the dialog
        String name = getArguments().getString("NAME");
        builder.setMessage(getString(R.string.call_progres)+" "+name);
        // Set the text and action for the positive button click
        builder.setPositiveButton(getString(R.string.dial_confirm), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Notify the OnDeleteDialogInteractionListener interface of positive button click
                mListener.onDialDialogPositiveClick(DialDialog.this);
            }
        });
        // Set the text and action for the negative button click
        builder.setNegativeButton(getString(R.string.dial_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Notify the OnDeleteDialogInteractionListener interface of negative button click
                mListener.onDialDialogNegativeClick(DialDialog.this);
            }
        });
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDialDialogInteractionListener) {
            mListener = (OnDialDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeleteDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDialDialogInteractionListener {
        void onDialDialogPositiveClick(DialogFragment dialog);
        void onDialDialogNegativeClick(DialogFragment dialog);
    }
}
