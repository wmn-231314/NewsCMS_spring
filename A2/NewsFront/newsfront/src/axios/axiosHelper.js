import axios from 'axios';

// axios.defaults.timeout = 10000; //超时终止请求
axios.defaults.withCredentials = true;

// var loadingInstance
axios.interceptors.request.use(config => {
    //Ajax请求执行该方法，请求带上token
    var token = window.localStorage.getItem('token');
    if (token !== null && token !== undefined && token !== '') {
        config.headers.Authorization = token;
    }
    //全局配置，get请求加时间戳
    if (config.method.toLowerCase() === "get") {
        config.url += config.url.match(/\?/) ? "&" : "?";
        config.url += "_dc=" + new Date().getTime();
    }
    return config;
}, error => {  //请求错误处理
    return Promise.reject(error);
});


var count = 0;
axios.interceptors.response.use(response => {
    return response;
},
    error => {
        if (error.response.status === 401) {
            if (count === 0) {
                count = count + 1;
            } else if (count > 0) {
                return null;
            }
            // debugger
            $cookies.remove('token');
            setTimeout(function () {
                window.location.href = '/#/login';
            }, 3000);
            return Promise.reject(error);
        }
    }
);
export default axios; //暴露axios实例