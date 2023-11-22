package com.wnmc.web.controller.system;

import java.util.List;

import com.wnmc.web.domain.SysPatient;
import com.wnmc.web.domain.SysSch;
import com.wnmc.web.service.ISysPatientService;
import com.wnmc.web.service.ISysSchService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wnmc.common.annotation.Log;
import com.wnmc.common.enums.BusinessType;
import com.wnmc.common.core.controller.BaseController;
import com.wnmc.common.core.domain.AjaxResult;
import com.wnmc.common.utils.poi.ExcelUtil;
import com.wnmc.common.core.page.TableDataInfo;

/**
 * 门诊排班Controller
 * 
 * @author huming
 * @date 2023-04-05
 */
@Controller
@RequestMapping("/system/sch")
public class SysSchController extends BaseController
{
    private String prefix = "system/sch";

    @Autowired
    private ISysPatientService sysPatientService;

    @Autowired
    private ISysSchService sysSchService;

    @GetMapping()
    public String sch()
    {
        return prefix + "/sch";
    }

    /**
     * 查询门诊排班列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysSch sysSch)
    {
        startPage();
        List<SysSch> list = sysSchService.selectSysSchList(sysSch);
        return getDataTable(list);
    }


    /**
     * 新增门诊排班
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存门诊排班
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysSch sysSch)
    {
        return toAjax(sysSchService.insertSysSch(sysSch));
    }

    /**
     * 弹出预约界面
     */
    @GetMapping("/edit/{schId}")
    public String edit(@PathVariable("schId") Long schId, ModelMap mmap)
    {
        SysSch sysSch = sysSchService.selectSysSchBySchId(schId);
        SysPatient param = new SysPatient();
        param.setStatus("0");
        param.setDelFlag("0");
        List<SysPatient> patients = sysPatientService.selectSysPatientList(param);
        mmap.put("sysSch", sysSch);
        mmap.put("patients", patients);
        return prefix + "/edit";
    }

    /**
     * 预约挂号
     */
    @PostMapping("/appoint")
    @ResponseBody
    public AjaxResult editSave(SysSch sysSch)
    {
        return toAjax(sysSchService.updateSysSch(sysSch));
    }

    /**
     * 删除门诊排班
     */
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysSchService.deleteSysSchBySchIds(ids));
    }
}
