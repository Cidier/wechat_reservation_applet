
<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import {ref, reactive, onMounted, getCurrentInstance} from "vue";
import {getUserInfoName} from '@/utils/auth.js'


const { proxy } = getCurrentInstance();

const api = proxy.$api;
const request = proxy.$request;

const id = ref("")
const dialogFormVisible = ref(false)
const formLabelWidth = ref("120px")
const data = reactive([])
const rules = reactive({
  name: [{required: true, message: '请输入角色名称', trigger: 'blur'}],
  state: [{required: true, message: '请选择状态', trigger: 'change'}],
  permissionIds: [{required: true, message: '请选择权限', trigger: 'change'}],
})
const permissionlist = reactive([])
const permissionIds = reactive([])
const form = reactive({
  id: '',
  name: '',
  state: '',
  remarks: '',
  permissionIds: [],
})
const selectStateList = reactive( [
  {value: 1, label: '可用'},
  {value: 0, label: '不可用'}
])
const roleFormRef = ref(null)

///属性结束

function dataFormSubmitHandle() {
  roleFormRef.value.validate((valid) => {
    if (!valid) {
      return false
    }
    save()
  })
}
//删除
function handleDelete(index, roleId) {
  id.value = roleId;
  api.apiSysRoleDelete(id.value,data => {
    console.log(data);
    list();
    ElMessage({ type: 'info', message: '删除成功'})
  })
}

//添加弹窗
function addShow() {
  dialogFormVisible.value = true;
  clearForm();
}
//修改弹窗
function editShow(index, row) {
  api.apiSysRoleRoleById(row.id, res => {
        dialogFormVisible.value = true;
        form.id = res.vo.id;
        form.name = res.vo.name;
        form.remarks = res.vo.remarks;
        form.state = res.vo.state;
      }
  )
  editShowCascader(index, row);
}
function editShowCascader(index, row) {
  api.apiSysRolePids(row.id , res => {
    form.permissionIds = res.pids;
    console.log("role", res.pids)
  })
}
//取消
function resetForm() {
  dialogFormVisible.value = false;
  clearForm();
}

function clearForm() {
  form.id = '';
  form.name = '';
  form.state = '';
  form.remarks = '';
  form.permissionIds = [];
}
//删除弹窗
function deleteOpen(index, roleId) {
  ElMessageBox.confirm('此操作将删除该记录, 是否删除?', '提示',
      { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }
  )
      .then(() => {
        // ElMessage({ type: 'info', message: '点击删除'})
        handleDelete(index, roleId);
      })
      .catch(() => {
        ElMessage({ type: 'info', message: '已取消删除'})
      })

}
function permissionList() {
  api.apiSysPermissionMenuTree({}, res => {
    console.log(res);
    permissionlist.length = 0;
    res.permission.forEach(item=>permissionlist.push(item))
  })
}

function save() {
  console.log(form)
  let permissionIds = [];
  let pIdsList = form.permissionIds;

  pIdsList.forEach(item=> item.forEach(item2=>{
    if(!permissionIds.includes(item2)){
      //去重复 includes 检测数组中是否有某个值
      permissionIds.push(item2)
    }
  }))


  api.apiSysRoleSave({
    'id': form.id, 'state': form.state, 'remarks': form.remarks,
    'name': form.name, 'permissionIds': permissionIds,
  }, res => {
    console.log(form.permissionIds)
    list();
    resetForm();
  })
}

function list() {
  data.length = 0
  api.apiSysRoleList({}, res => {
    // console.log(data);
    res.roleList.forEach(item=>data.push(item))

  })
}

onMounted(()=>{
  list();
  permissionList();
})


</script>

<template>
  <div class="index">
    <el-card cardType="top">
      <el-card style="width: 100%" shadow="hover">
        <div  style="display: flex; margin: 15px;">
          <el-button style="margin-right: 1rem" type="primary" @click="addShow()" icon="Plus">添加角色</el-button>
          <el-input style="width: 25rem" prefix-icon="Search" placeholder="请输入搜索内容"></el-input>
        </div>

        <el-table :data="data" border style="width: 100%" max-height="700" ref="multipleTable">
          <el-table-column fixed type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" sortable width="80">
          </el-table-column>
          <el-table-column prop="name" label="角色名称" sortable width="150">
          </el-table-column>
          <el-table-column prop="state" label="角色状态" width="100">
            <template #default="scope">
              <el-tag v-if="scope.row.state===1">可用</el-tag>
              <el-tag type="info" v-if="scope.row.state===0">不可用</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" label="描述" sortable>
          </el-table-column>

          <el-table-column label="操作" width="340" fixed="right">
            <template #default="scope">
              <el-button type="primary" size="small" @click="editShow(scope.$index,scope.row)">修改</el-button>
              <el-button size="small" type="warning" @click="deleteOpen(scope.$index, scope.row.id)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-card>

    <!--添加弹窗-->
    <el-dialog v-model="dialogFormVisible"
       title="角色设置"
       width="1000"
    >
      <div style="display: flex; flex-direction: column; overflow: auto; max-height: 90%; padding: 2em;">
        <el-form :model="form" ref="roleFormRef" :rules="rules">
          <el-row :gutter="20">
            <el-col :span="10">
              <el-form-item prop="name" label="名称" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item prop="state" label="角色状态" :label-width="formLabelWidth">
                <el-select v-model="form.state" placeholder="请选择类型">
                  <el-option
                    v-for="item in selectStateList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="10">
              <el-form-item prop="permissionIds" label="添加权限" :label-width="formLabelWidth">
                <el-cascader placeholder="请选择权限"
                             clearable
                             :options="permissionlist"
                             v-model="form.permissionIds"
                             @change="handleChange"
                             :props="{
                                value:'id',
                                label:'name',
                                children:'childrens',
                                multiple: true
                            }"
                ></el-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item prop="remarks" label="描述" :label-width="formLabelWidth">
                <el-input v-model="form.remarks" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>


        </el-form>
      </div>
      <div class="bottom fixed-bottom">
        <el-button @click="resetForm()">取 消</el-button>
        <el-button type="primary" @click="dataFormSubmitHandle()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style scoped>

</style>
