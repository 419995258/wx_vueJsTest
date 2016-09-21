package com.mar.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
 






/*
 * 公众平台通用接口工具�?
 * @author xuhj
 * @date 2015-3-11
 */
public class weixinUtil {
	public static String token = null;
    public static String time = null;
    public static String jsapi_ticket = null;
    public static final String appid = "wx861cffa20ac505c8";
    public static final String appsecret = "61d46ebd06445b90351c2b59e202ea93";
	
    public static String getOpenId(String appid, String appsecret,String code) {
        //凭证获取(GET)
        String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	    String requestUrl = token_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE",code);
	    // 发起GET请求获取凭证
	    JSONObject jsonObject = httpsUtil.httpsRequest(requestUrl, "GET", null);
	    String openid = null;
	    if (null != jsonObject) {
	        try {
	            openid = jsonObject.getString("openid");
	        } catch (JSONException e) {
	            // 获取token失败
	        }
	    }
	    return openid;
	}
    
    public static void getUserFromServer(String openId) {
    	//ResultVo result = new ResultVo();
    	//result.setRecords(openId);
    	if(token == null){
            token = weixinUtil.getAccess_token(appid, appsecret);
            time = getTime();
        }else{
            if(!time.substring(0, 13).equals(getTime().substring(0, 13))){ //每小时刷新一�?
                token = null;
                token = weixinUtil.getAccess_token(appid, appsecret);
                time = getTime();
            }
        }
    	String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    	url = url.replace("ACCESS_TOKEN", token).replace("OPENID", openId);
    	JSONObject jsonObject = httpsUtil.httpsRequest(url, "GET", null);
    	//String name = null;
    	/*User u = new User();
	    if (null != jsonObject) {
	        try {
	            u.setName(jsonObject.getString("nickname"));
	            u.setSex(jsonObject.getString("sex"));
	            u.setHeadUrl(jsonObject.getString("headimgurl"));
	            u.setOpenId(jsonObject.getString("openid"));
	            //u.setCity(jsonObject.getString("city"));
	            //u.setCountry(jsonObject.getString("country"));
	            //u.setProvince(jsonObject.getString("province"));
	            u.setSubscribeTime(jsonObject.getString("subscribe_time"));
	        } catch (JSONException e) {
	            // 获取token失败
	        }
	    }*/
	    //result.setRecords(name);
    }
    /**
     * 获取接口访问凭证
     * 
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static String getAccess_token(String appid, String appsecret) {
            //凭证获取(GET)
            String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsUtil.httpsRequest(requestUrl, "GET", null);
        String access_token = null;
        if (null != jsonObject) {
            try {
                access_token = jsonObject.getString("access_token");
            } catch (JSONException e) {
                // 获取token失败
            }
        }
        return access_token;
    }
    
    /**
     * 调用微信JS接口的临时票�?
     * 
     * @param access_token 接口访问凭证
     * @return
     */
    public static String getJsApiTicket(String access_token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        String requestUrl = url.replace("ACCESS_TOKEN", access_token);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsUtil.httpsRequest(requestUrl, "GET", null);
        String ticket = null;
        if (null != jsonObject) {
            try {
                ticket = jsonObject.getString("ticket");
            } catch (JSONException e) {
                // 获取token失败
            }
        }
        return ticket;
    }
    
    /**
     * 
     * @param appId   公账号appId
     * @param appSecret
     * @param url    当前网页的URL，不包含#及其后面部分
     * @return
     */
    public static Map<String, String> getParam(String appId,String appSecret,String url){
        if(token == null){
            token = weixinUtil.getAccess_token(appId, appSecret);
            jsapi_ticket = weixinUtil.getJsApiTicket(token);
            time = getTime();
        }else{
            if(!time.substring(0, 13).equals(getTime().substring(0, 13))){ //每小时刷新一�?
                token = null;
                token = weixinUtil.getAccess_token(appId, appSecret);
                jsapi_ticket = weixinUtil.getJsApiTicket(token);
                time = getTime();
            }
        }
        
        
        Map<String, String> params = sign(jsapi_ticket, url);
        params.put("appid", appId);
         
        //JSONObject jsonObject = JSONObject.fromObject(params);  
        //String jsonStr = jsonObject.toString();
        //System.out.println(jsonStr);
        return params;
    }
     
    /*private static String getUrl(){
        HttpServletRequest request = Context.getRequest();
         
        StringBuffer requestUrl = request.getRequestURL();
         
        String queryString = request.getQueryString();
        String url = requestUrl +"?"+queryString;
        return url;
    }*/
     
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String str;
        String signature = "";
 
        //注意这里参数名必须全部小写，且必须有�?
        str = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
 
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
 
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
 
        return ret;
    }
 
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
 
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
 
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
     
    //获取当前系统时间 用来判断access_token是否过期
    public static String getTime(){
        Date dt=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

	@SuppressWarnings("unchecked")
	public static String getUrlfromServer(String openId) throws Exception{
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		String requestUrl = url.replace("TOKEN", token);
		JSONObject jo = new JSONObject();
		Map l = new HashMap();
		l.put("scene_str", openId);
		Map n = new HashMap();
		n.put("scene", l);
		Map m = new HashMap();
		m.put("action_name", "QR_LIMIT_STR_SCENE");
		m.put("action_info", n);
		jo.putAll(m);
		String jos = jo.toString();
		JSONObject jsonObject = httpsUtil.httpsRequest(requestUrl, "POST", jos);
		String codeUrl = null;
		if (null != jsonObject) {
            try {
                codeUrl = jsonObject.getString("ticket");
            } catch (JSONException e) {
                // 获取token失败
            }
        }
		return codeUrl;
	}
	
	public static Map<String, String> signature(String url) {
		if(token == null){
            token = weixinUtil.getAccess_token(appid, appsecret);
            jsapi_ticket = weixinUtil.getJsApiTicket(token);
            time = getTime();
        }else{
            if(!time.substring(0, 13).equals(getTime().substring(0, 13))){ //每小时刷新一�?
                token = null;
                token = weixinUtil.getAccess_token(appid, appsecret);
                jsapi_ticket = weixinUtil.getJsApiTicket(token);
                time = getTime();
            }
        }
		return sign(getJsApiTicket(token),url);
	}
}