import axios from 'axios'
import { getToken } from '@/utils/token'

//这个是供文件操作的接口

function getTokenF() {
	let data_token = getToken();
	let Bearer = 'Bearer ';
	return {
		'Content-Type': 'multipart/form-data',
		'Authorization': Bearer + data_token
	}
}
function getTokenF2() {
	let data_token = getToken();
	let Bearer = 'bearer ';
	return {
		'Content-Type': 'application/json',
		'Authorization': Bearer + data_token
	}
}

//以流的形式获取文件
export const getUploadFile = url => {
  return axios.request({
	url: 'http://localhost:8282/files/'+url,
	method: 'get',
    headers: getTokenF(),
    responseType: 'blob'
  })
}
//上传文件
export const uploadFile = data => {
	return axios.request({
	  url: 'http://localhost:8282/files/2019-01-01',
	  method: 'post',
	  headers: getTokenF(),
	  data
	})
}

//删除文件
export const delUploadFile = url => {
	return axios.request({
	  url: 'http://localhost:8282/files/'+url,
	  method: 'delete',
	  headers: getTokenF()
	})
}

//获取文件列表
export const selectFileList = () => {
	return axios.request({
	  url: 'http://localhost:8282/selectFileList',
	  method: 'post',
	  headers: getTokenF2()
	})
}

//将文件以这种形式，multipart/form-data，所以这样写
export function uploadFileFun(file){
	let data = new FormData();
    data.append('file', file)
	return uploadFile(data);
}

export function getWordFun(url){
	return 'http://39.107.34.210:8282/files/'+url;
}

