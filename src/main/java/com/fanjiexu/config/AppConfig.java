package com.fanjiexu.config;

import com.fanjiexu.interceptor.InterfaceTimingInterceptor;
import com.fanjiexu.model.base.UILoginUser;
import com.fanjiexu.util.TimeUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Create By Hong Cui
 * 2018-4-24
 */
@Configuration
@RefreshScope
@Component
public class AppConfig extends WebMvcConfigurationSupport {
    public static UILoginUser SystemAdmin;

    {
        SystemAdmin = new UILoginUser();
        SystemAdmin.setUserId(1);
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }




    public long serverId = 1;
    public static Date DATE_MIN;

    public static long TID_INIT = 10000L;
    public static long TID_MAX = 99999L;
    public static long EIGHT_HOUR_TOTAL_MILLISECONDS = 8L * 60L * 60L * 1000L * 10000L;

    public static Date TID_MIN_DATE = TimeUtil.string2Date("2010-01-01");

    public static long TID_SUB_VALUE = TID_MIN_DATE.getTime() - TimeUtil.string2Date("1970-01-01").getTime();

    private final static String privatekeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANLT7VgcWlE30rI2tJdbw2ds5Zx+QWTiE8Vnnt1IOb/qYBSqolL93zzz/OGrC2YJm0LYs4sR+qKmWC/OxNB1CF3DHg6x5QUHuel5M8Wi45WORDbtz8ikiJb2Qli4uNlPsBCBFlOdkxjRlAqZiFJ50hb2DfyA7xaIh6/JliP27kynAgMBAAECgYAAwy1vjSKyen4zkMkUZwuM2zL1XuuDnuGtbBOB/1neHM3eJUPTAwIEqWk3s7Ol2dEK69eyYA1E+08EVT4W9uVh172kEwNP3qPEyPaUys1QKqdYQMw6uomP1A3LSEaEHUTWveprQnhxRQlz2qnWD/wIfRbeMp/bYSHAXcBq742igQJBAOhR4ZG9UJyM+ezWx1ErihY1VuCCawnMIfUX6xZLesg+z65F0sLwQ0+/HlRnx6q5SNcoT/qHMMHP15kpKWHSeMECQQDoUTyfMqIbojchB+yfYJXEMp8WgKUlw5+L1c/a0FLhJaE5QbtNdKrc3a6TO9ex1D5TcLSibl/Y8I4xvT++nndnAkAYEOtiEo94OC5nt3QcV3tNg6cFSN1XvXrDDgA/ZgBJICCZeAg2Q3dlmnHE4p9PczQ1faqTVE/bs/Esn5P22zvBAkAhd3p1MJUbx/2LG5ohx4crsWMrmbKwwwKb4VxFGFeoKYSVO/vgh5sJoEHC4g8LhhjIamcLMz1JgjXpwmqJakinAkEArm81u21/J66IAW4bgqIeL+Q4fVVsPE9rZMOp6s30GUTK1LOxjfzf9tf6Iv1BIVG9RJOn2/jdG79a7tDhsNyX7w==";
    private final static String rsapublickeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBsRhV1791PFCk1m/STvayZ8gxjYedbzNfjIblF1P/YUW7Tkn/RzuiefYPTDpdu4UdqR9td+iDT6mijhcBX06mMQeidb80zBEeGQvoX4ZoBU12GZty2c/f0iwUqX5JpTAqAobGRnWE2fg59qAW4e+1G6so3X25F/9lN5pLpBLFIwIDAQAB";
    public static RSAPrivateKey rsaPrivateKey;
    public static RSAPublicKey rsaPublicKey;

    public final static String AES_KEY = "@2#456789012!@#$";

    static {
        try {
            DATE_MIN = new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterfaceTimingInterceptor()).addPathPatterns("/**/**/**");
        super.addInterceptors(registry);
    }

    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    @Bean
    public Encoder feignEncoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new SpringEncoder(objectFactory);
    }

    public ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return objectMapper;
    }


//
//    /**
//     * 设置 redisTemplate 的序列化设置
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        // 1.创建 redisTemplate 模版
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        // 2.关联 redisConnectionFactory
//        template.setConnectionFactory(redisConnectionFactory);
//        // 3.创建 序列化类
//        GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
//        // 6.序列化类，对象映射设置
//        // 7.设置 value 的转化格式和 key 的转化格式
//        template.setValueSerializer(genericToStringSerializer);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }

}
