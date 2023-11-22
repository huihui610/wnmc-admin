package com.wnmc.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wnmc.common.annotation.Excel;
import com.wnmc.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 患者预约信息对象 sys_patient_sch
 * 
 * @author 胡铭
 * @date 2023-04-05
 */
public class SysPatientSch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预约ID */
    private Long appointId;

    /** 排班ID */
    private Long schId;

    /** 患者编号 */
    private String patientNo;

    //排班医生
    private String schDoctorName;

    //排班日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date schDate;

    //排班号源数
    private Long schNumber;

    //剩余号源数
    private Long schRemain;

    //就诊地址
    private String schAddr;

    //身份证号码
    private String cardNumber;

    //患者名称
    private String patientName;

    //手机号码
    private String phonenumber;

    /** 序号 */
    private Long appointNum;


    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setAppointId(Long appointId) 
    {
        this.appointId = appointId;
    }

    public Long getAppointId() 
    {
        return appointId;
    }
    public void setSchId(Long schId) 
    {
        this.schId = schId;
    }

    public Long getSchId() 
    {
        return schId;
    }
    public void setPatientNo(String patientNo) 
    {
        this.patientNo = patientNo;
    }

    public String getPatientNo() 
    {
        return patientNo;
    }
    public void setAppointNum(Long appointNum) 
    {
        this.appointNum = appointNum;
    }

    public Long getAppointNum() 
    {
        return appointNum;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getSchDoctorName() {
        return schDoctorName;
    }

    public void setSchDoctorName(String schDoctorName) {
        this.schDoctorName = schDoctorName;
    }

    public Date getSchDate() {
        return schDate;
    }

    public void setSchDate(Date schDate) {
        this.schDate = schDate;
    }

    public Long getSchNumber() {
        return schNumber;
    }

    public void setSchNumber(Long schNumber) {
        this.schNumber = schNumber;
    }

    public Long getSchRemain() {
        return schRemain;
    }

    public void setSchRemain(Long schRemain) {
        this.schRemain = schRemain;
    }

    public String getSchAddr() {
        return schAddr;
    }

    public void setSchAddr(String schAddr) {
        this.schAddr = schAddr;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appointId", getAppointId())
            .append("schId", getSchId())
            .append("patientNo", getPatientNo())
            .append("appointNum", getAppointNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
