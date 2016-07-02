<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="user_loggin_dialog" class="easyui-dialog" title="登陆"
	modal=true
	data-options="
				buttons: [{
					text:'登陆',
					iconCls:'icon-ok',
					handler:function(){
						$('#user_loggin_form').form({
							url : 'userLoggin.action',
							onSubmit : function() {
							// do some check
							// return false to prevent submit;
							return $(this).form('enableValidation').form('validate');
						},
							success : function(data) {
							        var data = eval('(' + data + ')');
							        //console.info(data);
									var win = $.messager.progress({
										title : '请稍等',
										msg : data.message,
										interval:150
									});
									setTimeout(function() {
										$.messager.progress('close');
										if (data.success) {
											$('#user_loggin_dialog').dialog('close');
										}
									}, 1500)
							}
						});
						$('#user_loggin_form').submit();
					}
				},{
					text:'注册',
					handler:function(){
						$('#user_reg_dialog').dialog('open'); 
						$('#user_loggin_dialog').dialog('close'); 
						
					}
				}]
			">
	<form id="user_loggin_form" method="post">
		<table>
			<tr>
				<th>账号</th>
				<td><input class="easyui-validatebox" type="text"
					name="index_loggin_name" data-options="required:true"></input></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input class="easyui-validatebox" type="password"
					name="index_loggin_password" data-options="required:true"></input></td>
			</tr>

		</table>

	</form>
</div>