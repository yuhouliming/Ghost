package com.example.dockerdemo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 音频转写翻译信息
 */

public class AudioIatTraInfo implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 唯一id（交易id）
     */
    private String uniqueId;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 用户音频的撰写结果与标准
     */
    private Float deviationRate;

    /**
     * 标准结果
     */
    private String standardResult;

    /**
     * 转写结果
     */
    private String iatResult;

    /**
     * 翻译结果
     */
    private String trnResult;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 错误信息
     */
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Float getDeviationRate() {
        return deviationRate;
    }

    public void setDeviationRate(Float deviationRate) {
        this.deviationRate = deviationRate;
    }

    public String getStandardResult() {
        return standardResult;
    }

    public void setStandardResult(String standardResult) {
        this.standardResult = standardResult;
    }

    public String getIatResult() {
        return iatResult;
    }

    public void setIatResult(String iatResult) {
        this.iatResult = iatResult;
    }

    public String getTrnResult() {
        return trnResult;
    }

    public void setTrnResult(String trnResult) {
        this.trnResult = trnResult;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AudioIatTraInfo other = (AudioIatTraInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUniqueId() == null ? other.getUniqueId() == null : this.getUniqueId().equals(other.getUniqueId()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getDeviationRate() == null ? other.getDeviationRate() == null : this.getDeviationRate().equals(other.getDeviationRate()))
            && (this.getStandardResult() == null ? other.getStandardResult() == null : this.getStandardResult().equals(other.getStandardResult()))
            && (this.getIatResult() == null ? other.getIatResult() == null : this.getIatResult().equals(other.getIatResult()))
            && (this.getTrnResult() == null ? other.getTrnResult() == null : this.getTrnResult().equals(other.getTrnResult()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getErrorMsg() == null ? other.getErrorMsg() == null : this.getErrorMsg().equals(other.getErrorMsg()));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUniqueId() == null) ? 0 : getUniqueId().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getDeviationRate() == null) ? 0 : getDeviationRate().hashCode());
        result = prime * result + ((getStandardResult() == null) ? 0 : getStandardResult().hashCode());
        result = prime * result + ((getIatResult() == null) ? 0 : getIatResult().hashCode());
        result = prime * result + ((getTrnResult() == null) ? 0 : getTrnResult().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getErrorMsg() == null) ? 0 : getErrorMsg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uniqueId=").append(uniqueId);
        sb.append(", filePath=").append(filePath);
        sb.append(", deviationRate=").append(deviationRate);
        sb.append(", standardResult=").append(standardResult);
        sb.append(", iatResult=").append(iatResult);
        sb.append(", trnResult=").append(trnResult);
        sb.append(", createTime=").append(createTime);
        sb.append(", errorMsg=").append(errorMsg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}