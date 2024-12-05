<script setup>
import {
  Edit,
  Delete
} from '@element-plus/icons-vue'

import { ref, getCurrentInstance, reactive ,onMounted} from 'vue'
import global from '@/global'

import {ElMessage, ElMessageBox} from 'element-plus'

const { proxy } = getCurrentInstance();
console.log("打印", proxy)
const api = proxy.$api;
const request = proxy.$request;

const selectStateList = reactive([
  {value: "正常", label: "正常"},
  {value: "禁用", label: "禁用"},
])

//用户搜索时选中的分类id

//文章分类数据模型
const menu = ref({
  id: '5',
  menuTitle: '政策支持',
  parentId: '0',
  sortOrder: '2',
  remark:'',
  type:'',
  state:''

})

const articleModel = ref({
      id: '',
      menuId: menu.value.id,
      coverImg: '',
      title: '',
      content: '',
      state: '',
      type: menu.value.menuTitle,
      sortOrder: '',
      remark:''
    }
);

const articleContent = ref({
  articlesId: '',
  content: ''
})



const props = {

  checkStrictly: true,
  //  value: "areaId",
  //label: "areaName",
  //children: "children"
}

const handleChangeMenu = (value) => {
  console.log(value)
}
// 两次修改

//用户搜索时选中的分类id
const menuId = ref('')

//用户搜索时选中的发布状态
const state = ref('')



let requestP = global.request_host
const uploadUrl = ref(requestP + api.url.cmsSaveImg);
console.log("looookjk", uploadUrl)

const uploadImgData = {folderName: "pic"}



//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
  pageInfo.current = index;
  articleList()
}

const onhandleSizeChange=(size)=>{
  pageInfo.size = size;
  articleList()
}
// //回显文章分类
// import { articleMenuListService, articleListService,articleAddService } from '@/api/article.js'
// const articleMenuList = async () => {
//     let result = await articleMenuListService();

//     menus.value = result.data;
// }

// //获取文章列表数据
const pageInfo = reactive({
  current:1, //当前第几页
  total: 0, //总记录
  size: 20, //一页多少条
  pages: 0   //几页
})

const params = reactive({
  pageNo: 1,
  pageSize: 20,
  menuId: menu.value.id,
  // state: state.value ? state.value : null
})


const articles = reactive([]);
const articleList = async () => {
  console.log("look it", pageInfo.current)
  // articleModel.value = {};
  api.apiCmsArticlePageList({
    'pageNo': pageInfo.current,
    'pageSize': pageInfo.size,
    'menuId': menu.value.id
  }, d => {
    articles.length = 0;
    console.log("返回的d", d)

    d.page.records.forEach(bean => {
      console.log(bean)
      bean.ctime = bean.ctime.split('T')[0]
      articles.push(bean)
    })

    pageInfo.current = d.page.current
    pageInfo.total = d.page.total
    pageInfo.size = d.page.size
    pageInfo.pages = d.page.pages
    console.log("pageInfo", pageInfo)

    // userFormRef.value.doLayout()
  })

}

// articleMenuList()
articleList();

function handleSizeChange(size) {
  pageInfo.size = size;
  articleList();
}

function handleCurrentChange(index) {
  console.log("当前current is",index)
  pageInfo.current = index;
  articleList();
}

//富文本
import { Plus } from '@element-plus/icons-vue'
import * as http from "http";
//控制抽屉是否显示
const addVisible = ref(false)
const visibleDrawer = ref(false)
const visibleDrawerContent = ref(false)

// //导入token
// import { useTokenStore } from '@/stores/token.js';
// const tokenStore = useTokenStore();

// //上传成功的回调函数
// const uploadSuccess = (result)=>{
//     articleModel.value.coverImg = result.data;
//     console.log(result.data);
// }

// //添加文章


