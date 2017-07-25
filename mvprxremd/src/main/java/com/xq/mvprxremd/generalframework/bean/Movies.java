package com.xq.mvprxremd.generalframework.bean;

import java.util.List;

/**
 * @author 小侨
 * @time 2017/7/21  14:48
 * @desc 豆瓣250 bean
 */

public class Movies {

    public int count;
    public int start;
    public int total;
    public String title;

    public List<SubjectsBean> subjects;

    public static class SubjectsBean {
        /**
         * max : 10
         * average : 9.6
         * stars : 50
         * min : 0
         */

        public RatingBean rating;
        public String title;
        public int collect_count;
        public String original_title;
        public String subtype;
        public String year;
        /**
         * small : http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.webp
         * large : http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp
         * medium : http://img7.doubanio.com/view/movie_poster_cover/spst/public/p480747492.webp
         */

        public ImagesBean images;
        public String alt;
        public String id;
        public List<String> genres;
        /**
         * alt : https://movie.douban.com/celebrity/1054521/
         * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/17525.jpg","large":"http://img7.doubanio.com/img/celebrity/large/17525.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/17525.jpg"}
         * name : 蒂姆·罗宾斯
         * id : 1054521
         */

        public List<CastsBean> casts;
        /**
         * alt : https://movie.douban.com/celebrity/1047973/
         * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/230.jpg","large":"http://img7.doubanio.com/img/celebrity/large/230.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/230.jpg"}
         * name : 弗兰克·德拉邦特
         * id : 1047973
         */

        public List<DirectorsBean> directors;

        public static class RatingBean {
            public int max;
            public double average;
            public String stars;
            public int min;

        }

        public static class ImagesBean {
            public String small;
            public String large;
            public String medium;

        }

        public static class CastsBean {
            public String alt;
            /**
             * small : http://img7.doubanio.com/img/celebrity/small/17525.jpg
             * large : http://img7.doubanio.com/img/celebrity/large/17525.jpg
             * medium : http://img7.doubanio.com/img/celebrity/medium/17525.jpg
             */

            public AvatarsBean avatars;
            public String name;
            public String id;

            public static class AvatarsBean {
                public String small;
                public String large;
                public String medium;

            }
        }

        public static class DirectorsBean {
            public String alt;
            /**
             * small : http://img7.doubanio.com/img/celebrity/small/230.jpg
             * large : http://img7.doubanio.com/img/celebrity/large/230.jpg
             * medium : http://img7.doubanio.com/img/celebrity/medium/230.jpg
             */

            public AvatarsBean avatars;
            public String name;
            public String id;


            public static class AvatarsBean {
                public String small;
                public String large;
                public String medium;

            }
        }
    }

    @Override
    public String toString() {
        return "Movies{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}