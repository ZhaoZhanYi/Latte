package org.demo.zhanyi.latte.generators;

import org.demo.latte.annotation.PayEntryGenerator;
import org.demo.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by zhanyi on 2017/11/6.
 */

@PayEntryGenerator(
        packageName = "org.demo.zhanyi.latte",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
