// pages/rcpt/index03/index03_02/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
    content:null,

    name0:'姓名',
    job_level0:'职务',
       //0 省政协常委、委员；1 重点提案主办单位； 2 工作人员 ；3 市政协陪同人员；4：参会人员
    position0:[0,1,2,3,4],
    position0_CN:['省政协常委、委员','重点提案主办单位','工作人员','市政协陪同人员'],
    laibinData:[
     {
        name:'汪爽',
        job_level:'省政协常委、提案委员会主任',
        position:0
      },
      {
        name:'肖礼庆',
        job_level:'省政协常委、副秘书长、九三学社小委会专职副主委、景德镇市政协副主席',
        position:0
      },
      {
        name:'李光荣',
        job_level:'省政协常委、民建省委会常委，江西省越光电缆有限公司董事长',
        position:0
      },{
        name:'刘德辉',
        job_level:'省政协委员、南昌市人大常委会党组成员、副主任，市总工会党组书记、主席',
        position:0
      },
      {
        name:'樊胜',
        job_level:'省民政厅党组成员、副厅长',
        position:1
      },
      
      {
        name:'陈钢',
        job_level:'省政务服务办二级巡视员',
        position:1
      },
      
      {
        name:'李卫星',
        job_level:'省民政厅基层政权和社区建设处副处长',
        position:1
      },
      
      {
        name:'李文惠(女)',
        job_level:' 省科技厅条件财务处二级主任科员',
        position:1
      },
      {
        name:'陈春宏',
        job_level:' 省政协服务办指导处干部',
        position:1
      },
      {
        name:'程芳明',
        job_level:'省政协提案委员会办公室副主任',
        position:2
      },
      {
        name:'鲍剑辉',
        job_level:'省政协提案委员会办公室二级调研员',
        position:2
      },
      
      {
        name:'刘军生',
        job_level:'市政协副主席',
        position:3
      },
      {
        name:'管玉青(女)',
        job_level:'市政协提案委主任',
        position:3
      },{
        name:'赵卫平',
        job_level:'市政协提案委办公室主任',
        position:3
      }
      
      // {
      //   name:'刘德华',
      //   job_level:'市政协副书记、省教务处厅长',
      //   position:4
      // },
      
      
          ]

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
    let length = this.data.jsonUsedData[1].childrens[1].article.length -1
    console.log("length",length)
    this.setData({
      content:  app.towxml( this.data.jsonUsedData[1].childrens[1].article[length].content.content,'markdown')
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