package JavaNetworking;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

/**
 * HTTP State Management With Cookies: 创建有状态的http请求和回复的session
 * 1. HTTP is not Stateless protocol 无状态的传输协议
 * 2. HTTP request/response pairs are independent of each other 请求/响应对彼此独立
 */

/**
 * Session: context 用于在clients和servers直接交换上下文信息
 * Cookie : The state information 用于创建和维护会话的状态信息称为cookie
 * 1. A cookie is a piece of data that can be stored in a browser's cache
 * 2. If you visit a web site and then revisit it, the cookie data can be used to identify you
 * 3. 可以设置Cookie的存在时间长短(to be remembered)
 */
public class BaseCookieStore implements CookieStore, Runnable {

    /**
     * CookiePolicy: 预定义是否接受cookie的policy
     * 1. CookiePolicy.ACCEPT_ORIGINAL_SERVER only accepts cookies from the original server.
     * 2. CookiePolicy.ACCEPT_ALL accepts all cookies.
     * 3. CookiePolicy.ACCEPT_NONE accepts no cookies
     */

    /**
     * CookieStore: an interface that represents a storage area for cookies
     * 1. CookieManager adds the cookies to the CookieStore for every HTTP response
     * 2. CookieManager retrieves cookies from the CookieStore for every HTTP request
     */
    CookieStore store;

    // 创建时，先读取之前存储的cookies
    // During runtime, cookies are stored and retrieved from memory.
    // 结束时，将cookies写入到持久层的存储中
    public BaseCookieStore() {
        store = new CookieManager().getCookieStore();
        // read in cookies from persistent storage, and add them store
        // add a shutdown hook to write out the in memory cookies
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }

    @Override
    public void run() {
        // write cookies in store to persistent storage
    }

    @Override
    public void add(URI uri, HttpCookie cookie) {
        store.add(uri, cookie);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return store.get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return store.getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return store.getURIs();
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return store.remove(uri, cookie);
    }

    @Override
    public boolean removeAll() {
        return store.removeAll();
    }
}
