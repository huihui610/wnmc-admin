package com.wnmc.web.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.RandomUtil;
import com.wnmc.common.core.text.Convert;
import com.wnmc.common.exception.BusinessException;
import com.wnmc.common.utils.DateUtils;
import com.wnmc.web.domain.SysPatient;
import com.wnmc.web.mapper.SysPatientMapper;
import com.wnmc.web.service.ISysPatientService;
import com.wnmc.web.validator.SysPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 患者信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-07
 */
@Service
public class SysPatientServiceImpl implements ISysPatientService {
    @Autowired
    private SysPatientMapper sysPatientMapper;

    /**
     * 查询患者信息
     *
     * @param patientId 患者信息主键
     * @return 患者信息
     */
    @Override
    public SysPatient selectSysPatientByPatientId(Long patientId) {
        return sysPatientMapper.selectSysPatientByPatientId(patientId);
    }

    /**
     * 查询患者信息列表
     *
     * @param sysPatient 患者信息
     * @return 患者信息
     */
    @Override
    public List<SysPatient> selectSysPatientList(SysPatient sysPatient) {
        return sysPatientMapper.selectSysPatientList(sysPatient);
    }

    /**
     * 新增患者信息
     *
     * @param sysPatient 患者信息
     * @return 结果
     */
    @Override
    public int insertSysPatient(SysPatient sysPatient) throws Exception {
        //1、数据长度校验
        SysPatientValidator.insertSysPatientValidator(sysPatient);
        //2、身份证唯一性的校验
        int num = sysPatientMapper.countSysPatientByCardNumber(sysPatient.getCardNumber());
        if (num > 0) {
            throw new Exception("新增失败：身份证号码【" + sysPatient.getCardNumber() + "】已存在");
        }

        //随机生成患者编号   20230330113250 + 000001
        String patientNo = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN )
                + RandomUtil.randomNumbers(6);
        sysPatient.setPatientNo(patientNo);
        sysPatient.setCreateTime(DateUtils.getNowDate());
        return sysPatientMapper.insertSysPatient(sysPatient);
    }

    /**
     * 修改患者信息
     *
     * @param sysPatient 患者信息
     * @return 结果
     */
    @Override
    public int updateSysPatient(SysPatient sysPatient) {

        SysPatientValidator.updateSysPatientValidator(sysPatient);

        //3、唯一性校验
        int num = sysPatientMapper.countSysPatientByCardNumber1(sysPatient);
        if (num > 0) {
            throw new BusinessException("编辑失败：身份证号码【" + sysPatient.getCardNumber() + "】已存在");
        }

        sysPatient.setPatientNo(null);
        sysPatient.setUpdateTime(DateUtils.getNowDate());
        return sysPatientMapper.updateSysPatient(sysPatient);
    }

    /**
     * 批量删除患者信息
     *
     * @param patientIds 需要删除的患者信息主键
     * @return 结果
     */
    @Override
    public int deleteSysPatientByPatientIds(String patientIds) {
        return sysPatientMapper.deleteSysPatientByPatientIds(Convert.toStrArray(patientIds));
    }

    /**
     * 删除患者信息信息
     *
     * @param patientId 患者信息主键
     * @return 结果
     */
    @Override
    public int deleteSysPatientByPatientId(Long patientId) {
        return sysPatientMapper.deleteSysPatientByPatientId(patientId);
    }
}
