<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'

import { ref } from 'vue'


//数据模型
const department = ref([
    {
        "id": '',
        "name": '',
        "average":'' ,
    }
])

//声明一个异步函数
import {deparmentAchievementService} from '@/api/emp.js'
const departmentAchievement=async()=>{
    let result=await deparmentAchievementService();
    department.value=result.data;
}
departmentAchievement();

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>岗位成绩</span>
            </div>
        </template>
        <!-- 列表 -->
        <el-table :data="department" style="width: 100%">
           <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="岗位" prop="name"></el-table-column>
            <el-table-column label="岗位平均分" prop="average"> </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
    </el-card>
</template>
<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>