export const forEach = (arr, fn) => {
  if (!arr.length || !fn) return
  let i = -1
  let len = arr.length
  while (++i < len) {
    let item = arr[i]
    fn(item, i, arr)
  }
}

/**
 * @param {Array} arr1
 * @param {Array} arr2
 * @description 得到两个数组的交集, 两个数组的元素为数值或字符串
 */
export const getIntersection = (arr1, arr2) => {
  let len = Math.min(arr1.length, arr2.length)
  let i = -1
  let res = []
  while (++i < len) {
    const item = arr2[i]
    if (arr1.indexOf(item) > -1) res.push(item)
  }
  return res
}

/**
 * @param {Array} arr1
 * @param {Array} arr2
 * @description 得到两个数组的并集, 两个数组的元素为数值或字符串
 */
export const getUnion = (arr1, arr2) => {
  return Array.from(new Set([...arr1, ...arr2]))
}

/**
 * @param {Array} target 目标数组
 * @param {Array} arr 需要查询的数组
 * @description 判断要查询的数组是否至少有一个元素包含在目标数组中
 */
export const hasOneOf = (targetarr, arr) => {
  return targetarr.some(_ => arr.indexOf(_) > -1)
}

/**
 * @param {String|Number} value 要验证的字符串或数值
 * @param {*} validList 用来验证的列表
 */
export function oneOf (value, validList) {
  for (let i = 0; i < validList.length; i++) {
    if (value === validList[i]) {
      return true
    }
  }
  return false
}

/**
 * @param {Number} timeStamp 判断时间戳格式是否是毫秒
 * @returns {Boolean}
 */
const isMillisecond = timeStamp => {
  const timeStr = String(timeStamp)
  return timeStr.length > 10
}
/**
 * @param {} date 取当前时间，格式为yyyy-MM-dd
 * @returns {Boolean}
 */
export const getNewDate = () => {
  const newDate = new Date();
  const newY = newDate.getFullYear(); //年
  const newM = newDate.getMonth() + 1 < 10 ? "0" + (newDate.getMonth() + 1): newDate.getMonth() + 1; //月
  const newD = newDate.getDate()<10? "0" + newDate.getDate() : newDate.getDate(); //日
  return newY+"-"+newM+"-"+newD;
}
/**
 * @param {} date 取本月第一天时间，格式为yyyy-MM-dd
 * @returns {Boolean}
 */
export const getDate1 = () => {
  const date = new Date();
  date.setDate(1);
  const newY = date.getFullYear(); //年
  const newM = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1): date.getMonth() + 1; //月
  const newD = date.getDate()<10? "0" + date.getDate() : date.getDate(); //日
  return newY+"-"+newM+"-"+newD;
}
/**
 * @param {Number} timeStamp 传入的时间戳
 * @param {Number} currentTime 当前时间时间戳
 * @returns {Boolean} 传入的时间戳是否早于当前时间戳
 */
const isEarly = (timeStamp, currentTime) => {
  return timeStamp < currentTime
}

/**
 * @param {Number} num 数值
 * @returns {String} 处理后的字符串
 * @description 如果传入的数值小于10，即位数只有1位，则在前面补充0
 */
const getHandledValue = num => {
  return num < 10 ? '0' + num : num
}

/**
 * @param {Number} timeStamp 传入的时间戳
 * @param {Number} startType 要返回的时间字符串的格式类型，传入'year'则返回年开头的完整时间
 */
const getDate = (timeStamp, startType) => {
  const d = new Date(timeStamp * 1000)
  const year = d.getFullYear()
  const month = getHandledValue(d.getMonth() + 1)
  const date = getHandledValue(d.getDate())
  const hours = getHandledValue(d.getHours())
  const minutes = getHandledValue(d.getMinutes())
  const second = getHandledValue(d.getSeconds())
  let resStr = ''
  if (startType === 'year') resStr = year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + second
  else resStr = month + '-' + date + ' ' + hours + ':' + minutes
  return resStr
}

/**
 * @param {String|Number} timeStamp 时间戳
 * @returns {String} 相对时间字符串
 */
