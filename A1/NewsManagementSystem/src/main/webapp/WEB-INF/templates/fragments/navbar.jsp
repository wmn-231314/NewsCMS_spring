<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<body>
<!-- Fixed navbar -->
<div class="double-nav" th:fragment="navbar">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
        <a class="navbar-brand" th:href="@{/index}">Lemonade.</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" th:href="@{/index}">Lemonade.</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" th:href="@{/index}">Home</a>
                </li>
                <div class="check-categories" th:each="category:${categories}">
                    <li class="nav-item active" th:if="${curCategory} != null and ${curCategory.getId()} == ${category.getId()}">
                        <a class="nav-link" th:href="@{/news/findNewsByCategory/}+${category.getId()}" th:text="${category.getCategoryName()}">tttt</a>
                    </li>
                    <li class="nav-item" th:unless="${curCategory} != null and ${curCategory.getId()} == ${category.getId()}">
                        <a class="nav-link" th:href="@{/news/findNewsByCategory/}+${category.getId()}" th:text="${category.getCategoryName()}">tttt</a>
                    </li>
                </div>

            </ul>
            <form class="form-inline mt-2 mt-md-0" th:action="@{/news/searchNews}" method="post">
                <input class="form-control mr-sm-2" name="content" th:name="content" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
            </form>
            <a class="btn btn-outline-light ml-4 my-2 my-sm-0" th:href="@{/login}" sec:authorize="!isAuthenticated()">Sign in</a>
            <ul class="navbar-nav pull-right" sec:authorize="isAuthenticated()">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle pull-right" href="#" id="dropdown01" data-toggle="dropdown" aria-expanded="false" th:text="${session.user.getName()}">UserName</a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdown01">
                        <a class="dropdown-item" th:href="@{/newsManage/add}" sec:authorize="hasAnyRole('EDITOR','REVIEWER')" >Write a news</a>
                        <a class="dropdown-item" th:href="@{/newsManage/manage}" sec:authorize="hasAnyRole('EDITOR','REVIEWER')" >News management</a>
                        <a class="dropdown-item" th:href="@{/review/manage}" sec:authorize="hasRole('REVIEWER')" >News review</a>
                        <a class="dropdown-item" th:href="@{/admin/index}" sec:authorize="hasRole('ADMIN')" >Admin system</a>
                        <a class="dropdown-divider"></a>
                        <a class="dropdown-item" th:href="@{/logout}">Sign Out</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
</body>
</html>