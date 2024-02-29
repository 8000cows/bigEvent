import {defineStore} from "pinia";
import {ref} from "vue";

export const userInfoToken = defineStore('userInfo',
    () => {
        let userInfoToken = ref('');

        let setUserInfo = (userInfo) => {
            userInfoToken.value = userInfo;
        };

        let removeUserInfo = () => { userInfoToken.value = ''};

        return {userInfoToken, setUserInfo, removeUserInfo};
    },
    {persist: true});