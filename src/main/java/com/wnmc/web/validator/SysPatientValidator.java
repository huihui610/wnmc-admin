package com.wnmc.web.validator;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.PhoneUtil;
import com.wnmc.common.exception.BusinessException;
import com.wnmc.web.domain.SysPatient;

public class SysPatientValidator {


    /**
     * 新增患者信息参数校验
     * @param sysPatient
     * @throws Exception
     */
    public static void insertSysPatientValidator(SysPatient sysPatient) throws Exception {
        if (null == sysPatient.getPhonenumber() || sysPatient.getPhonenumber().length() != 11) {
            throw new Exception("新增失败：手机号码【" + sysPatient.getPhonenumber() + "】不合法");
        }

        if (null == sysPatient.getCardNumber() || sysPatient.getCardNumber().length() != 18) {
            throw new Exception("新增失败：身份证号码【" + sysPatient.getCardNumber() + "】不合法");
        }

        if (null == sysPatient.getPatientName() || sysPatient.getPatientName().length() > 30) {
            throw new Exception("新增失败：患者姓名【" + sysPatient.getPatientName() + "】长度不能超过30字符");
        }


        //1、2 身份证、手机号合法性校验
        //
        String cardNumber = sysPatient.getCardNumber();
        if (!IdcardUtil.isValidCard(sysPatient.getCardNumber())) {
            throw new Exception("新增失败：身份证号码【" + cardNumber + "】不合法");
        }

        if (!PhoneUtil.isMobile(sysPatient.getPhonenumber())) {
            throw new Exception("新增失败：手机号码【" + sysPatient.getPhonenumber() + "】不合法");
        }
    }


    /**
     * 编辑患者信息参数校验
     * @param sysPatient
     */
    public static void updateSysPatientValidator(SysPatient sysPatient) {
        //1、长度校验
        if (null == sysPatient.getPhonenumber() || sysPatient.getPhonenumber().length() != 11) {
            throw new BusinessException("编辑失败：手机号码【" + sysPatient.getPhonenumber() + "】不合法");
        }

        if (null == sysPatient.getCardNumber() || sysPatient.getCardNumber().length() != 18) {
            throw new BusinessException("编辑失败：身份证号码【" + sysPatient.getCardNumber() + "】不合法");
        }

        if (null == sysPatient.getPatientName() || sysPatient.getPatientName().length() > 30) {
            throw new BusinessException("编辑失败：患者姓名【" + sysPatient.getPatientName() + "】长度不能超过30字符");
        }
        //2、合法性校验 身份证 手机号
        if (!IdcardUtil.isValidCard(sysPatient.getCardNumber())) {
            throw new BusinessException("新增失败：身份证号码【" + sysPatient.getCardNumber() + "】不合法");
        }

        if (!PhoneUtil.isMobile(sysPatient.getPhonenumber())) {
            throw new BusinessException("新增失败：手机号码【" + sysPatient.getPhonenumber() + "】不合法");
        }
    }
}
