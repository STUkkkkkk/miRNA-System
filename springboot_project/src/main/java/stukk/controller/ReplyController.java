package stukk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stukk.Pojo.Reply;
import stukk.Pojo.User;
import stukk.config.Result;
import stukk.config.UserHolder;
import stukk.service.ReplyService;

import java.util.Map;

@RestController
@RequestMapping("/reply")
public class ReplyController {


    /*
content
CommentId
bereplyName
bereplyId
     */

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public Result add(@RequestBody Map<String ,Object> map){
        Long commentId = Long.parseLong(map.get("CommentId").toString());
        String content = (String)map.get("content");
        String bereplyName = (String)map.get("bereplyName");
        Long bereplyId = Long.parseLong(map.get("bereplyId").toString());
        Reply reply = new Reply();
        reply.setBereplyId(bereplyId);
        reply.setBereplyName(bereplyName);
        reply.setCommentId(commentId);
        reply.setContent(content);
        reply.setOkComment(false);
        User me = UserHolder.getUser();
        reply.setUserId(me.getId());
        reply.setUserName(me.getUsername());
        reply.setUserIcon(me.getIcon());
        if(replyService.save(reply)){
            return Result.success(reply);
        }
        else{
            return Result.error("112","回复失败，请联系管理员");
        }
    }
}
