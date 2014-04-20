package com.abhijit.mobeng.model;

/**
 * <pre>
 *  ================
 *  Sample Json
 *  ================
 *  {
 *      attrib: "$50 to Spend on Juicy Steaks, Seafood, and Drink",
 *      desc: "Bobby Van's Grill",
 *      href: "https://www.livingsocial.com/deals/1005533-50-to-spend-on-food-and-drink",
 *      src: "http://sheltered-bastion-2512.herokuapp.com/4230ddd6-6368-4c30-8a78-3f9fa361ff05-540_q80.jpg",
 *      user: {
 *          .......
 *      }
 *  }
 *
 *  </pre>
 * Created by AbhijitNukalapati on 4/20/14.
 */
public class Deal {
    private String attrib; // title
    private String desc; // place
    private String href;
    private String src; //image source url
    private User user;

    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
        this.attrib = attrib;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
