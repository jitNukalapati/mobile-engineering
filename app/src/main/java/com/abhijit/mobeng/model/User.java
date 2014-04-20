package com.abhijit.mobeng.model;

/**
 * <pre>
 *
 * =================
 * Sample Json
 * =================
 *  {
 *      name: "Michael Evans",
 *      avatar: {
 *          .....
 *      },
 *      username: "MichaelEvans"
 *  }
 *
 * </pre>
 * Created by AbhijitNukalapati on 4/20/14.
 */
public class User {
    private String name;
    private Avatar avatar;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    /**
     * <pre>
     * =================
     * Sample Json
     * =================
     *
     *  avatar: {
     *      src: "https://identicons.github.com/acec1ab342b7ac191c9c5797fe2267d1.png",
     *      width: 250,
     *      height: 250
     *  },
     *
     * </pre>
     */
    public static class Avatar {
        private String src;
        private int width;
        private int height;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
