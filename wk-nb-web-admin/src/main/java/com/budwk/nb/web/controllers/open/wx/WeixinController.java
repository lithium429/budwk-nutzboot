package com.budwk.nb.web.controllers.open.wx;

import com.alibaba.dubbo.config.annotation.Reference;
import com.budwk.nb.web.commons.ext.wx.WxHandler;
import com.budwk.nb.wx.services.WxConfigService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.weixin.util.Wxs;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author wizzer(wizzer @ qq.com) on 2016/7/3.
 */
@IocBean
@At("/open/weixin")
public class WeixinController {
    private static final Log log = Logs.get();
    @Inject
    @Reference(check = false)
    private WxConfigService wxConfigService;
    @Inject
    private WxHandler wxHandler;

    public WeixinController() {
        Wxs.enableDevMode(); // 开启debug模式,这样就会把接收和发送的内容统统打印,方便查看
    }

    @At("/api/{key}")
    @Fail("http:200")
    public View msgIn(String key, HttpServletRequest req) throws IOException {
        return Wxs.handle(wxHandler, req, key);
    }

}
