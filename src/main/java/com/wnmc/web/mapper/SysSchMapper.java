package com.wnmc.web.mapper;

import com.wnmc.web.domain.SysSch;

import java.util.List;

/**
 * 门诊排班Mapper接口
 * 
 * @author huming
 * @date 2023-04-05
 */
public interface SysSchMapper 
{
    /**
     * 查询门诊排班
     * 
     * @param schId 门诊排班主键
     * @return 门诊排班
     */
    public SysSch selectSysSchBySchId(Long schId);

    /**
     * 查询门诊排班列表
     * 
     * @param sysSch 门诊排班
     * @return 门诊排班集合
     */
    public List<SysSch> selectSysSchList(SysSch sysSch);

    /**
     * 新增门诊排班
     * 
     * @param sysSch 门诊排班
     * @return 结果
     */
    public int insertSysSch(SysSch sysSch);

    /**
     * 修改门诊排班
     * 
     * @param sysSch 门诊排班
     * @return 结果
     */
    public int updateSysSch(SysSch sysSch);

    /**
     * 删除门诊排班
     * 
     * @param schId 门诊排班主键
     * @return 结果
     */
    public int deleteSysSchBySchId(Long schId);

    /**
     * 批量删除门诊排班
     * 
     * @param schIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSchBySchIds(String[] schIds);

    /**
     * 根据医生的名字schDoctorName统计个数
     * @param sysSch
     * @return
     */
    int countSysSchByschDoctorName(SysSch sysSch);
}
