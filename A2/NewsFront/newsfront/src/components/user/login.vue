<template>
    <form class="form-signin" ref="loginForm" :model="loginForm" :rule="loginRule">
        <img class="mb-4" src="../../../static/image/icon.png" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-italic">Sign in to Lemonade.</h1>
        <div v-if="loginForm.popup == '1'">
            <div class="alert alert-danger">Invalid username or
                password.</div>
        </div>
        <div v-if="loginForm.popup == '2'">
            <div class="alert alert-success">You have successfully registered.</div>
        </div> 
        <label for="inputEmail" class="sr-only">Account</label>
        <div class="input-group">
            <input type="email" id="inputEmail" v-model="loginForm.account" class="form-control"
                placeholder="Account" required autofocus>
            <div class="invalid-feedback" style="width: 100%;">
                Please input your account.
            </div>
        </div>
        <label for="inputPassword" class="sr-only">Password</label>
        <div class="input-group">
            <input type="password" id="inputPassword" v-model="loginForm.password" class="form-control"
                placeholder="Password" required>
            <div class="invalid-feedback" style="width: 100%;">
                The password cannot be null.
            </div>
        </div>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" name="remember" th:name="remember_me" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" @click="loginVerify()">Sign in</button>
        <p class="text-muted mt-3 font-weight-normal">New to Lemonade.? <a @click="clickMenu('register')">Create an account.</a>
        </p>
        <p class="mt-5 mb-3 text-muted">&copy; 2022-2023</p>
    </form>
</template>

<style scoped>
@import '../../../static/css/bootstrap.min.css';
@import '../../../static/css/custom/signin.css';

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

<script>
import axios from 'axios';
export default {
    name: "login",
    components: { },
    data(){
        return{
            loginForm: {
                account: "",
                password: "",
                popup: ""
            },
            loginRule: {
                account: [{ required: true, message: 'Please input account', trigger: "blur"}],
                password: [{ required: true, message: 'Please input password', trigger: "blur" }]
            }
        }
    },
    created(){
        let success = this.$route.params.success;
        if(success == "success"){
            this.loginForm.popup = '2';
        }
    },
    methods: {
        clickMenu(item) {
            this.$router.push({ name: item });
        },
        loginVerify(){
            let errorhint = '0';
            if(this.loginForm.account == '' || this.loginForm.password == ''){
                return;
            }
            let param = {
                account: this.loginForm.account,
                password: this.loginForm.password
            };
            //axios.post('/foo', params);
            axios.post('/login', param).then(res => {
                this.loginForm.account = '';
                this.loginForm.password = '';
                console.log(res.data);
                if (res.data.code == '200') {
                    window.localStorage.setItem("token", res.data.data.token);
                    window.localStorage.setItem("account", res.data.data.account);
                    window.localStorage.setItem("userName", res.data.data.username);
                    window.localStorage.setItem("role", res.data.data.role);
                    this.$router.push({ name: 'index' });
                }else if(res.data.status == 401){
                    this.pwError();
                }
            }).catch(function (error) {
               console.log(error)
            });
            
        },
        pwError(){
            this.loginForm.popup = '1';
        }
    },
}
</script>