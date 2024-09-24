
<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import {ref, reactive, onMounted, getCurrentInstance} from "vue";
import {getUserInfoName} from '@/utils/auth.js'


const { proxy } = getCurrentInstance();

const api = proxy.$api;
const request = proxy.$request;

const dialogFormVisible = ref(false)
const formLabelWidth = ref("120px")
const parentId = ref("")
const data = reactive([])
const options = reactive([])
// const selectedOptions = reactive([])
const form = reactive({
  id: "",
  menuTitle: "",
  sortOrder: "",
  // url: "",
  // routeUrl: "",
  type: "",
  state: 0,
  // icon: "",
  parentId: "",
  remark:"",
  // precode: "",
  selectedOptions: [],
})
const rules = reactive( {
  name: [{ required: true, message: "请输入权限名称", trigger: "blur" }],
  od: [{ required: true, message: "请输入排序", trigger: "blur" }],
  // url: [{required: true, message: '请输入地址', trigger: 'blur'}],
  type: [{ required: true, message: "请选择类型", trigger: "change" }],
  state: [{ required: true, message: "请选择状态", trigger: "change" }],
  //parentId: [{required: true, message: '请选择上级资源', trigger: 'change'}],
  // precode: [{ required: true, message: "请填写权限代码", trigger: "blur" }],
})
const selectTypeList = reactive([
  {value: "menu", label: "菜单"},
  {value: "permission", label: "功能"},
])
const selectStateList = reactive([
  {value: 1, label: "正常"},
  {value: 0, label: "禁用"},
])

const permissionFormRef = ref(null)
///属性结束
//表单提交验证
function dataFormSubmitHandle() {

  permissionFormRef.value.validate((valid) => {
    if (!valid) {
      return false;
    }
    save();
  });
}

function list() {
  api.apiCmsContentMenuTreeList({}, (res) => {
    data.length = 0
    console.log(res);
    res.menu.forEach(item=>data.push(item))
    console.log(data)
  });
}
//弹窗上级资源下拉菜单
function listByMenu() {
  api.apiCmsContentMenuTree( {}, (res) => {
    options.length = 0
    res.menu.forEach(item=>options.push(item))
    console.log(res.menu, "打印options");
  });

}

//将id赋为parentId
function idChange() {
  form.parentId = form.selectedOptions[form.selectedOptions.length - 1];
  console.log(form.selectedOptions,"Sssssssssssssssssss");
}
function save() {
  console.log("要save的form_id",form.id)

  api.apiCmsContentMenuSave(
      {
        id: form.id,
        menuTitle: form.menuTitle,
        type: form.type,
        state: form.state,
        // icon: form.icon,
        parentId: form.parentId,
        remark: form.remark,
        sortOrder: form.sortOrder

      },
      (res) => {
        console.log(form.selectedOptions);
        list();
        dialogFormVisible.value = false;
        resetFormData();
      }
  );
}
function addShow() {
  dialogFormVisible.value = true;
  resetFormData();
}
//取消
function resetForm() {
  dialogFormVisible.value = false;
  resetFormData();
}
function resetFormData(){
  form.id = "";
  form.menuTitle = "";
  form.sortOrder = "";
  form.type = "";
  form.state = 0;
  form.parentId = "";
  form.remark = "";
  form.selectedOptions = [];
}
//修改弹窗
function editShow(index, row) {

  api.apiCmsContentMenuById(row.id ,
      (res) => {
        dialogFormVisible.value = true;
        console.log(res);
        form.id = res.vo.id;
        form.menuTitle = res.vo.menuTitle;
        form.sortOrder = res.vo.sortOrder;
        form.state = res.vo.state;
        form.type = res.vo.type;
        form.parentId = res.vo.parentId;
        form.remark = res.vo.remark;
        form.selectedOptions = res.vo.parentId;
        // console.log(selectedOptions)
      }
  );
}
const deleteOpen = function (index,row){
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
    // articleList();

  })

  list();

}


onMounted(()=>{
  list();
  listByMenu();
})


</script>

