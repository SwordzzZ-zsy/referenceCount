<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
const employee = ref([
    {
        "id": '',
        "name": '',
        "depart":'',
        "average": '',
        "month":''
    }
])

//用户搜索时的分类id
const selectedMonth=ref('')
const name=ref('')
//声明一个异步函数
import {employeeAchievementService} from '@/api/emp.js'
const employeeAchievement=async()=>{
    let result=await employeeAchievementService();
    employee.value=result.data;
}
employeeAchievement();

import {sortAchievementService} from '@/api/emp.js'
const sortAchievement=async(clickState)=>{
    employee.value.state=clickState;
    let result=await sortAchievementService();
    employee.value=result.data;
}
//去重
// 计算属性，获取去重后的月份列表
import {computed } from 'vue'
const uniqueMonths = computed(() => {
    const months = employee.value.map(item => item.month)
    return [...new Set(months)].map(month => {
        return {
            value: month,
            label: month
        }
    })
})

//获取数据
import {employeeListService} from '@/api/emp.js'
const employeeList=async()=>{
    let params={
        name:name.value?`%${name.value}%`:null,
        month:selectedMonth.value?selectedMonth.value:null
    }
    let result=await employeeListService(params);
    employee.value=result.data;
}
employeeList();

//重置方法
function resetForm() {
  // 重置表单
  selectedMonth.value = "";
  name.value = "";
  employeeList();
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>员工成绩</span>
                <div class="extra">
                    <el-button type="primary" @click="employeeAchievement()">还原</el-button>
                    <el-button type="primary" @click="sortAchievement('排序成功')">排序</el-button>
                </div>
            </div>
        </template>
          <!-- 搜索表单 -->
          <el-form inline>
            <el-form-item label="姓名：">
                <el-input placeholder="请输入" v-model="name"></el-input>
            </el-form-item>

            <el-form-item label="月份：">
                <el-select placeholder="请选择" v-model="selectedMonth">
                    <el-option v-for="m in uniqueMonths" :key="m.value" :label="m.label" :value="m.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="employeeList">搜索</el-button>
                <el-button  @click="resetForm">重置</el-button>
            </el-form-item>
        </el-form>

        
        <el-table :data="employee" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="员工名称" prop="name"></el-table-column>
            <el-table-column label="员工岗位" prop="depart" > </el-table-column>
            <el-table-column label="员工平均分" prop="average"></el-table-column>
            <el-table-column label="月份" prop="month"></el-table-column>
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