<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.codecoord.com" prefix="c" %>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>客户名称:</label><input id="search-name" class="wu-text" style="width:100px">
            <label>真实姓名:</label><input id="search-realName" class="wu-text" style="width:100px">
            <label>身份证号:</label><input id="search-idCard" class="wu-text" style="width:100px">
            <label>手机号码:</label><input id="search-mobile" class="wu-text" style="width:100px">
            <label>状态:</label>
            <select id="search-status" class="easyui-combobox" panelHeight="auto" style="width:120px">
            	<option value="-2">全部</option>
            	<option value="0">可用</option>
            	<option value="-1">冻结</option>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- 添加弹框 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:420px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">头像预览:</td>
                <td valign="middle">
                    <img id="preview-photo" style="float:left;" src="/easyui/images/user.jpg" width="100px">
                    <a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传图片</a>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">头像图片:</td>
                <td><input type="text" id="add-photo" name="photo" value="/easyui/images/user.jpg" readonly="readonly" class="wu-text " /></td>
            </tr>
            <tr>
                <td align="right">名称:</td>
                <td><input type="text" id="add-name" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写客户名称'" /></td>
            </tr>
             <tr>
                <td align="right">密码:</td>
                <td><input type="password" id="add-password" name="password" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写客户密码'" /></td>
            </tr>
            <tr>
                <td align="right">真实姓名:</td>
                <td><input type="text" id="add-realName" name="realname" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">身份证号:</td>
                <td><input type="text" id="add-idCard" name="idCard" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td align="right">手机号码:</td>
                <td><input type="text" id="add-mobile" name="mobile" validType="mobile" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td align="right">联系地址:</td>
                <td><input type="text" id="add-address" name="address" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td align="right">状态:</td>
                <td>
	                <select id="add-status" name="status" class="easyui-combobox" panelHeight="auto" style="width:268px" data-options="required:true, missingMessage:'请选择状态'">
		            	<option value="0">可用</option>
		            	<option value="-1">冻结</option>
	            	</select>	
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">头像预览:</td>
                <td valign="middle">
                    <img id="edit-preview-photo" style="float:left;" src="/easyui/images/user.jpg" width="100px">
                    <a style="float:left;margin-top:40px;" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadPhoto()" plain="true">上传图片</a>
                </td>
            </tr>
            <tr>
                <td width="60" align="right">头像图片:</td>
                <td><input type="text" id="edit-photo" name="photo" value="/easyui/images/user.jpg" readonly="readonly" class="wu-text " /></td>
            </tr>
           <tr>
                <td align="right">名称:</td>
                <td><input type="text" id="edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写客户名称'" /></td>
            </tr>
             <tr>
                <td align="right">密码:</td>
                <td><input type="password" id="edit-password" name="password" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写客户密码'" /></td>
            </tr>
            <tr>
                <td align="right">真实姓名:</td>
                <td><input type="text" id="edit-realName" name="realname" class="wu-text easyui-validatebox" /></td>
            </tr>
            <tr>
                <td align="right">身份证号:</td>
                <td><input type="text" id="edit-idCard" name="idCard" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td align="right">手机号码:</td>
                <td><input type="text" id="edit-mobile" name="mobile" validType="mobile" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td align="right">联系地址:</td>
                <td><input type="text" id="edit-address" name="address" class="wu-text easyui-validatebox"  /></td>
            </tr>
            <tr>
                <td align="right">状态:</td>
                <td>
	                <select id="edit-status" name="status" class="easyui-combobox" panelHeight="auto" style="width:268px">
		            	<option value="0">可用</option>
		            	<option value="-1">冻结</option>
	            	</select>	
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="process-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-upload',title:'正在上传图片'" style="width:450px; padding:10px;">
    <div id="p" class="easyui-progressbar" style="width:400px;" data-options="text:'正在上传中...'"></div>
