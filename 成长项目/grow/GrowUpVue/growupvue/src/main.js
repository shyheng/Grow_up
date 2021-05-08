import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
//引入axios
import axios from "axios";

//挂载axios
Vue.prototype.$http=axios
//设置访问根路径
axios.defaults.baseURL = "http://120.77.255.179:9000/"

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
