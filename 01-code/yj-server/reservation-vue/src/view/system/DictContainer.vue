<script setup>
import { ElMessage, ElMessageBox } from 'element-plus'
import {ref, reactive, onMounted, getCurrentInstance} from "vue";


const { proxy } = getCurrentInstance();

const api = proxy.$api;
const request = proxy.$request;

const pageList = reactive([])
const drawer = ref(false)
const drawer1 = ref(false)
const activeName = ref("")
const checkList = reactive([])
const radio = ref("")
const pageInfo = reactive({
  current: 1, //当前第几页
  total: 0, //总记录
  size: 10, //一页多少条
  pages: 0, //几页
})

const dictDrawer = reactive({
  drawerVisible: false,
  size:"1000px",
  direction: "rtl",
  form: {
    id: '',
    parentId: '',
    state: 1,
    code: '',
    dictKey: '',
    dictValue: '',
    sort: '',
    remark: '',
  },
  rules:{
    code: [{required: true, message: '请输入字典编号', trigger: 'blur'}],
    dictKey: [{required: true, message: '请输入字典键值', trigger: 'blur'}],
    dictValue: [{required: true, message: '请选择字典名称', trigger: 'blur'}]
  }
})

const dictConfigDrawer = reactive({
  drawerVisible: false,
  size:"1000px",
  direction: "rtl",
  parentId: null,
  dictList:[],
  dictInnerDrawer:{
    drawerVisible: false,
    size:"700px",
    direction: "rtl",
    form: {
      id: '',
      parentId: '',
      innerParentId: '',//相当于在里面点击加入子项。
      state: 1,
      code: '',
      dictKey: '',
      dictValue: '',
      sort: '',
      remark: '',
    },
    rules:{
      code: [{required: true, message: '请输入字典编号', trigger: 'blur'}],
      dictKey: [{required: true, message: '请输入字典键值', trigger: 'blur'}],
      dictValue: [{required: true, message: '请选择字典名称', trigger: 'blur'}]
    }
  },
  bodyMenus:[
    [
      { code: 'clearLoaded', name: '清除加载状态', disabled: false },
      { code: 'reloadNodes', name: '重新加载子节点', disabled: false },
      { code: 'expand', name: '展开节点', disabled: false },
      { code: 'contract', name: '收起节点', disabled: false }
    ]
  ]
})

const dictForm = reactive({
  name:"",
  state:"",
  remarks:"",
  rule:""
})
const table = ref(null)
const dictFormRef = ref(null)
const dictInnerFormRef = ref(null)
const childTableRef = ref(null)
// 属性结束

function list() {
  api.apiSysBasicDictPageList({pageNo: pageInfo.current, pageSize: pageInfo.size,}
      ,(res) => {
      console.log(res);
      pageList.length = 0
      res.pageList.records.forEach(item=>{pageList.push(item)})
      pageInfo.current = res.pageList.current;
      pageInfo.total = res.pageList.total;
      pageInfo.size = res.pageList.size;
    }
  );
}

function handleSizeChange(size) {
  pageInfo.size = size;
  list();
}

function handleCurrentChange(index) {
  pageInfo.current = index;
  list();
}

function editShow(row){
  api.apiSysBasicDictFindById(row.id, res => {
        dictDrawer.drawerVisible = true;
        console.log("res.vo", res)
        Object.keys(res.vo).forEach(key=>{
          // console.log(key)
          dictDrawer.form[key] = res.vo[key];
        })
      }
  )
}
function onResetDict(){
  // dictDrawer.form = { id: '', parentId: '', state: 1, code: '', dictKey: '', dictValue: '', sort: '', remark: ''}
  Object.keys(dictDrawer.form).forEach(key=>{
    dictDrawer.form[key] = "";
  })
  dictDrawer.drawerVisible = false;
}
function onSaveDict(){
  dictFormRef.value.validate((valid) => {
    if (!valid) {
      return false
    }
    console.log("请求")
    let dictForm = {}
    Object.keys(dictDrawer.form).forEach(key=>{
      dictForm[key] = dictDrawer.form[key];
    })
    api.apiSysBasicDictSave(dictForm,
        (res) => {
          console.log(res);
          ElMessage({type: 'success', message: '保存成功!'})
          onResetDict();
          list();
        }
    );
  })
}

function listDictConfig(){
  api.apiSysBasicDictList({parentId: dictConfigDrawer.parentId},
      (res) => {
        console.log(res);
        dictConfigDrawer.dictList.length = 0;
        Object.keys(res.list).forEach(key=>{
          dictConfigDrawer.dictList[key] = res.list[key];
        })
      }
  );
}

