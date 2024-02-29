import {defineStore} from "pinia";
import {ref} from "vue";

export const useTokenStore = defineStore('token', () => {
    let token = ref('');
    let setToken = (tokenStr) => {
        token.value = tokenStr;
    };
    let removeToken = () => {
        token.value = '';
    };

    return {token, setToken, removeToken}
}, {persist: true});

