package com.wnmc.web.mapper;

import com.wnmc.web.domain.SysPatient;

import java.util.List;


/**
 * 患者信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
public interface SysPatientMapper 
{
    /**
     * 查询患者信息
     * 
     * @param patientId 患者信息主键
     * @return 患者信息
     */
    public SysPatient selectSysPatientByPatientId(Long patientId);

    /**
     * 查询患者信息列表
     * 
     * @param sysPatient 患者信息
     * @return 患者信息集合
     */
    public List<SysPatient> selectSysPatientList(SysPatient sysPatient);

    /**
     * 新增患者信息
     * 
     * @param sysPatient 患者信息
     * @return 结果
     */
    public int insertSysPatient(SysPatient sysPatient);

    /**
     * 修改患者信息
     * 
     * @param sysPatient 患者信息
     * @return 结果
     */
    public int updateSysPatient(SysPatient sysPatient);

    /**
     * 删除患者信息
     * 
     * @param patientId 患者信息主键
     * @return 结果
     */
    public int deleteSysPatientByPatientId(Long patientId);

    /**
     * 批量删除患者信息
     * 
     * @param patientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPatientByPatientIds(String[] patientIds);

    /**
     * 通过身份证号码查询数据库中该身份证号码存在的数据条数
     * @param cardNumber 身份证号码
     * @return 数据条数
     */
    int countSysPatientByCardNumber(String cardNumber);

    /**
     * 通过身份证号码查询数据条数（排除自身通过怕patient_id）
     * @param sysPatient
     * @return
     */
    int countSysPatientByCardNumber1(SysPatient sysPatient);
}
