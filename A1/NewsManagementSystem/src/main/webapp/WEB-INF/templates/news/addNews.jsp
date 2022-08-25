<!doctype html>
<html lang="en">
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <title>Creating News</title>
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
<body class="bg-light">
<!-- Header -->
<div th:replace="fragments/navbar :: navbar" id="top"></div>

<div class="container checkout-container">
    <form th:action="@{addVerify}" method="post" th:object="${newsParam}">
        <h4 class="mb-3">Creating new article</h4>
        <div class="row-cols-1">
            <div class="mb-3">
                <label for="title">Title</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="title" th:name="title" placeholder="Title" required>
                    <div class="invalid-feedback" style="width: 100%;">
                        The title is required.
                    </div>
                </div>
            </div>
            <div class="mb-3">
                <label for="category">Category</label>
                <select class="custom-select d-block w-100" id="category" name="reviewerId" th:name="categoryId" required>
                    <option value="">Choose...</option>
                    <div th:each="category:${categories}">
                        <option th:text="${category.getCategoryName()}" th:value="${category.getId()}">Others</option>
                    </div>
                </select>
                <div class="invalid-feedback">
                    Please select a valid category.
                </div>
            </div>
            <div class="mb-3">
                <label for="reviewer">Reviewer</label>
                <select class="custom-select d-block w-100" id="reviewer" name="reviewerId" th:name="reviewerId" required>
                    <option value="">Choose...</option>
                    <div th:each="reviewer:${reviewers}">
                        <option th:text="${reviewer.getName()}+' '+${reviewer.getAccount()}" th:value="${reviewer.getId()}" th:if="${reviewer.getId()} != ${session.user.getId()}">Others</option>
                    </div>
                </select>
                <div class="invalid-feedback">
                    Please select a valid reviewer.
                </div>
            </div>
            <div class="mb-3">
                <label for="content">Content</label>
                <textarea class="form-control" type="text" id="content" th:name="content" placeholder="Please input something..." rows="10" required></textarea>
                <div class="invalid-feedback" style="width: 100%;">
                    The content is required.
                </div>
            </div>
            <div class="mb-3">
                <label for="description">Review description</label>
                <textarea class="form-control" type="text" id="description" th:name="committerDescription" placeholder="Add an optional extended description" rows="4"></textarea>
                <div class="invalid-feedback" style="width: 100%;">
                    The content is required.
                </div>
            </div>
            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit">Commit News</button>
        </div>
    </form>
</div>


<footer class="footer blog-footer">
    <div th:replace="fragments/footer :: footer" />
</footer>

<script type="text/javascript" th:src="@{/static/js/jquery-3.2.1.slim.min.js}"></script>
<script type="text/javascript" th:src="@{/static/js/popper.min.js}"></script>
<script th:src="@{/static/js/bootstrap.min.js}"></script>
<script th:src="@{/static/js/bootstrap.bundle.min.js}"></script>

</body>
</html>
