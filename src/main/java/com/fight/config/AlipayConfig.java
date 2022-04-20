package com.fight.config;

public class AlipayConfig {



    // 作为身份标识的应用ID
    public static String app_id = "2021000119668094";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCRjfHcSYGBufrLlBbJwFnB0cQz3FvR6sqVd5qJHQK7/jTcMk4w/lvfOOwsv7NzVTFZpryLeuY/zmyc2Xx+9m2410XQQGTThn3qPDCJM/Q4qPa6aZey6QFMEWbVJHEROSf5YN1v/VA3QGEv+BV7I0Yskp+YsdU3zth3ybN4L+nZP0UFmu9o7BUG8dQ33ZRDKuirWLpe7u5kv9vLdIwJnpy/pO3yExaXpyYg4zz+WlFV/iU6CL3cXXwcKW6XepIs618hTfiaob6gweSMzEuo4pUUA82PBqszJualP2uBpHhPNeBSKFvq6NeyBU22OPusYcUJBkojsz0JoNckThgCfwFnAgMBAAECggEAGE04umzTYJNqmh7JhznEQqYBOh9qfSvUUww7EvVzBWw16yXDBE8Ti7t3QylmjrUEnlFGXYtRcgnG/JEbRh9ixl+62kBGFUL5NuiBugagme0r9Ie4ujhI7ShioOeKsTN3xx0zG4WRpQfsshObkombJ8n7N4VJch3o6mcUvdZSFuFGvFMzrtQulxXpWB4FV2eYxp1CAbaj2BjDrs9VN7aOfIBmSNZvIymJpvvclttG1oETMyt6CYsrIrZlLnneUBe4ZVjYPt8EIqxEG6+dtg1Yp7QTG7fVz+FbGZq0rvFbLle6l1v+T1ChDNQ1rsXn5Hg9QUfde35uzfemwewmaPDzEQKBgQDRKv20+262aWmYAMO6/7cbewxfomH5nRl9UGvGfpT+KvZayYhy1EYySnyQV6OHxnwy6Ro6ZDdmcE2LC2Oy1vAm1yOYAsfFlNRyUvnlewsCUtmw6eZio8YfZrupKom25036oVjoo5dAygmn1XDdG5cQTFogLARxbBKOdMBc3JoY3QKBgQCyJMf/ADuv3RxqffWM9XmBhVGgnw5wwwzNY1VqpfLHu6+V+9aVgENQYVaiewNkrohpKZxFfhwmGSEwx918mvPEvB9MDtx532s0jg3i11hsIF6X/noiqkGXfmP7A4hi/H8bkd32r+yOfwujj+842qB1iDE5GKxa7jb/4xjuFES9EwKBgBEsi3pBrrxxeUcOwQNonZs3BjQP/CVnncEpSkAZApi/5ExeAcMT5NOaRBIB8B5k0KvJcerbcaUKkYNaIwx7E2rM/VIs4EQ7zRm58GTELoo5TUB2ZAEzW0AWyk/Ae2A2K7PpbYhkbjXWoOZT4xl0e3nOGxRLKO47Jmbl9Slqv4hNAoGBAJSbiFdaS3BexMi1gPTxBkac/8Y9SZ+ZpwFjXysTep9SG5zDtHOMxxqzyMDMW71Ot676rkludW72wbBe2o6K+NXilT0unI/yaA00f7L/L90rd53WBO8MFqGZJzCL9ueUeTh3i7GxemY4522vscE86GSDk4Rs58MHO8xlFzPsppcPAoGATWN6TJ9wqdPb2W6VmKt67BSbRF6hL26C9+OjBgYZjapRR1ReGagsXC7T13pc4oUvJ1OORUL2kdUyV87xYUOGao2rO37DUmFSrFXTn1nJDChy3RCedBDTJvoROAH+sKNPJGeewKyDOdnUC0NI9kNYAZaSDw5l8BqB0fYpSeQ8EMc=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuriily7pOuCX9fhAoMlkKhzahEUBcvh1NL6P0mHC2YZ/8uvN9HgJK5NMrZ+P4/KICO+a50BUADdvuVQA95461klYpFgngS1rxvYIWX8nP4lMHAVC478zvuVS3yJrjpzAyFh0/velyBFudKrsrylBdipp6dV8ql4ZnFkLk+nxxXnfPo4y4OVD3tzGZhgcB1AEpWbHwNq/Ya2MFUtnKloXQkob9Hbg77mNFqmCpvJZgQspdlFHscTbocQRK2T5ZDd69Nln+Z+TICWC5QhNJaWOswY2iIR1rdL4tKoIrtI8GR0WbnjQYaqk7hZn/LnHnGK8C8LvBHQBJnWeo86+Rr+bmQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.baidu.com";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8082/alipayReturnNotice";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
