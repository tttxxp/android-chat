package cn.wildfire.chat.app;

public interface WfcIntent {
    String ACTION_MAIN = MyApp.APPLICATION_ID + ".main";
    String ACTION_CONVERSATION = MyApp.APPLICATION_ID + ".conversation";
    String ACTION_CONTACT = MyApp.APPLICATION_ID + ".contact";
    String ACTION_USER_INFO = MyApp.APPLICATION_ID + ".user.info";
    String ACTION_GROUP_INFO = MyApp.APPLICATION_ID + ".group.info";
    String ACTION_VOIP_SINGLE = MyApp.APPLICATION_ID + ".kit.voip.single";
    String ACTION_VOIP_MULTI = MyApp.APPLICATION_ID + ".kit.voip.multi";
    String ACTION_VIEW = MyApp.APPLICATION_ID + ".webview";

    String ACTION_MOMENT = MyApp.APPLICATION_ID + ".moment";
}
