package org.demo.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by feibai on 2017/8/29.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue601'),
    icon_ali_pay('\ue600');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return this.name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
