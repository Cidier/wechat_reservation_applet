<script setup>
import Editor from "@tinymce/tinymce-vue";
import { reactive, ref, toRefs } from "@vue/reactivity";
const content = ref("默认文字 hello word");
const tiny = reactive({
  //https://www.tiny.cloud/my-account/integrate/#vue
  apiKey: "t1o5aazalrcvkdmswp8cmsmm3c6zgav0uvj8a3ay65nmju2i", //https://github.com/tinymce/tinymce-vue/blob/main/src/demo/views/Iframe.vue
  init: {
    language: "zh_CN", //语言类型
    placeholder: "在这里输入文字", //textarea中的提示信息
    min_width: 320,
    min_height: 420,
    height: 500, //注：引入autoresize插件时，此属性失效
    resize: "both", //编辑器宽高是否可变，false-否,true-高可变，'both'-宽高均可，注意引号
    branding: false, //tiny技术支持信息是否显示
    // statusbar: false,  //最下方的元素路径和字数统计那一栏是否显示
    // elementpath: false, //元素路径是否显示
    skeletonScreen: true, //编辑器懒加载，但设置后好像不生效

    font_formats:
        "微软雅黑=Microsoft YaHei,Helvetica Neue,PingFang SC,sans-serif;苹果苹方=PingFang SC,Microsoft YaHei,sans-serif;" +
        "宋体=simsun,serif;仿宋体=FangSong,serif;黑体=SimHei,sans-serif;Arial=arial,helvetica,sans-serif;" +
        "Arial Black=arial black,avant garde;Book Antiqua=book antiqua,palatino;", //字体样式
    plugins:
        "print searchreplace autolink directionality visualblocks visualchars fullscreen image link media " +
        "code paste codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist " +
        "lists wordcount textpattern autosave emoticons", //插件配置 axupimgs indent2em
    toolbar: [
      "fullscreen undo redo restoredraft | cut copy paste pastetext | forecolor backcolor bold italic underline strikethrough link anchor " +
      "| alignleft aligncenter alignright alignjustify outdent indent | bullist numlist | blockquote subscript superscript removeformat ",
      "styleselect formatselect fontselect fontsizeselect |  table image axupimgs media emoticons charmap hr pagebreak insertdatetime  " +
      "selectall visualblocks searchreplace | code print | indent2em lineheight formatpainter",
    ], //工具栏配置，设为false则隐藏
    // menubar: "file edit my1", //菜单栏配置，设为false则隐藏，不配置则默认显示全部菜单，也可自定义配置--查看 http://tinymce.ax-z.cn/configure/editor-appearance.php --搜索“自定义菜单”

    // images_upload_url: '/apib/api-upload/uploadimg',  //后端处理程序的url，建议直接自定义上传函数image_upload_handler，这个就可以不用了
    // images_upload_base_path: '/demo',  //相对基本路径--关于图片上传建议查看--http://tinymce.ax-z.cn/general/upload-images.php
    paste_data_images: true, //图片是否可粘贴
    //此处为图片上传处理函数
    images_upload_handler: async (blobInfo, success, failure) => {
      // 这里用base64的图片形式上传图片,
      console.log("进入上传")
	  let reader = new FileReader(); // 创建 FileReader 对象
	  reader.readAsDataURL(await blobInfo.blob())// 将 Blob 对象转换为 base64 格式的数据 URL
	  
	   // 当读取操作完成时,触发 onload 事件
	    reader.onload = function() {
	      const base64String = reader.result.split(',')[1]; // 从数据 URL 中提取 base64 字符串
		  console.log('base64img',base64String)
	      success(base64String); // 将 base64 字符串传递给 TinyMCE 的成功回调函数
		  
	    }
		
		  // 如果读取操作发生错误,触发 onerror 事件
		  reader.onerror = function() {
		    failure('Failed to read the image file.');
		  }
		
      // let reader = new FileReader(); //本地预览
      // reader.readAsDataURL(blobInfo.blob());
      // reader.onloadend = function () {
      //   const imgbase64 = reader.result;
      //   success(imgbase64);
      // };


      // success("data:" + blobInfo.blob().type + ";base64," + blobInfo.base64());

    },

  },
});

const overviewForm = reactive({
  name: '余江概况-01',
  state: '开启'
})

