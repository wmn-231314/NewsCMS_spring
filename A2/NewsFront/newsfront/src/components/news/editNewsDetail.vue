<template>
    <div>
        <Navbar></Navbar>
        <main role="main" class="container">
            <div class="row">
                <div class="col-md-8 blog-main pt-5">
                    <div class="blog-post">
                        <h2 class="col-md-10 offset-md-1 blog-post-title text-center" th:text="${news.getTitle()}">Title
                        </h2>
                        <h5 class="font-italic text-center"><a class="blog-post-meta"
                                th:text="${category.getCategoryName()}">Others</a></h5>
                        <p class="blog-post-meta text-center"><a th:text="${news.getPostTime()}">December 14, 2020 </a>
                            by
                            <a class="blog-post-meta" href="#" th:text="${provider.getName()}">Chris</a>
                        </p>
                        <hr>
                        <div class="blog-pagination" style="word-break:break-all;word-wrap:break-word;"
                            th:utext="${#strings.unescapeJava(#strings.replace(#strings.escapeJava(news.getContent()),'\n','&lt;br/&gt;'))}">
                            Main part</div>
                    </div><!-- /.blog-post -->
                </div><!-- /.blog-main -->
                <aside class="col-md-4 blog-sidebar pt-lg-5">
                    <div class="p-4 mb-3 bg-light rounded">
                        <h4 class="font-italic">Feedback</h4>
                        <p class="mb-0" th:if="${feedback} == null">Nothing.</p>
                        <p class="mb-0" th:unless="${feedback} == null"
                            th:text="${#strings.unescapeJava(#strings.replace(#strings.escapeJava(feedback),'\n','&lt;br/&gt;'))}">
                            Nothing.</p>
                    </div>
                </aside><!-- /.blog-sidebar -->
            </div>
        </main><!-- /.container -->
        <Footer></Footer>
    </div>
</template>

<style scoped>

@import '../../../static/css/bootstrap.min.css';
@import '../../../static/css/custom/blog.css';
@import '../../../static/css/custom/form-validation.css'
</style>

<script>
import '../../../static/js/bootstrap';
import '../../../static/js/bootstrap.bundle';
import Footer from '../fragments/footer'
import Navbar from '../fragments/navbar'

export default {
    name: "Index",
    components: { Footer, Navbar },
}
</script>