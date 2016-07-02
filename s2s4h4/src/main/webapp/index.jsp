<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sshe</title>
<link rel="stylesheet" type="text/css"
	href="jslib/jquery-easyui-1.4.5/themes/material/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jslib/jquery-easyui-1.4.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jslib/jquery-easyui-1.4.5/demo/demo.css">
<script type="text/javascript"
	src="jslib/jquery-easyui-1.4.5/jquery.min.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jslib/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="jslib/util/syutil.js"></script>
<jsp:include page="jsp/user/loggin.jsp"></jsp:include>
<jsp:include page="jsp/user/reg.jsp"></jsp:include>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:60px;background:#B3DFDA;padding:1px">描述</div>
	<div data-options="region:'west',title:'菜单'"
		style="width:15%;padding:1px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="权限管理" style="padding:10px;">content1</div>
			<div title="菜单管理" data-options="selected:true" style="padding:10px;">
				content2</div>
			<div title="流程管理" style="padding:10px">content3</div>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;background:#A9FACD;padding:10px;">广告位</div>
	<div data-options="region:'center',title:'Center'">
		<div class="easyui-tabs"
			data-options="fit:true,border:false,plain:true">
			<div title="About"
				data-options="href:'jslib/jquery-easyui-1.4.5/demo/tabs/_content.html'"
				style="padding:10px"></div>
			<div title="DataGrid" style="padding:5px">
				<table class="easyui-datagrid"
					data-options="url:'jslib/jquery-easyui-1.4.5/demo/datagrid/datagrid_data1.json',method:'get',singleSelect:true,fit:true,fitColumns:true">
					<thead>
						<tr>
							<th data-options="field:'itemid'" width="80">Item ID</th>
							<th data-options="field:'productid'" width="100">Product ID</th>
							<th data-options="field:'listprice',align:'right'" width="80">List
								Price</th>
							<th data-options="field:'unitcost',align:'right'" width="80">Unit
								Cost</th>
							<th data-options="field:'attr1'" width="150">Attribute</th>
							<th data-options="field:'status',align:'center'" width="50">Status</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	

	
</body>
</html>