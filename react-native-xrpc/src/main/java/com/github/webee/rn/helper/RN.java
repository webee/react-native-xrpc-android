package com.github.webee.rn.helper;

import android.app.Application;
import android.os.Bundle;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.github.webee.rn.xrpc.RNXRPCClient;

import java.util.List;

/**
 * Created by webee on 16/10/24.
 */

public class RN {
    private static RNX rnx;

    public static void setup(Application application, boolean isDev, List<ReactPackage> extraPackages) {
        rnx = new RNX(application, "", isDev, extraPackages, null);
    }
    public static void setup(Application application, boolean isDev, List<ReactPackage> extraPackages, String jsBundlePath) {
        rnx = new RNX(application, "", isDev, extraPackages, jsBundlePath);
    }

    public static void start() {
        rnx.start();
    }

    public static RNX rnx() {
        return rnx;
    }

    public static ReactInstanceManager inst() {
        return rnx.inst();
    }

    /**
     * get default xrpc.
     * @return
     */
    public static RNXRPCClient xrpc() {
        return rnx.xrpc();
    }

    /**
     * get a xrpc with default context.
     * @param context
     * @return
     */
    public static RNXRPCClient newXrpc(Bundle context) {
        return rnx.newXrpc(context);
    }

    public static RNXRPCClient newXrpc() {
        return rnx.newXrpc();
    }
}
