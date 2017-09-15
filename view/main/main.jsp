<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>青岛豪迈电缆集团有限公司MES系统</title>
    <%@include file="/view/resource.jsp" %>
    <link rel="stylesheet" type="text/css" href="${msUrl}/css/main.css">
    <script type="text/javascript" src="${msUrl}/js/ux/main/main.js"></script>
  </head>
  <body class="easyui-layout">
  	
 	<div class="ui-header" data-options="region:'north',split:true,border:false" style="height:40px;overflow: hidden;">
 	<h1>豪迈电缆MES</h1>
 	<div  class="ui-login">
 		<div class="ui-login-info">
	 		欢迎 <span class="orange">${user.nickName}</span> 第[<span class="orange">${user.loginCount}</span>]次登录系统 
	 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 		<a class="modify-pwd-btn"  href="javascript:void(0);">修改密码</a> |
 			<a class="logout-btn" href="${msUrl}/logout.shtml">退出</a>
 		</div>
 	</div>
 	</div>
	<!-- 树形菜单 -->
	<div data-options="region:'west',split:true,title:'导航'" style="width:160px;">
		<div id="tree-box" class="easyui-accordion" data-options="fit:true,border:false">
			<c:forEach var="item" items="${menuList}">
			<div title="${item.text}">
				<c:forEach var="node" items="${item.children}">
				<a class="menu-item" href="${msUrl}${node.url}">${node.text}</a>
				</c:forEach>
			</div>
			</c:forEach>
		</div>
	</div>
	<div data-options="region:'south',split:true,border:false" style="height: 30px;overflow:hidden;">
		<div class="panel-header" style="border: none;text-align: center;" >CopyRight &copy; 2017-2020  青岛豪迈电缆集团有限公司  &nbsp;&nbsp;网址:www.haomai.com.cn  </div>
	</div>
	<!-- 中间内容页面 -->
	<div data-options="region:'center'" >
		<div class="easyui-tabs" id="tab-box" data-options="fit:true,border:false">
			<div title="使用简介" style="padding:20px;overflow:hidden;"> 
				<div style="margin-top:20px;">
					<h3>简要说明</h3>
					<ul>
						<li>使用Java平台,采用SpringMVC+Mybatis等主流框架</li> 
						<li>数据库:MYSQL</li> 
						<li>前端:使用Jquery和Easyui技术.界面清晰简洁,易操作.</li> 
						<li>权限:对菜单,按钮控制.仅展示有权限的菜单和按钮.</li> 
						<li>拦截:对所有无权限URL进行拦截,防止手动发送HTTP请求,确保系统全性.</li> 
						<li>主要完成：销售订单管理、生产计划管理等功能。</li> 
					</ul>
				</div>
				
			</div>
		</div>	
	</div>
	<!--  modify password start -->
	<div id="modify-pwd-win"  class="easyui-dialog" buttons="#editPwdbtn" title="修改用户密码" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:350px;height:200px;">
		<form id="pwdForm" action="modifyPwd.do" class="ui-form" method="post">
     		 <input class="hidden" name="id">
     		 <input class="hidden" name="email">
     		 <div class="ui-edit">
	           <div class="fitem">  
	              <label>旧密码:</label>  
	              <input id="oldPwd" name="oldPwd" type="password" class="easyui-validatebox"  data-options="required:true"/>
	           </div>
	            <div class="fitem">  
	               <label>新密码:</label>  
	               <input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" />
	           </div> 
	           <div class="fitem">  
	               <label>重复密码:</label>  
	              <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"   required="required" validType="equals['#newPwd']" />
	           </div> 
	         </div>
     	 </form>
     	 <div id="editPwdbtn" class="dialog-button" >  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-submit">提交</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-close">放弃</a>  
         </div>
	</div>
	<!-- modify password end  -->
  </body>
<script type="text/javascript">  
//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function banBackSpace(e){   var ev = e || window.event;
//获取event对象   
var obj = ev.target || ev.srcElement;
//获取事件源   
var t = obj.type || obj.getAttribute('type');//获取事件源类型   
//获取作为判断条件的事件类型   
var vReadOnly = obj.getAttribute('readonly');   
var vEnabled = obj.getAttribute('enabled');   
//处理null值情况   
vReadOnly = (vReadOnly == null) ? false : vReadOnly; 
vEnabled = (vEnabled == null) ? true : vEnabled;  
//当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
//并且readonly属性为true或enabled属性为false的，则退格键失效   
var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   && (vReadOnly==true || vEnabled!=true))?true:false;   
//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效   
var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")   ?true:false;  
//判断   
if(flag2)
	{   return false;   }  
if(flag1)
{   return false;   }   
}   
//禁止后退键 作用于Firefox、Opera   
document.onkeypress=banBackSpace;   
//禁止后退键 作用于IE、Chrome   
document.onkeydown=banBackSpace;   
</script> 

</html>
