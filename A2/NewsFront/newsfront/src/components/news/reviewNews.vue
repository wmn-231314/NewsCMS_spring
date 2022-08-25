<template>
    <div>
        <Navbar></Navbar>
        <main role="main" class="container">
            <div class="col-md-6 py-2">
                <h1 class="display-5 font-weight-bold">Reviewing list</h1>
            </div>
            <hr>
            <div class="blog-post-meta" th:if="${newsList.size()} == 0">
                <h4 class="mb-3 blog-post-meta">There isn't any work you need to do.</h4>
            </div>
            <div class="all-news" th:each="news:${newsList}">
                <div class="col-md-12">
                    <div
                        class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 text-primary" th:each="category:${categories}"
                                th:if="${news.getCategoryId()}==${category.getId()}"
                                th:text="${category.getCategoryName()}">Others</strong>
                            <h3 class="mb-0" th:text="${news.getTitle()}">tttt</h3>
                            <div class="mb-1 text-muted" th:text="${news.getPostTime()}">Nov 12</div>
                            <strong class="d-inline-block mb-2 text-secondary"
                                th:text="${news.getStatus()}">Modifying</strong>
                            <p class="card-text mb-auto pr-5" th:text="${#strings.abbreviate(news.getContent(),100)}">
                                This
                                is a wider card with supporting text below as a natural lead-in to additional content.
                            </p>
                            <a th:href="@{reviewNewsDetailById/}+${news.getId()}" class="stretched-link">View detail</a>
                        </div>
                    </div>
                </div>
            </div>
        </main><!-- /.container -->
        <Footer></Footer>
    </div>
</template>

<style scoped>

@import '../../../static/css/bootstrap.min.css';
@import '../../../static/css/custom/blog.css'
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