function editDictConfig(row){
  //外部点击加入子项。相当于一级字典加入二级字典
  dictConfigDrawer.parentId = row.id;
  dictConfigDrawer.innerParentId = row.id;
  dictConfigDrawer.drawerVisible= true;

  listDictConfig();
}
function editInnerDictConfig(row){
  //二级字典加入三级字典，或N级字典加入N+1级字典
  //这里考虑二级禁止分页问题。
  dictConfigDrawer.dictInnerDrawer.form.parentId = row.id;
  dictConfigDrawer.dictInnerDrawer.drawerVisible = true;
}

function onInnerResetDict(){
  Object.keys(dictConfigDrawer.dictInnerDrawer.form).forEach(key=>{
    dictConfigDrawer.dictInnerDrawer.form[key] = "";
  })
  dictConfigDrawer.dictInnerDrawer.form.state = 1;
  dictConfigDrawer.dictInnerDrawer.drawerVisible = false;
}

function onInnerSaveDict(){
  dictInnerFormRef.value.validate((valid) => {
    if (!valid) {
      return false
    }
    console.log("请求")
    let dictForm = {}
    Object.keys(dictConfigDrawer.dictInnerDrawer.form).forEach(key=>{
      dictForm[key] = dictConfigDrawer.dictInnerDrawer.form[key];
    })
    api.apiSysBasicDictSave(dictForm ,
        (data) => {
          console.log(data);
          ElMessage({type: 'success', message: '保存成功!'})
          onInnerResetDict();
          listDictConfig();
        }
    );
  })
}

function onDictInnerShow(){
  dictConfigDrawer.dictInnerDrawer.form.parentId = dictConfigDrawer.parentId;
  dictConfigDrawer.dictInnerDrawer.drawerVisible = true;
}

