package com.mar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mar.controller.vo.Group;
import com.mar.controller.vo.Message;
import com.mar.entity.Student;
import com.mar.util.httpsUtil;
import com.mar.util.weixinUtil;

@RestController
@RequestMapping("/testC")
@Scope("prototype")
public class testController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Message deleteGroup(@RequestBody Student student) throws Exception {
        Message message = new Message();
        message.setSuccess(true);
        Map<String, Object> result =new HashMap<>();
        result.put("first", 555);
        result.put("seco", 132);
        message.setResult(result);
        return message;
    }
}
