package org.demo.zhanyi.latte.generators;

import org.demo.latte.annotation.EntryGenerator;
import org.demo.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by zhanyi on 2017/11/6.
 */

@EntryGenerator(
        packageName = "org.demo.zhanyi.latte",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
