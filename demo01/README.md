## Springboot Security 最简单的项目

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "HELLO WORLD!";
    }

}
```

### 访问方式

```sh
# 不返回任何数据
curl http://localhost:8080/hello

# 使用程序生成的用户名密码访问，可以拿到数据
curl -u user:c830e163-60f7-49f9-bafb-c9fa4fe0480f localhost:8080/hello
```

用到的站点：https://www.base64encode.org/

### 手动创建 `Authorization` 头信息

```shell
# linux 命令生成 base64 编码的字符串
echo -n user:c830e163-60f7-49f9-bafb-c9fa4fe0480f | base64

# 重新使用
curl -H "Authorization: Basic dXNlcjpjODMwZTE2My02MGY3LTQ5ZjktYmFmYi1jOWZhNGZlMDQ4MGY=" localhost:8080/hello
```

- https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication

## 配置HTTPS
### 生成证书
```shell script
# 生成私钥和公共证书
openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365

# 执行上面命令错误信息
# Can't load /home/shenjy/.rnd into RNG
# 解决方案
openssl rand -writerand .rnd

# 输入自签名证书
openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"

# 查看别名
keytool -list -keystore certificate.p12
```

### 免费生成证书
- https://letsencrypt.org/zh-cn/
- [Different types of keystore in Java](https://www.pixelstech.net/article/1408345768-Different-types-of-keystore-in-Java----Overview#:~:text=Depending%20on%20what%20entries%20the,Oracle's%20Java%20Cryptography%20Architecture%20description.)
- [Keystore type: which one to use?](https://stackoverflow.com/questions/11536848/keystore-type-which-one-to-use)
- [Java Security Standard Algorithm Names](https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keystore-types)

### 相关资料
- [网络数字身份认证术](https://coolshell.cn/articles/21708.html)
- https://livebook.manning.com/book/real-world-cryptography/real-world-cryptography/
