<template>
    <div>
        <Navbar></Navbar>
        <div class="container checkout-container">
            <form th:action="@{/newsManage/editVerify/}+${news.getId()}" method="post" th:object="${newsParam}">
                <h4 class="mb-3">Editing your article</h4>
                <div class="row-cols-1">
                    <div class="mb-3">
                        <label for="title">Title</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="title" th:name="title" placeholder="Title"
                                required th:value="${news.getTitle()}">
                            <div class="invalid-feedback" style="width: 100%;">
                                The title is required.
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="category">Category</label>
                        <select class="custom-select d-block w-100" id="category" th:name="categoryId" required>
                            <option value="">Choose...</option>
                            <div th:each="category:${categories}">
                                <option th:unless="${category.getId()} == ${news.getCategoryId()}"
                                    th:text="${category.getCategoryName()}" th:value="${category.getId()}">Others
                                </option>
                                <option th:if="${category.getId()} == ${news.getCategoryId()}"
                                    th:text="${category.getCategoryName()}" th:value="${category.getId()}" selected>
                                    Others
                                </option>
                            </div>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid category.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="reviewer">Reviewer</label>
                        <select class="custom-select d-block w-100" id="reviewer" name="reviewerId" th:name="reviewerId"
                            required>
                            <option value="">Choose...</option>
                            <div th:each="reviewer:${reviewers}">
                                <option th:text="${reviewer.getName()}+' '+${reviewer.getAccount()}"
                                    th:value="${reviewer.getId()}"
                                    th:if="${reviewer.getId()} != ${session.user.getId()} and ${reviewer.getId()} != ${news.getReviewerId()}">
                                    Others</option>
                                <option th:text="${reviewer.getName()}+' '+${reviewer.getAccount()}"
                                    th:value="${reviewer.getId()}"
                                    th:if="${reviewer.getId()} != ${session.user.getId()} and ${reviewer.getId()} == ${news.getReviewerId()}"
                                    selected>Others</option>
                            </div>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid reviewer.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="content">Content</label>
                        <textarea class="form-control" type="text" id="content" th:name="content"
                            placeholder="Please input something..." rows="10" required
                            th:utext="${news.getContent()}"></textarea>
                        <div class="invalid-feedback" style="width: 100%;">
                            The content is required.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="description">Review description</label>
                        <textarea class="form-control" type="text" id="description" th:name="committerDescription"
                            placeholder="Add an optional extended description" rows="4"></textarea>
                        <div class="invalid-feedback" style="width: 100%;">
                            The content is required.
                        </div>
                    </div>
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Release News</button>
                </div>
            </form>
        </div>
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