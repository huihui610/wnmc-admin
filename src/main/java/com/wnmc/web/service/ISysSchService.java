package com.wnmc.web.service;

import java.util.List;
import com.wnmc.web.domain.SysSch;

/**
 * 门诊排班Service接口
 * 
 * @author huming
 * @date 2023-04-05
 */
public interface ISysSchService 
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
     * 批量删除门诊排班
     * 
     * @param schIds 需要删除的门诊排班主键集合
     * @return 结果
     */
    public int deleteSysSchBySchIds(String schIds);

    /**
     * 删除门诊排班信息
     * 
     * @param schId 门诊排班主键
     * @return 结果
     */
    public int deleteSysSchBySchId(Long schId);
}