const save = function (){
  if (buttonContent.value ===judgeSignal.value[0]){
    console.log('是保存哦哦哦hihiii')

    saveArticle();
    visibleDrawer.value = false;
    visibleDrawerContent.value = true;

  }
  else if (buttonContent.value ===judgeSignal.value[1]){
    console.log('hihiii')

    saveArticle();
    visibleDrawer.value = false;
    // visibleDrawerContent.value = true;

  }
  else if (buttonContent.value ===judgeSignal.value[2]){
    saveArticleContent();
  }
}
const articleGetBackEndId = ref();
const saveArticle = async () => {

  //把发布状态赋值给数据模型
  // articleModel.value.state = clickState;
  console.log("menu_ida", articleModel.value.menuId)
  let datac = null  ;
  // 新增
  if (articleModel.id ==null||articleModel.id ==''){
    console.log('进入保存哦')
    datac = {
      title: articleModel.value.title,

      // content:articleModel.value.content,
      menuId: articleModel.value.menuId,
      coverImg: articleModel.value.coverImg,
      state: articleModel.value.state,
      type: articleModel.value.type,
      sortOrder: articleModel.value.sortOrder,
      remark:  articleModel.value.remark
      // state:articleModel.value.state
    }
  }
  // 修改
  else{
    console.log('进入修改哦')

    datac = {
      title: articleModel.value.title,

      // content:articleModel.value.content,
      menuId: articleModel.value.menuId,
      coverImg: articleModel.value.coverImg,
      id: articleModel.value.id,
      state: articleModel.value.state,
      type: articleModel.value.type,
      sortOrder: articleModel.value.sortOrder,
      remark:  articleModel.value.remark
      // state:articleModel.value.state
    }
  }

  console.log("saveData", datac)
  api.apiCmsSaveArticle(datac, (res) => {
        console.log(res);
        console.log("saveSuccess");
        articleGetBackEndId.value = res.articlesId;
        console.log("saveSuccessIDDDDDDDDDDDDDDDDDDD",articleGetBackEndId.value);
        articleModel.value.id = res.articlesId
        console.log("saveSuccessIDDDDDDDDDDDDDDDDDDD",articleGetBackEndId.value);
        console.log("articleModelId",articleModel.value.id)

        articleList()
      }
  )
  visibleDrawer.value = false;
  // visibleDrawerContent.value = true;？

}

const saveArticleContent = async () => {
  console.log(articleGetBackEndId.value,"保存文章内容",articleModel.value.content,"id",articleModel.value.id)
  // articleGetBackEndId.value = api.apiCmsFind

  let dataContent = {
    articlesId: articleGetBackEndId.value=null?articleModel.value.id:articleGetBackEndId.value,
    content: articleModel.value.content
  }
  console.log(dataContent.articlesId)
  await api.apiCmsSaveArticleContent(dataContent, (res) => {
    console.log("保存文章内容成功", res)
    articleList();
  })

  visibleDrawerContent.value = false;
  resetArticleModel();
  await articleList();

}
const resetArticleModel =function (){
  articleModel.value.title ='',
      // articleModel.value.menuId ='',
      articleModel.value.coverImg ='',
      articleModel.value.state ='',
      // articleModel.value.type ='',
      articleModel.value.id ='',
      articleModel.value.sortOrder ='',
      articleModel.value.content =''

}
const buttonContent = ref();



const judgeSignal =  ref(['下一步','保存修改基础信息','保存修改详情'])

const editShowDetail = function (index,row){

  console.log('row',row)
  visibleDrawer.value = false;
  visibleDrawerContent.value = true;
  buttonContent.value = judgeSignal.value[2];
  // articleId的获取
  articleGetBackEndId.value = row.id;
  console.log("row.id")
  api.apiCmsArticleContentFindById(row.id, res => {
    console.log("content is", res)
    articleModel.value.id = res.vo.articlesId;
    articleModel.value.content = res.vo.content;

  })

}

