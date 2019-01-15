import Vue from "vue"
import "todomvc-app-css/index.css"
import App from "./App.vue"
//import TodoList from "./TodoList.vue"
import "./styles.css"
import Router from './router';

new Vue({
  router: Router,
  el: '#app',
  render: h => h(App),
});