<template>
  <div class="index">
    <el-card cardType="top">
      <el-card style="width: 100%" shadow="hover">
        <div style="display: flex; margin: 15px;">
          <el-button style="margin-right: 1rem" type="primary" @click="addShow()" icon="Plus">添加权限</el-button>
          <el-input style="width: 25rem" prefix-icon="Search" placeholder="请输入搜索内容"></el-input>
        </div>

        <!--      list显示  default-expand-all-->
        <el-table
            :data="data"
            border
            style="width: 100%"
            max-height="600"
            ref="multipleTable"
            row-key="id"
            :tree-props="{ children: 'childrens', hasChildren: 'hasChildren' }"
        >
          <el-table-column type="selection" ></el-table-column>

          <el-table-column type="index" width="200">
          </el-table-column>

          <el-table-column prop="menuTitle" parent-key="parentId" label="名称" sortable width="300">
          </el-table-column>

          <el-table-column prop="type" label="类型" width="80">
            <template #default="scope">
              <el-tag v-if="scope.row.type === 'menu'">
                菜单
              </el-tag>
              <el-tag v-if="scope.row.type === 'permission'">
                功能
              </el-tag>
            </template>
          </el-table-column>

          <el-table-column prop="state" label="状态" width="100" header-align="center">
            <template #default="scope">
              <el-tag v-if="scope.row.state === 1">
                <el-icon><CircleCheck /></el-icon>
                正常
              </el-tag>
              <el-tag type="info" v-if="scope.row.state === 0">
                <el-icon><CircleClose /></el-icon>
                禁用
              </el-tag>
            </template>
          </el-table-column>

          <el-table-column prop="sortOrder" label="排序" width="100"> </el-table-column>
          <!--          <el-table-column prop="parentId" label="上级" width="80"></el-table-column>-->
          <!--          <el-table-column prop="url" label="地址" width="350"> </el-table-column>-->
          <!--          <el-table-column prop="routeUrl" label="地址" width="350"> </el-table-column>-->
          <el-table-column prop="remark" label="备注" width="200">
          </el-table-column>
          <el-table-column label="操作" width="180" fixed="right">
            <template #default="scope">
              <el-button type="primary" size="small" @click="editShow(scope.$index, scope.row)">修改</el-button>
              <el-button   plain type="danger" @click="deleteOpen(scope.$index,scope.row)">删除</el-button>

            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-card>


    <el-dialog
        title="权限设置"
        v-model="dialogFormVisible"
        @open="listByMenu()"
    >
      <el-form :model="form" ref="permissionFormRef" :rules="rules">
        <el-form-item prop="menuTitle" label="名称" :label-width="formLabelWidth">
          <el-input v-model="form.menuTitle" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="sortOrder" label="排序" :label-width="formLabelWidth">
          <el-input v-model="form.sortOrder" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="type" label="类型" :label-width="formLabelWidth">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option
                v-for="item in selectTypeList"
                :key="item.type"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="state" label="状态" :label-width="formLabelWidth">
          <el-select v-model="form.state" placeholder="请选择类型">
            <el-option
                v-for="item in selectStateList"
                :key="item.state"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="remark" label="备注" :label-width="formLabelWidth">
          <el-input v-model="form.remark" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
            prop="parentId"
            label="上级资源"
            :label-width="formLabelWidth"
        >
          <el-cascader
              placeholder="请选择上级资源"
              :options="options"
              v-model="form.selectedOptions"
              @change="idChange()"
              :props="{
              value: 'id',
              label: 'menuTitle',
              children: 'childrens',
              checkStrictly: true,
            }"
              clearable
          ></el-cascader>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm()">取 消</el-button>
        <el-button type="primary" @click="dataFormSubmitHandle()"
        >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>

.completed {
  display: inline-block;
  width: 8rem;
  height: 24px;
  border-radius: 12px;
  background: #00c46f;
  text-align: center;

  i {
    font-size: 12px;
    color: #fff;
  }
}
.hangInTheAir {
  @extend .completed;
  background: #ff5268;
}
</style>
