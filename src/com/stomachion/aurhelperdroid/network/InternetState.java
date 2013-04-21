package com.stomachion.aurhelperdroid.network;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * User: Pedro Veloso
 */
public final class InternetState {

    /**
     * @param ctx Context of the running activity/service
     * @return True if device is connected to the Internet
     */
    public static boolean isConnectedToInternet(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        WifiManager wm = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();
        return !((wi == null || WifiInfo.getDetailedStateOf(wi.getSupplicantState()) == NetworkInfo.DetailedState.IDLE) &&
                tm.getDataState() != TelephonyManager.DATA_CONNECTED);
    }
}
