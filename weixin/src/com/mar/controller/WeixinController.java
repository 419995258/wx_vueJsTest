package com.mar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.Menu;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;

import com.mar.controller.vo.Group;
import com.mar.controller.vo.Message;
import com.mar.controller.vo.Result;
import com.mar.controller.vo.message2;
import com.mar.entity.MessageSend;
import com.mar.util.RequestHandler;
import com.mar.util.SHA1Util;
import com.mar.util.XMLUtil;
import com.mar.util.httpsUtil;
import com.mar.util.weixinUtil;

@RestController
@RequestMapping("/weixinC")
@Scope("prototype")
public class WeixinController {
    private String Token = "1234567";

    public void liaotian(Model model, HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        // System.out.println("锟斤拷锟斤拷chat");
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if (isGet) {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            System.out.println(signature);
            System.out.println(timestamp);
            System.out.println(nonce);
            System.out.println(echostr);
            access(request, response);
        } else {
            // 锟斤拷锟斤拷POST锟斤拷锟届处锟斤拷
            // System.out.println("enter post");
            // 锟斤拷锟斤拷锟斤拷息锟斤拷锟斤拷锟斤拷锟斤拷息
            acceptMessage(request, response);

        }
    }

    /*
     * @RequestMapping(value = "/xiadan", method = RequestMethod.POST) public
     * Message xiadan(@RequestBody TradeRecord tr, HttpServletRequest request,
     * HttpServletResponse response) throws Exception { String spbill_create_ip
     * = request.getRemoteAddr(); RequestHandler reqHandler = new
     * RequestHandler(request, response); Message message =
     * businessService.xiadan(tr,spbill_create_ip,reqHandler); return message; }
     * 
     * @RequestMapping(value = "/getSignature/{appId}/{appSecret}", method =
     * RequestMethod.POST) public Message wxConfig(@RequestBody Message m,
     * HttpServletRequest request, HttpServletResponse response) throws
     * Exception { Message message = new Message();
     * message.setResult(weixinUtil.signature(m.getMessage())); return message;
     * }
     */

