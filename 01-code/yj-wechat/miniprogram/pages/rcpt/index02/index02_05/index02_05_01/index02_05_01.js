// pages/rcpt/index02/index02_05/index02_05_01/index02_05_01.js

Page({

  /**
   * 页面的初始数据
   */
  
  data: {
    id:'',
    content:'',
    redtour:redtour,
    redtourIndex02:null,
    redtourContentIndex02:null

    
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
      // redtourContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
      redtourIndex02:app.globalData.jsonUsedData[0].childrens[4].article
    })
    console.log('hehe',this.data.redtourIndex02)
    const redtourItem = this.data.redtourIndex02.find(item => item.id == this.data.id)
    console.log("redtourItem",redtourItem)
     //let content='waiting data ing' 
    if(redtourItem){
      this.setData({
        // content :redtourItem.content.content.replace('<img ', '<img style="max-width:100%;height:auto;display:block;margin:10px 0;"') 
        content:  app.towxml(redtourItem.content.content,'markdown')
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