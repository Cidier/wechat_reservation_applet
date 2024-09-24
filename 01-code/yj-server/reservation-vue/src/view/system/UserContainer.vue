<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import {ref, reactive, onMounted, getCurrentInstance} from "vue";
import {getUserInfoName} from '@/utils/auth.js'


const { proxy } = getCurrentInstance();

const api = proxy.$api;
const request = proxy.$request;
//属性定义
const sysRole = ref('sysNirs');
const roleCheckList = reactive([]);
const data = reactive([]);
const id = ref('');
const form = reactive({
  id: '',
  name: '',
  userName: '',
  state: '',
  type: '',
  nickName: '',
  email: '',
  remarks: '',
  roleIds: [],
});
const rules = reactive({
  name: [{required: true, message: '请输入角色名称', trigger: 'blur'}],
  userName: [{required: true, message: '请输入用户名', trigger: 'blur'}],
  state: [{required: true, message: '请选择状态', trigger: 'change'}],
  type: [{required: true, message: '请选择类型', trigger: 'change'}],
  nickName: [{required: false, message: '昵称', trigger: 'blur'}],
  email: [{required: false, message: '邮箱', trigger: 'blur'}],
  remarks: [{required: false, message: '备注', trigger: 'blur'}],
  roleIds: [{type: 'array', required: true, message: '请选择权限', trigger: 'change'}]
});
const selectStateList = [
  {value: '1',label: '可用'},
  {value: '0', label: '不可用'}
];
const selectTypeList = reactive([]);
const organizationDialog = reactive({
  dialogFormVisible: false,
  form: {
    userId: '',
    userName: '',
    organizationNo: '',
  },
  rules: {
    userId: [{required: true, message: '请输入用户名', trigger: 'blur'}],
    organizationNo: [{required: true, message: '请选择组织机构', trigger: 'blur'}],
  },
  formLabelWidth: '120px',
  organizationList: []
});
const orgListBox = reactive([]);
const formLabelWidth = ref('120px');
const dialogFormVisible = ref(false);
const pageInfo = reactive({
  current: 1,//当前第几页
  total: 0,//总记录
  size: 200, //一页多少条
  pages: 0 //几页
})

const userFormRef = ref(null)

//属性定义结束
function dataFormSubmitHandle() {
  userFormRef.value.validate((valid) => {
    if (!valid) {
      return false
    }
    console.log("调用了")
    save()
  })
}

function handleSizeChange(size) {
  pageInfo.size = size;
  list();
}

function handleCurrentChange(index) {
  pageInfo.current = index;
  list();
}
function handleSelectionChange(data) {
  console.log(data)
}
//添加弹窗
function addShow() {
  dialogFormVisible.value = true;
  form.id = '';
  form.name = '';
  form.userName = '';
  form.nickName = '';
  form.state = '';
  form.type = '';
  form.email = '';
  form.remarks = '';
  form.roleIds = [];
}

//取消
function resetForm() {
  dialogFormVisible.value = false;
  form.id = '';
  form.name = '';
  form.userName = '';
  form.nickName = '';
  form.state = '';
  form.type = '';
  form.email = '';
  form.remarks = '';
  form.roleIds = [];
}

function save() {
  console.log(form)

  //请求接口
  api.apiSysUserSave({
    'id': form.id, 'state': form.state, 'type': form.type,
    'name': form.name, 'userName': form.userName, 'nickName': form.nickName,
    'email': form.email, 'roleIds': form.roleIds,
    'remarks': form.remarks,
  }, data => {
    console.log(form.roleIds)
    resetForm();
    list();
  })
}
function pwdReset(index, row) {
  id.value = row.id;
  //请求接口
  api.apiSysUserResetPwd(id.value, data => {
    ElMessage({type: 'success', message: '重置成功!'})
  })
}
//重置密码弹窗
function resetOpen(index, row) {
  ElMessageBox.confirm('此操作将重置密码, 是否重置?', 'Wa提示rning',
      { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }
  ).then(() => {
      pwdReset(index, row);
    })
    .catch(() => {
      ElMessage({ type: 'info', message: '已取消重置'})
    })
}
//删除弹窗
function deleteOpen(index, id) {
  ElMessageBox.confirm('此操作将删除该记录, 是否删除?', '提示',
      { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }
  )
      .then(() => {
        // ElMessage({ type: 'info', message: '点击删除'})
        handleDelete(index, id);
      })
      .catch(() => {
        ElMessage({ type: 'info', message: '已取消删除'})
      })
}
//修改弹窗
function editShow(index, row) {
  //请求接口
  api.apiSysUserUserById(row.id, data => {
        dialogFormVisible.value = true;
        // console.log(data);
        form.id = data.vo.id;
        form.name = data.vo.name
        form.userName = data.vo.userName
        form.email = data.vo.email
        form.type = Number(data.vo.type)
        if (data.vo.state === 1) {
          form.state = "1"
        } else {
          form.state = "0"
        }
        form.nickName = data.vo.nickName
        form.remarks = data.vo.remarks
      }
  )
  editShowCheck(index, row);
}

