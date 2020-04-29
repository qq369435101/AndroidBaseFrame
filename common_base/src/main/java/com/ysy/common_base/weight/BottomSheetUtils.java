package com.ysy.common_base.weight;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.ysy.common_base.R;
import com.ysy.common_base.utils.ToastUtil;

import java.lang.ref.WeakReference;

/**
 * Created by YuShengyang on 2020/1/19
 * Email       ：18210490506@163.com
 * Description ：
 */
public class BottomSheetUtils {
    /**
     * 分享到微信
     */
    private static final int TAG_SHARE_WECHAT_FRIEND = 0;
    /**
     * 分享到朋友圈
     */
    private static final int TAG_SHARE_WECHAT_MOMENT = 1;
    /**
     * 分享到微博
     */
    private static final int TAG_SHARE_WEIBO = 2;
    /**
     * 分享到私信
     */
    private static final int TAG_SHARE_CHAT = 3;
    /**
     * 保存到本地
     */
    private static final int TAG_SHARE_LOCAL = 4;
    /**
     * 复制链接
     */
    private static final int TAG_COPY_URL = 5;

    public static void showShareDialog(Context context) {
        WeakReference<Context> contextWeakReference = new WeakReference<Context>(context);
        Context mContext = contextWeakReference.get();
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(mContext);
        builder.addItem(R.mipmap.icon_more_operation_share_friend, "分享到微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_moment, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_weibo, "分享到微博", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_chat, "分享到私信", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_save, "保存到本地", TAG_SHARE_LOCAL, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener((dialog, itemView) -> {
                    dialog.dismiss();
                    int tag = (int) itemView.getTag();
                    switch (tag) {
                        case TAG_SHARE_WECHAT_FRIEND:
                            ToastUtil.showToast(mContext, "分享到微信");
                            break;
                        case TAG_SHARE_WECHAT_MOMENT:
                            ToastUtil.showToast(mContext, "分享到朋友圈");
                            break;
                        case TAG_SHARE_WEIBO:
                            ToastUtil.showToast(mContext, "分享到微博");
                            break;
                        case TAG_SHARE_CHAT:
                            ToastUtil.showToast(mContext, "分享到私信");
                            break;
                        case TAG_SHARE_LOCAL:
                            ToastUtil.showToast(mContext, "保存到本地");
                            break;
                    }
                }).build().show();
    }

    public static void showWebDialog(Context context) {
        WeakReference<Context> contextWeakReference = new WeakReference<>(context);
        Context mContext = contextWeakReference.get();
        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(mContext);
        builder.addItem(R.mipmap.icon_more_operation_share_friend, "分享到微信", TAG_SHARE_WECHAT_FRIEND, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_moment, "分享到朋友圈", TAG_SHARE_WECHAT_MOMENT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_weibo, "分享到微博", TAG_SHARE_WEIBO, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_chat, "分享到私信", TAG_SHARE_CHAT, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_save, "复制链接", TAG_COPY_URL, QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener((dialog, itemView) -> {
                    dialog.dismiss();
                    int tag = (int) itemView.getTag();
                    switch (tag) {
                        case TAG_SHARE_WECHAT_FRIEND:
                            ToastUtil.showToast(mContext, "分享到微信");
                            break;
                        case TAG_SHARE_WECHAT_MOMENT:
                            ToastUtil.showToast(mContext, "分享到朋友圈");
                            break;
                        case TAG_SHARE_WEIBO:
                            ToastUtil.showToast(mContext, "分享到微博");
                            break;
                        case TAG_SHARE_CHAT:
                            ToastUtil.showToast(mContext, "分享到私信");
                            break;
                        case TAG_COPY_URL:
                            ToastUtil.showToast(mContext, "复制链接");
                            break;
                    }
                }).build().show();
    }
}
