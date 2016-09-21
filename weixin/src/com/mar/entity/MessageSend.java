package com.mar.entity;

import java.util.Map;

public class MessageSend {
	private String access_token;
	private String touser;
	private String msgtype;
	private Map<String, String> text;
	private Map<String, Object> filter;
	private String media_id;
	private String thumb_media_id;
	private String title;
	private String description;
	private String musicurl;
	private String hqmusicurl;
	private String url;
	private String picurl;
	private String openid;
	
	
	
	public String getMedia_id() {
        return media_id;
    }
    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
    public String getThumb_media_id() {
        return thumb_media_id;
    }
    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getMusicurl() {
        return musicurl;
    }
    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl;
    }
    public String getHqmusicurl() {
        return hqmusicurl;
    }
    public void setHqmusicurl(String hqmusicurl) {
        this.hqmusicurl = hqmusicurl;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPicurl() {
        return picurl;
    }
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getAccess_token() {
		return access_token;
	}
	public Map<String, Object> getFilter() {
		return filter;
	}
	public void setFilter(Map<String, Object> filter) {
		this.filter = filter;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public Map<String, String> getText() {
		return text;
	}
	public void setText(Map<String, String> text) {
		this.text = text;
	}
}