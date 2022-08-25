<!doctype html>
<html lang="en">
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head>
    <meta charset="UTF-8">
    <title>The Lemonade</title>
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

<div class="container">
    <header class="blog-header py-3" id="top">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1"></div>
            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark" th:href="@{/index}">Lemonade.</a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center navbar-expand-md">
                <button class="navbar-toggler text-muted" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle search">
                    <svg t="1653312065351" class="icon" width="30" height="30" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" p-id="2909" xmlns:xlink="http://www.w3.org/1999/xlink">
                        <defs>
                            <style type="text/css">@font-face { font-family: feedback-iconfont; src: url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff2?t=1630033759944") format("woff2"), url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.woff?t=1630033759944") format("woff"), url("//at.alicdn.com/t/font_1031158_u69w8yhxdu.ttf?t=1630033759944") format("truetype"); }</style>
                        </defs>
                        <title>Search</title>
                        <path d="M675.328 117.717333A425.429333 425.429333 0 0 0 512 85.333333C276.352 85.333333 85.333333 276.352 85.333333 512s191.018667 426.666667 426.666667 426.666667 426.666667-191.018667 426.666667-426.666667c0-56.746667-11.093333-112-32.384-163.328a21.333333 21.333333 0 0 0-39.402667 16.341333A382.762667 382.762667 0 0 1 896 512c0 212.074667-171.925333 384-384 384S128 724.074667 128 512 299.925333 128 512 128c51.114667 0 100.8 9.984 146.986667 29.12a21.333333 21.333333 0 0 0 16.341333-39.402667zM298.666667 554.666667a42.666667 42.666667 0 1 0 0-85.333334 42.666667 42.666667 0 0 0 0 85.333334z m213.333333 0a42.666667 42.666667 0 1 0 0-85.333334 42.666667 42.666667 0 0 0 0 85.333334z m213.333333 0a42.666667 42.666667 0 1 0 0-85.333334 42.666667 42.666667 0 0 0 0 85.333334z" fill="#3D3D3D" p-id="2910"></path>
                    </svg>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <form class="form-inline mt-2 mt-md-0" th:action="@{/news/searchNews}" method="post">
                        <input class="form-control m-auto" name="content" th:name="content" type="text" placeholder="Search" aria-label="Search">
                        <button class="btn text-muted" type="submit">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" class="mx-0" role="img" viewBox="0 0 24 24" focusable="false"><title>Search</title><circle cx="10.5" cy="10.5" r="7.5"/><path d="M21 21l-5.2-5.2"/></svg>
                        </button>
                    </form>
                </div>
                <a class="btn btn-sm btn-outline-secondary" th:href="@{/login}" sec:authorize="!isAuthenticated()">Sign in</a>
                <div class="dropdown" sec:authorize="isAuthenticated()">
                    <a class="text-muted dropdown-toggle" id="dropdown01" data-toggle="dropdown" aria-expanded="false" th:text="${session.user.getName()}">UserName</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <a class="dropdown-item" th:href="@{/newsManage/add}" sec:authorize="hasAnyRole('EDITOR','REVIEWER')" >Write a news</a>
                        <a class="dropdown-item" th:href="@{/newsManage/manage}" sec:authorize="hasAnyRole('EDITOR','REVIEWER')" >News management</a>
                        <a class="dropdown-item" th:href="@{/review/manage}" sec:authorize="hasRole('REVIEWER')" >News review</a>
                        <a class="dropdown-item" th:href="@{/admin/index}" sec:authorize="hasRole('ADMIN')" >Admin system</a>
                        <a class="dropdown-divider"></a>
                        <a class="dropdown-item" th:href="@{/logout}">Sign Out</a>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="nav-scroller py-1 mb-2">
        <nav class="nav d-flex justify-content-between" th:each="category:${categories}">
            <li th:each="category:${categories}">
                <a class="p-2 text-muted" th:href="@{/news/findNewsByCategory/}+${category.getId()}" th:text="${category.getCategoryName()}">tttt</a>
            </li>
        </nav>
    </div>
    <div class="jumbotron p-4 p-md-5 text-white rounded bg-dark" th:style="'background-image: url(static/image/lemon.png)'">
        <div class="col-md-6 px-0" >
            <h1 class="display-4 font-italic">The world is in your lemonade.</h1>
            <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and efficiently about what’s most interesting in this post’s contents.</p>
            <p class="lead mb-0"><a href="@{/about}" class="text-white font-weight-bold">Continue reading...</a></p>
        </div>
    </div>

    <div class="col-md-4 py-2">
        <h1 class="display-4 font-weight-bold">Top Story</h1>
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
</div>

<footer class="blog-footer">
    <div th:replace="fragments/footer :: footer" />
</footer>

<script type="text/javascript" th:src="@{/static/js/jquery-3.2.1.slim.min.js}"></script>
<script type="text/javascript" th:src="@{/static/js/popper.min.js}"></script>
<script th:src="@{/static/js/bootstrap.min.js}"></script>
<script th:src="@{/static/js/bootstrap.bundle.min.js}"></script>

</body>
</html>
