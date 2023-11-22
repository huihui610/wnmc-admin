package com.wnmc.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.wnmc.common.annotation.Excel;
import com.wnmc.common.core.domain.BaseEntity;

/**
 * 门诊排班对象 sys_sch
 * 
 * @author huming
 * @date 2023-04-05
 */
public class SysSch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排版ID */
    private Long schId;

    /** 排班医生 */
    private String schDoctorName;

    /** 排班日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date schDate;

    /** 排班号源数 */
    private Long schNumber;

    /** 剩余号源数 */
    private Long schRemain;

    /** 诊室 */
    private String schAddr;

    /** 患者编号 */
    private String patientNo;

    public void setSchId(Long schId) 
    {
        this.schId = schId;
    }

    public Long getSchId() 
    {
        return schId;
    }
    public void setSchDoctorName(String schDoctorName) 
    {
        this.schDoctorName = schDoctorName;
    }

    public String getSchDoctorName() 
    {
        return schDoctorName;
    }
    public void setSchDate(Date schDate) 
    {
        this.schDate = schDate;
    }

    public Date getSchDate() 
    {
        return schDate;
    }
    public void setSchNumber(Long schNumber) 
    {
        this.schNumber = schNumber;
    }

    public Long getSchNumber() 
    {
        return schNumber;
    }
    public void setSchAddr(String schAddr) 
    {
        this.schAddr = schAddr;
    }

    public String getSchAddr() 
    {
        return schAddr;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public Long getSchRemain() {
        return schRemain;
    }

    public void setSchRemain(Long schRemain) {
        this.schRemain = schRemain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("schId", getSchId())
            .append("schDoctorName", getSchDoctorName())
            .append("schDate", getSchDate())
            .append("schNumber", getSchNumber())
            .append("schAddr", getSchAddr())
            .toString();
    }
}
