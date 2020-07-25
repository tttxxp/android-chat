package cn.wildfire.chat.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

import cn.wildfire.chat.app.third.location.viewholder.LocationMessageContentViewHolder;
import cn.wildfire.chat.kit.WfcUIKit;
import cn.wildfire.chat.kit.conversation.message.viewholder.MessageViewHolderManager;
import cn.wildfirechat.push.PushService;

public class MyApp extends BaseApp {

    public static final String APPLICATION_ID = "cn.wildfire.chat.app";

    public static String VIDEO_SAVE_DIR;
    public static String AUDIO_SAVE_DIR;
    public static String FILE_SAVE_DIR;
    public static String PHOTO_SAVE_DIR;

    @Override
    public void onCreate() {
        super.onCreate();
        Config.validateConfig();

        // bugly，务必替换为你自己的!!!
        if ("wildfirechat.cn".equals(Config.IM_SERVER_HOST)) {
            CrashReport.initCrashReport(getApplicationContext(), Config.BUGLY_ID, false);
        }
        // 只在主进程初始化
        if (getCurProcessName(this).equals(APPLICATION_ID)) {
            initChatApp(this);
        }
    }

    public static void initChatApp(Application app) {
        setContext(app);

        WfcUIKit wfcUIKit = WfcUIKit.getWfcUIKit();
        wfcUIKit.init(app);
        wfcUIKit.setAppServiceProvider(AppService.Instance());
        PushService.init(app, APPLICATION_ID);
        MessageViewHolderManager.getInstance().registerMessageViewHolder(LocationMessageContentViewHolder.class);
        setupWFCDirs(app);
    }

    private static void setupWFCDirs(Application app) {
        File cacheDir = app.getCacheDir();
        VIDEO_SAVE_DIR = new File(cacheDir, "wfc/video").getPath();
        AUDIO_SAVE_DIR = new File(cacheDir, "wfc/audio").getPath();
        PHOTO_SAVE_DIR = new File(cacheDir, "wfc/photo").getPath();
        FILE_SAVE_DIR = new File(cacheDir, "wfc/file").getPath();

        File file = new File(MyApp.VIDEO_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(MyApp.AUDIO_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(MyApp.FILE_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(MyApp.PHOTO_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
