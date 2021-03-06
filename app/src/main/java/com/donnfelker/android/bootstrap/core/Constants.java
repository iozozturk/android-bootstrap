

package com.donnfelker.android.bootstrap.core;

/**
 * Bootstrap constants
 */
public final class Constants {
    private Constants() {}

    public static final class Auth {
        private Auth() {}

        /**
         * Account type id
         */
        public static final String BOOTSTRAP_ACCOUNT_TYPE = "com.androidbootstrap";

        /**
         * Account name
         */
        public static final String BOOTSTRAP_ACCOUNT_NAME = "Android Bootstrap";

        /**
         * Provider id
         */
        public static final String BOOTSTRAP_PROVIDER_AUTHORITY = "com.androidbootstrap.sync";

        /**
         * Auth token type
         */
        public static final String AUTHTOKEN_TYPE = BOOTSTRAP_ACCOUNT_TYPE;
    }

    /**
     * All HTTP is done through a REST style API built for demonstration purposes on Parse.com
     * Thanks to the nice people at Parse for creating such a nice system for us to use for bootstrap!
     */
    public static final class Http {
        private Http() {}


        /**
         * Base URL for all requests
         */
        public static final String URL_BASE = "https://api.parse.com";

        /**
         * Authentication URL
         */
        public static final String URL_AUTH = URL_BASE + "/1/login";

        /**
         * List Users URL
         */
        public static final String URL_USERS = URL_BASE + "/1/classes/users";//TODO Change

        /**
         * List News URL
         */
        public static final String URL_NEWS = URL_BASE + "/1/classes/news";

        /**
         * List Checkin's URL
         */
        public static final String URL_CHECKINS = URL_BASE + "/1/classes/checkins";

        public static final String PARSE_APP_ID = "r3M4SM1kw5Hm0QK5BQzoa11oXnT1EAxFW6XQojhh";
        public static final String PARSE_REST_API_KEY = "daNcA5UsgMFV2n37swJ5gbwZjkAQxAvDj9LcyGbK";
        public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
        public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
        public static final String CONTENT_TYPE_JSON = "application/json";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String SESSION_TOKEN = "sessionToken";


    }

    public interface IMeetapp{
        public static final String Log = "com.ismet.app.meetapp";

        public static final String PREFERENCES = "preferences";
        public static final int PREFERENCE_COUNT = 14;

        public static final String USERS = "users";


        public static final String JAVA = "java";
        public static final String CPLUSPLUS = "cpp";
        public static final String C = "c";
        public static final String EXPERIENCE = "xp";
        public static final String SCHOOL = "schoolName";
        public static final String SCHOOL_GRADE = "schoolGrade";
        public static final String MATLAB = "matlab";
        public static final String CUDA = "cuda";
        public static final String HTML = "html";
        public static final String JS = "js";
        public static final String XML = "xml";
        public static final String PHP = "php";
        public static final String PYTHON = "python";
        public static final String CSS = "css";
        public static final String ENGLISH = "english";
    }


    public static final class Extra {
        private Extra() {}

        public static final String NEWS_ITEM = "news_item";

        public static final String USER = "user";

    }

    public static final class Intent {
        private Intent() {}

        /**
         * Action prefix for all intents created
         */
        public static final String INTENT_PREFIX = "com.donnfelker.android.bootstrap.";

    }

    public static class Notification {
        private Notification() {
        }

        public static final int TIMER_NOTIFICATION_ID = 1000; // Why 1000? Why not? :)
    }

}


