# iptr
==========
1、我能做什么
-----------------------------------  
每小时扫描一次tomcat或apache服务器，若请求没有响应，则重启服务。支持监控多个tomcat和apache，支持发送邮件
2、我要依赖的jar文件
-----------------------------------  
本程序为java编写的web工程，依赖commons-email-1.4.jar， 	commons-lang3-3.1.jar   ，javax.mail-1.4.4.jar

3、如何搭建环境
----------------------------------- 
>1>请使用myeclipse或eclipse创建web工程，并将工程命名为iptr
> 
> 2>然后将src下的文件复制到src目录下即可
> 
> 3>注意，上面操作完成后，将web.xml放到WEB-INF目录下，这里面是配置监听器的class
> 
> 总统5个类文件，比较简单

4、如何配置和运行
----------------------------------- 
这个就比较简单了，配置文件就两个，都在resources目录下
>1>application.properties这个里面的注释很详细了，我就不写了
>
>2>restart_8080.sh,这个文件名称为restart_端口.sh，请将端口替换为application.properties中的端口号，如果有多个端口的话，需要添加多个sh文件，sh文件里是linux下的命令，修改就可以了


###最后，有问题请联系tianshiyeben@qq.com，还有别忘了点star哦
