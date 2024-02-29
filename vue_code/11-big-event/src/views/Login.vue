<script setup>
    import { User, Lock } from '@element-plus/icons-vue';
    import { ElMessage } from 'element-plus'
    import { ref } from 'vue';
    import { registerUserService, loginService } from "@/api/user";
    import {useRouter} from "vue-router";
    import {useTokenStore} from "@/store/token";

    let router = useRouter();
    // 控制注册与登录表单的显示， 默认显示注册
    const isRegister = ref(false);

    let formData = ref({
        username: '',
        password: '',
        rePassword: ''
    });

    // 自定义校验规则，检查两次输入密码是否一致
    let checkRePassword = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请再次确认密码'));
        }
        else if (value !== formData.value.password) {
            callback(new Error('两次输入的密码不一致'));
        }
        else callback();
    }

    // 表单校验规则
    let rules = {
        username: [
            {required: true, message: '用户名不能为空', trigger: 'blur'},
            {min: 5, max: 16, message: '用户名的长度必须为5~16位', trigger: 'blur'}
        ],
        password: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {min: 5, max: 16, message: '密码的长度必须为5~16位', trigger: 'blur'}
        ],
        rePassword: [{ validator: checkRePassword, trigger: 'blur' }]
    };

    // 调用后台接口，注册用户
    async function register() {
        let result = await registerUserService(formData.value);
        ElMessage.success('注册成功');
    }

    // 调用后台接口，登录
    async function login() {
        let result = await loginService(formData.value);
        useTokenStore().setToken(result.data);
        ElMessage.success('登录成功');
        router.push('/article/manage');
    }

    // 清空表单数据
    function clearFormData() {
        formData.value = {username: '', password: '', rePassword: '' };
    }
</script>

<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">
            <!-- 注册表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="formData" :rules="rules">
                <el-form-item>
                    <h1>注册</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="formData.username"/>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="formData.password"/>
                </el-form-item>
                <el-form-item prop="rePassword">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码" v-model="formData.rePassword"/>
                </el-form-item>
                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register">
                        注册
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false; clearFormData()">
                        ← 返回
                    </el-link>
                </el-form-item>
            </el-form>
            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" v-else :model="formData" :rules="rules">
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="formData.username"/>
                </el-form-item>

                <el-form-item prop="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="formData.password"/>
                </el-form-item>
                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>记住我</el-checkbox>
                        <el-link type="primary" :underline="false">忘记密码？</el-link>
                    </div>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true; clearFormData()">
                        注册 →
                    </el-link>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
/* 样式 */
.login-page {
    height: 100vh;
    background-color: #fff;

    .bg {
        background: url('@/assets/logo2.png') no-repeat 60% center / 240px auto,
        url('@/assets/login_bg.jpg') no-repeat center / cover;
        border-radius: 0 20px 20px 0;
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}
</style>