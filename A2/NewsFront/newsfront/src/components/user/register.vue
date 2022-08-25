<template>
    <form class="form-signin" ref="signupForm" :model="signupForm" :rule="signupRule">
        <fieldset>
            <h1 class="h3 mb-3 font-italic">Welcome to Lemonade.</h1>
            <div v-if="signupForm.popup == '1'">
                <div class="alert alert-danger">{{signupForm.popupMsg}}</div>
            </div>
            <div class="control-group">
                <!-- Account -->
                <label for="inputEmail" class="sr-only">Account</label>
                <div class="input-group">
                    <input type="email" id="inputEmail" v-model="signupForm.account" class="form-control"
                        placeholder="Account" required autofocus>
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
                    <input type="text" id="inputUsername" v-model="signupForm.username" class="form-control"
                        placeholder="Username" required autofocus>
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
                    <input type="password" id="inputPassword" v-model="signupForm.password" class="form-control"
                        placeholder="Password" required autofocus>
                    <div class="invalid-feedback" style="width: 100%;">
                        The password is required.
                    </div>
                </div>
                <p class="text-muted">Password should be at least 4 characters</p>
            </div>

            <div class="control-group">
                <!-- Password -->
                <label for="inputPasswordConfirm" class="sr-only">Password (Confirm)</label>
                <input type="password" id="inputPasswordConfirm" v-model="signupForm.passwordConfirm"
                    class="form-control" placeholder="PasswordAgain" required autofocus>
                <p class="text-muted">Please confirm password</p>
            </div>

            <div class="mb-3">
                <label for="permission" class="sr-only">Identity</label>
                <select class="custom-select d-block w-100" id="permission" v-model="signupForm.permission" required>
                    <option value="">Choose...</option>
                    <option v-bind:value="'ROLE_MEMBER'">Member</option>
                    <option v-bind:value="'ROLE_EDITOR'">Editor</option>
                    <option v-bind:value="'ROLE_REVIEWER'">Senior Editor</option>
                </select>
                <div class="invalid-feedback">
                    Please select a valid identity.
                </div>
            </div>

            <!-- Button -->
            <div class="controls">
                <button class="btn btn-success" @click="signupVerify()">Register</button>
            </div>
        </fieldset>
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
    name: "register",
    components: { },
    data(){
        return{
            signupForm: {
                account: "",
                username: "",
                password: "",
                passwordConfirm:"",
                permission:"",
                popup: "",
                popupMsg:""
            },
            signupRule: {
                account: [{ required: true, message: 'Please input account', trigger: "blur"}],
                password: [{ required: true, message: 'Please input password', trigger: "blur" }],
                username: [{ required: true, message: 'Please input username', trigger: "blur" }],
                passwordConfirm: [{ required: true, message: 'Please input', trigger: "blur" }],
                permission: [{ required: true, message: 'Please choose role', trigger: "blur" }],
            }
        }
    },
    created(){
    },
    methods: {
        clickMenu(item) {
            this.$router.push({ name: item });
        },
        signupVerify(){
            if(this.signupForm.account == '' || this.signupForm.password == '' 
                || this.signupForm.passwordConfirm == '' || this.signupForm.username == ''
                    || this.signupForm.permission == ''){
                        console.log("不完整的表单")
                return;
            }
            if (this.signupForm.password != this.signupForm.passwordConfirm) {
                this.signupForm.popup = '1';
                this.signupForm.popupMsg = "The two passwords are inconsistent";
                this.signupForm.account = '';
                this.signupForm.username = '';
                this.signupForm.password = '';
                this.signupForm.passwordConfirm = '';
                this.signupForm.permission = '';
                return;
            }
            let param = {
                account: this.signupForm.account,
                name: this.signupForm.username,
                password: this.signupForm.password,
                permission: this.signupForm.permission,
            };
            //axios.post('/foo', params);
            axios.post('/register', param).then(res => {
                console.log(res.data);
                this.signupForm.account = '';
                this.signupForm.username = '';
                this.signupForm.password = '';
                this.signupForm.passwordConfirm = '';
                this.signupForm.permission = '';
                if (res.data.code == '200') {
                    this.$router.push({ name:'login',params:{success:'success'}});
                }else{
                    this.signupForm.popup = '1';
                    this.signupForm.popupMsg = res.data.msg;
                }
            }).catch(function (error) {
               console.log(error)
            });
            
        }
    },
}
</script>