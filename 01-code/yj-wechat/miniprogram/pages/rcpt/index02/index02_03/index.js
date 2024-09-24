// pages/rcpt/index02/index02_03/index.js
// import scenery from './scenery.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sceneryIndex02:null,
    jsonUsedData:null
    
    // sceneryContentIndex02:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // console.log(sceneryl)
    const app = getApp();
    this.setData({
      jsonUsedData:app.globalData.jsonUsedData
    })
    let length = this.data.jsonUsedData[0].childrens[2].article
    console.log("length",length)
    this.setData({
      // sceneryContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
      sceneryIndex02:this.data.jsonUsedData[0].childrens[2].article
    
    })
    // this.data.contentIndex01 = this.data.jsonUsedData[4].article[0].content.content
    console.log("hote data",this.data.jsonUsedData)
    console.log("scenery data",this.data.sceneryIndex02)
 

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

    wx.setNavigationBarTitle({title:"余江美景"})
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

  },

  onClickInfo(e){
    
    const currentId = e.currentTarget.dataset.id;
    console.log('test',currentId)
    wx.navigateTo({
      url: './index02_03_01/index?id='+currentId,
    })
  }
})