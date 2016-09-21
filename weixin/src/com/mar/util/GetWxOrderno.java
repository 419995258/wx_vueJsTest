package com.mar.util;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.mar.util.http.HttpClientConnectionManager;

public class GetWxOrderno
{
  public static DefaultHttpClient httpclient;

  static
  {
    httpclient = new DefaultHttpClient();
    httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
  }


  public static String getPayNo(String url,String xmlParam){
	  DefaultHttpClient client = new DefaultHttpClient();
	  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
	  HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	  String prepay_id = "";
     try {
		 httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
		 HttpResponse response = httpclient.execute(httpost);
	     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
	     Map<String, Object> dataMap = new HashMap<String, Object>();
	     System.out.println(jsonStr);
	     
	    if(jsonStr.indexOf("FAIL")!=-1){
	    	return prepay_id;
	    }
	    Map map = doXMLParse(jsonStr);
	    String return_code  = (String) map.get("return_code");
	    prepay_id  = (String) map.get("prepay_id");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prepay_id;
  }
  
  public static String remit(String url,String xmlParam) throws Exception{
	//指定读取证书格式为PKCS12

	  KeyStore keyStore = KeyStore.getInstance("PKCS12");

	  //读取本机存放的PKCS12证书文件

	  FileInputStream instream = new FileInputStream(new File("C:/apiclient_cert.p12"));

	  try {

	  //指定PKCS12的密码(商户ID)

	  keyStore.load(instream, "1356982002".toCharArray());

	  } finally {

	  instream.close();

	  }

	  SSLContext sslcontext = SSLContexts.custom()

	  .loadKeyMaterial(keyStore, "1356982002".toCharArray()).build();

	  //指定TLS版本

	  SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(

	  sslcontext,new String[] { "TLSv1" },null,

	  SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER); //设置httpclient的SSLSocketFactory

	  CloseableHttpClient httpclient = HttpClients.custom()

	  .setSSLSocketFactory(sslsf)

	  .build();
	  
	  //DefaultHttpClient client = new DefaultHttpClient();
	  //httpclient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
	  HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	  String message = "";
     try {
		 httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
		 HttpResponse response = httpclient.execute(httpost);
	     String jsonStr = EntityUtils.toString(response.getEntity(),"UTF-8");
	     Map<String, Object> dataMap = new HashMap<String, Object>();
	     System.out.println(jsonStr);
	    /*if(jsonStr.indexOf("result_code")!=-1){
	    	Map map = doXMLParse(jsonStr);
		    String result_code  = (String) map.get("result_code");
		    if (result_code.equals("SUCCESS")) {
		    	message = "提现成功";
		    } else {
		    	message = (String)map.get("err_code_des");
		    }
	    } else {
	    	Map map = doXMLParse(jsonStr);
	    	message = (String) map.get("return_msg");
	    }*/
		if (jsonStr.indexOf("payment_no")!=-1) {
			message = "提现成功";
		} else {
			message = "系统繁忙，请稍候重试或联系管理员";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return message;
  }
  /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
  public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
}