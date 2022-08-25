<template>
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
                <a href="javascript:;">
                    {{username}}
                </a>
                <dl class="layui-nav-child">
                    <dd><a @click="clickMenu('index')">Back to Front</a></dd>
                    <dd><a href="#">Settings</a></dd>
                    <dd><a @click="logout()">Sign out</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</template>

<style scoped>
@import '../../../static/layui/css/layui.css'
</style>

<script>
import axios from 'axios';
import '../../../static/layui/layui';
export default {
    name: "adminIndex",
    components: {  },
    data() {
        return {
            username: "",
            role: ""
        }
    },
    created(){
        this.role = window.localStorage.getItem("role");
        this.username = window.localStorage.getItem("userName");
    },
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
</script>