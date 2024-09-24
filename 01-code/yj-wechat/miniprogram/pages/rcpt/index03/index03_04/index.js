
Page({

  /**
   * 页面的初始数据
   */
  data: {
    jsonUsedData:null,
    kaochadianIndex02:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //kaochadianl赋值不上去
     // this.setData({
     //   kaochadianl:kaochadian
     // })
    //  console.log("cidier", kaochadian)
     // console.log(kaochadianl)
     const app = getApp();
     this.setData({
       jsonUsedData:app.globalData.jsonUsedData
     })
     let length = this.data.jsonUsedData[1].childrens[3].article
     console.log("length",length)
     this.setData({
       // kaochadianContentIndex02:  app.towxml(this.data.jsonUsedData[0].article,'markdown')
       kaochadianIndex02:this.data.jsonUsedData[1].childrens[3].article
     
     })
     // this.data.contentIndex01 = this.data.jsonUsedData[4].article[0].content.content
     console.log("hote data",this.data.jsonUsedData)
     console.log("kaochadian data",this.data.kaochadianIndex02)
  
 
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
    wx.setNavigationBarTitle({title:"考察点介绍"})

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
    // console.log(this.data.kaochadian)
  
    // this.setData({
    //   contentP: kaochadianItem.content
    // })
  
    // const currentId = e.currentTarget.dataset.id;
    console.log(currentId);
    //console.log(kaochadianItem.content)
    //console.log(JSON.stringify(e)+"第一个e")
    wx.navigateTo({
      url: './index03_04_01/index03_04_01?id='+currentId
    })
  },

  onFooterTabber(e){
    console.log("父组件", e)
    let route = e.detail.route
    // let url = `/pages/rcpt/`+route+`/index`
    // wx.reLaunch({
    //   url: url,
    //   success(e){
    //     console.log("成功", e)
    //   },fail(e){
    //     console.log("失败", e)
    //   }
    // })
  }

})