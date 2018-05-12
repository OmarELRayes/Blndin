package com.example.android.blndin.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.android.blndin.R;
import com.karumi.dexter.DexterBuilder;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

/**
 * Created by Luffy on 5/12/2018.
 */

public class SampleMultiplePermissionListener implements MultiplePermissionsListener  {
    private final Context activity;

    public SampleMultiplePermissionListener(Context activity) {
        this.activity = activity;
    }

    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
        for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
           // Toast.makeText(activity,"Granted",Toast.LENGTH_SHORT).show();
        }

        for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
            Toast.makeText(activity,"Dienied",Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                             final PermissionToken token) {
        new AlertDialog.Builder(activity).setTitle("We need this permission")
                .setMessage("This permission is needed for doing some fancy stuff so please, allow it!")
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .show();
    }


}
