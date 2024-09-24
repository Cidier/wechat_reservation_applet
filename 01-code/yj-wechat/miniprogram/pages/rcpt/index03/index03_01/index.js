// pages/rcpt/index03_01/index.js


Page({

  /**
   * 页面的初始数据
   */
  data: {
    content:null,
    // config:{
    //   table_sum_height:'600',
    //   table_content_height:'80'
    // },
    // 表格自定义格式
    // settings:{
    //   type:Object,
    //   value:{}
    // },
    tableData:[{
      date:"2023-10-16",
      timeInterval:"上午",
      details:[
        {
          concreteTime:"10:00",
          scheduleContent:"抵达余江区\n前往中童乘龙村高石塘组"
        },
        {
          
          concreteTime:"10:05",
          scheduleContent:"考察中童乘龙村高石塘组宅改工作"
        },
        {
          
          concreteTime:"10:40",
          scheduleContent:"前往余江区政协委员文化活动中心"
        },
        {
          
          concreteTime:"11:15",
          scheduleContent:"参观余江区政协委员文化活动中心"
        },
        {
          
          concreteTime:"12:00",
          scheduleContent:"中餐地点: 金怡大酒店"
        }
      ]
    },
    {
      date:"2023-10-18",
      timeInterval:"上午",
      details:[
        {
          concreteTime:"12:00",
          scheduleContent:"中餐地点: 喜来登大酒店"
        },
        {
          
          concreteTime:"17:00",
          scheduleContent:"前往余江区政协委员文化活动中心"
        }
      ]
    }
    ,
    {
      date:"2023-10-20",
      timeInterval:"下午",
      details:[
        {
          concreteTime:"10:00",
          scheduleContent:"前往中童乘龙村高石塘组"
        },
        {
          
          concreteTime:"20:00",
          scheduleContent:"安排车辆送往高铁站"
        }
      ]
    }
  ]
  },
  observers: {
      'tableData'(val){

      }
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
    let length = this.data.jsonUsedData[1].childrens[0].article.length -1
    console.log("length",length)
    this.setData({
      content:  app.towxml( this.data.jsonUsedData[1].childrens[0].article[length].content.content,'markdown')
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