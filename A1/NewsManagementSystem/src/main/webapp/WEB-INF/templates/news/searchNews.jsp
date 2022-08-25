<!doctype html>
<html lang="en">
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <title th:text="'Search·'+${searchString}">The Lemonade</title>
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
    <link th:href="@{../static/css/custom/blog.css}" rel="stylesheet">
</head>
<body>
<!-- Header -->
<div th:replace="fragments/navbar :: navbar" id="top"></div>
<!-- Header -->
<main role="main" class="container">
    <div class="col-md-4 py-2">
        <h1 class="display-4 font-weight-bold" th:text="'Result:'+${content}">NULL</h1>
    </div>
    <hr>
    <div class="blog-post-meta" th:if="${newsList.size()} == 0">
        <h4 class="mb-3 blog-post-meta" >There isn't anything you can find......</h4>
        <h4 class="mb-3 blog-post-meta" >Try to search something else.</h4>
    </div>

    <div class="all-news" th:each="news:${newsList}">
        <div class="col-md-12">
            <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                    <strong class="d-inline-block mb-2 text-primary" th:each="category:${categories}" th:if="${news.getCategoryId()}==${category.getId()}" th:text="${category.getCategoryName()}">Others</strong>
                    <h3 class="mb-0" th:text="${news.getTitle()}">tttt</h3>
                    <div class="mb-1 text-muted" th:text="${news.getPostTime()}">Nov 12</div>
                    <p class="card-text mb-auto pr-5" th:text="${#strings.abbreviate(news.getContent(),100)}">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
                    <a th:href="@{/news/getNewsDetailById/}+${news.getId()}" class="stretched-link">Continue reading</a>
                </div>
            </div>
        </div>
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
