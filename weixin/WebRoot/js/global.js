
function verifyIsNull(valStr) {
	if (valStr == undefined || valStr == "" || valStr == null){  
		return true;
	}
	return false;
};

function covNull(valStr) {
	if (valStr == undefined || valStr == "" || valStr == null){  
		return "";
	}
	return valStr;
};

//设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    //document.cookie = cname + "=" + URLEncoder.encode(cvalue, "utf-8"); + "; " + expires;
    document.cookie = cname + "=" + encodeURI(encodeURI(cvalue)) + "; " + expires;
}

//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) 
        	//return URLDecoder.decode(c.substring(name.length, c.length),"utf-8");
        	return decodeURI(c.substring(name.length, c.length));
    }
    return "";
}

//清除cookie  
function clearCookie(name) {  
    setCookie(name, "", -1);  
}

function encode(str) {
	return encodeURI(encodeURI(str));
}

function decode(str) {
	return decodeURI(str);
}

function removeAllChild(id) {
    var div = document.getElementById(id);
    while(div.hasChildNodes()) { //当div下还存在子节点时 循环继续
        div.removeChild(div.firstChild);
    }
}

/*function isNum(text) {
	var reg = "/^\d+(\.\d+)?$/";
	return reg.test(text);
}*/

function isNum(value) {
    //var re = /^\d+(?=\.{0,1}\d+$|$)/;
	/*var re = /[\d.]/;
    if (value != "") {
        if (!re.test(value)) {
            return false;
        }
    }*/
    
	if (verifyIsNull(value)) {
		return false;
	}
	
    var reg = /^\d+(\.\d+)?$/;
	//if(/\D/.test($('#aaa').val())){
	if(!value.match(reg)){
		return false;
	}
    return true;
} 