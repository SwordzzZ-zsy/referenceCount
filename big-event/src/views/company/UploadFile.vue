<script setup>
import { ref, reactive } from 'vue';
import { Plus, Upload } from '@element-plus/icons-vue';
import { uploadFileService } from '@/api/emp.js';

const uploadRef = ref(null);
const state = reactive({
  fileName: '', // 存储文件名或者操作结果
});

// 选择文件并获取文件信息
const handleFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    state.fileName = file.name; // 更新文件名
    console.log('选择的文件名字：', file.name);
  }
};

// 提交文件到后端
const submitFile = async () => {
  if (!uploadRef.value || !uploadRef.value.files.length) {
    alert('请先选择一个文件');
    return;
  }

  const formData = new FormData();
  const file = uploadRef.value.files[0];
  formData.append('file', file);

  try {
    await uploadFileService(formData);
    state.fileName = '提交成功'; // 更新状态消息
  } catch (error) {
    console.error('上传文件失败:', error);
    state.fileName = '提交失败，请重新上传'; // 更新状态消息
  }
};
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span>上传绩效表</span>
      </div>
    </template>
    <el-row>
      <el-col :span="12">
        <br />
        <input type="file" name="file" ref="uploadRef" style="display:none" @change="handleFileChange"/>
        <el-button type="primary" :icon="Plus" size="large" @click="$refs.uploadRef.click()">
          选择文件
        </el-button>
        <el-button type="success" :icon="Upload" size="large" @click="submitFile">
          提交给数据库
        </el-button>
        <!-- 显示文件名或状态消息 -->
        <div v-if="state.fileName" class="file-name">{{ state.fileName }}</div>
      </el-col>
    </el-row>
  </el-card>
</template>

<style lang="scss" scoped>
.page-container {
  .header {
    font-size: 20px;
  }
  .file-name {
    margin-top: 10px; // 调整间距以符合你的设计
  }
}
</style>
