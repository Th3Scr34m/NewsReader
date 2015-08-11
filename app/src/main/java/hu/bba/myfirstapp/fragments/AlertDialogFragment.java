package hu.bba.myfirstapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import hu.bba.myfirstapp.R;
import rx.functions.Action1;

public class AlertDialogFragment extends DialogFragment {
    public static final String TITLE_ID = "title";
    public static final String MESSAGE_ID = "message";
    public static final String MESSAGE_RES_ID = "messageRes";
    public static final String POSITIVE_RES_ID = "positiveRes";
    public static final String NEGATIVE_RES_ID = "negativeRes";
    private Action1<Boolean> mCallback;

    public static AlertDialogFragment newInstance(int titleRes, int messageRes) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(TITLE_ID, titleRes);
        arguments.putInt(MESSAGE_RES_ID, messageRes);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static AlertDialogFragment newInstance(int titleRes, int messageRes, int positiveRes, int negativeRes) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(TITLE_ID, titleRes);
        arguments.putInt(MESSAGE_RES_ID, messageRes);
        arguments.putInt(POSITIVE_RES_ID, positiveRes);
        arguments.putInt(NEGATIVE_RES_ID, negativeRes);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static AlertDialogFragment newInstance(int titleRes, String message) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(TITLE_ID, titleRes);
        arguments.putString(MESSAGE_ID, message);
        fragment.setArguments(arguments);
        return fragment;
    }

    public static AlertDialogFragment newInstance(int titleRes, String message, int positiveRes, int negativeRes) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(TITLE_ID, titleRes);
        arguments.putString(MESSAGE_ID, message);
        arguments.putInt(POSITIVE_RES_ID, positiveRes);
        arguments.putInt(NEGATIVE_RES_ID, negativeRes);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle messages = getArguments();
        if (messages == null) messages = new Bundle();

        int titleRes = messages.getInt(TITLE_ID, 0);
        int messageRes = messages.getInt(MESSAGE_RES_ID, 0);
        String message = messages.getString(MESSAGE_ID);
        int positiveRes = messages.getInt(POSITIVE_RES_ID, R.string.button_retry);
        int negativeRes = messages.getInt(NEGATIVE_RES_ID, R.string.button_close);

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        if (titleRes != 0) {
            dialog.setTitle(titleRes);
        }

        if (message != null) {
            dialog.setMessage(message);
        } else if (messageRes != 0) {
            dialog.setMessage(messageRes);
        }

        if (positiveRes != 0) {
            dialog.setPositiveButton(positiveRes, (dialog1, which) -> {
                if (mCallback != null) {
                    mCallback.call(true);
                }
            });
        }

        dialog.setNegativeButton(negativeRes, (dialog1, which) -> {
            if (mCallback != null) {
                mCallback.call(false);
            }
        });

        return dialog.create();
    }

    public void setCallback(Action1<Boolean> callback) {
        mCallback = callback;
    }
}