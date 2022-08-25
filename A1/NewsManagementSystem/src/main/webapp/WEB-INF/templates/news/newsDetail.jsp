<!doctype html>
<html lang="en">
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <title th:text="${news.getTitle()}">The Lemonade</title>
    <link th:fragment="favicon" rel="shortcut icon" th:href="@{/static/favico.ico}">


    <!-- Bootstrap core CSS -->
    <link th:href="@{/static/css/bootstrap.min.css}" rel="stylesheet">



    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link th:href="@{/static/css/custom/blog.css}" rel="stylesheet">
</head>
<body>
<!-- Header -->
<div th:replace="fragments/navbar :: navbar" id="top"></div>
<!-- Header -->
<main role="main" class="container">
    <div class="col-md-10 offset-md-1 blog-main pt-5">
        <div class="blog-post">
            <h2 class="col-md-10 offset-md-1 blog-post-title text-center" th:text="${news.getTitle()}">Title</h2>
            <h5 class="font-italic text-center"><a class="blog-post-meta" th:href="@{/news/findNewsByCategory/}+${category.getId()}" th:text="${category.getCategoryName()}">Others</a></h5>
            <p class="blog-post-meta text-center"><a th:text="${news.getPostTime()}">December 14, 2020 </a> by <a class="blog-post-meta" href="#" th:text="${provider.getName()}">Chris</a></p>
            <hr>
            <div class="blog-pagination" style="word-break:break-all;word-wrap:break-word;" th:utext="${#strings.unescapeJava(#strings.replace(#strings.escapeJava(news.getContent()),'\n','&lt;br/&gt;'))}">Main part</div>
        </div><!-- /.blog-post -->
        <div class="blog-post-meta">
            <hr>
            <h4 class="mb-3">Comments</h4>
            <div class="all-news" th:each="comment:${comments}">
                <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm position-relative" th:if="!${session.user} or ${comment.getUserId()} != ${session.user.getId()}">
                    <div class="col p-4 d-flex flex-column position-static">
                        <div class="mb-1 text-muted" th:text="${comment.getCommitTime()}">Unknown</div>
                        <p class="card-text mb-auto pr-5" th:text="${comment.getContent()}">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
                    </div>
                </div>
            </div>
            <hr>
        </div>
        <form th:action="@{/user/addComment/}+${news.getId()}" method="post" sec:authorize="isAuthenticated()" >
            <div class="row-cols-1">
                <div class="mb-3">
                    <label for="description">Your Comment</label>
                    <textarea class="form-control" type="text" id="description" th:name="content" name="content"
                              placeholder="Add anything you want to talk about" rows="4" th:utext="${yourComment}" th:if="${yourComment}" disabled></textarea>
                    <textarea class="form-control" type="text" id="description" th:name="content" name="content"
                              placeholder="Add anything you want to talk about" rows="4" th:if="!${yourComment} and ${session.user.getBanned()} == 1"></textarea>
                    <textarea class="form-control" type="text" id="description" th:name="content" name="content"
                              placeholder="You are forbidden to comment. Please wait for the administrator to cancel." rows="4" th:if="!${yourComment} and ${session.user.getBanned()} == 2" disabled></textarea>
                    <div class="invalid-feedback" style="width: 100%;">
                        The content is required.
                    </div>
                </div>
                <div class="btn-group">
                    <a class="btn btn-sm btn-outline-secondary" th:href="@{/user/deleteComment/}+${news.getId()}" th:if="${yourComment}">Delete</a>
                    <button class="btn btn-sm btn-outline-secondary" th:if="!${yourComment} and ${session.user.getBanned()} == 1" type="submit">Add</button>
                </div>

            </div>
        </form>
    </div><!-- /.blog-main -->

</main><!-- /.container -->


<footer class="footer blog-footer">
    <div th:replace="fragments/footer :: footer" />
</footer>

<script type="text/javascript" th:src="@{/static/js/jquery-3.2.1.slim.min.js}"></script>
<script type="text/javascript" th:src="@{/static/js/popper.min.js}"></script>
<script th:src="@{/static/js/bootstrap.min.js}"></script>
<script th:src="@{/static/js/bootstrap.bundle.min.js}"></script>

</body>
</html>
