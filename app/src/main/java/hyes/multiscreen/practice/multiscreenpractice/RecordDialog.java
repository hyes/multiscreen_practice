package hyes.multiscreen.practice.multiscreenpractice;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hyes on 2015. 9. 10..
 */
public class RecordDialog extends DialogFragment {

    public RecordDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Recording now...", " recording will automatically end after 30 seconds", true);

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);


        long delayInMillis = 5000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, delayInMillis);

        DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    dialog.dismiss();

                }
                return true;
            }
        };

        dialog.setOnKeyListener(keyListener);

        return dialog;

    }


}
