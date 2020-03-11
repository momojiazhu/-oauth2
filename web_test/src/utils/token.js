import Cookies from 'js-cookie'
// cookie保存的天数

export const TOKEN_KEY = 'token'

export const setToken = token => {
  Cookies.set(TOKEN_KEY, token, { expires: 1 })
}

export const getToken = () => {
  const token = Cookies.get(TOKEN_KEY)
  if (token) return token
  else return null
}

// 取cookies
const getCookie = (name) => {
  let arr = document.cookie.match(new RegExp('(^| )' + name + '=([^;]*)(;|$)'))
  if (arr != null) return unescape(arr[2])
  return null
}

// 删除cookie
export const delCookie = name => {
  var exp = new Date()
  exp.setTime(exp.getTime() - 10000)
  var cval = getCookie(name)
  if (cval != null) {
    document.cookie=name+"=v; expire="+exp.toGMTString()+"; path=/";
  }
}
