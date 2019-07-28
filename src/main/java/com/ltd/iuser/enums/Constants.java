package com.ltd.iuser.enums;

public interface Constants {

    interface GeneratorStrategy{
        String SNOWFLAKE_NAME = "snowflake";
        String SNOWFLAKE_REFERENCE = "com.itian.busker.common.pojo.generator.SnowflakeIdGenerator";
    }

    interface Redis {
        String LOGIN_RETRY_TEMPLATE = "login_retry_%s";
    }

    interface Limit {
        int LOGIN_RETRY_MAX = 5;
        int HASH_ITERATIONS = 3;
    }

    interface Http {
        String AUTH_HEADER = "Authorization";
        String TOKEN_KEY = "zJEpbL6nebCpsghq";
        String EMAIL_CLAIM_KEY = "email";
        String ID_CLAIM_KEY = "id";
        String TOKEN_PART = "part";
    }

    interface Init {
        String ADMINISTRATOR_PASSWORD = "88888888";
    }

    interface Algorithm {
        String SHA_512 = "SHA-512";
        String HS256 = "HS256";
    }

    interface Security {
        String PERMISSION_TMPLATE = "%s:%s:%s";
        String SEARCH_PATTERN = "/**/search/**";
        String AUTHENTICATED_ID = "X-AUTH-ID";
    }

    interface FeignClient {
        interface AUTHORIZATION {
            String SERVICE = "AUTHORIZATION-SERVICE";
        }
        interface TRIPARTITE {
            String SERVICE = "TRIPARTITE-SERVICE";
        }
    }

    interface Qiniu {
        interface BUCKET {
            String BUSKER = "busker";
        }
    }

}