function editShowCheck(index, row) {
  api.apiSysUserRoleRelationById({'userId': row.id}, data => {
    form.roleIds = data.bean
  })
}
//删除
function handleDelete(index, idVal) {
  id.value = idVal;
  //请求接口
  api.apiSysUserDelete(id.value, data => {
    console.log(data);
    list();
  })
}

function organizationOpen(index, row) {
  organizationDialog.form.userName = row.name;
  organizationDialog.form.userId = row.id;
  console.log(row)
  let orgNo = null;
  orgListBox.forEach((orgBean) => {
    if (orgBean.orgIdSeq === row.orgIdSeq) {
      orgNo = orgBean.orgNo;
    }
  })
  organizationDialog.form.organizationNo = orgNo;
  organizationDialog.dialogFormVisible = true;
}
function organizationBoxList() {
  // this.$request(this.$api.url.sysOrgListBox, 'GET', null, resp => {
  //   that.orgListBox = resp.listBox;
  //   const nodes = resp.listBox.map(item => ({
  //     value: item.orgNo,
  //     label: item.orgName
  //   }));
  //   that.organizationDialog.organizationList = nodes;
  // })
}

function resetOrgForm() {
  organizationDialog.form = {
    userId: '',
    userName: '',
    organizationNo: '',
  };
  organizationDialog.dialogFormVisible = false;
}
function onOrgAndUserFormSubmit() {
  console.log(organizationDialog.form)
  api.apiSysUserUpUserOrg({
    'userId': organizationDialog.form.userId,
    'organizationNo': organizationDialog.form.organizationNo
  }, data => {
    console.log(data)
    organizationDialog.dialogFormVisible = false;
    resetOrgForm();
    list();
  })
}
function list() {
  api.apiSysUserPage({
    'pageNo': pageInfo.current,
    'pageSize': pageInfo.size
  }, d => {
    data.length = 0;
    d.page.records.forEach(bean=>{ data.push(bean) })
    pageInfo.current = d.page.current
    pageInfo.total = d.page.total
    pageInfo.size = d.page.size
    pageInfo.pages = d.page.pages
    console.log(pageInfo)

    // userFormRef.value.doLayout()
  })

}
//展示role多选框
function roleList() {
  roleCheckList.length = 0
  api.apiSysRoleList( {}, checkList => {
    // console.log(checkList);
    checkList.roleList.forEach(item=>{
      roleCheckList.push(item)
    })
  })
}

onMounted(()=>{
  list();
  roleList();

  let userInfo = getUserInfoName();
  if(userInfo){
    userInfo = JSON.parse(getUserInfoName());
    console.log("userInfo", userInfo)

    if ('sys' === userInfo.role) {
      selectTypeList.push({value: 1, label: '系统管理员'})
      selectTypeList.push({value: 2, label: '企业管理员'})
      selectTypeList.push({value: 3, label: '普通用户'})
    } else if ('sysOrg' === userInfo.role) {
      selectTypeList.push({value: 2, label: '企业管理员'})
      selectTypeList.push({value: 3, label: '普通用户'})
    } else {
      selectTypeList.push({value: 3, label: '普通用户'})
    }
  }
})

