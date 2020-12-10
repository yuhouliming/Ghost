package com.example.dockerdemo.utils;



public class DictionaryNode{


    private Long id;

    private String label;

    private String value;

    private Integer level = 0;

    private Long dictionaryId;

    private Integer priority;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    //父节点
    private DictionaryNode parent;

    private Boolean isParentNode = false;

    public Boolean getParentNode() {
        return isParentNode;
    }

    public void setParentNode(Boolean parentNode) {
        isParentNode = parentNode;
    }

    // 逻辑删除位
    private Boolean deleted = false;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public DictionaryNode getParent() {
        return parent;
    }

    public void setParent(DictionaryNode parent) {
        this.parent = parent;
    }

    public DictionaryNode() {
    }

    public DictionaryNode value(String value){
        this.value = value;
        return  this;
    }

    public DictionaryNode label(String label){
        this.label = label;
        return this;
    }

    public DictionaryNode description(String description){
        this.description = description;
        return this;

    }

    public DictionaryNode level(Integer level){
        this.level = level;
        return this;
    }

    public DictionaryNode parent(DictionaryNode dictionaryNode){
        this.parent = dictionaryNode;
        return this;
    }

    public DictionaryNode dictId(Long dictionaryId){
        this.dictionaryId = dictionaryId;
        return this;
    }


}
