<template>
    <div>
        <div class="container">
            <header class="blog-header py-3" id="top">
                <div class="row flex-nowrap justify-content-between align-items-center">
                    <div class="col-4 pt-1"></div>
                    <div class="col-4 text-center">
                        <a class="blog-header-logo text-dark" @click="clickMenu('index')">
                            Lemonade.
                        </a>
                    </div>
                    <div class="col-4 d-flex justify-content-end align-items-center navbar-expand-md">
                        <button class="navbar-toggler text-muted" type="button" data-toggle="collapse"
                            data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false"
                            aria-label="Toggle search">
                            <svg t="1653312065351" class="icon" width="30" height="30" viewBox="0 0 1024 1024"
                                xmlns="http://www.w3.org/2000/svg" p-id="2909"
                                xmlns:xlink="http://www.w3.org/1999/xlink">
                                <title>Search</title>
                                <path
                                    d="M675.328 117.717333A425.429333 425.429333 0 0 0 512 85.333333C276.352 85.333333 85.333333 276.352 85.333333 512s191.018667 426.666667 426.666667 426.666667 426.666667-191.018667 426.666667-426.666667c0-56.746667-11.093333-112-32.384-163.328a21.333333 21.333333 0 0 0-39.402667 16.341333A382.762667 382.762667 0 0 1 896 512c0 212.074667-171.925333 384-384 384S128 724.074667 128 512 299.925333 128 512 128c51.114667 0 100.8 9.984 146.986667 29.12a21.333333 21.333333 0 0 0 16.341333-39.402667zM298.666667 554.666667a42.666667 42.666667 0 1 0 0-85.333334 42.666667 42.666667 0 0 0 0 85.333334z m213.333333 0a42.666667 42.666667 0 1 0 0-85.333334 42.666667 42.666667 0 0 0 0 85.333334z m213.333333 0a42.666667 42.666667 0 1 0 0-85.333334 42.666667 42.666667 0 0 0 0 85.333334z"
                                    fill="#3D3D3D" p-id="2910"></path>
                            </svg>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarCollapse">
                            <div class="form-inline mt-2 mt-md-0">
                                <input class="form-control m-auto" ref="content" type="text" placeholder="Search"
                                    aria-label="Search">
                                <button class="btn text-muted" @click="searchNews()">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none"
                                        stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                        stroke-width="2" class="mx-0" role="img" viewBox="0 0 24 24" focusable="false">
                                        <title>Search</title>
                                        <circle cx="10.5" cy="10.5" r="7.5" />
                                        <path d="M21 21l-5.2-5.2" />
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <a class="btn btn-sm btn-outline-secondary" @click="clickMenu('login')"
                            v-if="!(username !== null && username !== undefined && username !== '')">Sign in</a>
                        <div class="dropdown">
                            <a class="text-muted dropdown-toggle" id="dropdown01" data-toggle="dropdown"
                                v-if="username !== null && username !== undefined && username !== ''"
                                aria-expanded="false">{{username}}</a>
                            <div class="dropdown-menu" aria-labelledby="dropdown01">
                                <a class="dropdown-item" @click="clickMenu('addNews')"
                                    v-if="role == 'ROLE_EDITOR' || role == 'ROLE_REVIEWER'">Write a news</a>
                                <a class="dropdown-item" @click="clickMenu('manageNews')"
                                    v-if="role == 'ROLE_EDITOR' || role == 'ROLE_REVIEWER'">News management</a>
                                <a class="dropdown-item" @click="clickMenu('reviewNews')"
                                    v-if="role == 'ROLE_REVIEWER'">News review</a>
                                <a class="dropdown-item" @click="clickMenu('adminIndex')"
                                    v-if="role == 'ROLE_ADMIN'">Admin
                                    system</a>
                                <a class="dropdown-divider"></a>
                                <a class="dropdown-item" @click="logout()">Sign Out</a>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <li v-for="category in categories">
                        <a class="p-2 text-muted"
                            @click="clickMenuWithId('/news/category/',category.id)">{{category.categoryName}}</a>
                    </li>
                </nav>
            </div>
            <div class="jumbotron p-4 p-md-5 text-white rounded bg-dark lemonade">
                <div class="col-md-6 px-0">
                    <h1 class="display-4 font-italic">The world is in your lemonade.</h1>
                    <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and
                        efficiently about what’s most interesting in this post’s contents.</p>
                    <p class="lead mb-0"><a href="@{/about}" class="text-white font-weight-bold">Continue reading...</a>
                    </p>
                </div>
            </div>

            <div class="col-md-4 py-2">
                <h1 class="display-4 font-weight-bold">Top Story</h1>
            </div>
            <div class="all-news" v-for="news in newsList">
                <div class="col-md-12">
                    <div
                        class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary" v-for="category in categories"
                                v-if="news.categoryId == category.id">{{category.categoryName}}</strong>
                            <h3 class="mb-0">{{news.title}}</h3>
                            <div class="mb-1 text-muted">{{ dateFormat(news.postTime) }}</div>
                            <p class="card-text mb-auto pr-5" th:text="${#strings.abbreviate(news.getContent(),100)}">
                                {{ abstract(news.content)}}
                            </p>
                            <a @click="clickMenuWithId('/news/getNewsDetail/',news.id)" class="stretched-link">Continue
                                reading</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Footer></Footer>
    </div>
</template>

<style scoped>

@import '../../static/css/bootstrap.min.css';
@import '../../static/css/custom/blog.css';

.lemonade{
    background-image: url("../../static/image/lemon.png");
};
</style>

<script>
import axios from 'axios';
import '../../static/js/bootstrap';
import '../../static/js/bootstrap.bundle';
import Footer from '../components/fragments/footer';

export default {
    name: "index",
    components: { Footer },
    data(){
        return{
            newsList: [],
            categories:[],
            username:"",
            role:""
        }
    },
    created(){
        axios.get('/index').then(res => {
            this.newsList = res.data.data.newsList;
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
        clickMenuWithId(path,id){
            this.$router.push({
                path:path+id,
            });
        },
        searchNews(){
            this.$router.push({
                name:'searchNews',
                params:{content:this.$refs.content.value}
            })
        },
        dateFormat(time) {
            let date = new Date(time);
            let year = date.getFullYear();
            // 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
            let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            // 拼接
            return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
            //return year + "-" + month + "-" + day;
        },
        abstract(content){
            let end = 100;
            let str_content = new String(content);
            if(str_content.length < 100){
                end = str_content.length;
            }
            return str_content.substring(0,end)+"...";
        },
        logout(){
            window.localStorage.clear();
            location.reload();
        }
    },
}
</script>