</script>
<template>
  <div class="index">
    <el-card shadow="never">
      <el-card style="width: 100%" shadow="hover">
        <div style="display: flex; margin: 15px;">
          <el-button style="margin-right: 1rem" type="primary" @click="addShow()" icon="Plus">
            添加用户
          </el-button>
          <el-input style="width: 25rem" prefix-icon="Search" placeholder="请输入搜索内容"></el-input>
        </div>
        <el-table ref="userFormRef" border :data="data" style="width: 100%" height="650" @selection-change="handleSelectionChange">
          <el-table-column fixed type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" sortable width="80"></el-table-column>
          <el-table-column prop="userName" label="用户名" width="150"></el-table-column>
          <el-table-column prop="name" label="名称" width="100"></el-table-column>
          <el-table-column prop="email" label="Email" width="150"></el-table-column>
          <el-table-column prop="nickName" label="昵称" width="150"></el-table-column>
          <el-table-column prop="state" label="状态" width="150">
            <template #default="scope">
              <el-tag v-if="scope.row.state==='1'">可用</el-tag>
              <el-tag type="info" v-if="scope.row.state==='0'">不可用</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="remarks" label="备注"></el-table-column>
          <el-table-column label="操作" width="340" fixed="right">
            <template #default="scope">
              <el-button type="primary" size="small" @click="editShow(scope.$index,scope.row)">修改</el-button>
              <el-button size="small" type="danger" @click="deleteOpen(scope.$index, scope.row.id)">删除</el-button>
              <el-button type="warning" size="small" @click="resetOpen(scope.$index,scope.row)">重置密码</el-button>
<!--              <el-button type="warning" size="small" @click="organizationOpen(scope.$index,scope.row)">配置企业</el-button>-->
            </template>
          </el-table-column>
        </el-table>


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
      </el-card>
    </el-card>

    <!--添加弹窗-->
    <el-dialog v-model="dialogFormVisible"
      title="用户设置"
      width="1000"
    >
      <div style="display: flex; flex-direction: column; overflow: auto; max-height: 90%; padding: 2em;">
        <el-form :model="form" ref="userFormRef" :rules="rules">
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item prop="name" label="名称" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item prop="userName" label="用户名" :label-width="formLabelWidth">
                <el-input v-model="form.userName" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item prop="state" label="状态" :label-width="formLabelWidth">
                <el-select v-model="form.state" placeholder="请选择类型">
                  <el-option
                    v-for="item in selectStateList"
                    :key="item.state"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item prop="nickName" label="昵称" :label-width="formLabelWidth">
                <el-input v-model="form.nickName" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="10">
              <el-form-item prop="email" label="类型" :label-width="formLabelWidth">
                <el-select v-model="form.type" placeholder="请选择类型">
                  <el-option
                    v-for="item in selectTypeList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item prop="email" label="邮箱" :label-width="formLabelWidth">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <el-form-item prop="remarks" label="备注" :label-width="formLabelWidth">
                <el-input v-model="form.remarks" :rows="2" type="textarea" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-checkbox-group v-model="form.roleIds">
            <el-checkbox-button
              v-for="role in roleCheckList"
              :label="role.id"
              :key="role.id">
              {{ role.name }}
            </el-checkbox-button>
          </el-checkbox-group>

        </el-form>
      </div>
      <div class="dialog-footer">
        <el-button @click="resetForm(form)">取 消</el-button>
        <el-button type="primary" @click="dataFormSubmitHandle()">确 定</el-button>
      </div>
    </el-dialog>

    <!--配置公司弹窗-->
<!--    <el-dialog title="公司设置" :visible.sync="organizationDialog.dialogFormVisible"-->
<!--               :close-on-click-modal="false" :close-on-press-escape="false" :show-close="false">-->
<!--      <el-form :model="organizationDialog.form" ref="form" :rules="organizationDialog.rules">-->
<!--        <el-row :gutter="10">-->
<!--          <el-col :span="10">-->
<!--            <el-form-item prop="name" label="用户" :label-width="organizationDialog.formLabelWidth">-->
<!--              <el-input v-model="organizationDialog.form.userName" autocomplete="off" disabled></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span="10">-->
<!--            <el-form-item prop="organizationId" label="公司名称" :label-width="organizationDialog.formLabelWidth">-->
<!--              <el-select v-model="organizationDialog.form.organizationNo" placeholder="请选择类型">-->
<!--                <el-option-->
<!--                  v-for="item in organizationDialog.organizationList"-->
<!--                  :key="item.value"-->
<!--                  :label="item.label"-->
<!--                  :value="item.value">-->
<!--                </el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->

<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button @click="resetOrgForm()">取 消</el-button>-->
<!--        <el-button type="primary" @click="onOrgAndUserFormSubmit()">确 定</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

  </div>
</template>


<style>
.el-input.is-disabled .el-input__inner {
  background-color: #204960;
}
</style>
