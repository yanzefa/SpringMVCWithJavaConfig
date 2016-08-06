package com.zhangzhihao.SpringMVCWithJavaConfig.Controller;

import com.zhangzhihao.SpringMVCWithJavaConfig.Model.Log;
import com.zhangzhihao.SpringMVCWithJavaConfig.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import static com.zhangzhihao.SpringMVCWithJavaConfig.Utils.LogUtils.LogToDB;


@Controller
@RequestMapping("/Log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 日志统计界面
     *
     * @return 日志统计界面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String LogPage(Map<String, Object> map) {
        Long LogUtilsCount = logService.getExceptionCountByCaller_filename("LogUtils.java");//Controller出了异常
        Long LogAspectCount = logService.getExceptionCountByCaller_filename("LogAspect.java");//自定义类异常
        Long otherCount = logService.getExceptionCount() - LogAspectCount - LogUtilsCount;
        map.put("LogUtilsCount", LogUtilsCount);
        map.put("LogAspectCount", LogAspectCount);
        map.put("otherCount", otherCount);
        List<Log> listByPage = null;
        try {
            listByPage = (List<Log>) logService.getListByPage(1, 10);
        } catch (Exception e) {
            LogToDB(e);
        }
        map.put("list",listByPage);
        return "Log/Log";
    }


}
