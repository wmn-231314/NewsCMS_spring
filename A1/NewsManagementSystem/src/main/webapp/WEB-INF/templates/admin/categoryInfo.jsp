<!DOCTYPE html>
<html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <title>The Lemonade</title>
    <link th:fragment="favicon" rel="shortcut icon" th:href="@{/static/favico.ico}">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 注意：项目正式环境请勿引用该地址 -->
    <link th:href="@{/static/layui/css/layui.css}" rel="stylesheet">
</head>
<body>

<div class="layui-layout layui-layout-admin">
    <div th:replace="fragments/adminHeader :: adminHeader" />

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">账号管理</a>
                    <dl class="layui-nav-child">
                        <dd><a th:href="@{adminInfo}">管理员管理</a></dd>
                        <dd><a th:href="@{userInfo}" >用户管理</a></dd>
                        <dd><a th:href="@{editorInfo}">编辑管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item  layui-nav-itemed" ><a th:href="@{categoryInfo}">分类管理</a></li>
                <li class="layui-nav-item"><a href="">系统日志</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <table class="layui-hide" id="categoryList" lay-filter="category"></table>
        </div>
    </div>


    <footer class="footer layui-footer">
        <div th:replace="fragments/footer :: footer" />
    </footer>
</div>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">删除选中</button>
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>



<!-- 注意：项目正式环境请勿引用该地址 -->
<script th:src="@{/static/layui/layui.js}"></script>

<script th:inline="none">
    layui.use(['form', 'layer', 'table'], function(){
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery,
            table = layui.table;

        var tableIns = table.render({
            elem: '#categoryList'
            ,method:'post'
            ,url:'getCategoryInfo'
            ,toolbar: '#toolbarDemo'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {type: 'checkbox'}
                ,{field:'id', title: 'ID', sort: true}
                ,{field:'categoryName',title: '名称'}
                ,{field:'operation',title:'操作',toolbar: '#barDemo'}
            ]]
            ,page: true
        });

        //工具栏事件
        table.on('toolbar(category)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
            };
        });

        //行工具事件
        table.on('tool(category)', function(obj){
            var data = obj.data;
            console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('确认删除', function(index){
                    $.post("delete/category",
                        {
                            "id": data.id
                        }, function (data) {
                            if(data==="success"){
                                parent.layer.msg('删除成功！', {icon: 1,time:1000,shade:0.2});
                                location.reload();
                            }else if(data === "origin"){
                                parent.layer.msg('请勿删除默认分类', {icon: 2,time:2000,shade:0.2});
                            }else{
                                parent.layer.msg('删除失败！', {icon: 2,time:2000,shade:0.2});
                            }
                        });
                    layer.close(index);
                    tableIns.reload();
                });
            }
        });
    });
</script>

</body>
</html>