package cn.wildfire.chat.kit.utils;

import android.util.Log;
import android.util.SparseIntArray;

import java.lang.reflect.Field;

import cn.wildfirechat.chat.R;
import cn.wildfirechat.chat.R2;

/**
 * @author tengxp
 * @date 2020/6/13 Sat
 */
public class R2Utils {
    private static final String TAG = "R2Utils";
    public static final SparseIntArray LAYOUT_ARRAY = new SparseIntArray();

    public static void init() {
        Field[] rFields = R.layout.class.getDeclaredFields();
        Field[] r2Fields = R2.layout.class.getDeclaredFields();
        int rLen = rFields.length;
        int r2Len = r2Fields.length;
        Log.d(TAG, "init() : R's length = [" + rLen + "], R2's length = [" + r2Len + "]");
        try {
            for (Field r2Field : r2Fields) {
                String r2Name = r2Field.getName();
                boolean match = false;
                for (Field rField : rFields) {
                    if (r2Name.equals(rField.getName())) {
                        int key = r2Field.getInt(null);
                        int value = rField.getInt(null);

//                        Log.i(TAG, "init() key = [" + Integer.toHexString(key)
//                                + "], value = [" + Integer.toHexString(value) + "]");
                        LAYOUT_ARRAY.put(key, value);
                        match = true;
                        break;
                    }
                }

                if (!match) {
                    Log.w(TAG, "init() : Match failed attr is " + r2Name);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
