package cc.msonion.carambola.fileresource.pojo;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionDateUtils;
import cc.msonion.carambola.parent.interfaces.pojo.base.MsOnionBasePojoAdapter;
import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class FileResourceDownload implements MsOnionBasePojoAdapter {
    /**
     * 文件下载主键idx，必须是分布式架构，全局唯一递增的
     */
    private Long idx;

    /**
     主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    private String idxCodeS;

    /**
     * 主键idx生成的code
     */
    private Long idxCode;

    /**
     * 文件idx## @NotNull(message = "文件idx不能为空")##
     */
    @NotNull(message = "文件idx不能为空")
    private Long fileIdx;

    /**
     * 唯一文件消息id
     */
    private Long messageId;

    /**
     * 文件总大小
     */
    private Long totalSize;

    /**
     * 下载大小
     */
    private Long downloadSize;

    /**
     * 上传状态，1:成功，2:失败，0：下载中
     */
    private Short downloadStatus;

    /**
     * 备注，不能为null## @Length(max = 100, message = "备注最大100个字符")##
     */
    @Length(max = 100, message = "备注最大100个字符")
    private String remark;

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    private Short status;

    /**
     * 版本号，高并发，乐观解决方案，不可以为null
     */
    private Long version;

    /**
     * 被哪个成员创建
     */
    private Long createByMemberIdx;

    /**
     * 被哪个成员修改
     */
    private Long updateByMemberIdx;

    /**
    创建时间，第一次创建时间，后续不可以修改时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    private String createTimeEnYyyyMMddHHmmss;

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    private Date createTime;

    /**
     * 更新时间，每次修改，都要更新时间
     */
    private Date updateTime;

    /**
     * 扩展字段，最多100个字符，不能为null
     */
    private String ext;

    private static final long serialVersionUID = 1L;

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    private String id;

    /**
     * 文件下载主键idx，必须是分布式架构，全局唯一递增的
     */
    public Long getIdx() {
        return idx;
    }

    /**
     * 文件下载主键idx，必须是分布式架构，全局唯一递增的
     */
    public void setIdx(Long idx) {
        this.idx = idx;
    }

    /**
     主键idx生成的code，
     MyBatis逆向工程，动态生成字符串类型
     */
    public String getIdxCodeS() {
        return String.valueOf(this.idxCode);
    }

    /**
     * 主键idx生成的code
     */
    public Long getIdxCode() {
        return idxCode;
    }

    /**
     * 主键idx生成的code
     */
    public void setIdxCode(Long idxCode) {
        this.idxCode = idxCode;
    }

    /**
     * 文件idx## @NotNull(message = "文件idx不能为空")##
     */
    public Long getFileIdx() {
        return fileIdx;
    }

    /**
     * 文件idx## @NotNull(message = "文件idx不能为空")##
     */
    public void setFileIdx(Long fileIdx) {
        this.fileIdx = fileIdx;
    }

    /**
     * 唯一文件消息id
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * 唯一文件消息id
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 文件总大小
     */
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     * 文件总大小
     */
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * 下载大小
     */
    public Long getDownloadSize() {
        return downloadSize;
    }

    /**
     * 下载大小
     */
    public void setDownloadSize(Long downloadSize) {
        this.downloadSize = downloadSize;
    }

    /**
     * 上传状态，1:成功，2:失败，0：下载中
     */
    public Short getDownloadStatus() {
        return downloadStatus;
    }

    /**
     * 上传状态，1:成功，2:失败，0：下载中
     */
    public void setDownloadStatus(Short downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    /**
     * 备注，不能为null## @Length(max = 100, message = "备注最大100个字符")##
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，不能为null## @Length(max = 100, message = "备注最大100个字符")##
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 状态，1：可用，0：删除，取值范围0-9，不可以为null
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 版本号，高并发，乐观解决方案，不可以为null
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 版本号，高并发，乐观解决方案，不可以为null
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 被哪个成员创建
     */
    public Long getCreateByMemberIdx() {
        return createByMemberIdx;
    }

    /**
     * 被哪个成员创建
     */
    public void setCreateByMemberIdx(Long createByMemberIdx) {
        this.createByMemberIdx = createByMemberIdx;
    }

    /**
     * 被哪个成员修改
     */
    public Long getUpdateByMemberIdx() {
        return updateByMemberIdx;
    }

    /**
     * 被哪个成员修改
     */
    public void setUpdateByMemberIdx(Long updateByMemberIdx) {
        this.updateByMemberIdx = updateByMemberIdx;
    }

    /**
    创建时间，第一次创建时间，后续不可以修改时间，
    日期格式：yyyy-MM-dd HH:mm:ss，例如：2017-05-02 22:12:28
     */
    public String getCreateTimeEnYyyyMMddHHmmss() throws MsOnionException {
        return MsOnionDateUtils.formatYyyyMMddHHmmss(this.createTime) ;
    }

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间，第一次创建时间，后续不可以修改时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间，每次修改，都要更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间，每次修改，都要更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 扩展字段，最多100个字符，不能为null
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展字段，最多100个字符，不能为null
     */
    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    /**
     * 这个id是字符串的，和数据库中的idx（Long)对应，是JQuery EasyUI组件使用了,
     * 其他地方不要随便使用，例如：不可以使用在DAO层进行业务处理或者插入数据到数据库，
     * 不是使用id，而是使用idx（Long）
     * 唯一标识，idx, JavaScript  对 18位数字idx支持不好，导致最后一位丢失，所以使用字符串id
     */
    @Override
    public String getId() {
        return this.idx + "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idx=").append(idx);
        sb.append(", idxCodeS=").append(idxCodeS);
        sb.append(", idxCode=").append(idxCode);
        sb.append(", fileIdx=").append(fileIdx);
        sb.append(", messageId=").append(messageId);
        sb.append(", totalSize=").append(totalSize);
        sb.append(", downloadSize=").append(downloadSize);
        sb.append(", downloadStatus=").append(downloadStatus);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", createByMemberIdx=").append(createByMemberIdx);
        sb.append(", updateByMemberIdx=").append(updateByMemberIdx);
        sb.append(", createTimeEnYyyyMMddHHmmss=").append(createTimeEnYyyyMMddHHmmss);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}