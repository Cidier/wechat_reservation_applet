/**
 * 授权相关操作
 */

const tokenName = 'yj-tk';

const userInfoName = 'yj-ui';
const menusName = "yj-menus"

export function setLoginAll(loginData){
    setToken(loginData.userInfo.token);
    setUserInfoName(JSON.stringify(loginData.userInfo));
    setMenus(JSON.stringify(loginData.menuList));
}


//获取授权token
export function getToken(){
    return localStorage.getItem(tokenName)
}


//设置授权token
export function setToken(token){
    localStorage.setItem(tokenName, token);
}

//设置授权token
export function clearToken(){
    localStorage.removeItem(tokenName);
}
/**
 *
 *         VueCookies.set("userInfo",params.userInfo);
 *         VueCookies.set("u_nirs_token",params.userInfo.token);*/
export function getUserInfoName(){
    return localStorage.getItem(userInfoName)
}


export function setUserInfoName(userInfo){
    localStorage.setItem(userInfoName, userInfo);
}

export function clearUserInfoName(){
    localStorage.removeItem(userInfoName);
}

export function getMenus(){
    return localStorage.getItem(menusName)
}


export function setMenus(menuList){
    localStorage.setItem(menusName, menuList);
}

export function clearMenus(){
    localStorage.removeItem(menusName);
}

export function clearAll(){
    clearToken();
    clearUserInfoName();
    clearMenus();
}