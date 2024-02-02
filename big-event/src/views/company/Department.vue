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
        "month":''
    }
])

//声明一个异步函数
import {deparmentAchievementService} from '@/api/emp.js'
const departmentAchievement=async()=>{
    let result=await deparmentAchievementService();
    department.value=result.data;
}
departmentAchievement();

const selectedMonth=ref('')
//去重
// 计算属性，获取去重后的月份列表
import {computed } from 'vue'
const uniqueMonths = computed(() => {
    const months = department.value.map(item => item.month)
    return [...new Set(months)].map(month => {
        return {
            value: month,
            label: month
        }
    })
})

const departmentList=async()=>{
    let params={
        month:selectedMonth.value?selectedMonth.value:null
    }
    let result=await deparmentAchievementService(params);
    department.value=result.data;
}
departmentList();

//重置方法
function resetForm() {
  // 重置表单
  selectedMonth.value = "";
  departmentList();
}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>岗位成绩</span>
            </div>
        </template>
            <el-form inline>
            <el-form-item label="月份：">
                <el-select placeholder="请选择" v-model="selectedMonth">
                    <el-option v-for="m in uniqueMonths" :key="m.value" :label="m.label" :value="m.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="departmentList">搜索</el-button>
                <el-button  @click="resetForm">重置</el-button>
            </el-form-item>
        </el-form>
        <!-- 列表 -->
        <el-table :data="department" style="width: 100%">
           <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="岗位" prop="name"></el-table-column>
            <el-table-column label="岗位平均分" prop="average"> </el-table-column>
            <el-table-column label="月份" prop="month"> </el-table-column>
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