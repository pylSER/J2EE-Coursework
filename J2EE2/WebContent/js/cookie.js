function setCookie(name,value)
{
	  var expdate = new Date();
	    expdate.setTime(expdate.getTime() + 3*24 * 60 * 60 * 1000);   //时间
	    document.cookie = name+"="+value+";expires="+expdate.toGMTString()+";path=/J2EE2";
}

function getCookie(name)
{
var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
if(arr=document.cookie.match(reg))
return unescape(arr[2]);
else
return null;
}