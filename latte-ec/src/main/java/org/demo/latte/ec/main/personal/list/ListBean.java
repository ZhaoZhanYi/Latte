package org.demo.latte.ec.main.personal.list;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ListBean implements MultiItemEntity {

    private int id;

    private String title;

    private String icon;

    private int itemType;

    public ListBean(int id, String title, String icon, int itemType) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.itemType = itemType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    /**
     * 建造者类
     */
    public static final class Builder {

        private int id;

        private String title;

        private String icon;

        private int itemType;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }


        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public ListBean build() {
            return new ListBean(id, title, icon, itemType);
        }
    }

}
