<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec=“http://www.thymeleaf.org/thymeleaf-extras-springsecurity5”>
<head>
    <meta charset="UTF-8">
    <title>Sign in to Lemonade.</title>
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
    <link th:href="@{/static/css/custom/signin.css}" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin" th:action="@{/login}" method="post">
    <img class="mb-4" th:src="@{/static/image/icon.png}" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-italic">Sign in to Lemonade.</h1>
    <div th:if="${param.error}">
        <div class="alert alert-danger">Invalid username or
            password.</div>
    </div>
    <div th:if="${param.register}">
        <div class="alert alert-success">You have successfully registered.</div>
    </div>
    <label for="inputEmail" class="sr-only">Account</label>
    <div class="input-group">
        <input type="email" id="inputEmail" name="account" th:name="account" class="form-control" placeholder="Account" required autofocus>
        <div class="invalid-feedback" style="width: 100%;">
            Please input your account.
        </div>
    </div>
    <label for="inputPassword" class="sr-only">Password</label>
    <div class="input-group">
        <input type="password" id="inputPassword" name="password" th:name="password" class="form-control" placeholder="Password" required>
        <div class="invalid-feedback" style="width: 100%;">
            The password cannot be null.
        </div>
    </div>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" name="remember" th:name="remember_me" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="text-muted mt-3 font-weight-normal">New to Lemonade.? <a th:href="@{/register}">Create an account.</a></p>
    <p class="mt-5 mb-3 text-muted">&copy; 2022-2023</p>
</form>
</body>
</html>