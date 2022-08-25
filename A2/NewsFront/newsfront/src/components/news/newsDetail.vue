<template>
    <div>
        <Navbar></Navbar>
        <main role="main" class="container">
            <div class="col-md-10 offset-md-1 blog-main pt-5">
                <div class="blog-post">
                    <h2 class="col-md-10 offset-md-1 blog-post-title text-center" th:text="${news.getTitle()}">Title
                    </h2>
                    <h5 class="font-italic text-center"><a class="blog-post-meta"
                            @click="clickMenuWithId('/news/category/',category.id)">{{category.categoryName}}</a></h5>
                    <p class="blog-post-meta text-center"><a>{{ dateFormat(news.postTime) }}</a> by
                        <a class="blog-post-meta">{{provider.name}}</a>
                    </p>
                    <hr>
                    <div class="blog-pagination" style="word-break:break-all;word-wrap:break-word"
                        v-html="checkLine(news.content)"></div>
                </div><!-- /.blog-post -->
                <div class="blog-post-meta">
                    <hr>
                    <h4 class="mb-3">Comments</h4>
                    <div class="all-news" v-for="comment in comments">
                        <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm position-relative"
                            th:if="!${session.user} or ${comment.getUserId()} != ${session.user.getId()}">
                            <div class="col p-4 d-flex flex-column position-static">
                                <div class="mb-1 text-muted">{{dateFormat(comment.commitTime)}}</div>
                                <p class="card-text mb-auto pr-5" v-html="checkLine(comment.content)"></p>
                            </div>
                        </div>
                    </div>
                    <hr>
                </div>
                <form th:action="@{/user/addComment/}+${news.getId()}" method="post" sec:authorize="isAuthenticated()">
                    <div class="row-cols-1">
                        <div class="mb-3">
                            <label for="description">Your Comment</label>
                            <textarea class="form-control" type="text" id="description" th:name="content" name="content"
                                placeholder="Add anything you want to talk about" rows="4" th:utext="${yourComment}"
                                th:if="${yourComment}"></textarea>
                            <div class="invalid-feedback" style="width: 100%;">
                                The content is required.
                            </div>
                        </div>
                        <div class="btn-group">
                            <a class="btn btn-sm btn-outline-secondary"
                                th:href="@{/user/deleteComment/}+${news.getId()}" th:if="${yourComment}">Delete</a>
                            <button class="btn btn-sm btn-outline-secondary"
                                th:if="!${yourComment} and ${session.user.getBanned()} == 1" type="submit">Add</button>
                        </div>

                    </div>
                </form>
            </div><!-- /.blog-main -->
        </main><!-- /.container -->
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
    name: "NewsDetail",
    components: { Footer, Navbar },
    data() {
        return {
            news: [],
            category: [],
            provider:[],
            categories:[],
            comments:[]
        }
    },
    created() {
        let id = this.$route.params.id;
        axios.get('/news/getNewsDetailById/' + id).then(res => {
            this.news = res.data.data.news;
            this.category = res.data.data.category;
            this.provider = res.data.data.provider;
            this.comments = res.data.data.comments;
            console.log(res.data.data.news);
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
            // 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
            let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            // 拼接
            return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
        },
        checkLine(content){
            var data = new String(content);
            console.log(data);
            data = data.replace(/\r\n/g, '<br/>')
            console.log(data);
            return data;
        }
    },
}
</script>