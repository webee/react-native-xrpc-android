package com.github.webee.rn.helper;

/**
 * Created by webee on 17/3/13.
 */

public final class RNLogs {
    public static void setLog(boolean enable) {
        RN.xrpc().emit("xrpc.log.set", new Object[]{enable}, null);
    }
}
