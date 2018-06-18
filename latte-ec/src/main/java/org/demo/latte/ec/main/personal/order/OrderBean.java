package org.demo.latte.ec.main.personal.order;

public class OrderBean {

    private String title;

    private String price;

    private String time;

    private String image;

    public OrderBean(String title, String price, String time, String image) {
        this.title = title;
        this.price = price;
        this.time = time;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static final class Builder {
        private String title;

        private String price;

        private String time;

        private String image;


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setPrice(String price) {
            this.price = price;
            return this;
        }
        public Builder setTime(String time) {
            this.time = time;
            return this;
        }
        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public OrderBean build() {
            return new OrderBean(title, price, time, image);
        }
    }
}
