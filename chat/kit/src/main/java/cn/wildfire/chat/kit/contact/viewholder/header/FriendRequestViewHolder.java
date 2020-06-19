package cn.wildfire.chat.kit.contact.viewholder.header;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.wildfire.chat.kit.annotation.LayoutRes;
import cn.wildfire.chat.kit.contact.UserListAdapter;
import cn.wildfire.chat.kit.contact.model.FriendRequestValue;
import cn.wildfirechat.chat.R;
import cn.wildfirechat.chat.R2;
import cn.wildfirechat.remote.ChatManager;

@SuppressWarnings("unused")
@LayoutRes(resId = R2.layout.contact_header_friend)
public class FriendRequestViewHolder extends HeaderViewHolder<FriendRequestValue> {

    public static final int layoutRes = R.layout.contact_header_friend;

    @BindView(R2.id.unreadFriendRequestCountTextView)
    TextView unreadRequestCountTextView;
    private FriendRequestValue value;


    public FriendRequestViewHolder(Fragment fragment, UserListAdapter adapter, View itemView) {
        super(fragment, adapter, itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBind(FriendRequestValue value) {
        this.value = value;
        int count = ChatManager.Instance().getUnreadFriendRequestStatus();
        if (count > 0) {
            unreadRequestCountTextView.setVisibility(View.VISIBLE);
            unreadRequestCountTextView.setText("" + count);
        } else {
            unreadRequestCountTextView.setVisibility(View.GONE);
        }
    }
}
