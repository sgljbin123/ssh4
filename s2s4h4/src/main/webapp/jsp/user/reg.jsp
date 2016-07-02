<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(function() {
		$('#user_reg_form').form({
		    url  : 'saveUser.action',
			success : function(data) {
				var data = eval('(' + data + ')');
				var win = $.messager.progress({
					title : '请稍等',
					msg : data.message + ',正在跳转页面',
					interval : 150
				});
				setTimeout(function() {
					$.messager.progress('close');
					if (data.success) {
						$('#user_reg_dialog').dialog('close');
						$('#user_loggin_dialog').dialog('open');
					}
				}, 1500);
			}
		});

		$('#user_reg_form').bind('keyup', function(event) {
			if (event.keyCode == '13') {
				$('#user_reg_form').submit();
			}
		});
	});
</script>
<div id="user_reg_dialog" style="" class="easyui-dialog" title="注册"
	modal=true closed=true
	data-options="
				buttons: [{
					text:'注册',
					iconCls:'icon-ok',
					handler:function(){
						$('#user_reg_form').submit();
				 }
				},{
					text:'取消',
					handler:function(){
					$('#user_reg_dialog').dialog('destroy');
				
					}
				}]
			">
	<form id="user_reg_form" action="" method="post">
		<table>
			<tr>
				<th>账号</th>
				<td><input class="easyui-validatebox" type="text"
					name="index_reg_name"
					data-options="required:true,missingMessage:'账号必填'"></input></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input id="index_reg_password" class="easyui-validatebox"
					type="password" name="index_reg_password"
					data-options="required:true"></input></td>
			</tr>
			<tr>
				<th>确认密码</th>
				<td><input id="index_confirm_reg_password"
					class="easyui-validatebox" type="password"
					name="index_confirm_reg_password" data-options="required:true"
					validType="equals['#index_reg_password']"></input></td>
			</tr>
		</table>
	</form>
</div>