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