const editShow = function (index, row) {
  // buttonContent.value = condiState;
  // visibleDrawer.value = true;

  visibleDrawer.value = true;
  visibleDrawerContent.value = false;
  buttonContent.value = judgeSignal.value[1];

  articleGetBackEndId.value = row.id;
  console.log("edit", row)
  api.apiCmsArticleFindById(row.id, data => {
    console.log("edit transfer data", data)
    articleModel.value.title = data.vo.title;
    articleModel.value.coverImg = data.vo.coverImg;
    articleModel.value.id = data.vo.id;
    articleModel.value.menuId = data.vo.menuId;
    articleModel.value.state = data.vo.state;
    articleModel.value.type = data.vo.type;
    articleModel.value.sortOrder = data.vo.sortOrder;

    articleModel.value.remark = data.vo.remark;
    // selectedOptions.value = data.vo.menuId;

    console.log("artivleModel is", articleModel)
    // articleModel.content=


  })
}
//
//
// const saveArticle = async (clickState) => {
//   //把发布状态赋值给数据模型
//   articleModel.value.state = clickState;
//   console.log("menu_id", articleModel.value.menuId)
//   const datac = {
//     title: articleModel.value.title,
//     // content:articleModel.value.content,
//     menuId: articleModel.value.menuId,
//     coverImg: articleModel.value.coverImg,
//     id: articleModel.value.id
//     // state:articleModel.value.state
//   }
//   console.log("saveData", datac)
//   api.apiSaveArticle(datac,(res)=>{
//     console.log(res);
//     console.log("saveSuccess");
//     let articlesId = res.articlesId;
//     let dataContent = {
//       articlesId: articlesId,
//       content: articleModel.value.content
//     }
//     console.log(dataContent.content)
//     api.apiSaveArticleContent(dataContent,(res)=>{
//       console.log("保存文章内容成功", res)
//
//     })
//
//   })
//   visibleDrawer.value = false;
//   articleList();
//
//
//
//
//   // 刷新当前列表
//   // articleList()
// }
//
// const editShow = function(index,row){
//   // visibleDrawer.value = true;
//   visibleDrawer.value = true;
//
//   console.log("edit",row)
//   api.apiArticleFindById(row.id, data=>{
//     console.log("edit transfer data",data)
//     articleModel.value.title  = data.vo.title;
//     articleModel.value.coverImg =  data.vo.coverImg;
//     articleModel.value.id  = data.vo.id;
//     console.log("artivleModel is" ,articleModel)
//     // articleModel.content=
//     api.apiArticleContentFindById(row.id,res=>{
//       console.log("content is",res)
//       articleModel.value.content = res.vo.content;
//
//     } )
//
//   })
// }


