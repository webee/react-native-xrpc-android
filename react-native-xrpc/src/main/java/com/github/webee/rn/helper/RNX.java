package com.github.webee.rn.helper;

import android.app.Application;
import android.os.Bundle;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import com.github.webee.rn.xrpc.RNXRPCClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by webee on 16/10/24.
 */

/**
 * 包含ReactInstanceManager和xrpc, 及新建关联xrpc的方法
 */
public class RNX {
    private static Map<String, RNX> rnxes = new ConcurrentHashMap<>();
    private String id;
    private ReactInstanceManager instanceManager;
    private RNXRPCClient xrpc;

    public RNX(Application application, String name, boolean isDev, List<ReactPackage> extraPackages) {
        ReactInstanceManager.Builder builder = ReactInstanceManager.builder()
                .setApplication(application)
                .setUseDeveloperSupport(isDev)
                .setBundleAssetName("index.android" + name + ".jsbundle")
                .setJSMainModuleName("index.android" + name)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .addPackage(new MainReactPackage());
                //.setUseOldBridge(true) // uncomment this line if your app crashes
        for (ReactPackage pk : extraPackages) {
            builder = builder.addPackage(pk);
        }


        instanceManager = builder.build();
        xrpc = new RNXRPCClient(instanceManager);
        id = UUID.randomUUID().toString();
        rnxes.put(id, this);
    }

    public void start() {
        if (!instanceManager.hasStartedCreatingInitialContext()) {
            instanceManager.createReactContextInBackground();
        }
    }

    public ReactInstanceManager inst() {
        return instanceManager;
    }

    /**
     * get default xrpc.
     * @return
     */
    public RNXRPCClient xrpc() {
        return xrpc;
    }

    /**
     * get a xrpc with default context.
     * @param context
     * @return
     */
    public RNXRPCClient newXrpc(Bundle context) {
        return new RNXRPCClient(instanceManager, context);
    }

    public RNXRPCClient newXrpc() {
        return new RNXRPCClient(instanceManager);
    }

    public String id() {
        return id;
    }

    /**
     * get rnx by id.
     * @param id rnx id
     * @return rnx
     */
    public static RNX get(String id) {
        return rnxes.get(id);
    }
}