    /**
     * 锟斤拷证URL锟斤拷实锟斤拷
     * 
     * @author morning
     * @date 2015锟斤拷2锟斤拷17锟斤拷 锟斤拷锟斤拷10:53:07
     * @param request
     * @param response
     * @return String
     */
    private String access(HttpServletRequest request,
                    HttpServletResponse response) {
        // 锟斤拷证URL锟斤拷实锟斤拷
        System.out.println("锟斤拷锟斤拷锟斤拷证access");
        String signature = request.getParameter("signature");// 微锟脚硷拷锟斤拷签锟斤拷
        String timestamp = request.getParameter("timestamp");// 时锟斤拷锟�
        String nonce = request.getParameter("nonce");// 锟斤拷锟斤拷锟�
        String echostr = request.getParameter("echostr");// 锟斤拷锟斤拷址锟�
        List<String> params = new ArrayList<String>();
        params.add(Token);
        params.add(timestamp);
        params.add(nonce);
        // 1. 锟斤拷token锟斤拷timestamp锟斤拷nonce锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟街碉拷锟斤拷锟斤拷锟斤拷
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // 2. 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷址锟狡达拷映锟揭伙拷锟斤拷址锟斤拷锟斤拷sha1锟斤拷锟斤拷
        String temp = SHA1Util.encode(params.get(0) + params.get(1)
                        + params.get(2));
        if (temp.equals(signature)) {
            try {
                response.getWriter().write(echostr);
                System.out.println("锟缴癸拷锟斤拷锟斤拷 echostr锟斤拷" + echostr);
                return echostr;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("失锟斤拷 锟斤拷证");
        return null;
    }

    /**
     * 发送信息给个人
     * 
     * @param message2
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Message xiadan(@RequestBody message2 message2,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        Message message = new Message();
        MessageSend ms = new MessageSend();
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        String access_token = weixinUtil.getAccess_token(weixinUtil.appid,
                        weixinUtil.appsecret);
        url = url.replace("ACCESS_TOKEN", access_token);
        // ms.setAccess_token(weixinUtil.getAccess_token(weixinUtil.appid,
        // weixinUtil.appsecret));
        ms.setAccess_token(access_token);
        ms.setTouser(message2.getOpenId());
        ms.setMsgtype("text");
        Map t = new HashMap<String, String>();
        t.put("content", message2.getMessage());
        ms.setText(t);
        JSONObject json = JSONObject.fromObject(ms);
        httpsUtil.httpsRequest(url, "POST", json.toString());
        // System.out.println(j.toString());
        message.setSuccess(true);
        return message;
    }

    /**
     * 获取所有用户信息
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public Result wxConfig(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        Result r = new Result();
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", weixinUtil.getAccess_token(
                        weixinUtil.appid, weixinUtil.appsecret));
        JSONObject j = httpsUtil.httpsRequest(url, "GET", null);
        JSONObject datajson = JSONObject.fromObject(j.get("data"));
        System.out.println(datajson.toString());
        List<String> openIds = (List<String>) datajson.get("openid");
        List<message2> rows = new ArrayList<>();
        for (Iterator iterator = openIds.iterator(); iterator.hasNext();) {
            String openId = (String) iterator.next();
            message2 msg = new message2();
            msg.setOpenId(openId);
            rows.add(msg);
        }
        r.setSuccess(true);
        r.setRows(rows);
        return r;
    }

    /**
     * 给所有用户发送消息
     * 
     * @param message2
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendAll", method = RequestMethod.POST)
    public Message sendAll(@RequestBody message2 message2,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        Message message = new Message();
        MessageSend ms = new MessageSend();
        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
        String access_token = weixinUtil.getAccess_token(weixinUtil.appid,
                        weixinUtil.appsecret);
        url = url.replace("ACCESS_TOKEN", access_token);
        // ms.setAccess_token(weixinUtil.getAccess_token(weixinUtil.appid,
        // weixinUtil.appsecret));
        ms.setAccess_token(access_token);
        ms.setMsgtype("text");
        Map t = new HashMap<String, String>();
        t.put("content", message2.getMessage());
        ms.setText(t);
        Map<String, Object> filter = new HashMap<>();
        filter.put("is_to_all", false);
        filter.put("group_id", 100);
        ms.setFilter(filter);
        JSONObject json = JSONObject.fromObject(ms);
        httpsUtil.httpsRequest(url, "POST", json.toString());
        // System.out.println(j.toString());
        message.setSuccess(true);
        return message;
    }

    /**
     * 获取信息
     */

    /**
     * @param: request
     * @param: response
     */
    @RequestMapping(value = "/getMessage", method = { RequestMethod.GET,
            RequestMethod.POST })
    private void acceptMessage(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        Map<String, String> m = XMLUtil.parseXml(request);
        String openId = (String) m.get("FromUserName");

    }

    /**
     * 获取所有分组信息
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGroup", method = RequestMethod.POST)
    public Result getGroup(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        Result r = new Result();
        String url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", weixinUtil.getAccess_token(
                        weixinUtil.appid, weixinUtil.appsecret));
        JSONObject j = httpsUtil.httpsRequest(url, "GET", null);
        JSONArray group = JSONArray.fromObject(j.get("groups"));
        // JSONObject idjson = JSONObject.fromObject(j.get("groups"));
        List<Map<String, Object>> groups = group;
        List<Group> rs = new ArrayList<>();
        for (Iterator iterator = groups.iterator(); iterator.hasNext();) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            Group allGroup = new Group();
            allGroup.setId((Integer) map.get("id"));
            allGroup.setName((String) map.get("name"));
            allGroup.setCount((Integer) map.get("count"));
            rs.add(allGroup);
        }
        // List<message2> rows = new ArrayList<>();
        /*
         * for (Iterator iterator = openIds.iterator(); iterator.hasNext();) {
         * String openId = (String) iterator.next(); message2 msg = new
         * message2(); msg.setOpenId(openId); rows.add(msg); }
         */
        r.setSuccess(true);
        r.setRows(rs);
        return r;
    }

    /**
     * 查询分组信息
     * 
     * @param message2
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryGroup", method = RequestMethod.POST)
    public Message queryGroup(@RequestBody message2 message2,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        Message message = new Message();
        MessageSend ms = new MessageSend();
        String url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
        String access_token = weixinUtil.getAccess_token(weixinUtil.appid,
                        weixinUtil.appsecret);
        url = url.replace("ACCESS_TOKEN", access_token);
        // ms.setAccess_token(weixinUtil.getAccess_token(weixinUtil.appid,
        // weixinUtil.appsecret));
        ms.setAccess_token(access_token);
        ms.setOpenid(message2.getOpenId());
        JSONObject json = JSONObject.fromObject(ms);
        JSONObject groupId = httpsUtil.httpsRequest(url, "POST",
                        json.toString());
        // System.out.println(j.toString());
        message.setSuccess(true);
        message.setMessage(groupId.get("groupid").toString());
        return message;
    }

    /**
     * 更新分组名
     * 
     * @param group
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateGroupName", method = RequestMethod.POST)
    public Message updateGroupName(@RequestBody Group group,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        Message message = new Message();
        // MessageSend ms = new MessageSend();
        String url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
        String access_token = weixinUtil.getAccess_token(weixinUtil.appid,
                        weixinUtil.appsecret);
        url = url.replace("ACCESS_TOKEN", access_token);
        // ms.setAccess_token(access_token);
        // ms.setTouser(message2.getOpenId());
        // ms.setMsgtype("text");
        // Map t = new HashMap<String, String>();
        // t.put("content", message2.getMessage());
        // ms.setText(t);
        JSONObject json2 = JSONObject.fromObject(group);
        JSONObject json = new JSONObject();
        json.put("group", json2);
        httpsUtil.httpsRequest(url, "POST", json.toString());
        // System.out.println(j.toString());
        message.setSuccess(true);
        return message;
    }

    /**
     * 添加分组
     * 
     * @param group
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public Message addGroup(@RequestBody Group group,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        Message message = new Message();
        String url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
        String access_token = weixinUtil.getAccess_token(weixinUtil.appid,
                        weixinUtil.appsecret);
        url = url.replace("ACCESS_TOKEN", access_token);
        JSONObject json2 = JSONObject.fromObject(group);
        JSONObject json = new JSONObject();
        json.put("group", json2);
        JSONObject result = httpsUtil
                        .httpsRequest(url, "POST", json.toString());
        message.setSuccess(true);
        return message;
    }
    
    /**
     * 删除分组
     * @param group
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public Message deleteGroup(@RequestBody Group group,
                    HttpServletRequest request, HttpServletResponse response)
                    throws Exception {
        Message message = new Message();
        // MessageSend ms = new MessageSend();
        String url = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
        String access_token = weixinUtil.getAccess_token(weixinUtil.appid,
                        weixinUtil.appsecret);
        url = url.replace("ACCESS_TOKEN", access_token);
        JSONObject json2 = JSONObject.fromObject(group);
        JSONObject json = new JSONObject();
        json.put("group", json2);
        JSONObject result = httpsUtil.httpsRequest(url, "POST", json.toString());
        message.setSuccess(true);
        return message;
    }
}
