<script setup>

    import { Edit, Delete } from '@element-plus/icons-vue';
    import { ref } from 'vue';
    import { getAllArticleCategoryService, addArticleCategoryService, updateArticleCategoryService, deleteArticleCategoryService} from '@/api/article';
    import {ElMessage, ElMessageBox} from 'element-plus'

    const categories = ref([]);

    // 调用后台接口，获取所有的文章分类
    let getAllArticleCategory = async () => {
        let result = await getAllArticleCategoryService();
        categories.value = result.data;
    }

    getAllArticleCategory();

    //控制添加分类弹窗
    const dialogVisible = ref(false)

    //添加分类数据模型
    const categoryModel = ref({ categoryName: '', categoryAlias: '' });

    //添加分类表单校验
    const rules = {
        categoryName: [
            { required: true, message: '请输入分类名称', trigger: 'blur' },
        ],
        categoryAlias: [
            { required: true, message: '请输入分类别名', trigger: 'blur' },
        ]
    }

    // 调用后台接口，添加文章分类
    async function addArticleCategory() {
        debugger
        if (categoryModel.value.categoryName === '' || categoryModel.value.categoryAlias === '') {
            ElMessage.info('请输入分类名称和别名');
            return;
        }

        let result = await addArticleCategoryService(categoryModel.value);
        ElMessage.success(result['msg'] ? result['msg'] : '添加成功');
        await getAllArticleCategory();
        categoryModel.value = { categoryName: '', categoryAlias: '' };
        dialogVisible.value = false;
    }

    // 弹窗标题
    const dialogTitle = ref('');

    function editArticleCategory(row) {
        dialogVisible.value = true;
        dialogTitle.value = '修改分类';
        categoryModel.value.id = row.id;
        categoryModel.value.categoryName = row.categoryName;
        categoryModel.value.categoryAlias = row.categoryAlias;
    }

    // 更新文章分类
    async function updateArticleCategory() {
        if (categoryModel.value.categoryName === '' || categoryModel.value.categoryAlias === '') {
            ElMessage.info('请输入分类名称和别名');
            return;
        }
        let result = await updateArticleCategoryService(categoryModel.value);
        ElMessage.success(result['msg'] ? result['msg'] : '修改成功');
        await getAllArticleCategory();
        categoryModel.value = { categoryName: '', categoryAlias: '' };
        dialogVisible.value = false;
    }

    //删除分类  给删除按钮绑定事件
    const deleteCategory = (row) => {
        ElMessageBox.confirm(
            '你确认删除该分类信息吗？',
            '温馨提示',
            {
                confirmButtonText: '确认',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
            .then(async () => {
                let result = await deleteArticleCategoryService(row.id);
                //用户点击了确认
                ElMessage({
                    type: 'success',
                    message: result['msg'] ? result['msg'] : '删除成功',
                })

                await getAllArticleCategory();
            })
            .catch(() => {
                //用户点击了取消
                ElMessage({
                    type: 'info',
                    message: '取消删除',
                })
            })
    }

    function hideDialog() {
        dialogVisible.value = false;
        categoryModel.value = { categoryName: '', categoryAlias: '' };
    }

</script>
<template>
    <el-card class="page-container">
        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%" @close="hideDialog">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="分类名称" prop="categoryName">
                    <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="分类别名" prop="categoryAlias">
                    <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
        <span class="dialog-footer">
            <el-button @click="hideDialog">取消</el-button>
            <el-button type="primary" @click="dialogTitle === '添加分类' ? addArticleCategory() : updateArticleCategory()"> 确认 </el-button>
        </span>
            </template>
        </el-dialog>
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true; dialogTitle='添加分类'">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categories" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="editArticleCategory(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>
                </template>
            </el-table-column>
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