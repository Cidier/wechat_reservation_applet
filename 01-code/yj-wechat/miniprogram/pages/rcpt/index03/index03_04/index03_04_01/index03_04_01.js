// pages/rcpt/index03/index03_04/index03_04_01.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    kaochadianIndex02:null,
    kaochadianIndex02:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const app = getApp();
    console.log("跳转后",options)
    // this.data.contentP = options.content
    this.setData({
      id:options.id,
    })
    this.setData({
      // kaochadianContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
      kaochadianIndex02:app.globalData.jsonUsedData[1].childrens[3].article
    })
    console.log('hehe',this.data.kaochadianIndex02)
    const kaochadianItem = this.data.kaochadianIndex02.find(item => item.id == this.data.id)
    console.log("kaochadianItem",kaochadianItem)
     //let content='waiting data ing' 
    if(kaochadianItem){
      this.setData({
        // content :kaochadianItem.content.content.replace('<img ', '<img style="max-width:100%;height:auto;display:block;margin:10px 0;"') 
        content:  app.towxml(kaochadianItem.content.content,'markdown')
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