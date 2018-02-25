package com.example.administrator.demo.interfaze;

import com.example.administrator.demo.bean.Comment;
import com.example.administrator.demo.bean.Reply;
import com.example.administrator.demo.bean.Topic;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public interface ITopicModel {

    //获取话题集合
    List<Topic> getTopics();

    //通过id获取话题
    List<Comment> getCommentByTopicId(int topicId);

    //通过id获取回复
    List<Reply> getReplyByCommentId(int commentId);


    //发布话题
    boolean publishTopic(Topic topic);

    //发表评论
    boolean publishComment(Comment comment);

    //回复评论
    boolean publishReply(Reply reply);

    //点赞
    boolean like(int userId, String likeIds, int topicId);

    //取消赞
    boolean cancelLike(int userId, String likeIds, int topicId);

}
