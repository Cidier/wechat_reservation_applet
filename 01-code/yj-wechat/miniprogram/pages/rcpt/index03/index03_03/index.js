// pages/rcpt/index03/index03_03/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content:null,
    
    name0:'姓名',
    job_level0:'职务',
    peitongData:[
      {
         name:'黄贵开',
         job_level:'余江区政协党组书记、主席',
        
       },
       {
        name:'潘富生',
        job_level:'余江区政协党组成员、副主席',
       
      },{
        name:'刘积运',
        job_level:'余江区政协办公室主任',
       
      },{
        name:'杨淑美',
        job_level:'余江区政协提案和教科卫体委主任',
       
      }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const app = getApp();
    this.setData({
      jsonUsedData:app.globalData.jsonUsedData
    })
    // [0].article.length -1
    let length = this.data.jsonUsedData[1].childrens[2].article.length -1
    console.log("length",length)
    this.setData({
      content:  app.towxml( this.data.jsonUsedData[1].childrens[2].article[length].content.content,'markdown')
    })
    // this.data.contentIndex01 = this.data.jsonUsedData[4].article[0].content.content
    console.log("index01 data",this.data.jsonUsedData)
    console.log("index01 data",this.data.content)
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