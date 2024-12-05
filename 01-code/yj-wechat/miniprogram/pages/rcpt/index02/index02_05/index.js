
Page({

  /**
   * 页面的初始数据
   */
  data: {
      redtour:redtour,
      redtourIndex02:null,
      jsonUsedData:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log("cidier", redtour)
    // console.log(redtourl)
    const app = getApp();
    this.setData({
      jsonUsedData:app.globalData.jsonUsedData
    })
    let length = this.data.jsonUsedData[0].childrens[4].article
    console.log("length",length)
    this.setData({
      // redtourContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
      redtourIndex02:this.data.jsonUsedData[0].childrens[4].article
    
    })
    // this.data.contentIndex01 = this.data.jsonUsedData[4].article[0].content.content
    console.log("hotel data",this.data.jsonUsedData)
    console.log("redtour data",this.data.redtourIndex02)
 
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
    wx.setNavigationBarTitle({title:"红色旅游"})
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
      url: './index02_05_01/index02_05_01?id='+currentId,
    })
  }
})