# 一、说明
解析 Apple 的证书和描述文件程序，标准输出 JSON 格式的信息。

# 二、用法
```shell
# 证书
java -jar cert_parser-v0.1.0.jar certificate -f <p12> -p <passwd>
# 描述文件
java -jar cert_parser-v0.1.0.jar provision -f <pf>
```
