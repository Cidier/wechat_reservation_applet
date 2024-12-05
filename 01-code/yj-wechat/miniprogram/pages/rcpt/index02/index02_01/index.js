// pages/rcpt/index02/index02_01/index.js
// import hotel from './hotel.js'

Page({
  /**
   * 页面的初始数据
   */
  data: {
    current: 'tab1',
    jsonUsedData:null,
    hotelIndex02:null,
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
   //hotell赋值不上去
    // this.setData({
    //   hotell:hotel
    // })
    // console.log(hotell)
    const app = getApp();
    this.setData({
      jsonUsedData:app.globalData.jsonUsedData
    })
    let length = this.data.jsonUsedData[0].childrens[0].article
    console.log("length",length)
    this.setData({
      // hotelContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
      hotelIndex02:this.data.jsonUsedData[0].childrens[0].article
    
    })
    // this.data.contentIndex01 = this.data.jsonUsedData[4].article[0].content.content
    console.log("hote data",this.data.jsonUsedData)
    console.log("hotel data",this.data.hotelIndex02)
 

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
    wx.setNavigationBarTitle({title:"星级酒店"})
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

  onClickInfo(e){
    const currentId = e.currentTarget.dataset.id;
    // detail: list.find(item => item.id === parseInt(id))
    console.log(this.data.hotel)
  
    // this.setData({
    //   contentP: hotelItem.content
    // })
  
    // const currentId = e.currentTarget.dataset.id;
    console.log(currentId);
    //console.log(hotelItem.content)
    //console.log(JSON.stringify(e)+"第一个e")
    wx.navigateTo({
      url: './index02_01_01/index?id='+currentId
    })
  }

})