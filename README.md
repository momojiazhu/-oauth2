# -oauth2
这是我搭建起来，为了今后在小公司干活用的，是一个初级框架，希望自学的伙伴互相交流。多做试验。


我是自学编程的，沈阳的，30多岁，做了两个作品找到工作，目前工作1年，现在正在找工作。我是全栈开发，前端可能会的多一些，觉得前端很容易，所以想再强化下后端，所以这段时间搭了个框架，一是供和我一样的自学青年参考，二是估计得去一个小公司，作为我工作使用的框架。得用idea安装一下lombok插件，简化实体类操作的东西，否则实体类报错。该项目主要分两个微服务，一个是正常系统的，一个是文件上传、浏览、删除的系统。

我先发布第一阶段的作品，包括：
1、	多个微服务的建立，可以把依赖的包融合进来，独立部署。
2、	日志输出文件，日志格式。
3、	mybatis的tkmapper、pagehelper。
4、	带oauth2的swagger。
5、	springboot全局异常，前端传递后端参数@Validated校验，也统一全局异常处理。
6、	操作日志的编写，统一格式，存到数据库中。
7、	查询日志是通过切面写的，能统计出查询参数和返回结果的数量。
8、	重头戏是oauth2搭配spring security，这个挺好用，oauth2在其中一个微服务认证以后，在所有的微服务以验证token形式的访问，证明已登录。所有的url可以验证登录和角色。我用的是oauth2的密码模式，认证的时候传登录用户名，登录密码，客户端id，客户端密码。以及它的过滤器和跨域的配置。
9、	文件的上传，可以是图片和文档，什么都行，上传的url可以带路径，然后就放到该路径的文件夹里，以post或put流的方式，可以上传多文件。文件的浏览，前端可以直接访问地址，也可以以流的形式进行访问，这个地址在oauth2里已经放开了，不需要验证了。文件的浏览和删除支持中文文件名的方式。
10、	搭了一个前端框架vue+element，vue-cli3，主要是为了搭配oauth2，可以进行测试效果，可以工作用，前端代码可以测试url访问权限，可以图片的上传、展示、删除，word的上传、预览、删除，有的为了方便，简略下了，但是框架没问题。
11、	练习了一下部署前后端项目，后端打成jar包，linux部署，同时部署两个微服务，内存每个给了512，超了，服务器总共才1个G，后来改成128。前端可以用nginx部署和tomcat部署，分别解决刷新404的问题，开启nginx的gzip压缩，我有一个8M的js文件，如果不用gzip压缩，首次访问网页的效果，需要等待30秒，如果用gzip，只需5秒。前端vue用hash模式和history模式分别部署。他们的区别是在vue.config.js中publicPath的属性设置为hash模式是./,history模式是/或/web_test/，其中web_test是文件夹的名字。

postman:

![图片1](https://github.com/momojiazhu/-oauth2/blob/master/data/1.png)

![图片2](https://github.com/momojiazhu/-oauth2/blob/master/data/2.png)

![图片3](https://github.com/momojiazhu/-oauth2/blob/master/data/3.png)

