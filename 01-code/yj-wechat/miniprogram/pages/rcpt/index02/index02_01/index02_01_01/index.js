// pages/rcpt/index02/index02_01/index02_01_01/index.js

Page({

  /**
   * 页面的初始数据
   */
  data: {
      id:'',
      content:'',
      hotelIndex02:null,
      hotelContentIndex02:null
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
      // hotelContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
      hotelIndex02:app.globalData.jsonUsedData[0].childrens[0].article
    })
    console.log('hehe',this.data.hotelIndex02)
    const hotelItem = this.data.hotelIndex02.find(item => item.id == this.data.id)
    console.log("hotelItem",hotelItem)
     //let content='waiting data ing' 
    if(hotelItem){
      this.setData({
        // content :hotelItem.content.content.replace('<img ', '<img style="max-width:100%;height:auto;display:block;margin:10px 0;"') 
        content:  app.towxml(hotelItem.content.content,'markdown')
      })
     }
     console.log('contttent',this.data.content)
   
   // let id = options.id
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
    wx.setNavigationBarTitle({title:"酒店详情"})
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
  onMap(){
    wx.navigateTo({
      url: '../../../map/index',
    })
  }
})