import request from '@/utils/request.js'
import {useTokenStore} from '@/stores/token.js'
//员工成绩查询
export const employeeAchievementService=()=>{
    //const tokenStore=useTokenStorel;
    //在pinia中定义的响应式数据都不需要.value
    //return request.get('/show/emp',{headers:{'Authorization':tokenStore.token}})
    return request.get('/show/emp')
}
//员工排序成绩查询
export const sortAchievementService=()=>{
    //const tokenStore=useTokenStorel;
    //在pinia中定义的响应式数据都不需要.value
    //return request.get('/show/emp',{headers:{'Authorization':tokenStore.token}})
    return request.get('/show/sort')
}

//岗位成绩查询
export const deparmentAchievementService=(params)=>{
   return request.get('/show/dept',{params:params})
}

//文章列表查询
export const employeeListService=(params)=>{
    return request.get('/show/search',{params:params})
 }

 //上传excel文件并解析到数据库
export const uploadFileService=(file)=>{
    return request.post('/upload',file)
}