const deleteOpen = function (index, row) {
  console.log(row.id, row, "deleteeee")
  ElMessageBox.confirm('此操作将删除该记录, 是否删除?', '提示',
      {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'}
  )
      .then(() => {
        // ElMessage({ type: 'info', message: '点击删除'})
        handleDelete(index, row);
      })
      .catch(() => {
        ElMessage({type: 'info', message: '已取消删除'})
      })
}
const handleDelete = function (index, row) {
  api.apiCmsDeleteArticle(row.id, res => {

    console.log("delete.success");
    api.apiCmsDeleteMenuById(row.id, data => {
      console.log("删除文章内容", data)
    })
    articleList();
  })
}

//图片
const handlePicSuccess = function (res) {
  console.log(res)
  articleModel.value.coverImg = res.data.file.previewUrl;
}


onMounted(()=>{
  articleList();
})



// makedown

const leftBar = ref('undo redo clear h bold italic strikethrough quote ul ol table hr link image code');
//右侧工具栏
const rightBar = ref('preview sync-scroll fullscreen');



const handleUploadImage = function (event, insertImage, files) {
  const formData = new FormData();
  // file =  event.target.files;
  console.log("event", event.target.files)
  console.log("file", files[0])
  formData.append("file", files[0]);
  formData.append("folderName", "imageFileSingle")
  console.log(formData + "前端formDatanotrequest")
  // http.post(uploadUrl, formData, {
  //   headers:{
  //     'Content-Type':'multipart/form-data'
  //   }
  // });
  formData.forEach((value, key) => {
    console.log(key + ': ' + value);
  });
  api.apiCmsUploadImg(formData, res => {
    console.log("返回image", res)
    insertImage({
      url: res.file.previewUrl

    })
    // callback(res.data.url)
  })
}

const uploadToMini = function (){
  api.apicmsSynchronizationAll(null,res=>{
    console.log('一键同步成功',res)
  })
}

const addNewArticleOpen = function (){
  resetArticleModel();

  buttonContent.value = '下一步'
  visibleDrawer.value = true;

}
</script>

<template>
  <el-card class="page-container">

    <template #header>

      <div class="header">
        <span>{{ menu.menuTitle }}</span>
        <div class="extra">

          <el-button type="primary" @click="resetArticleModel();visibleDrawer = true; buttonContent = judgeSignal[0];">添加文章</el-button>
        </div>

        <div class="extra">

          <el-button type="primary" @click="uploadToMini">一键同步</el-button>
        </div>
      </div>
    </template>
    <!-- 搜索表单 -->
    <el-form inline>

      <!--            <el-form-item label="发布状态：">-->
      <!--                <el-select placeholder="请选择" v-model="state" style="width: 200px;">-->
      <!--                    <el-option label="已发布" value="已发布"></el-option>-->
      <!--                    <el-option label="草稿" value="草稿"></el-option>-->
      <!--                </el-select>-->
      <!--            </el-form-item>-->

      <!--      <el-form-item>-->
      <!--        <el-input style="width: 25rem" prefix-icon="Search" placeholder="请输入搜索内容"></el-input>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item>-->
      <!--        <el-button type="primary" @click="articleList">搜索</el-button>-->
      <!--        <el-button @click="menuId = ''; state = ''">重置</el-button>-->
      <!--      </el-form-item>-->

    </el-form>
    <!-- 文章列表 -->
    <el-table :data="articles" style="width: 100%">
      <el-table-column label="文章标题" width="400" prop="title"></el-table-column>
      <el-table-column label="分类" prop="type"></el-table-column>
      <el-table-column label="发表时间" prop="ctime"></el-table-column>
      <el-table-column label="状态" prop="state"></el-table-column>
      <el-table-column label="排序" prop="sortOrder"></el-table-column>
      <el-table-column label="备注" prop="remark"></el-table-column>
      <el-table-column label="操作" width="300">
        <template #default="row">
          <el-button   plain type="primary" @click="editShow(row.$index,row.row)">修改</el-button>
          <el-button   plain type="danger" @click="editShowDetail(row.$index,row.row)">修改详情</el-button>
          <el-button   plain type="danger" @click="deleteOpen(row.$index,row.row)">删除</el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据"/>
      </template>
    </el-table>
    <!-- 分页条 -->
    <!--        <el-pagination v-model:current-page="params.pageNo" v-model:page-size="params.pageSize" :page-sizes="[3, 5, 10, 15]"-->
    <!--            layout="jumper, total, sizes, prev, pager, next" background-->
    <!--            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />-->
    <el-pagination
        :page-count="pageInfo.pages"
        background
        style="margin: 15px;"
        layout="sizes,total, prev, pager, next,jumper"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="pageInfo.size"
        :total="pageInfo.total"
        :current-page="pageInfo.current">
    </el-pagination>
    <!-- 抽屉 -->
    <!-- title: '',
    menuId: '',
    coverImg: '',
    content: '',
    state: '',
    address: '' -->
    <el-drawer v-model="visibleDrawer" title="文章" direction="rtl" size="50%"

    >
      <!-- 添加文章表单 -->
      <el-form :model="articleModel" label-width="100px">

        <el-form-item label="文章状态">
          <el-select v-model="articleModel.state" placeholder="请选择类型">
            <el-option
                v-for="item in selectStateList"
                :key="item.state"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文章分类">
          <el-input v-model="articleModel.type" placeholder=""  disabled></el-input>


        </el-form-item>


        <!-- <el-form-item label="文章分类">
            <el-cascader v-model="articleModel.menuId" :options="options" :props="props"
                @change="handleChangeMenu" />
        </el-form-item> -->
        <el-form-item label="文章标题">
          <el-input v-model="articleModel.title" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="封面图片">
          <el-upload class="avatar-uploader"
                     :show-file-list="false"
                     :action="uploadUrl" name="file"
                     :data="uploadImgData" :on-success="handlePicSuccess" :before-upload="beforePicUpload">
            <img v-if="articleModel.coverImg" :src="articleModel.coverImg" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item label="排序">
          <el-input v-model="articleModel.sortOrder" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="articleModel.remark" placeholder=""></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="save()">{{buttonContent}}</el-button>
          <!--          <el-button type="info" @click="addArticle('addvis')">不显示</el-button>-->
        </el-form-item>


      </el-form>
    </el-drawer>

    <!--2-->
    <el-drawer v-model="visibleDrawerContent" title="文章" direction="rtl" size="50%">
      <el-form  :model="articleModel" label-width="100px">
        <el-form-item label="文章内容">
          <div class="editor">
            <div style="border: 1px solid #ccc;">

              <v-md-editor
                  :autofocus="true"
                  v-model="articleModel.content"
                  height="510px"
                  placeholder="请输入内容"
                  left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | image | save "
                  :disabled-menus="[]"
                  @upload-image="handleUploadImage"
              >
              </v-md-editor>
            </div>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveArticleContent()">发布</el-button>
          <!--          <el-button type="info" @click="addArticle('addvis')">不显示</el-button>-->
        </el-form-item>


      </el-form>
    </el-drawer>
  </el-card>

</template>
<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;
  margin: 30px;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}

/* 抽屉样式 */
.avatar-uploader {
  :deep() {
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }

    .el-upload {
      border: 1px dashed var(--el-border-color);
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
    }

    .el-upload:hover {
      border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
    }
  }
}

.editor {
  width: 100%;


}
</style>
