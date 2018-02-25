package com.example.administrator.demo.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.demo.R;
import com.example.administrator.demo.base.BaseAdapter;
import com.example.administrator.demo.bean.Comment;
import com.example.administrator.demo.bean.Reply;
import com.example.administrator.demo.databinding.ItemCommentBinding;
import com.example.administrator.demo.listener.ClickSpannable;
import com.example.administrator.demo.model.TopicModelImp;
import com.example.administrator.demo.model.UserModelImp;
import com.example.administrator.demo.util.CommentUtil;
import com.example.administrator.demo.util.LogUtil;
import com.example.administrator.demo.util.UserUtil;
import com.example.administrator.demo.view.CommentPopupWindow;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/1/16.
 */

public class CommentAdapter extends BaseAdapter<ItemCommentBinding, Comment> {

    private Context mContext;
    private List<Comment> mCommentList = new ArrayList<>();

    public CommentAdapter(Context context, List<Comment> dataList) {
        super(context, dataList);
        this.mContext = context;
        this.mCommentList = dataList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_comment;
    }

    @Override
    public void bindView(CommonViewHolder viewHolder, final int position) {
        final Comment comment = mCommentList.get(position);
        LogUtil.D("hutcwp","进入到了bindView方法（）");

        List<Reply> replyList = TopicModelImp.getInstance().getReplyByCommentId(comment.getId());

        String fromUsername = UserModelImp.getInstance().getUserById(comment.getFromUId()).getUsername();
        int lenFromUsername = fromUsername.length();
        String content = null;
        content = fromUsername + "说: " + comment.getContent();

        if (replyList != null) {
            for (Reply reply : replyList) {
                Log.d("test", "查询到的回复信息：" + reply.getContent());
                String username1 = UserModelImp.getInstance().getUserById(reply.getFromUId()).getUsername();
                String username2 = UserModelImp.getInstance().getUserById(reply.getToUId()).getUsername();
                content += "\n" + username1 + " 回复: " + username2 + ":" + reply.getContent();
            }
        }
        SpannableString ss = new SpannableString(content);

        ss.setSpan(new ClickSpannable(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了有颜色的文字", Toast.LENGTH_SHORT).show();
            }
        }), 0, lenFromUsername, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        viewHolder.bindView.tvComment.setText(ss);
        viewHolder.bindView.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comment.getFromUId() == UserUtil.getCurUser().getId()) {
                    Toast.makeText(mContext, "自己不能回复自己", Toast.LENGTH_SHORT).show();
                } else {
                    publishReply(comment, position);
                }
            }
        });
        viewHolder.bindView.tvComment.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void publishReply(final Comment comment, final int position) {
        CommentPopupWindow popupWindow = new CommentPopupWindow(
                mContext, new CommentPopupWindow.CommentSendClick() {
            @Override
            public void onSendClick(View v, String result) {
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                LogUtil.D(TAG, "输入的信息为：" + result);
                Reply reply = new Reply(comment.getTopicId(),
                        comment.getId(),
                        UserUtil.getCurUser().getId(),
                        comment.getFromUId(),
                        result,
                        "",
                        CommentUtil.getCurTime() + ""
                );
                //耗时操作？
                if (TopicModelImp.getInstance().publishReply(reply)) {
                    mCommentList.add(position+1,reply);
                    notifyItemInserted(position+1);
                }
                LogUtil.D(TAG, "==============发布评论信息===============");
                LogUtil.D(TAG, "topId:" + reply.getTopicId() + "content:" + result);
            }
        });
        popupWindow.showReveal();
    }

    public void addData(Comment comment) {
        mCommentList.add(comment);
        LogUtil.D("hutcwp", "评论列表更新");
        notifyItemChanged(getItemCount());
    }

}
