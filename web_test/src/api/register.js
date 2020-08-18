import qs from 'qs'
import { service,service2 } from '@/utils/request'

//认证的接口
export const accountLogin = ({ username, password }) => {
	let data = qs.stringify({
		grant_type: 'password',
		username: username,
		password: password
	})
	return service2.request({
		url: '/oauth/token?'+data,
		method: 'post',
		auth: {
			username: 'gaobo_system',
			password: '654321'
		}
	})
}

export const Test1 = () => {
	return service.request({
		url: '/system',
		method: 'get'
	})
}
export const Test2 = () => {
	return service.request({
		url: '/user',
		method: 'get'
	})
}


