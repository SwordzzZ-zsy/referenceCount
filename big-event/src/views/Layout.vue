<script setup>
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
import {userInfoService} from '@/api/user.js'
import useUserInfoStore from '@/stores/userInfo.js'
const userInfoStore=useUserInfoStore();
//调用函数，获取用户信息
const getUserInfo=async()=>{
    //调用接口
    let result=await userInfoService();
    //数据存储到pinia中
    userInfoStore.setInfo(result.data);
}
getUserInfo();
</script>

<template>
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"
                router>
                <el-menu-item index="/show/emp">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>员工成绩</span>
                </el-menu-item>
                <el-menu-item index="/show/dept">
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>岗位成绩</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>   
            <!-- 头部区域 -->
            <el-header>
                <div>用户名：<strong>{{userInfoStore.info.username}}</strong></div>
            </el-header>
            <!-- 中间区域 -->
            <el-main>
                <!-- <div style="width: 1290px; height: 570px;border: 1px solid red;">
                    内容展示区
                </div> -->
                <router-view></router-view>
            </el-main>
            <!-- 底部区域 -->
            <el-footer>九月绩效</el-footer>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

        &__logo {
            height: 120px;
            background: url('@/assets/logo.png') no-repeat center / 120px auto;
        }

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>