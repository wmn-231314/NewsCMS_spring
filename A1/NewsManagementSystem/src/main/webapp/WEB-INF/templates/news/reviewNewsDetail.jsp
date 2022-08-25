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
    <link th:href="@{/static/css/custom/form-validation.css}" rel="stylesheet">
</head>
<body>
<!-- Header -->
<div th:replace="fragments/navbar :: navbar" id="top"></div>
<!-- Header -->
<main role="main" class="container">
    <div class="row">
        <div class="col-md-8 blog-main pt-5">
            <div class="blog-post">
                <h2 class="col-md-10 offset-md-1 blog-post-title text-center" th:text="${news.getTitle()}">Title</h2>
                <h5 class="font-italic text-center"><a class="blog-post-meta" th:text="${category.getCategoryName()}">Others</a></h5>
                <p class="blog-post-meta text-center"><a th:text="${news.getPostTime()}">December 14, 2020 </a> by <a class="blog-post-meta" href="#" th:text="${provider.getName()}">Chris</a></p>
                <hr>
                <div class="blog-pagination" style="word-break:break-all;word-wrap:break-word;" th:utext="${#strings.unescapeJava(#strings.replace(#strings.escapeJava(news.getContent()),'\n','&lt;br/&gt;'))}">Main part</div>
            </div><!-- /.blog-post -->
        </div><!-- /.blog-main -->
        <aside class="col-md-4 blog-sidebar pt-lg-5">
            <div class="p-4 mb-3 bg-light rounded">
                <h4 class="font-italic">Description</h4>
                <p class="mb-0" th:text="${#strings.unescapeJava(#strings.replace(#strings.escapeJava(description),'\n','&lt;br/&gt;'))}">Nothing.</p>
            </div>
            <form th:action="@{/review/reviewRequest/}+${news.getId()}" method="post">
                <div class="mb-3">
                    <label for="content" class="h4 font-italic">Feedback</label>
                    <textarea class="form-control" type="text" id="content" th:name="content" name="content" placeholder="Please input something..." rows="10" required></textarea>
                    <div class="invalid-feedback" style="width: 100%;">
                        The content is required.
                    </div>
                </div>
                <div class="btn-group">
                    <a th:href="@{/review/reviewApprove/}+${news.getId()}" type="button" class="btn btn-sm btn-outline-secondary">Approve</a>
                    <button type="submit" class="btn btn-sm btn-outline-secondary">Need change</button>
                </div>
            </form>
        </aside><!-- /.blog-sidebar -->
    </div>
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
