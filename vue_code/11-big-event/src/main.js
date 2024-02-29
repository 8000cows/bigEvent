import './assets/main.scss';
import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import router from "@/router";
import 'element-plus/dist/index.css';
import {createPinia} from "pinia";
import {createPersistedState} from "pinia-plugin-persistedstate";
import App from './App.vue';
import locale from 'element-plus/dist/locale/zh-cn';

const app = createApp(App);
const piniaPlugin = createPersistedState();
const pinia = createPinia();

pinia.use(piniaPlugin)
app.use(ElementPlus,{locale})
app.use(pinia);
app.use(ElementPlus);
app.use(router);
app.mount('#app');
