// components/footer/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {

  },

  /**
   * 组件的方法列表
   */
  methods: {
    onTabber(e){
      var route = e.currentTarget.dataset.route;
      console.log(route)
      let url = '/pages/rcpt/'+route+'/index'
      wx.reLaunch({url: url})
      console.log(url)
      // var myEventDetail = {route} // detail对象，提供给事件监听函数
      // var myEventOption = {} // 触发事件的选项
      // this.triggerEvent('onFooterTabber', myEventDetail, myEventOption)
    }
  }
})
