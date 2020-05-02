# [提问箱](http://172.81.235.232:8080/mesBoard_Web_exploded/login.html)

## 一、项目概述：
    提问者匿名，回答者公开。使用技术与框架，ssm,Bootstap,前后端分离开发。

## 二、项目地址：  
    提问箱: :http://172.81.235.232:8080/mesBoard_Web_exploded/login.html
   
## 三、登录注册、忘记密码![登录页面](https://img-blog.csdnimg.cn/20200502100751866.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502101136601.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70#pic_center)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502101258115.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70#pic_center)

  ###   1.注册。（需要输入，用户名、密码、确认密码、邮箱、验证码。）
        1.1 用户名：目前未对用户名有限制（如中英文，特殊符号）。
        1.2 密码：密码必须为大于8位的数字和字母和其他字符组合。（不符合要求页面会提示，密码hash后传送给后端）
        1.3 确认密码：与密码一致，（页面会给出提示）。
        1.4 邮箱：前端和后端都有对邮箱格式正确性进行校验。（一般格式：..XXX@XX.com）
        1.5 验证码：字母与数字混合。
  ###     2. 账号激活。（注册成功向邮箱发送激活邮件）
      	2.1  激活邮件中的激活链接含有唯一标识码，标识该用户的激活。
 ###   3. 登录  
      	3.1.可使用用户名或邮箱登录。
      	3.2. 验证码：字母与数字混合。
 ###   4.忘记密码（重置密码）
      	4.1 输入邮箱，若邮箱有效将发送重置密码链接，重置密码链接含有唯一标识码，标识该用户的本次密码重置。
      	4.2 重置密码：为提升用户体验，需输入密码和确认密码，其满足条件同注册。
      	4.3 每次重置密码将唯一标识符存入数据库，![用作](https://img-blog.csdnimg.cn/20200502101058342.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70#pic_center)
检验token的密钥（修正，其实是将token的部分字段存入数据库，然后修改密码则改变此字段，使得原来token失效。），从将非法用户踢下线。
      	
## 四、用户资料管理：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502101731819.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70#pic_center)

		1. 用户名：用户名唯一，故更改用户名同名，不能成功更改。并给出提示。
		2. 邮箱：更改邮箱需要输入密码验证并重新激活。
			2.1. 若注册时输错邮箱可凭密码更改邮箱重新激活账户。
			2.2. 邮箱唯一，故更改邮箱已存在，不能更改，并给出提示。
		3.密码：
		 	3.1.通过提供原密码来修改密码。
		 	3.2.为提升用户体验，需输入密码和确认密码，其满足条件同注册。
		 	3.3.更改密码同忘记密码（4.3），可将用户踢下线
		 4.头像（可更换头像）
		   4.1.超大文件，前端后端都有判断，不允许超过1M
		   4.2.前端后端都有判断只允许图片的后缀名，(jpg,png等）
		   4.3.文件名有+UUID，故实际存储不会相同。
		  5.提问箱（可以在个人资料中设置开启关闭）
		  
## 五、提问箱管理：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502162302207.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70#pic_center)

	5.1. 提问箱激活成功后，默认提问箱打开，接受其他用户的提问，也可以发起提问。
	5.2. 提问箱管理页面可以查看已回复的提问、未回复的提问、已删除的提问。提问者匿名。
	5.3. 提问：（点击提问按钮。不能提问情况：封禁、未激活、账户被拉黑、提问箱未开放）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502162844735.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
 5.4. 详情页面（点击view details）
		![在这里插入图片描述](https://img-blog.csdnimg.cn/2020050216584433.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
	5.4.1.可以对提问进行回复，删除。举报提问者、拉黑提问者。举报/拉黑操作时顺便将提问标记为删除状态。且不能重复拉黑提问。
	5.4.2.

## 六、个人资料页面：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502212943952.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)


	6.1 提问：（点击提问按钮。不能提问情况：封禁、未激活、账户被拉黑、提问箱未开放）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502162844735.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
  6.2.用户列表（无论是否登录，都可以进入用户的个人资料页面）

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502213238185.png)
## 七、网站管理：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502222551383.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
7.1.用户管理（默认页面）：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020050222275959.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
	7.1.1 点击id
		查看查看这个用户的数据（所有收到的提问、回复的提问等）
		![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502230010895.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
#### 7.1.2 点击封禁状态，可以改变其封禁状态	

### 7.2.举报管理：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502230447280.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
#### 7.2.1点击ID进行处理
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502230527853.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)

# 安全
## 一、xss（限于能力与时间，以下并未全部应用，只应用1.2）
### 1.通过过滤器过滤关键词。
### 2.使用cookie时设置Http Only cookie。
### 3.对不可信数据进行 HTML Entity 编码。
### 4.对不可信数据进行JavaScript编码。
 
## 二、 CSRF（实际采用将token放入请求头中，使用拦截器进行验证。）
### 1.验证 HTTP Referer 字段（记录本网站的url）。
### 2.在请求地址中添加 token 并验证。

## 三、接口与URL的安全
### 重置密码将产生该用户，本次重置密码唯一标识码，重置密码时验证通过才能进行操作。

## 四、密码安全
### 1.前后端传输首先已经md5加密。
### 2.密码加密前进行md5加盐
		生成16位随机数字，按照特定规则加入密码后，求md5摘要。往摘要中再次按照特定规则加入这16位随机数字。验证时通过该规则取出16位随机数字，进行验证。
		具体算法：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200502235647955.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODA1MDUz,size_16,color_FFFFFF,t_70)
