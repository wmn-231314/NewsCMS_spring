<template>
    <div>
        <Navbar></Navbar>
        <main role="main" class="container">
            <div class="col-md-4 py-2">
                <h1 class="display-4 font-weight-bold">{{curCategory.categoryName}}</h1>
            </div>
            <hr>
            <div class="all-news" v-for="news in newsList">
                <div class="col-md-12">
                    <div
                        class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary">{{curCategory.categoryName}}</strong>
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
        </main>
        <Footer></Footer>
    </div>
</template>

<style scoped>
@import '../../../static/css/bootstrap.min.css';
@import '../../../static/css/custom/blog.css'
</style>

<script>
import axios from 'axios';
import '../../../static/js/bootstrap';
import '../../../static/js/bootstrap.bundle';
import Footer from '../fragments/footer'
import Navbar from '../fragments/navbar'

export default {
    name: "Index",
    components: { Footer, Navbar },
    data() {
        return {
            newsList: [],
            curCategory: []
        }
    },
    created() {
        let id = this.$route.params.id;
        axios.get('/news/findNewsByCategory/'+id).then(res => {
            this.newsList = res.data.data.newsList;
            this.curCategory = res.data.data.curCategory;
            console.log(res.data.msg);
        }).catch(function (error) {
            console.log(error);
        });
    },
    updated(){
        let id = this.$route.params.id;
        axios.get('/news/findNewsByCategory/' + id).then(res => {
            this.newsList = res.data.data.newsList;
            this.curCategory = res.data.data.curCategory;
            console.log(res.data.msg);
        }).catch(function (error) {
            console.log(error);
        });
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
        dateFormat(time) {
            let date = new Date(time);
            let year = date.getFullYear();
            // ?????????????????????????????????0????????????????????????0?????????????????????????????????10????????????0????????????????????????  ??? 09:11:05
            let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            // ??????
            return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
            //return year + "-" + month + "-" + day;
        },
        abstract(content) {
            let end = 100;
            let str_content = new String(content);
            if (str_content.length < 100) {
                end = str_content.length;
            }
            return str_content.substring(0, end) + "...";
        }
    },
}
</script>