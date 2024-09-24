// pages/rcpt/index03/index03_04/index03_04_01.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    policyIndex02:null,
    policyIndex02:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const app = getApp();
    console.log("跳转后",options)
    this.setData({
      jsonUsedData:app.globalData.jsonUsedData
    })
    // this.data.contentP = options.content
    this.setData({
      id:options.id,
    })
    console.log('hehe',this.data.jsonUsedData)
    this.setData({
      // policyContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
        policyIndex02:this.data.jsonUsedData[3].article
    })
    console.log('hehe',this.data.policyIndex02)
    const policyItem = this.data.policyIndex02.find(item => item.id == this.data.id)
    console.log("policyItem",policyItem)
     //let content='waiting data ing' 
    if(policyItem){
      this.setData({
        // content :policyItem.content.content.replace('<img ', '<img style="max-width:100%;height:auto;display:block;margin:10px 0;"') 
        content:  app.towxml(policyItem.content.content,'markdown')
      })
     }
     console.log('contttent',this.data.content)
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})