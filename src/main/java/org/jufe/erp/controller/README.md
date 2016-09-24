### 接口文档的规范

#### 请求
* GET方法请求均以url绑定参数的形式传参
* POST/PUT/DELETE 以json格式向服务器传递参数（上载文件除外）


#### 返回
* GET请求的返回：若成功，则为json对象或者json列表；若失败则需关注状态码
* PUT/POST/DELETE: 返回值类型为{result:true/false, msg:"错误信息,当result为null时才有"}
> 目前的策略：
* 对于返回一个List的接口，若返回的值为null，而不是一个空列表[],则说明请求错误了，调用请求的地方需要引起注意

#### 统一鉴权的返回：
> * 401： token无效，未登陆或token过期
* 403： 无权限