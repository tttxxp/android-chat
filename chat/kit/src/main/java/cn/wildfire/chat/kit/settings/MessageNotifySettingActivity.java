package cn.wildfire.chat.kit.settings;

import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import cn.wildfire.chat.kit.WfcBaseActivity;
import cn.wildfirechat.chat.R;
import cn.wildfirechat.chat.R2;
import cn.wildfirechat.remote.ChatManager;
import cn.wildfirechat.remote.GeneralCallback;

public class MessageNotifySettingActivity extends WfcBaseActivity {
    @BindView(R2.id.switchMsgNotification)
    SwitchButton switchMsgNotification;
    @BindView(R2.id.switchShowMsgDetail)
    SwitchButton switchShowMsgDetail;

    @Override
    protected int contentLayout() {
        return R.layout.activity_msg_notify_settings;
    }


    @Override
    protected void afterViews() {
        super.afterViews();

        switchMsgNotification.setChecked(!ChatManager.Instance().isGlobalSilent());
        switchShowMsgDetail.setChecked(!ChatManager.Instance().isHiddenNotificationDetail());


        switchMsgNotification.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ChatManager.Instance().setGlobalSilent(!isChecked, new GeneralCallback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFail(int errorCode) {
                    Toast.makeText(MessageNotifySettingActivity.this, "网络错误", Toast.LENGTH_SHORT);
                }
            });
        });

        switchShowMsgDetail.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ChatManager.Instance().setHiddenNotificationDetail(!isChecked, new GeneralCallback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFail(int errorCode) {
                    Toast.makeText(MessageNotifySettingActivity.this, "网络错误", Toast.LENGTH_SHORT);
                }
            });
        });
    }
}
