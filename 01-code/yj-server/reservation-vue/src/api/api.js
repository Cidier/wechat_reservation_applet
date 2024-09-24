
// request = from 提交   requestJson = json 提交
import {request, requestJson,requestFormData} from '../utils/request.js'
import {isNavigationFailure} from "vue-router";

/*所有的URL请求地址统一维护在api.js中进行定义*/
export function api(){
    const url = {
        //登录
        login: "/login",
        isAuthLogin: 'isAuthLogin',
        loginout: "/loginout",


        sysUserSave:"/mm-sys-user/save",
        sysUserResetPwd:"/mm-sys-user/resetPWD",
        sysUserUserById:"/mm-sys-user/",
        sysUserRoleRelationById:"/mm-sys-user/userRoleRelationById",
        sysUserDelete:"/mm-sys-user/",
        sysUserUpUserOrg:"/mm-sys-user/upUserOrg",
        sysUserPage:"/mm-sys-user/page",

        sysRoleList:"/mm-sys-role/list",
        sysRoleDelete:"/mm-sys-role/",
        sysRoleRoleById:"/mm-sys-role/",
        sysRolePids:"/mm-sys-role/pids",
        sysRoleSave:"/mm-sys-role/save",


        sysPermissionTreeList:"/mm-sys-permission/treeList",
        sysPermissionMenuTree:"/mm-sys-permission/menuTree",

        sysPermissionSave:"/mm-sys-permission/save",
        sysPermissionById:"/mm-sys-permission/",


        sysBasicDictPageList:"/mm-sys-basic-dict/pageList",
        sysBasicDictList:"/mm-sys-basic-dict/list",
        sysBasicDictSave:"/mm-sys-basic-dict/save",
        sysBasicDictFindById:"/mm-sys-basic-dict/",
        sysBasicDictDeleteById:"/mm-sys-basic-dict/deleteById",

        cmsSaveArticle:"/mm-cms-articles/save",
        cmsUpdateArticle:"/mm-cms-articles/update",
        cmsDeleteArticle:"/mm-cms-articles/delete",
        cmsArticleFindById:"/mm-cms-articles/findById",
        cmsArticlePageList:"/mm-cms-articles/pageList",
        //content
        cmsArticleContent:"/mm-cms-articles-content/save",
        cmsDeleteArticleContent:"/mm-cms-articles-content/delete",
        cmsSaveImg:"/file/uploadOSS",
        cmsArticleContentFindById:"/mm-cms-articles-content/findById",

        cmsContentMenuTreeList:"/mm-cms-menu/treeList",
        //second 上级
        cmsContentMenuTree:"/mm-cms-menu/menuTree",
        cmsContentMenuSave:"/mm-cms-menu/save",
        cmsContentMenuById:"/mm-cms-menu",
        cmsDeleteMenuById:"/mm-cms-menu/delete",

        cmsSynchronizationAll:"/mm-cms-transfer-json/list"

    }
    return {
        url,
        apiLogin:  (data={}, success, fail, complete)=>{
            requestJson(url.login, 'POST', data, success, fail, complete)
        },
        apiIsAuthLogin:  (success, fail, complete)=>{
            requestJson(url.isAuthLogin, 'POST', {}, success, fail, complete)
        },
        apiSysUserPage:  (data={}, success, fail, complete)=>{
            requestJson(url.sysUserPage, 'POST', data, success, fail, complete)
        },
        apiSysUserSave:  (data={}, success, fail, complete)=>{
            requestJson(url.sysUserSave, 'POST', data, success, fail, complete)
        },
        apiSysUserResetPwd:  (id, success, fail, complete)=>{
            requestJson(url.sysUserResetPwd+"/"+id, 'POST', {}, success, fail, complete)
        },
        apiSysUserRoleRelationById:  (data={}, success, fail, complete)=>{
            requestJson(url.sysUserRoleRelationById, 'GET', data, success, fail, complete)
        },
        apiSysUserDelete:  (id, success, fail, complete)=>{
            requestJson(url.sysUserDelete+"/"+id, 'DELETE', {}, success, fail, complete)
        },
        apiSysUserUserById: (id, success, fail, complete)=>{
            requestJson(url.sysUserUserById+"/"+id, 'GET', {}, success, fail, complete)
        },
        apiSysUserUpUserOrg: (data={}, success, fail, complete)=>{
            requestJson(url.sysUserUpUserOrg, 'POST', data, success, fail, complete)
        },
        apiSysRoleList: (data={}, success, fail, complete)=>{
            requestJson(url.sysRoleList, 'GET', data, success, fail, complete)
        }
        ,
        apiSysRoleDelete: (id, success, fail, complete)=>{
            requestJson(url.sysRoleDelete+"/"+id, 'DELETE', {}, success, fail, complete)
        },
        apiSysRoleRoleById: (id, success, fail, complete)=>{
            requestJson(url.sysRoleRoleById+"/"+id, 'GET', {}, success, fail, complete)
        },
        apiSysRoleSave:  (data={}, success, fail, complete)=>{
            requestJson(url.sysRoleSave, 'POST', data, success, fail, complete)
        },
        apiSysRolePids: (id, success, fail, complete)=>{
            requestJson(url.sysRolePids+"/"+id, 'GET', {}, success, fail, complete)
        },
        apiSysPermissionTreeList:(data, success, fail, complete)=>{
            requestJson(url.sysPermissionTreeList, 'GET', data, success, fail, complete)
        },
        apiSysPermissionMenuTree:(data, success, fail, complete)=>{
            requestJson(url.sysPermissionMenuTree, 'GET', data, success, fail, complete)
        },

        apiSysPermissionSave:  (data={}, success, fail, complete)=>{
            requestJson(url.sysPermissionSave, 'POST', data, success, fail, complete)
        },
        apiSysPermissionById: (id, success, fail, complete)=>{
            requestJson(url.sysPermissionById+"/"+id, 'GET', {}, success, fail, complete)
        },

        apiSysBasicDictPageList: (data={}, success, fail, complete)=>{
            requestJson(url.sysBasicDictPageList, 'POST', data, success, fail, complete)
        },
        apiSysBasicDictFindById: (id, success, fail, complete)=>{
            requestJson(url.sysBasicDictFindById+"/"+id, 'GET', {}, success, fail, complete)
        },
        apiSysBasicDictSave:  (data={}, success, fail, complete)=>{
            requestJson(url.sysBasicDictSave, 'POST', data, success, fail, complete)
        },
        apiSysBasicDictList: (data={}, success, fail, complete)=>{
            requestJson(url.sysBasicDictList, 'GET', data, success, fail, complete)
        },
        apiSysBasicDictDeleteById: (id, success, fail, complete)=>{
            requestJson(url.sysBasicDictDeleteById+"/"+id, 'POST', {}, success, fail, complete)
        },

        apiCmsSaveArticle:(data={}, success, fail, complete)=>{
            requestJson(url.cmsSaveArticle,'POST',data, success,fail,complete)
        },
        apiCmsSaveArticleContent:(data={},success,fail,complete)=>{
            requestJson(url.cmsArticleContent,'POST',data, success, fail, complete)
        },
        apiCmsArticleFindById:(id,success, fail,complete)=>{
            requestJson(url.cmsArticleFindById+"?id="+id,'GET',{},success,fail,complete)
        },
        apiCmsArticleContentFindById: (id,success,fail,complete)=>{
          requestJson(url.cmsArticleContentFindById+"?articlesId="+id,'GET',{},success,fail,complete)
        },
        apiCmsDeleteArticle: (id, success,fail,complete)=>{
            requestJson(url.cmsDeleteArticle+"?id="+id,'GET',{},success,fail,complete)
        },
        apiCmsDeleteArticleContent: (id, success,fail,complete)=>{
            requestJson(url.cmsDeleteArticleContent+"?articlesId="+id,'GET',{},success,fail,complete)
        },
        apiCmsArticlePageList:(data={}, success, fail, complete)=>{
            requestJson(url.cmsArticlePageList, 'POST', data, success, fail, complete)
        },

        apiCmsUploadImg:(data={},success,fail,complete)=>{
            console.log(data)
            requestFormData(url.cmsSaveImg,'POST',data, success,fail, complete)
        },
        apiCmsContentMenuTreeList:(data, success, fail, complete)=>{
            requestJson(url.cmsContentMenuTreeList,'GET', data, success, fail, complete)
        },
        apiCmsContentMenuTree :(data, success, fail, complete)=>{
            requestJson(url.cmsContentMenuTree,'GET', data, success, fail, complete)
        },
        apiCmsContentMenuSave:  (data={}, success, fail, complete)=>{
            requestJson(url.cmsContentMenuSave, 'POST', data, success, fail, complete)
        },
        apiCmsContentMenuById: (id, success, fail, complete)=>{
            requestJson(url.cmsContentMenuById+"/"+id, 'GET', {}, success, fail, complete)
        },
        apiCmsDeleteMenuById:(id, success, fail, complete)=>{
            // delete
            requestJson(url.cmsDeleteMenuById+"/"+id, 'DELETE', {}, success, fail, complete)
        },

        apicmsSynchronizationAll:(id, success, fail, complete)=>{
            // delete
            requestJson(url.cmsSynchronizationAll, 'POST', {}, success, fail, complete)
        }
        // apiUpdateArticle:()
    }
}



