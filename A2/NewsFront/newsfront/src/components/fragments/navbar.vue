<template>
    <!-- Fixed navbar -->
    <div class="double-nav" th:fragment="navbar">
        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" th:href="@{/index}">Lemonade.</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </nav>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <a class="navbar-brand" @click="clickMenu('index')">Lemonade.</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" @click="clickMenu('index')">Home</a>
                    </li>
                    <div class="check-categories" v-for="category in categories">
                        <li class="nav-item">
                            <a class="nav-link"
                                @click="clickMenuWithId('/news/category/',category.id)">{{category.categoryName}}</a>
                        </li>
                    </div>

                </ul>
                <form class="form-inline mt-2 mt-md-0" th:action="@{/news/searchNews}" method="post">
                    <input class="form-control mr-sm-2" ref="content" type="text" placeholder="Search"
                        aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" @click="searchNews()">Search</button>
                </form>
                <a class="btn btn-outline-light ml-4 my-2 my-sm-0" @click="clickMenu('login')"
                    v-if="!(username !== null && username !== undefined && username !== '')">Sign in</a>
                <ul class="navbar-nav pull-right">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle pull-right" href="#" id="dropdown01" data-toggle="dropdown"
                            v-if="username !== null && username !== undefined && username !== ''"
                            aria-expanded="false">{{username}}</a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdown01">
                            <a class="dropdown-item" @click="clickMenu('addNews')"
                                v-if="role == 'ROLE_EDITOR' || role == 'ROLE_REVIEWER'">Write a news</a>
                            <a class="dropdown-item" @click="clickMenu('manageNews')"
                                v-if="role == 'ROLE_EDITOR' || role == 'ROLE_REVIEWER'">News management</a>
                            <a class="dropdown-item" @click="clickMenu('reviewNews')"
                                v-if="role == 'ROLE_REVIEWER'">News review</a>
                            <a class="dropdown-item" @click="clickMenu('adminIndex')" v-if="role == 'ROLE_ADMIN'">Admin
                                system</a>
                            <a class="dropdown-divider"></a>
                            <a class="dropdown-item" @click="logout()">Sign Out</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>

<style scoped>
@import '../../../static/css/bootstrap.min.css';
@import '../../../static/css/custom/blog.css'
</style>

<script>
import axios from 'axios';

export default {
    name: "navbar",
    data(){
        return{
            categories:[],
            username: "",
            role: ""
        }
    },
    created(){
        axios.get('/news/getCategory').then(res => {
            this.categories = res.data.data.categories;
            console.log(this.categories);
        }).catch (function (error){
            console.log(error);
        });
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
        searchNews() {
            this.$router.push({
                name: 'searchNews',
                params: { content: this.$refs.content.value }
            })
        },
        logout() {
            window.localStorage.clear();
            this.$router.push({ name: 'index' });
        }
    },
}
</script>