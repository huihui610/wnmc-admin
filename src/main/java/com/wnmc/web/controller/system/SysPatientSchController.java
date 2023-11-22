package com.wnmc.web.controller.system;

import java.util.List;

import com.wnmc.common.annotation.Log;
import com.wnmc.common.core.controller.BaseController;
import com.wnmc.common.core.domain.AjaxResult;
import com.wnmc.common.core.page.TableDataInfo;
import com.wnmc.common.enums.BusinessType;
import com.wnmc.common.utils.poi.ExcelUtil;
import com.wnmc.web.domain.SysPatientSch;
import com.wnmc.web.service.ISysPatientSchService;
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
 * 患者预约信息Controller
 * 
 * @author 胡铭
 * @date 2023-04-05
 */
@Controller
@RequestMapping("/system/appoint")
public class SysPatientSchController extends BaseController
{
    private String prefix = "system/appoint";

    @Autowired
    private ISysPatientSchService sysPatientSchService;

    @GetMapping()
    public String appoint()
    {
        return prefix + "/appoint";
    }

    /**
     * 查询患者预约信息列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPatientSch sysPatientSch)
    {
        startPage();
        List<SysPatientSch> list = sysPatientSchService.selectSysPatientSchList(sysPatientSch);
        return getDataTable(list);
    }

    /**
     * 新增患者预约信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存患者预约信息
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPatientSch sysPatientSch)
    {
        return toAjax(sysPatientSchService.insertSysPatientSch(sysPatientSch));
    }

    /**
     * 修改患者预约信息
     */
    @GetMapping("/edit/{appointId}")
    public String edit(@PathVariable("appointId") Long appointId, ModelMap mmap)
    {
        SysPatientSch sysPatientSch = sysPatientSchService.selectSysPatientSchByAppointId(appointId);
        mmap.put("sysPatientSch", sysPatientSch);
        return prefix + "/edit";
    }

    /**
     * 修改保存患者预约信息
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPatientSch sysPatientSch)
    {
        return toAjax(sysPatientSchService.updateSysPatientSch(sysPatientSch));
    }

    /**
     * 删除患者预约信息
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysPatientSchService.deleteSysPatientSchByAppointIds(ids));
    }
}
