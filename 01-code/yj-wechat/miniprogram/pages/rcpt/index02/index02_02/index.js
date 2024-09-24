// pages/rcpt/index02/index02_02/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    foodIndex02:null,
    jsonUsedData:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // console.log("cidier", food)
    // console.log(foodl)
    const app = getApp();
    this.setData({
      jsonUsedData:app.globalData.jsonUsedData
    })
    let length = this.data.jsonUsedData[0].childrens[1].article
    console.log("length",length)
    this.setData({
      // foodContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
      foodIndex02:this.data.jsonUsedData[0].childrens[1].article
    
    })
    // this.data.contentIndex01 = this.data.jsonUsedData[4].article[0].content.content
    console.log("hotel data",this.data.jsonUsedData)
    console.log("food data",this.data.foodIndex02)
 
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },
  onChange(e) {
    console.log('onChange', e)
    this.setData({
      current: e.detail.key,
    })
  },
  onClickInfo(e){
    
    const currentId = e.currentTarget.dataset.id;
    console.log('test',currentId)
    wx.navigateTo({
      url: '../index02_02/index02_02_01/index?id='+currentId,
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    wx.setNavigationBarTitle({title:"余江美食"})
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

  onChange(e) {
    console.log('onChange', e)
    this.setData({
      current: e.detail.key,
    })
  },
  // onDetails(){
  //   wx.navigateTo({
  //     url: './index02_02_01/index',
  //   })
  // }
})