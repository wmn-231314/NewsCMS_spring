<template>
    <div class="bd-light">
        <Navbar></Navbar>
        <div class="container checkout-container">
            <form th:action="@{addVerify}" method="post" th:object="${newsParam}">
                <h4 class="mb-3">Creating new article</h4>
                <div class="row-cols-1">
                    <div class="mb-3">
                        <label for="title">Title</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="title" th:name="title" placeholder="Title"
                                required>
                            <div class="invalid-feedback" style="width: 100%;">
                                The title is required.
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="category">Category</label>
                        <select class="custom-select d-block w-100" id="category" name="reviewerId" th:name="categoryId"
                            required>
                            <option value="">Choose...</option>
                            <option v-for="category in categories" v-bind:value="category.id">
                                {{category.categoryName}}
                            </option>
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
                            <option v-for="reviewer in reviewers" v-bind:value="reviewer.id"
                                th:if="${reviewer.getId()} != ${session.user.getId()}">{{reviewer.name}} {{reviewer.account}}</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid reviewer.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="content">Content</label>
                        <textarea class="form-control" type="text" id="content" th:name="content"
                            placeholder="Please input something..." rows="10" required></textarea>
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
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Commit News</button>
                </div>
            </form>
        </div>
        <Footer></Footer>
    </div>
</template>

<style scoped>
@import '../../../static/css/bootstrap.min.css';
@import '../../../static/css/custom/form-validation.css';

@import '../../../static/css/custom/blog.css';

</style>

<script>
import axios from 'axios';
import '../../../static/js/bootstrap';
import '../../../static/js/bootstrap.bundle';
import Footer from '../fragments/footer'
import Navbar from '../fragments/navbar'

export default {
    name: "Index",
    components: { Footer, Navbar },
    data() {
        return {
            reviewers: [],
            categories: []
        }
    },
    created() {
        axios.get('/newsManage/add').then(res => {
            this.reviewers = res.data.data.reviewers;
            this.categories = res.data.data.categories;
            console.log(this.reviewers);
        }).catch(function (error) {
            console.log(error);
        });
    },
    methods: {
        clickMenu(item) {
            this.$router.push({ name: item });
        },
        clickMenuWithId(path, id) {
            this.$router.push({
                path: path + id,
            });
        }
    },
}
</script>