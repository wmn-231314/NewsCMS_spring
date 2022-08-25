<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<body>
<div class="layui-header" th:fragment="adminHeader">
    <div class="layui-logo layui-hide-xs layui-bg-black">Lemonade.</div>
    <!-- 头部区域（可配合layui 已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
        <!-- 移动端显示 -->
        <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
            <i class="layui-icon layui-icon-spread-left"></i>
        </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item layui-hide layui-show-md-inline-block">
            <a href="javascript:;" th:text="${session.user.getName()}">
                UserName
            </a>
            <dl class="layui-nav-child">
                <dd><a th:href="@{/index}">Back to Front</a></dd>
                <dd><a href="#">Settings</a></dd>
                <dd><a th:href="@{/logout}">Sign out</a></dd>
            </dl>
        </li>
    </ul>
</div>
</body>
</html>