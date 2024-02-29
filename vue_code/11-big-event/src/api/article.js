import request from "@/utils/request";

export function getAllArticleCategoryService() {
    return request.get('/category');
}

export function addArticleCategoryService(categoryModel) {
    return request.post('/category', categoryModel);
}

export function updateArticleCategoryService(categoryModel) {
    return request.put('/category', categoryModel);
}

export function deleteArticleCategoryService(categoryId) {
    return request.delete('/category', {params: {id: categoryId}});
}

export function getAllArticleService(params) {
    return request.get('/article', {params});
}

export function addArticleService(article) {
    return request.post('/article', {params: article});
}