</div>
<input type="file" id="photo-file" style="display:none;" onchange="upload()">
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
    //上传图片
    function start(){
        var value = $('#p').progressbar('getValue');
        if (value < 100){
            value += Math.floor(Math.random() * 10);
            $('#p').progressbar('setValue', value);
        }else{
            $('#p').progressbar('setValue',0)
        }
    };
    function upload(){
        if($("#photo-file").val() == '')return;
        var formData = new FormData();
        formData.append('photo',document.getElementById('photo-file').files[0]);
        $("#process-dialog").dialog('open');
        var interval = setInterval(start,200);
        $.ajax({
            url:'../user/upload_photo',
            type:'post',
            data:formData,
            contentType:false,
            processData:false,
            success:function(data){
                clearInterval(interval);
                $("#process-dialog").dialog('close');
                if(data.success == true){
                    $("#preview-photo").attr('src',data.filepath);
                    $("#add-photo").val(data.filepath);
                    $("#edit-preview-photo").attr('src',data.filepath);
                    $("#edit-photo").val(data.filepath);
                }else{
                    $.messager.alert("消息提醒",data.msg,"warning");
                }
            },
            error:function(data){
                clearInterval(interval);
                $("#process-dialog").dialog('close');
                $.messager.alert("消息提醒","上传失败!","warning");
            }
        });
    }

    function uploadPhoto(){
        $("#photo-file").click();

    }
	
	
	/**
	*  添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
		//对身份证号和手机号进行判断
		var idCard = $('#add-idCard').val();
		var mobile = $('#add-mobile').val();
		console.log(idCard.length)

		if(idCard.length != 18) {
            $.messager.alert("消息提醒","身份证位数必须为18位!","warning");
            return;
        }
		if(mobile.length != 11) {
            $.messager.alert("消息提醒","手机号码位数必须为11位!","warning");
            return;
        }
		//判断真实姓名小于5位
        if($('#add-realName').val().length > 5) {
            $.messager.alert("消息提示","真实姓名格式不正确，必须小于5位","warning");
            return;
        }

		var data = $("#add-form").serialize();
		$.ajax({
			url:'add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.success == true){
					$.messager.alert('信息提示','添加成功！','info');
					$("#add-name").val('');
					$("#add-remark").val('');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* 编辑记录
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warning");
			return;
		}
        //对身份证号和手机号进行判断
        var idCard = $('#edit-idCard').val();
        var mobile = $('#edit-mobile').val();
        console.log(idCard.length)

        if(idCard.length != 18) {
            $.messager.alert("消息提醒","身份证位数必须为18位!","warning");
            return;
        }
        if(mobile.length != 11) {
            $.messager.alert("消息提醒","手机号码位数必须为18位!","warning");
            return;
        }
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.success == true){
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				if(item == null || item.length == 0){
					$.messager.alert('信息提示','请选择要删除的数据！','info');
					return;
				}
				
				$.ajax({
					url:'delete',
					dataType:'json',
					type:'post',
					data:{id:item.id},
					success:function(data){
						if(data.success == true){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开编辑窗口
	*/
	function openEdit(){
		//$('#add-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要编辑的数据！','info');
			return;
		}
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "编辑客户信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	//$("#add-form input").val('');
            	$("#edit-id").val(item.id);
            	$("#edit-name").val(item.name);
            	$("#edit-password").val(item.password);
            	$("#edit-realName").val(item.realname);
            	$("#edit-idCard").val(item.idCard);
            	$("#edit-mobile").val(item.mobile);
            	$("#edit-status").combobox('setValue',item.status);
            	$("#edit-address").val(item.address);
                $("#edit-preview-photo").attr('src',item.photo);
                $("#edit-photo").val(item.photo);
            }
        });
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加客户信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#add-form input").val('');
            }
        });
	}
	
	//搜索按钮监听
	$("#search-btn").click(function(){
		var option = {name:$("#search-name").val()};
		var status = $("#search-status").combobox('getValue');
		if(status != -2){
			option.status = status;
		}
		option.realname = $("#search-realName").val();
		option.idCard = $("#search-idCard").val();
		option.mobile = $("#search-mobile").val();
		$('#data-datagrid').datagrid('reload',option);
	});
	
	
	/** 
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'list',
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
            {field:'photo',title:'头像',width:'100',formatter:function (value,row,index) {
                    var img = '<img src="'+value+'" width="50px" style="margin-top:5px;" />';
                    return img;
                }},
			{ field:'name',title:'名称',width:100,sortable:true},
			{ field:'password',title:'密码',width:100,sortable:true},
			{ field:'realname',title:'真实姓名',width:100,sortable:true},
			{ field:'idCard',title:'身份证号',width:100,sortable:true},
			{ field:'mobile',title:'手机号',width:100,sortable:true},
			{ field:'address',title:'地址',width:100,sortable:true},
			{ field:'status',title:'状态',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return '可用';
					}
					case -1:{
						return '冻结';
					}
				}
				return value;
			}}
		]]
	});
</script>