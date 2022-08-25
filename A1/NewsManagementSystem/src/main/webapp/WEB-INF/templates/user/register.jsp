<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<html xmlns:th="http://www.thymeleaf.org" xmlns:sec=“http://www.thymeleaf.org/thymeleaf-extras-springsecurity5”>
<head>
    <meta charset="UTF-8">
    <title>Join Lemonade · Lemonade.</title>
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
<form class="form-signin" th:action="@{/verify/register}" method="POST">
    <fieldset>
        <h1 class="h3 mb-3 font-italic">Welcome to Lemonade.</h1>
        <div th:if="${param.error}">
            <div class="alert alert-danger" th:text="${param.msg}">Unknown error.</div>
        </div>
        <div class="control-group">
            <!-- Account -->
            <label for="inputEmail" class="sr-only">Account</label>
            <div class="input-group">
                <input type="email" id="inputEmail" th:name="account" class="form-control" placeholder="Account" required autofocus>
                <div class="invalid-feedback" style="width: 100%;">
                    You account is required.
                </div>
            </div>
             <p class="text-muted">Please provide your E-mail</p>
        </div>

        <div class="control-group">
            <!-- UserName -->
            <label for="inputUsername" class="sr-only">Username</label>
            <div class="input-group">
                <input type="text" id="inputUsername" th:name="name" class="form-control" placeholder="Username" required autofocus>
                <div class="invalid-feedback" style="width: 100%;">
                    Your username is required.
                </div>
            </div>
            <p class="text-muted">Letters or numbers, without spaces</p>
        </div>

        <div class="control-group">
            <!-- Password -->
            <label for="inputPassword" class="sr-only">Password</label>
            <div class="input-group">
                <input type="password" id="inputPassword" th:name="password" class="form-control" placeholder="Password" required autofocus>
                <div class="invalid-feedback" style="width: 100%;">
                    The password is required.
                </div>
            </div>
            <p class="text-muted">Password should be at least 4 characters</p>
        </div>

        <div class="control-group">
            <!-- Password -->
            <label for="inputPasswordConfirm" class="sr-only">Password (Confirm)</label>
            <input type="password" id="inputPasswordConfirm" th:name="passwordConfirm" class="form-control" placeholder="PasswordAgain" required autofocus>
            <p class="text-muted">Please confirm password</p>
        </div>

        <div class="mb-3">
            <label for="permission" class="sr-only">Identity</label>
            <select class="custom-select d-block w-100" id="permission" th:name="permission" required>
                <option value="">Choose...</option>
                <option th:value="${'ROLE_MEMBER'}">Member</option>
                <option th:value="${'ROLE_EDITOR'}">Editor</option>
                <option th:value="${'ROLE_REVIEWER'}">Senior Editor</option>
            </select>
            <div class="invalid-feedback">
                Please select a valid identity.
            </div>
        </div>

        <!-- Button -->
        <div class="controls">
            <button class="btn btn-success" type="submit">Register</button>
        </div>
    </fieldset>
</form>
</body>
</html>