export const getRelativeTime = timeStamp => {
  // 判断当前传入的时间戳是秒格式还是毫秒
  const IS_MILLISECOND = isMillisecond(timeStamp)
  // 如果是毫秒格式则转为秒格式
  if (IS_MILLISECOND) Math.floor(timeStamp /= 1000)
  // 传入的时间戳可以是数值或字符串类型，这里统一转为数值类型
  timeStamp = Number(timeStamp)
  // 获取当前时间时间戳
  const currentTime = Math.floor(Date.parse(new Date()) / 1000)
  // 判断传入时间戳是否早于当前时间戳
  const IS_EARLY = isEarly(timeStamp, currentTime)
  // 获取两个时间戳差值
  let diff = currentTime - timeStamp
  // 如果IS_EARLY为false则差值取反
  if (!IS_EARLY) diff = -diff
  let resStr = ''
  const dirStr = IS_EARLY ? '前' : '后'
  // 少于等于59秒
  if (diff <= 59) resStr = diff + '秒' + dirStr
  // 多于59秒，少于等于59分钟59秒
  else if (diff > 59 && diff <= 3599) resStr = Math.floor(diff / 60) + '分钟' + dirStr
  // 多于59分钟59秒，少于等于23小时59分钟59秒
  else if (diff > 3599 && diff <= 86399) resStr = Math.floor(diff / 3600) + '小时' + dirStr
  // 多于23小时59分钟59秒，少于等于29天59分钟59秒
  else if (diff > 86399 && diff <= 2623859) resStr = Math.floor(diff / 86400) + '天' + dirStr
  // 多于29天59分钟59秒，少于364天23小时59分钟59秒，且传入的时间戳早于当前
  else if (diff > 2623859 && diff <= 31567859 && IS_EARLY) resStr = getDate(timeStamp)
  else resStr = getDate(timeStamp, 'year')
  return resStr
}

/**
 * @returns {String} 当前浏览器名称
 */
export const getExplorer = () => {
  const ua = window.navigator.userAgent
  const isExplorer = (exp) => {
    return ua.indexOf(exp) > -1
  }
  if (isExplorer('MSIE')) return 'IE'
  else if (isExplorer('Firefox')) return 'Firefox'
  else if (isExplorer('Chrome')) return 'Chrome'
  else if (isExplorer('Opera')) return 'Opera'
  else if (isExplorer('Safari')) return 'Safari'
}

/**
 * @description 绑定事件 on(element, event, handler)
 */
export const on = (function () {
  if (document.addEventListener) {
    return function (element, event, handler) {
      if (element && event && handler) {
        element.addEventListener(event, handler, false)
      }
    }
  } else {
    return function (element, event, handler) {
      if (element && event && handler) {
        element.attachEvent('on' + event, handler)
      }
    }
  }
})()

/**
 * @description 解绑事件 off(element, event, handler)
 */
export const off = (function () {
  if (document.removeEventListener) {
    return function (element, event, handler) {
      if (element && event) {
        element.removeEventListener(event, handler, false)
      }
    }
  } else {
    return function (element, event, handler) {
      if (element && event) {
        element.detachEvent('on' + event, handler)
      }
    }
  }
})()

/**
 * 判断一个对象是否存在key，如果传入第二个参数key，则是判断这个obj对象是否存在key这个属性
 * 如果没有传入key这个参数，则判断obj对象是否有键值对
 */
export const hasKey = (obj, key) => {
  if (key) return key in obj
  else {
    let keysArr = Object.keys(obj)
    return keysArr.length
  }
}

/**
 * @param {*} obj1 对象
 * @param {*} obj2 对象
 * @description 判断两个对象是否相等，这两个对象的值只能是数字或字符串
 */
export const objEqual = (obj1, obj2) => {
  const keysArr1 = Object.keys(obj1)
  const keysArr2 = Object.keys(obj2)
  if (keysArr1.length !== keysArr2.length) return false
  else if (keysArr1.length === 0 && keysArr2.length === 0) return true
  /* eslint-disable-next-line */
  else return !keysArr1.some(key => obj1[key] != obj2[key])
}

export const proDownImage = (path,imgObj) => {
  // 等比压缩图片工具 
  //var proMaxHeight = 185; 
  var proMaxHeight=300; 
  var proMaxWidth = 175; 
  var size = new Object();　 
  var image = new Image();　 
  image.src = path;　 
  image.attachEvent("onreadystatechange", 
  function() { // 当加载状态改变时执行此方法,因为img的加载有延迟 
    if (image.readyState == "complete") { // 当加载状态为完全结束时进入 
      if (image.width > 0 && image.height > 0) { 
        var ww = proMaxWidth / image.width; 
        var hh = proMaxHeight / image.height;　 
        var rate = (ww < hh) ? ww: hh; 
        if (rate <= 1) {　 
          alert("imgage width*rate is:" + image.width * rate); 
          size.width = image.width * rate; 
          size.height = image.height * rate; 
        } else { 
          alert("imgage width is:" + image.width);　　 
          size.width = image.width;　　 
          size.height = image.height;　　　 
        }　 
      } 
    } 
    imgObj.attr("width",size.width); 
    imgObj.attr("height",size.height); 
  }); 
}

