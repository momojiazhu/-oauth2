import axios from '@/libs/api.request'
import qs from 'qs'
import { getToken } from '@/utils/token'

//这个是每个请求后端带有token的操作，应该是不太优雅，应该有更简便的方法，我在研究
function getTokenF() {
	let data_token = getToken();
	let Bearer = 'bearer ';
	return {
		'Content-Type': 'application/json',
		'Authorization': Bearer + data_token
	}
}
//认证的接口
export const accountLogin = ({ username, password }) => {
	let data = qs.stringify({
		grant_type: 'password',
		username: username,
		password: password
	})
	return axios.request({
		url: '/oauth/token',
		method: 'post',
		auth: {
			username: 'gaobo_system',
			password: '654321'
		},
		data
	})
}

export const Test1 = () => {
	return axios.request({
		url: 'system',
		method: 'get',
		headers: getTokenF()
	})
}
export const Test2 = () => {
	return axios.request({
		url: 'user',
		method: 'get',
		headers: getTokenF()
	})
}


