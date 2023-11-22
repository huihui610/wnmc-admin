package com.wnmc.web.service.impl;

import java.util.List;

import com.wnmc.common.exception.BusinessException;
import com.wnmc.web.domain.SysPatientSch;
import com.wnmc.web.domain.SysSch;
import com.wnmc.web.mapper.SysSchMapper;
import com.wnmc.web.service.ISysPatientSchService;
import com.wnmc.web.service.ISysSchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wnmc.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 门诊排班Service业务层处理
 * 
 * @author huming
 * @date 2023-04-05
 */
@Service
public class SysSchServiceImpl implements ISysSchService
{
    @Autowired
    private SysSchMapper sysSchMapper;

    @Autowired
    private ISysPatientSchService sysPatientSchService;

    /**
     * 查询门诊排班
     * 
     * @param schId 门诊排班主键
     * @return 门诊排班
     */
    @Override
    public SysSch selectSysSchBySchId(Long schId)
    {
        return sysSchMapper.selectSysSchBySchId(schId);
    }

    /**
     * 查询门诊排班列表
     * 
     * @param sysSch 门诊排班
     * @return 门诊排班
     */
    @Override
    public List<SysSch> selectSysSchList(SysSch sysSch)
    {
        return sysSchMapper.selectSysSchList(sysSch);
    }

    /**
     * 新增门诊排班
     * 
     * @param sysSch 门诊排班
     * @return 结果
     */
    @Override
    public int insertSysSch(SysSch sysSch)
    {
        int num=sysSchMapper.countSysSchByschDoctorName(sysSch);
        if (num > 0) {
            throw new BusinessException("新增失败：该医生已经被排班"+sysSch.getSchDoctorName()+"无法再排班");

        }
        sysSch.setSchRemain(sysSch.getSchNumber());
        return sysSchMapper.insertSysSch(sysSch);
    }

    /**
     * 修改门诊排班
     * 
     * @param sysSch 门诊排班
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)//事务处理注解
    public int updateSysSch(SysSch sysSch)
    {
        //1、参数校验
        SysSch entity = sysSchMapper.selectSysSchBySchId(sysSch.getSchId());
        if (null == entity){
            throw new BusinessException("挂号失败：排班信息丢失");
        }
        //修改挂号个数（remainNum）
        Long remainNum=entity.getSchRemain();
        if (remainNum == 0) {
            throw new BusinessException("挂号失败,排班已满");

        }
        //1、2 校验该患者是否预约了该排班 sch_id 、 patient_no
        int count = sysPatientSchService.countBySchIdAndPatientNo(sysSch);
        if (count > 0){
            throw new BusinessException("挂号失败：患者【" + sysSch.getPatientNo() +
                    "】已经预约了该排班");
        }


        //2、修改剩余号源数量
        entity.setSchRemain(entity.getSchRemain() - 1);
        sysSchMapper.updateSysSch(entity);

        //3、添加患者预约挂号信息
        SysPatientSch param = new SysPatientSch();
        param.setSchId(sysSch.getSchId());
        param.setPatientNo(sysSch.getPatientNo());
        //3.1、生成序号
        Integer appointNum = sysPatientSchService.generateAppointNum(sysSch.getSchId());
        param.setAppointNum(Long.valueOf(appointNum)+1);
        //3.2 插入患者预约信息
        return sysPatientSchService.insertSysPatientSch(param);
    }

    /**
     * 批量删除门诊排班
     * 
     * @param schIds 需要删除的门诊排班主键
     * @return 结果
     */
    @Override
    public int deleteSysSchBySchIds(String schIds)
    {
        return sysSchMapper.deleteSysSchBySchIds(Convert.toStrArray(schIds));
    }

    /**
     * 删除门诊排班信息
     * 
     * @param schId 门诊排班主键
     * @return 结果
     */
    @Override
    public int deleteSysSchBySchId(Long schId)
    {
        return sysSchMapper.deleteSysSchBySchId(schId);
    }

}