const tableData = ref([
  {name:'zhangs', state:'开启', date: '2023-12-25 22:21:11'}
])

const overviewRules = reactive({
  name: [
    { required: true, message: '名称不能为空!', trigger: 'blur' },
    { min: 3, max: 15, message: '不能小于3个字，不能超过15个字', trigger: 'blur' },
  ]
})

const onClick = ()=>{
  console.log("内容", content)
}
const onStartOverview = ()=>{

}

const onOpenDetail = ()=>{

}

const onSave = ()=>{

}

/*export default {
  app:'YJ-overview',
  components: {Editor},
  setup(){

    const content = ref("默认文字 hello word");
    const tiny = reactive({
      apiKey: "qagffr3pkuv17a8on1afax661irst1hbr4e6tbv888sz91jc", //https://github.com/tinymce/tinymce-vue/blob/main/src/demo/views/Iframe.vue
      init: {
        language: "zh_CN", //语言类型
        placeholder: "在这里输入文字", //textarea中的提示信息
        min_width: 320,
        min_height: 220,
        height: 500, //注：引入autoresize插件时，此属性失效
        resize: "both", //编辑器宽高是否可变，false-否,true-高可变，'both'-宽高均可，注意引号
        branding: false, //tiny技术支持信息是否显示
        // statusbar: false,  //最下方的元素路径和字数统计那一栏是否显示
        // elementpath: false, //元素路径是否显示

        font_formats:
            "微软雅黑=Microsoft YaHei,Helvetica Neue,PingFang SC,sans-serif;苹果苹方=PingFang SC,Microsoft YaHei,sans-serif;宋体=simsun,serif;仿宋体=FangSong,serif;黑体=SimHei,sans-serif;Arial=arial,helvetica,sans-serif;Arial Black=arial black,avant garde;Book Antiqua=book antiqua,palatino;", //字体样式
        plugins:
            "print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template code codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists wordcount textpattern autosave emoticons", //插件配置 axupimgs indent2em
        toolbar: [
          "fullscreen undo redo restoredraft | cut copy paste pastetext | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | bullist numlist | blockquote subscript superscript removeformat ",
          "styleselect formatselect fontselect fontsizeselect |  table image axupimgs media emoticons charmap hr pagebreak insertdatetime  selectall visualblocks searchreplace | code print preview | indent2em lineheight formatpainter",
        ], //工具栏配置，设为false则隐藏
        // menubar: "file edit my1", //菜单栏配置，设为false则隐藏，不配置则默认显示全部菜单，也可自定义配置--查看 http://tinymce.ax-z.cn/configure/editor-appearance.php --搜索“自定义菜单”

        // images_upload_url: '/apib/api-upload/uploadimg',  //后端处理程序的url，建议直接自定义上传函数image_upload_handler，这个就可以不用了
        // images_upload_base_path: '/demo',  //相对基本路径--关于图片上传建议查看--http://tinymce.ax-z.cn/general/upload-images.php
        paste_data_images: true, //图片是否可粘贴
        //此处为图片上传处理函数
        images_upload_handler: (blobInfo, success, failure) => {
          // 这里用base64的图片形式上传图片,
          let reader = new FileReader(); //本地预览
          reader.readAsDataURL(blobInfo.blob());
          reader.onloadend = function () {
            const imgbase64 = reader.result;
            success(imgbase64);
          };
        },

        file_picker_types: "file image media", //file image media分别对应三个类型文件的上传：link插件，image和axupimgs插件，media插件。想屏蔽某个插件的上传就去掉对应的参数
        // 文件上传处理函数
        file_picker_callback: function (callback, value, meta) {
          // 使用案例http://tinymce.ax-z.cn/general/upload-images.php
          // meta.filetype  //根据这个判断点击的是什么file image media

          let filetype; //限制文件的上传类型,需要什么就添加什么的后缀
          if (meta.filetype == "image") {
            filetype = ".jpg, .jpeg, .png, .gif, .ico, .svg";
          } else if (meta.filetype == "media") {
            filetype = ".mp3, .mp4, .avi, .mov";
          } else {
            filetype =
                ".pdf, .txt, .zip, .rar, .7z, .doc, .docx, .xls, .xlsx, .ppt, .pptx, .mp3, .mp4, .jpg, .jpeg, .png, .gif, .ico, .svg";
          }
          let inputElem = document.createElement("input"); //创建文件选择
          inputElem.setAttribute("type", "file");
          inputElem.setAttribute("accept", filetype);
          inputElem.click();
          inputElem.onchange = () => {
            let file = inputElem.files[0]; //获取文件信息

            // 所有都转成base64文件流,来自官方文档https://www.tiny.cloud/docs/configure/file-image-upload/#file_picker_callback
            let reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
              // Note: Now we need to register the blob in TinyMCEs image blob
              // registry. In the next release this part hopefully won't be
              // necessary, as we are looking to handle it internally.
              let id = "blobid" + new Date().getTime();
              let blobCache = tinymce.activeEditor.editorUpload.blobCache;
              let base64 = reader.result.split(",")[1];
              let blobInfo = blobCache.create(id, file, base64);
              blobCache.add(blobInfo);

              // call the callback and populate the Title field with the file name
              callback(blobInfo.blobUri(), { title: file.name });
            };
          };
        },
      },
    });

    // 将变量和函数返回，以便在模版中使用
    return {
      content, ...toRefs(tiny)
    }
  }
};*/


