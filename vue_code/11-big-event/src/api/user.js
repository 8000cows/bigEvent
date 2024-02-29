import request from '../utils/request';
export const registerUserService = registerData => {
    return request.post('/user/register', toFormParam(registerData));
};
export const loginService = registerData => {
    return request.post('/user/login', toFormParam(registerData));
};

function toFormParam(args) {
    const param = new URLSearchParams();
    for (let key in args) {
        param.append(key, args[key]);
    }

    return param;
}

export const getUserInfoService = () => {
    return request.get('/user/userInfo');
};