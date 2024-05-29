# burp-modifer-header-branch

实现请求头的动态修改burp插件 

目前已停止更新,后续有功能需求会合并到 Knife分支中

基本使用请参考: https://github.com/PortSwigger/random-ip-address-header

### 基本功能

    1、支持 动态IPv4 IPv6地址生成 并用于替换请求头
    2、支持 动态请求体内容获取 用于动态添加refer等功能

###v1.2 更新记录

    1、添加%RealHost%变量,当端口为80或443时，RealHost==RHost,否则RealHost = RHOST+:+RPORT
    2、对于请求头为--开头的项目，将不会被替换，间接实现请求头替换开关。
    3、关闭默认开启的Apply to in-scope requests only
    4、修改LOGO并取消点击LOGO图标跳转到网页
    5、添加一些--开头的常用示例，--开头不会被启用

### v1.1 更新记录

    1、修复UI显示重合问题
    2、通过字符串替换实现动态变量,
        %RURL%      请求URL
        %RHOST%     请求HOST 
        %RPORT%     请求PORT
        %RPROTOCOL% 请求协议
        %RURLPATH% 请求URL的路径
        %RURLQUERY% 请求URL的参数
        %RMETHOD%   请求方法

    v1.1版本具体介绍与使用方法：
    RandomHeadrPlus-实现请求头动态替换
    https://mp.weixin.qq.com/s/51MbozMPZAZTc2mrcRNjGA


