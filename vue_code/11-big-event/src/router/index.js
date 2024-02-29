import Login from "@/views/Login.vue";
import Layout from "@/views/Layout.vue";
import ArticleCategory from '@/views/article/ArticleCategory.vue';
import ArticleManage from '@/views/article/ArticleManage.vue';
import UserAvatar from '@/views/user/UserAvatar.vue';
import UserInfo from '@/views/user/UserInfo.vue';
import UserResetPassword from '@/views/user/UserResetPassword.vue';

import {createRouter, createWebHistory} from "vue-router";

let routes = [
    {
        path:'/',
        component: Layout,
        redirect: '/login',
        children: [
            {path: '/article/category', component: ArticleCategory},
            {path: '/article/manage', component: ArticleManage},
            {path: '/user/avatar', component: UserAvatar},
            {path: '/user/info', component: UserInfo},
            {path: '/user/resetPassword', component: UserResetPassword},
        ]
    },
    {path:'/login', component: Login }
];

let router = createRouter({routes, history: createWebHistory()});

export default router;