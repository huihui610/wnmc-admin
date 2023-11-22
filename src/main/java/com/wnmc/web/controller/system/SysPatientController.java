package com.wnmc.web.controller.system;

import java.util.List;

import com.wnmc.common.annotation.Log;
import com.wnmc.common.core.controller.BaseController;
import com.wnmc.common.core.domain.AjaxResult;
import com.wnmc.common.core.page.TableDataInfo;
import com.wnmc.common.enums.BusinessType;
import com.wnmc.common.utils.poi.ExcelUtil;
import com.wnmc.web.domain.SysPatient;
import com.wnmc.web.service.ISysPatientService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 患者信息Controller
 * 
 * @author ruoyi
 * @date 2023-03-07
 */
@Controller
@RequestMapping("/system/patient")
public class SysPatientController extends BaseController
{
    private String prefix = "system/patient";

    @Autowired
    private ISysPatientService sysPatientService;

    @GetMapping()
    public String patient()
    {
        return prefix + "/patient";
    }

    /**
     * 查询患者信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPatient sysPatient)
    {
        startPage();
        List<SysPatient> list = sysPatientService.selectSysPatientList(sysPatient);
        return getDataTable(list);
    }

    /**
     * 导出患者信息列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPatient sysPatient)
    {
        List<SysPatient> list = sysPatientService.selectSysPatientList(sysPatient);
        ExcelUtil<SysPatient> util = new ExcelUtil<SysPatient>(SysPatient.class);
        return util.exportExcel(list, "患者信息数据");
    }

    /**
     * 新增患者信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存患者信息
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPatient sysPatient)
    {
       try {
           return toAjax(sysPatientService.insertSysPatient(sysPatient));
       }catch (Exception e){
           return AjaxResult.error(e.getMessage());
       }
    }

    /**
     * 修改患者信息
     */
    @GetMapping("/edit/{patientId}")
    public String edit(@PathVariable("patientId") Long patientId, ModelMap mmap)
    {
        SysPatient sysPatient = sysPatientService.selectSysPatientByPatientId(patientId);
        mmap.put("sysPatient", sysPatient);
        return prefix + "/edit";
    }

    /**
     * 修改保存患者信息
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPatient sysPatient)
    {
        return toAjax(sysPatientService.updateSysPatient(sysPatient));
    }

    /**
     * 删除患者信息
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysPatientService.deleteSysPatientByPatientIds(ids));
    }
}
