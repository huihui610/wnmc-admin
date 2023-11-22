package com.wnmc.web.mapper;

import com.wnmc.web.domain.SysPatientSch;
import com.wnmc.web.domain.SysSch;

import java.util.List;

/**
 * 患者预约信息Mapper接口
 * 
 * @author 胡铭
 * @date 2023-04-05
 */
public interface SysPatientSchMapper 
{
    /**
     * 查询患者预约信息
     * 
     * @param appointId 患者预约信息主键
     * @return 患者预约信息
     */
    public SysPatientSch selectSysPatientSchByAppointId(Long appointId);

    /**
     * 查询患者预约信息列表
     * 
     * @param sysPatientSch 患者预约信息
     * @return 患者预约信息集合
     */
    public List<SysPatientSch> selectSysPatientSchList(SysPatientSch sysPatientSch);

    /**
     * 新增患者预约信息
     * 
     * @param sysPatientSch 患者预约信息
     * @return 结果
     */
    public int insertSysPatientSch(SysPatientSch sysPatientSch);

    /**
     * 修改患者预约信息
     * 
     * @param sysPatientSch 患者预约信息
     * @return 结果
     */
    public int updateSysPatientSch(SysPatientSch sysPatientSch);

    /**
     * 删除患者预约信息
     * 
     * @param appointId 患者预约信息主键
     * @return 结果
     */
    public int deleteSysPatientSchByAppointId(Long appointId);

    /**
     * 批量删除患者预约信息
     * 
     * @param appointIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPatientSchByAppointIds(String[] appointIds);

    /**
     * 根据预约ID生成序号
     * @param schId 预约ID
     * @return
     */
    Integer generateAppointNum(Long schId);

    /**
     * 通过患者编号 排班ID 查询患者是否已经预约
     * @param sysSch
     * @return
     */
    int countBySchIdAndPatientNo(SysSch sysSch);
}