export const getGVerify = (id) => 
{
  function GVerify (options) { //创建一个图形验证码对象，接收options对象为参数
    this.options = { //默认options参数值
      id: "", //容器Id
      canvasId: "verifyCanvas", //canvas的ID
      width: "100", //默认canvas宽度
      height: "30", //默认canvas高度
      type: "blend", //图形验证码默认类型blend:数字字母混合类型、number:纯数字、letter:纯字母
      code: ""
    }
    
    if(Object.prototype.toString.call(options) == "[object Object]"){//判断传入参数类型
      for(var mm in options) { //根据传入的参数，修改默认参数值
        this.options[mm] = options[mm];
      }
    }else{
      this.options.id = options;
    }
    
    this.options.numArr = "0,1,2,3,4,5,6,7,8,9".split(",");
    this.options.letterArr = getAllLetter();
    
    this._init();
    
    this.refresh();
    
  }

  GVerify.prototype = {
    /**版本号**/
    version: '1.0.0',

    /**初始化方法**/
    _init: function() {
      var con = document.getElementById(this.options.id);
      var canvas = document.createElement("canvas");
      /*this.options.width = con.offsetWidth > 0 ? con.offsetWidth : "100";
      this.options.height = con.offsetHeight > 0 ? con.offsetHeight : "30";*/
      canvas.id = this.options.canvasId;
      canvas.width = this.options.width;
      canvas.height = this.options.height;
      canvas.style.cursor = "pointer";
      canvas.innerHTML = "您的浏览器版本不支持canvas";
      con.appendChild(canvas);
      var parent = this;
      canvas.onclick = function(){
        parent.refresh();
      }
    },

    /**生成验证码**/
    refresh: function () {
      this.options.code = '';
      var canvas = document.getElementById(this.options.canvasId);
      if(canvas.getContext) {
        var ctx = canvas.getContext('2d');
      }
      ctx.textBaseline = "middle";

      ctx.fillStyle = randomColor(180, 240);
      ctx.fillRect(0, 0, this.options.width, this.options.height);

      var txtArr = this.options.letterArr;
      if(this.options.type == "blend") { //判断验证码类型
        txtArr = this.options.numArr.concat(this.options.letterArr);
      } else if(this.options.type == "number") {
        txtArr = this.options.numArr;
      } 
      
      for(var j = 1; j <= 4; j++) {
        var txt = txtArr[randomNum(0, txtArr.length)];
        this.options.code += txt;
        ctx.font = '20px SimHei';
        //ctx.font = randomNum(this.options.height/2, this.options.height) + 'px SimHei'; //随机生成字体大小
        ctx.fillStyle = randomColor(50, 160); //随机生成字体颜色
        /* ctx.shadowOffsetX = randomNum(-3, 3);
        ctx.shadowOffsetY = randomNum(-3, 3);*/
        ctx.shadowBlur = randomNum(-3, 3);
        ctx.shadowColor = "rgba(0, 0, 0, 0.3)";
        var x = this.options.width / 5 * j;
        var y = this.options.height / 2;
        var deg = randomNum(-30, 30);
        /**设置旋转角度和坐标原点**/
        ctx.translate(x, y);
        ctx.rotate(deg * Math.PI / 180);
        ctx.fillText(txt, 0, 0);
        /**恢复旋转角度和坐标原点**/
        ctx.rotate(-deg * Math.PI / 180);
        ctx.translate(-x, -y);
        
      }
      /**绘制干扰线**/
      for(var k = 0; k < 4; k++) {
        ctx.strokeStyle = randomColor(40, 180);
        ctx.beginPath();
        ctx.moveTo(randomNum(0, this.options.width/2), randomNum(0, this.options.height/2));
        ctx.lineTo(randomNum(0, this.options.width/2), randomNum(0, this.options.height));
        ctx.stroke();
        
      }
      /**绘制干扰点**/
      for(var l = 0; l < this.options.width/4; l++) {
        ctx.fillStyle = randomColor(0, 255);
        ctx.beginPath();
        ctx.arc(randomNum(0, this.options.width), randomNum(0, this.options.height), 1, 0, 2 * Math.PI);
        ctx.fill();
      }
    },

    /**验证验证码**/
    validate: function(code){
      var verifyCode = code.toLowerCase();
      var v_code = this.options.code.toLowerCase();
      if(verifyCode == v_code){
        return true;
      }else{
        return false;
      }
    }
  }

  /**生成字母数组**/
  function getAllLetter() {
    var letterStr = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
    return letterStr.split(",");
  }
  /**生成一个随机数**/
  function randomNum(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
  }
  /**生成一个随机色**/
  function randomColor(min, max) {
    var r = randomNum(min, max);
    var g = randomNum(min, max);
    var b = randomNum(min, max);
    return "rgb(" + r + "," + g + "," + b + ")";
  }
  return new GVerify(id);
}

// 日期格式yyyy-dd-mm 转成 data型
export function convertStringToDate(dateStr,separator){
  if(!separator){
         separator="-";
  }
  var dateArr = dateStr.split(separator);
  var year = parseInt(dateArr[0]);
  var month;
  //处理月份为04这样的情况                         
  if(dateArr[1].indexOf("0") == 0){
      month = parseInt(dateArr[1].substring(1));
  }else{
       month = parseInt(dateArr[1]);
  }
  var day = parseInt(dateArr[2]);
  var date = new Date(year,month -1,day);
  return date;
}