function editInnerShow(row){
  api.apiSysBasicDictFindById(row.id, res => {
      dictConfigDrawer.dictInnerDrawer.drawerVisible = true;
      Object.keys(res.vo).forEach(key=>{
        // console.log(key)
        dictConfigDrawer.dictInnerDrawer.form[key] = res.vo[key];
      })
    }
  )
}
//删除弹窗
function deleteOpen(id, isInner) {
  ElMessageBox.confirm('此操作将删除该记录, 是否删除?', '提示',
      { confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' }
  ).then(() => {
    handleDelete(id, isInner);
  }).catch(() => {
    ElMessage({ type: 'info', message: '已取消删除'})
  })
}
function handleDelete(id, isInner){
  api.apiSysBasicDictDeleteById(id, res => {
    console.log(res);
    isInner ? list() : listDictConfig();
  })
}

function visibleMethod  ({ row, type }) {
  let xTree = childTableRef.value
  if (type === 'body') {
    this.bodyMenus.forEach(list => {
      list.forEach(item => {
        if (['clearLoaded', 'reloadNodes'].includes(item.code)) {
          item.disabled = !row.hasChild || !xTree.isTreeExpandLoaded(row)
        } else if (['expand', 'contract'].includes(item.code)) {
          if (row.hasChild) {
            let isExpand = xTree.isTreeExpandByRow(row)
            item.disabled = ['expand'].includes(item.code) ? isExpand : !isExpand
          } else {
            item.disabled = true
          }
        }
      })
    })
  }
  return true
}

function loadChildrenMethod({ row }) {
  // 异步加载子节点
  console.log("子字典：", row.id)
  return new Promise(resolve => {
    setTimeout(() => {

      api.apiSysBasicDictList({parentId: row.id},
          (res) => {
            console.log(res);
            resolve(res.list)
          }
      );

    }, 300)
  })

}

function contextMenuClickEvent ({ menu, row, column }) {
  let xTree = childTableRef.value
  switch (menu.code) {
    case 'clearLoaded':
      xTree.clearTreeExpandLoaded(row)
      break
    case 'reloadNodes':
      xTree.reloadTreeExpand(row)
      break
    case 'expand':
      xTree.setTreeExpand(row, true)
      break
    case 'contract':
      xTree.setTreeExpand(row, false)
      break
  }
}

onMounted(()=>{
  list();
})


</script>

<template>
  <div class="index">
    <el-card shadow="never">
      <el-card cardType="top">
        <div style="display: flex; margin: 15px;">
          <el-button style="margin-right: 1rem" type="primary" @click="dictDrawer.drawerVisible = true" icon="Plus">
            添加字典
          </el-button>
          <el-input style="width: 25rem" prefix-icon="Search" placeholder="请输入搜索内容"></el-input>
        </div>

        <el-table border style="width: 100%; " ref="table"
                  max-height="500"
          :header-cell-style="{ textAlign: 'center' }"
          :data="pageList"
        >
          <el-table-column fixed type="selection" width="55"></el-table-column>
          <el-table-column prop="id" width="80" label="序号">
          </el-table-column>
          <el-table-column prop="dictValue" width="150" show-overflow-tooltip label="字典名称">
          </el-table-column>
          <el-table-column prop="code" width="120" show-overflow-tooltip label="字典编号">
          </el-table-column>
          <el-table-column prop="dictKey" width="150" show-overflow-tooltip label="字典键值">
          </el-table-column>
          <el-table-column prop="state" width="100" show-overflow-tooltip label="规则状态">
            <template #default="scope">
              <div>
                <el-tag v-if="scope.row.state===1">开启</el-tag>
                <el-tag type="info" v-if="scope.row.state===0">关闭</el-tag>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="sort" width="80" label="排序">
          </el-table-column>
          <el-table-column prop="remark" label="描述">
          </el-table-column>
<!--          <el-table-column prop="ctime" width="180" label="创建时间">-->
<!--            <template #default="scope">-->
<!--              <span>{{ scope.row.ctime }}</span>-->
<!--            </template>-->
<!--          </el-table-column>-->
<!--          <el-table-column prop="mtime" width="180" label="修改时间">-->
<!--            <template #default="scope">-->
<!--              <span>{{ scope.row.mtime }}</span>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column fixed="right" width="300" label="操作">
            <template #default="scope">
              <div>
                <el-button type="primary" size="small" @click="editShow(scope.row)" >编辑</el-button>
                <el-button type="warning" size="small" @click="editDictConfig(scope.row)" >字典配置</el-button>
                <el-button size="small" type="danger" @click="deleteOpen(scope.row.id, true)" >删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>

        <el-pagination
          background
          style="margin: 15px;"
          layout="sizes,total, prev, pager, next,jumper"
          @current-change="handleCurrentChange"
          :page-sizes="[10, 20, 30, 40]"
          @size-change="handleSizeChange"
          :page-size="pageInfo.size"
          :total="pageInfo.total"
          :current-page="pageInfo.current"
        >
        </el-pagination>
      </el-card>
    </el-card>

    <!-- 字典设置 -->
    <el-drawer title="字典设置" :size="dictDrawer.size" v-model="dictDrawer.drawerVisible" :direction="dictDrawer.direction" @close="onResetDict()">

      <div class="box1">
        <el-form :model="dictDrawer.form" ref="dictFormRef" :rules="dictDrawer.rules" label-width="80px" style="margin-left: 2rem" label-position="left">
          <el-row>
            <el-col :span="24">
              <el-form-item prop="code" label="字典编号">
                <el-input v-model="dictDrawer.form.code" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
<!--          <el-row>-->
<!--            <el-col :span="24">-->
<!--              <el-form-item label="上级字典">-->
<!--                <el-input placeholder="请输入" :disabled="true"></el-input>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--          </el-row>-->

          <el-row>
            <el-col :span="12">
              <el-form-item prop="dictValue" label="字典名称">
                <el-input v-model="dictDrawer.form.dictValue" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="dictKey" label="字典键值">
                <el-input v-model="dictDrawer.form.dictKey" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="字典状态">
                <el-radio-group v-model="dictDrawer.form.state">
                  <el-radio :label="1">开启</el-radio>
                  <el-radio :label="0">关闭</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="字典排序">
                <el-input-number v-model="dictDrawer.form.sort" :min="1" label="请输入"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="24">
              <el-form-item label="字典备注">
                <el-input v-model="dictDrawer.form.remark" placeholder="请输入"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
      </div>
      <div class="bottom fixed-bottom">
        <el-button @click="onResetDict()">取消</el-button>
        <el-button type="primary" @click="onSaveDict()">确定</el-button>
      </div>
    </el-drawer>


    <!-- 字典配置设置 -->
    <el-drawer title="字典配置设置" :size="dictConfigDrawer.size"
               v-model="dictConfigDrawer.drawerVisible" :direction="dictConfigDrawer.direction" @close="dictConfigDrawer.drawerVisible = false">
      <!-- 内嵌字典新增修改 -->
      <el-drawer title="字典子级设置" :append-to-body="true" @close="onInnerResetDict()"
                 :size="dictConfigDrawer.dictInnerDrawer.size" v-model="dictConfigDrawer.dictInnerDrawer.drawerVisible"
                 :direction="dictConfigDrawer.dictInnerDrawer.direction">
        <div class="box1">
          <el-form :model="dictConfigDrawer.dictInnerDrawer.form" ref="dictInnerFormRef"
                   :rules="dictConfigDrawer.dictInnerDrawer.rules" label-width="80px" style="margin-left: 2rem" label-position="left">
            <el-row>
              <el-col :span="24">
                <el-form-item prop="code" label="字典编号">
                  <el-input v-model="dictConfigDrawer.dictInnerDrawer.form.code" placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <el-form-item prop="parentId" label="上级字典">
                  <el-input v-model="dictConfigDrawer.dictInnerDrawer.form.parentId" placeholder="请输入" :disabled="true"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item prop="dictValue" label="字典名称">
                  <el-input v-model="dictConfigDrawer.dictInnerDrawer.form.dictValue" placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="dictKey" label="字典键值">
                  <el-input v-model="dictConfigDrawer.dictInnerDrawer.form.dictKey" placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="字典状态">
                  <el-radio-group v-model="dictConfigDrawer.dictInnerDrawer.form.state">
                    <el-radio :label="1">开启</el-radio>
                    <el-radio :label="0">关闭</el-radio>
                  </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="字典排序">
                  <el-input-number v-model="dictConfigDrawer.dictInnerDrawer.form.sort" :min="1" label="请输入"></el-input-number>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <el-form-item label="字典备注">
                  <el-input v-model="dictConfigDrawer.dictInnerDrawer.form.remark" placeholder="请输入"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
        </div>
        <div class="bottom fixed-bottom">
          <el-button @click="onInnerResetDict()">取消</el-button>
          <el-button type="primary" @click="onInnerSaveDict()">确定</el-button>
        </div>

      </el-drawer>

      <el-card shadow="never">
        <el-card style="width: 100%" shadow="hover">

          <div style="display: flex; margin: 15px;">
            <el-button type="primary" @click="onDictInnerShow()">添加字典</el-button>
          </div>
          <div class="box1">
            <el-table
                border
                resizable
                show-overflow
                style="width: 100%;margin-top: 2rem"
                ref="childTableRef"
                show-header-overflow
                stripe
                export-config
                max-height="600px"
                :menu-config="{body: {options: dictConfigDrawer.bodyMenus}, visibleMethod}"
                :tree-config="{lazy: true, transform: true, rowField: 'id', parentField: 'parentId', hasChild: 'hasChild', loadMethod: loadChildrenMethod}"
                :header-cell-style="{ textAlign: 'center' }"
                @menu-click="contextMenuClickEvent"
                :data="dictConfigDrawer.dictList"
            >
              <el-table-column prop="id" width="80" label="序号" tree-node>
              </el-table-column>
              <el-table-column prop="dictValue" width="180" show-overflow-tooltip label="字典名称">
              </el-table-column>
              <el-table-column prop="code" width="130" show-overflow-tooltip label="字典编号">
              </el-table-column>
              <el-table-column prop="dictKey" width="130" show-overflow-tooltip label="字典键值">
              </el-table-column>
              <el-table-column prop="state" width="100" show-overflow-tooltip label="规则状态">
                <template #default="scope">
                  <div>
                    <el-tag v-if="scope.row.state===1">开启</el-tag>
                    <el-tag type="info" v-if="scope.row.state===0">关闭</el-tag>
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="sort" width="80" label="排序">
              </el-table-column>
              <el-table-column prop="remark" label="描述" min-width="300" fit="false">
              </el-table-column>
              <!--          <el-table-column prop="ctime" width="180" label="创建时间">-->
              <!--            <template #default="scope">-->
              <!--              <span>{{ scope.row.ctime }}</span>-->
              <!--            </template>-->
              <!--          </el-table-column>-->
              <!--          <el-table-column prop="mtime" width="180" label="修改时间">-->
              <!--            <template #default="scope">-->
              <!--              <span>{{ scope.row.mtime }}</span>-->
              <!--            </template>-->
              <!--          </el-table-column>-->
              <el-table-column fixed="right" width="300" label="操作">
                <template #default="scope">
                  <div>
                    <el-button type="primary" size="small" @click="editShow(scope.row)" >编辑</el-button>
                    <el-button type="warning" size="small" @click="editDictConfig(scope.row)" >字典配置</el-button>
                    <el-button size="small" type="danger" @click="deleteOpen(scope.row.id, false)" >删除</el-button>
                  </div>
                </template>
              </el-table-column>
            </el-table>

          </div>
        </el-card>
      </el-card>

      <div class="bottom fixed-bottom" style="margin: 15px">
        <el-button @click="dictConfigDrawer.drawerVisible = false">取消</el-button>
        <el-button type="primary" @click="dictConfigDrawer.drawerVisible = false">确定</el-button>
      </div>
    </el-drawer>

  </div>
</template>


<style scoped>
</style>
