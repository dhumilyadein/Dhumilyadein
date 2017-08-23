package com.example.user.helper;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.user.dhumilyadein.R;

/**
 * Created by user on 12-12-2015.
 */
public class CommonHelper {

    public static boolean isNotNull(String str) {

        boolean result = false;
        if (null != str) {
            result = true;
        }
        return result;
    }

    public static boolean isNotNullAndNotEmpty(String str) {

        boolean result = false;
        if (null != str && str.length() != 0) {
            result = true;
        }
        return result;
    }

    /**
     * Shows popup
     *
     * @param context            activity context on which popup is to be shown
     * @param popUpViewId        Id of the pop up view
     * @param mainActivityViewId Id of the main activity view on which pop up will be shown
     */
    private void showSingleButtonPopup(final Activity context, int popUpViewId, int mainActivityViewId, int btnId) {

        // Activity on which pop up to be shown
        final RelativeLayout mainActivityLayout = (RelativeLayout) context.findViewById(mainActivityViewId);

        // Inflate the popup_layout.xml
        RelativeLayout viewGroup = (RelativeLayout) context.findViewById(popUpViewId);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.content_invalid_login, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setOutsideTouchable(false);

        popup.showAtLocation(layout, Gravity.CENTER, 0, 0);

        // Getting a reference to Close button, and close the popup when clicked.
        Button popupBtn = (Button) layout.findViewById(btnId);
        popupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
                mainActivityLayout.setFocusable(false);
            }
        });
    }

}
