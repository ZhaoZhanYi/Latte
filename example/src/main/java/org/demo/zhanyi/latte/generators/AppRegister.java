package org.demo.zhanyi.latte.generators;

import org.demo.latte.annotation.AppRegisterGenerator;
import org.demo.latte.wechat.templates.AppRigisterTemplate;

/**
 * Created by zhanyi on 2017/11/6.
 */
@AppRegisterGenerator(
        packageName = "org.demo.zhanyi.latte",
        registerTemplate = AppRigisterTemplate.class
)
public interface AppRegister {
}
