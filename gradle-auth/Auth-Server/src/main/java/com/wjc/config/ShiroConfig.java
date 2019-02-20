package com.wjc.config;

import com.wjc.shiro.UserRealm;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjc
 * @see ShiroAutoConfiguration
 */
@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
    //Session过期时间,单位是分钟
    private static int expireTime = 30;
    //缓存前缀
    private static String prefix = "cardp-shiro-session:";

    @Bean
    public Realm realm() {
        UserRealm realm = new UserRealm();
        //启用缓存授权信息，默认不缓存
        realm.setAuthorizationCachingEnabled(true);
        return realm;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        //哪些请求可以匿名访问
        chain.addPathDefinition("/auth/login", "anon");
        chain.addPathDefinition("/auth/logout", "anon");
        chain.addPathDefinition("/page/401", "anon");
        chain.addPathDefinition("/page/403", "anon");
        chain.addPathDefinition("/page/index", "anon");

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");
        return chain;
    }

    /**
     * 这里只要返回实现了CacheManagerAware接口的SessionDao，就能注入CacheManager对象
     * 这样Session缓存和权限缓存(AuthorizationCaching)都会统一用CacheManager管理了
     */
    @Bean
    protected SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    /**
     * 用于支持授权信息缓存到Redis，需启用Realm.authenticationCachingEnabled=true
     */
    //@Bean
    protected CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate){
        return new RedisCacheManager(redisTemplate);
    }

    //@Bean
    public SessionManager sessionManager(SessionDAO sessionDAO, CacheManager cacheManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setGlobalSessionTimeout(TimeUnit.MINUTES.toMillis(expireTime));
        sessionManager.setCacheManager(cacheManager);
        return sessionManager;
    }

    /*@Bean
    public DefaultWebSecurityManager securityManager(SessionManager sessionManager, CacheManager cacheManager, Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Deprecated
    private static class RedisSessionDAO extends EnterpriseCacheSessionDAO {
        private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

        private RedisTemplate<String, Object> redisTemplate;
        public RedisSessionDAO(RedisTemplate<String, Object> redisTemplate) {
            this.redisTemplate = redisTemplate;
        }

        // 创建session，保存到数据库
        @Override
        protected Serializable doCreate(Session session) {
            Serializable sessionId = super.doCreate(session);
            logger.debug("创建session:{}", session.getId());
            redisTemplate.opsForValue().set(prefix + sessionId.toString(), session);
            return sessionId;
        }

        // 获取session
        @Override
        protected Session doReadSession(Serializable sessionId) {
            logger.debug("获取session:{}", sessionId);
            // 先从缓存中获取session，如果没有再去数据库中获取
            Session session = super.doReadSession(sessionId);
            if (session == null) {
                session = (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
            }
            return session;
        }

        // 更新session的最后一次访问时间
        @Override
        protected void doUpdate(Session session) {
            super.doUpdate(session);
            logger.debug("获取session:{}", session.getId());
            String key = prefix + session.getId().toString();
            if (!redisTemplate.hasKey(key)) {
                redisTemplate.opsForValue().set(key, session);
            }
            redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
        }

        // 删除session
        @Override
        protected void doDelete(Session session) {
            logger.debug("删除session:{}", session.getId());
            super.doDelete(session);
            redisTemplate.delete(prefix + session.getId().toString());
        }
    }*/

    private static class ShiroCache<K, V> implements Cache<K, V> {
        private String cacheKey;
        private RedisTemplate<K, V> redisTemplate;

        @SuppressWarnings("rawtypes")
        public ShiroCache(String name, RedisTemplate client) {
            this.cacheKey = prefix + name + ":";
            this.redisTemplate = client;
        }

        @Override
        public V get(K key) throws CacheException {
            redisTemplate.boundValueOps(getCacheKey(key)).expire(expireTime, TimeUnit.MINUTES);
            return redisTemplate.boundValueOps(getCacheKey(key)).get();
        }

        @Override
        public V put(K key, V value) throws CacheException {
            V old = get(key);
            redisTemplate.boundValueOps(getCacheKey(key)).set(value);
            return old;
        }

        @Override
        public V remove(K key) throws CacheException {
            V old = get(key);
            redisTemplate.delete(getCacheKey(key));
            return old;
        }

        @Override
        public void clear() throws CacheException {
            redisTemplate.delete(keys());
        }

        @Override
        public int size() {
            return keys().size();
        }

        @Override
        public Set<K> keys() {
            return redisTemplate.keys(getCacheKey("*"));
        }

        @Override
        public Collection<V> values() {
            Set<K> set = keys();
            List<V> list = new ArrayList<>();
            for (K s : set) {
                list.add(get(s));
            }
            return list;
        }

        private K getCacheKey(Object k) {
            return (K) (this.cacheKey + k);
        }
    }

    private static class RedisCacheManager implements CacheManager {
        private RedisTemplate<String, Object> redisTemplate;
        public RedisCacheManager(RedisTemplate<String, Object> redisTemplate) {
            this.redisTemplate = redisTemplate;
        }

        @Override
        public <K, V> Cache<K, V> getCache(String name) throws CacheException {
            return new ShiroCache<K, V>(name, redisTemplate);
        }
    }
}

