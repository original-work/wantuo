package com.unionx.wantuo.domains;
public class Element {
    private Integer id;
    private String content;
    private Integer indexs;
    private Integer types;
    private Integer groups;
    private Integer exerciseId;
    private Integer categoryTypeId;
    private Integer categoryId;
    private Integer stageId;
    private Integer moduleOrder;
    private Integer moduleCode;
    private Integer exerciseBaseId;
    private Integer exercisePackId;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    public Integer getIndexs() {
        return indexs;
    }
    public void setIndexs(Integer indexs) {
        this.indexs = indexs;
    }
    public Integer getTypes() {
        return types;
    }
    public void setTypes(Integer types) {
        this.types = types;
    }
    public Integer getGroups() {
        return groups;
    }
    public void setGroups(Integer groups) {
        this.groups = groups;
    }
    public Integer getExerciseId() {
        return exerciseId;
    }
    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }
    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }
    public void setCategoryTypeId(Integer categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getStageId() {
        return stageId;
    }
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
    public Integer getModuleOrder() {
        return moduleOrder;
    }
    public void setModuleOrder(Integer moduleOrder) {
        this.moduleOrder = moduleOrder;
    }
    public Integer getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(Integer moduleCode) {
        this.moduleCode = moduleCode;
    }
    public Integer getExerciseBaseId() {
        return exerciseBaseId;
    }
    public void setExerciseBaseId(Integer exerciseBaseId) {
        this.exerciseBaseId = exerciseBaseId;
    }
    public Integer getExercisePackId() {
        return exercisePackId;
    }
    public void setExercisePackId(Integer exercisePackId) {
        this.exercisePackId = exercisePackId;
    }
}