import { createRouter,createWebHistory } from "vue-router";

//导入组件
import LoginVue from '@/views/Login.vue';
import LayoutVue from '@/views/Layout.vue';
import DepartmentVue from "@/views/company/Department.vue";
import EmployeeVue from "@/views/company/Employee.vue";

//定义路由关系
const routes=[
    {path:'/login',component:LoginVue},
    {path:'/',component:LayoutVue,redirect:'/show/emp',children:[
        {path:'/show/emp',component:EmployeeVue},
        {path:'/show/dept',component:DepartmentVue}
    ]}
]

//创建路由器
const router=createRouter({
    history:createWebHistory(),
    routes:routes
})

//导出路由
export default router