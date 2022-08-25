// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Axios from 'axios'
import axios from 'axios'
import axiosHelper from '../src/axios/axiosHelper'

Vue.prototype.axiosHelper = axiosHelper;
Vue.prototype.$axios = Axios
Axios.defaults.baseURL = "/api"
Vue.config.productionTip = false

axios.interceptors.request.use(config => {
  let token = localStorage.getItem("token");//token保存在本地
  if (token) {
    config.headers.token = token;
  }
  return config
}, error => {
  return Promise.reject(error)
})

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
