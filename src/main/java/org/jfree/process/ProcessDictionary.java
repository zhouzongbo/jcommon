package org.jfree.process;

import java.util.ArrayList;
import java.util.List;

public class ProcessDictionary {
    private Long id;
    private Long parentId;
    private Integer deep;
    private String processKey;
    private String processName;
    private Integer sort;
    private String startUrl = "";
    private Integer type = 1045000001;
    private Integer status = 1;
    private List<ProcessDictionary> children = new ArrayList<>();
    
    public ProcessDictionary() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getDeep() {
        return deep;
    }
    
    public void setDeep(Integer deep) {
        this.deep = deep;
    }
    
    public String getProcessKey() {
        return processKey;
    }
    
    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }
    
    public String getProcessName() {
        return processName;
    }
    
    public void setProcessName(String processName) {
        this.processName = processName;
    }
    
    public Integer getSort() {
        return sort;
    }
    
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    
    public String getStartUrl() {
        return startUrl;
    }
    
    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "ProcessDictionary{" + "id=" + id + ", deep=" + deep + ", processKey='" + processKey + '\''
                + ", processName='" + processName + '\'' + ", sort=" + sort + ", startUrl='" + startUrl + '\''
                + ", type=" + type + ", status=" + status + '}';
    }
    
    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public void addChildren(ProcessDictionary processDictionary) {
        children.add(processDictionary);
    }
}