</script>

<template>
<!--  余江概况-->
  <div>
    <div style="padding-left: 10px; text-align: left;">
      <h4>余江概况</h4>
    </div>

    <div style="display: flex;">
      <div style="width: 30%; border-right: 1px solid #ccc;">
        <el-table :data="tableData" style="width: 100%; padding: 5px;" row-class-name="row rowIndex">
          <!-- 1 -->
		  <el-table-column prop="name" label="名称" />
          <!-- 2 -->
		  <el-table-column prop="state" label="状态" width="60" >
            <template #default="scope">
              <el-tag :type="scope.row.state === '关闭' ? 'info' : 'success'" disable-transitions>
                {{ scope.row.state }}
              </el-tag>
            </template>
          </el-table-column>
		  
          <el-table-column prop="date" label="创建时间" width="162" />
          
		  <el-table-column fixed="right" label="操作" width="100">
            <template #default>
              <el-button link type="primary" size="small" @click="onStartOverview">启用</el-button>
              <el-button link type="primary" size="small" @click="onOpenDetail">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>

      </div>
	  
	  
      <div style="width: 70%;">
        <div class="mb-4 flex" style="text-align: left; margin: 5px 5px;">
          <el-form
              ref="overviewFormRef"
              :model="overviewForm"
              :rules="overviewRules"
              label-width="80px"
              class="demo-ruleForm"
              status-icon
          >
            <el-row>
<!--              <div class="custom-block tip" style="width: 100%;">-->
<!--                <div>余江概况-编辑</div>-->
<!--                <div></div>-->
<!--              </div>-->

              <div style="width: 100%">
                <h5>余江概况-编辑</h5>
                <el-divider content-position="left">1.点击左侧编辑后是修改 | 2.清除后直接保存是新增</el-divider>
              </div>


            </el-row>
            <el-row>
              <el-col :span="7">
                <el-form-item label="名称" prop="name">
                  <el-input v-model="overviewForm.name" />
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="是否开启" prop="state">
                  <el-switch v-model="overviewForm.state" active-value="开启" inactive-value="关闭" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-form-item label="内容">
              <div class="tinymce-boxz">
                <Editor v-model="content" :api-key="tiny.apiKey" :init="tiny.init" >
                  正在加载组件....
                </Editor>
              </div>
            </el-form-item>

            <el-form-item>
              <div style="width: 100%; text-align: center;">
                <el-button type="primary" @click="onSave"> 保存 </el-button>
                <el-button @click="onSave"> 清除操作 </el-button>
              </div>
            </el-form-item>

          </el-form>

        </div>


      </div>
    </div>


  </div>
</template>

<style scoped>
.tinymce-boxz > textarea {
  display: none;
}
</style>
<style>
/* 隐藏apikey没有绑定当前域名的提示 */
.tox-notifications-container .tox-notification--warning {
  display: none !important;
}

.tox.tox-tinymce {
  max-width: 100%;
}
</style>

<style lang="scss">

.custom-block.tip {
  padding: 8px 16px;
  background-color: rgb(64, 158, 255, .1);
  border-radius: 4px;
  border-left: 5px solid var(--el-color-primary);
  margin: 20px 0;
}
</style>
