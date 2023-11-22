package com.wnmc.web.service.impl;

import java.util.List;

import com.wnmc.common.core.text.Convert;
import com.wnmc.common.exception.BusinessException;
import com.wnmc.common.utils.DateUtils;
import com.wnmc.web.domain.SysPatientSch;
import com.wnmc.web.domain.SysSch;
import com.wnmc.web.mapper.SysPatientMapper;
import com.wnmc.web.mapper.SysPatientSchMapper;
import com.wnmc.web.mapper.SysSchMapper;
import com.wnmc.web.service.ISysPatientSchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 患者预约信息Service业务层处理
 *
 * @author 胡铭
 * @date 2023-04-05
 */
@Service
public class SysPatientSchServiceImpl implements ISysPatientSchService {
    @Autowired
    private SysPatientSchMapper sysPatientSchMapper;
    @Autowired
    private SysSchMapper sysSchMapper;

    /**
     * 查询患者预约信息
     *
     * @param appointId 患者预约信息主键
     * @return 患者预约信息
     */
    @Override
    public SysPatientSch selectSysPatientSchByAppointId(Long appointId) {
        return sysPatientSchMapper.selectSysPatientSchByAppointId(appointId);
    }

    /**
     * 查询患者预约信息列表
     *
     * @param sysPatientSch 患者预约信息
     * @return 患者预约信息
     */
    @Override
    public List<SysPatientSch> selectSysPatientSchList(SysPatientSch sysPatientSch) {
        return sysPatientSchMapper.selectSysPatientSchList(sysPatientSch);
    }

    /**
     * 新增患者预约信息
     *
     * @param sysPatientSch 患者预约信息
     * @return 结果
     */
    @Override
    public int insertSysPatientSch(SysPatientSch sysPatientSch) {
        sysPatientSch.setCreateTime(DateUtils.getNowDate());
        return sysPatientSchMapper.insertSysPatientSch(sysPatientSch);
    }

    /**
     * 修改患者预约信息
     *
     * @param sysPatientSch 患者预约信息
     * @return 结果
     */
    @Override
    public int updateSysPatientSch(SysPatientSch sysPatientSch) {
        sysPatientSch.setUpdateTime(DateUtils.getNowDate());
        return sysPatientSchMapper.updateSysPatientSch(sysPatientSch);
    }

    /**
     * 批量删除患者预约信息
     *
     * @param appointIds 需要删除的患者预约信息主键
     * @return 结果
     */
    @Override

    public int deleteSysPatientSchByAppointIds(String appointIds) {
        //1.取消预约
        String[] ids = Convert.toStrArray(appointIds);//对字符串进行分割
        //遍历所有的ids[]逻辑删除相关的预约记录
        for (int i = 0; i < ids.length; i++) {
            SysPatientSch entity = sysPatientSchMapper.selectSysPatientSchByAppointId(Long.valueOf(ids[i]));
            if (entity != null) {
                SysSch sysSch = sysSchMapper.selectSysSchBySchId(entity.getSchId());
                sysSch.setSchRemain(sysSch.getSchRemain() + 1);
//                剩余号不能比设定的排班号大
                if (sysSch.getSchNumber() < sysSch.getSchRemain()) {
                    throw new BusinessException("取消预约失败：剩余号大于排班号源数");
                }
                //更新排班信息(schRemain)剩余号
                sysSchMapper.updateSysSch(sysSch);
            }
        }
        return sysPatientSchMapper.deleteSysPatientSchByAppointIds(Convert.toStrArray(appointIds));
    }

    /**
     * 删除患者预约信息信息
     *
     * @param appointId 患者预约信息主键
     * @return 结果
     */
    @Override
    public int deleteSysPatientSchByAppointId(Long appointId) {
        return sysPatientSchMapper.deleteSysPatientSchByAppointId(appointId);
    }

    /**
     * 生成挂号预约序号
     * @param schId 预约ID
     * @return count
     */
    @Override
    public Integer generateAppointNum(Long schId) {
        return sysPatientSchMapper.generateAppointNum(schId);
    }

    /**
     * 通过schid统计排班医生的次数
     * @param sysSch
     * @return count
     */
    @Override
    public int countBySchIdAndPatientNo(SysSch sysSch) {
        return sysPatientSchMapper.countBySchIdAndPatientNo(sysSch);
    }
}
