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
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">账号管理</a>
                    <dl class="layui-nav-child">
                        <dd><a th:href="@{adminInfo}">管理员管理</a></dd>
                        <dd><a th:href="@{userInfo}" >用户管理</a></dd>
                        <dd><a th:href="@{editorInfo}">编辑管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a th:href="@{categoryInfo}">分类管理</a></li>
                <li class="layui-nav-item"><a href="">系统日志</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <br><br>
            <blockquote class="layui-elem-quote layui-text">
                <ul>
                    <li>
                        欢迎进入后台管理系统
                    </li>
                    <li>
                        此系统有以下功能：账号管理，分类管理，系统日志
                    </li>
                    <li>
                        账号管理中，管理员可以查看所有用户账号，并注销某些长时间未登录或出现非法行为的用户
                    </li>
                </ul>
            </blockquote>
            <br><br>
        </div>
    </div>


    <footer class="footer layui-footer">
        <div th:replace="fragments/footer :: footer" />
    </footer>
</div>

<script th:src="@{/static/layui/layui.js}"></script>

<script>
    layui.use(['element', 'layer', 'util'], function(){
        var element = layui.element
            ,layer = layui.layer
            ,util = layui.util
            ,$ = layui.$;

        //头部事件
        util.event('lay-header-event', {
            //左侧菜单事件
            menuLeft: function(othis){
                layer.msg('展开左侧菜单的操作', {icon: 0});
            }
        });

    });
</script>

</body>
</html>