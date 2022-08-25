<template>
    <div class="layui-layout layui-layout-admin">
        <AdminHeader></AdminHeader>
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">账号管理</a>
                        <dl class="layui-nav-child">
                            <dd><a >管理员管理</a></dd>
                            <dd><a >用户管理</a></dd>
                            <dd><a >编辑管理</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a >分类管理</a></li>
                    <li class="layui-nav-item"><a >系统日志</a></li>
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
        <AdminFooter></AdminFooter>
    </div>
</template>

<style scoped>
@import '../../../static/layui/css/layui.css';
</style>

<script>
import axios from 'axios';
import '../../../static/layui/layui';
import AdminFooter from '../fragments/adminFooter'
import AdminHeader from '../fragments/adminHeader'

export default {
    name: "Index",
    components: { AdminFooter, AdminHeader },
    methods: {
        clickMenu(item) {
            this.$router.push({ name: item });
        },
        clickMenuWithId(path, id) {
            this.$router.push({
                path: path + id,
            });
        },
        logout() {
            window.localStorage.clear();
            this.$router.push({ name: 'index' });
        }
    },
}

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