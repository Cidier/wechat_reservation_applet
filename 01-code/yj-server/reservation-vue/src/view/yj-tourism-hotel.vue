<script setup>
import { onMounted, reactive, ref, getCurrentInstance, watch } from 'vue'
import { VXETable } from 'vxe-table'
import XEUtils from 'xe-utils'

const api = getCurrentInstance().appContext.config.globalProperties.$api;

console.log(api)



const drawerEditBean = ref({})
//在 Vue 3 中，ref 是一个响应式引用，用于创建响应式数据。当你使用 ref 创建一个响应式引用时，你需要调用该引用的方法（如 .value）来访问或修改其值。
//drawerEditBean.value.drawer 的值就会是 false，并且由于它是响应式的，任何依赖于此值的组件都会被更新。
drawerEditBean.value.drawer = false

const xGrid = ref()
const gridOptions = reactive({
  border: true,
  showHeaderOverflow: true,
  showOverflow: true,
  keepSource: true,
  id: 'yj-tourism-hotel',
  height: 600,
  //	行配置信息
  rowConfig: {
    keyField: 'id',
    isHover: true
  },
  //列配置信息
  columnConfig: {
    resizable: true
  },
  //	个性化信息配置项
  customConfig: {
    storage: true
  },
  //排序配置项
  sortConfig: {
    trigger: 'cell',
    remote: true
  },
  //筛选配置项
  filterConfig: {
    remote: true
  },
  //分页配置项
  pagerConfig: {
    enabled: true,
    // currentPage: 1,
    pageSize: 10,
    pageSizes: [5, 10, 15, 20, 50, 100, 200, 500, 1000]
  },
  //表单配置项
  formConfig: {
    titleWidth: 100,
    titleAlign: 'right',
    items: [// , folding: true 隐藏
      { field: 'title', title: '标题', span: 8, itemRender: { name: '$input', props: { placeholder: '请输入标题' } } },
      { field: 'state', title: '状态', span: 8, itemRender: { name: '$select', options: [{ label: '开启', value: '开启' }, { label: '关闭', value: '关闭' }] } },
      { span: 24, align: 'center', itemRender: { name: '$buttons', children: [{ props: { type: 'submit', content: '查询', status: 'primary' } }, { props: { type: 'reset', content: '清除' } }] } }
    ]
  },
  //工具栏配置
  toolbarConfig: {
    buttons: [ //左侧按钮列表
      { code: 'insert_actived', name: '新增' },
      { code: 'delete', name: '直接删除' },
      { code: 'mark_cancel', name: '删除/取消' },
      { code: 'save', name: '保存', status: 'success' }
    ],
    refresh: true,
    // import: true,
    // export: true,
    // print: true,
    zoom: true, //	是否允许最大化显示
    custom: true // 显示自定义列按钮
  },
  //数据代理配置项（基于 Promise API）
  proxyConfig: {
    seq: true,
    sort: true,
    filter: true,
    form: true,
    props: {
      // 对应响应结果 Promise<{ result: [], page: { total: 100 } }>
      result: 'result',
      total: 'page.total' // 配置响应结果总页数字段
    },
    // 只接收Promise，具体实现自由发挥
    ajax: {
      // 当点击工具栏查询按钮或者手动提交指令 query或reload 时会被触发
      query: ({ page, sorts, filters, form }) => {
        const queryParams = Object.assign({}, form)
        // 处理排序条件
        const firstSort = sorts[0]
        if (firstSort) {
          queryParams.sort = firstSort.field
          queryParams.order = firstSort.order
        }
        // 处理筛选条件
        filters.forEach(({ field, values }) => {
          queryParams[field] = values.join(',')
        })
        // return fetch(`${serveApiUrl}/api/pub/page/list/${page.pageSize}/${page.currentPage}?${XEUtils.serialize(queryParams)}`).then(response => response.json());
        return fetch(`/api/pub/page/list/${page.pageSize}/${page.currentPage}?${XEUtils.serialize(queryParams)}`).then(response => response.json());
      },
      // 当点击工具栏删除按钮或者手动提交指令 delete 时会被触发
      delete: ({ body }) => {
        // return fetch(`${serveApiUrl}/api/pub/save`, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body) }).then(response => response.json())
        return fetch(`/api/pub/save`, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body) }).then(response => response.json())
      },
      // 当点击工具栏保存按钮或者手动提交指令 save 时会被触发
      save: ({ body }) => {
        // return fetch(`${serveApiUrl}/api/pub/save`, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body) }).then(response => response.json())
        return fetch(`/api/pub/save`, { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(body) }).then(response => response.json())
      }
    }
  },
  //列配置
  columns: [
    { type: 'checkbox', title: 'ID', width: 120 },
    { field: 'title', title: '标题', sortable: true, titlePrefix: { message: '标题必须填写！' }, editRender: { name: 'input', attrs: { placeholder: '请输入标题' } } },
    { field: 'remark', title: '描述', width: 160, editRender: { name: '$input', props: { placeholder: '请输入描述信息' } } },
    {
      field: 'state',
      title: '状态',
      filters: [
        { label: '开启', value: '开启' },
        { label: '关闭', value: '关闭' }
      ],
      editRender: { name: '$select', options: [], props: { placeholder: '请选择状态' } }
    },
    {
      field: 'createDate',
      title: '创建时间',
      width: 160,
      visible: true,
      sortable: true,
      formatter ({ cellValue }) {
        return XEUtils.toDateString(cellValue, 'yyyy-MM-dd')
      }
    }
  ],
  //复选框配置项
  checkboxConfig: {
    labelField: 'id',
    reserve: true,
    highlight: true,
    range: true
  },
  //校验规则配置项
  editRules: {
    title: [
      { required: true, message: '标题' },
      { min: 3, max: 50, message: '名称长度在 3 到 50 个字符' }
    ]
  },
  //可编辑配置项
  editConfig: {
    trigger: 'click',
    mode: 'row',
    showStatus: true
  },
  //右键菜单配置项
  menuConfig:{
    body: {
      options: [
        [
          { code: 'editContent', name: '编辑内容', prefixIcon: 'vxe-icon-edit', visible: true, disabled: false }
        ]
      ]
    }
  }
})
const gridEvent = {
  proxyQuery () {
    console.log('数据代理查询事件')
  },
  proxyDelete () {
    console.log('数据代理删除事件')
  },
  proxySave () {
    console.log('数据代理保存事件')
  },
  menuClick ({ menu, row, column }){
    console.log('点击了编辑内容', menu, row, column)
    drawerEditBean.value.drawer = true;
  }
}

onMounted(() => {
  const stateList = [
    { label: '开启', value: '开启' },
    { label: '关闭', value: '关闭' }
  ]
  const { formConfig, columns } = gridOptions
  if (columns) {
    const stateColumn = columns[3]
    if (stateColumn && stateColumn.editRender) {
      stateColumn.editRender.options = stateList
    }
  }
  if (formConfig && formConfig.items) {
    const stateItem = formConfig.items[4]
    if (stateItem && stateItem.itemRender) {
      stateItem.itemRender.options = stateList
    }
  }
})


</script>

<template>
  <div style="width: 100%; display: flex;">
    <div style="width: 99%; padding: 5px;">
      <vxe-grid ref='xGrid' v-bind="gridOptions" v-on="gridEvent"></vxe-grid>
    </div>

    <el-drawer v-model="drawerEditBean.drawer" title="I am the title" :with-header="false">
      <span>Hi there!</span>
    </el-drawer>

  </div>
</template>

<style scoped>

</style>