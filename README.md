# voteDevicePen
*****

## 0x00 说明
课堂小作业。。。
####默认使用9999和10000两个端口。
####9999端口传输指令，由服务端的ControlSocket类和客户端的ControlSocket类实现
####10000端口传输数据，由服务端的DataSocket类和客户端的DataSocket类实现

***

## 0x01 结构

### candidate包
包含Candidate类，实现了Serializable接口，用来在网络上传输的可序列化对象，配合client包和server包使用。

### client包
客户端包，主类是ClientApp，需要配合candidate包使用。

### server包
服务端包，主类是ServerApp，需要配合candidate包使用。

***

## 0x02 修改的东西

### version 0.1
只有选一个不可变的候选人。。。

### version 0.2
1. 增加了两个候选人，改变了一下代码，有了支持动态可变候选人资料和数量的基础，但功能还未实现。  
2. 服务端处理请求时由单个处理改为并发处理，但没使用线程池，所以不能限制并发线程数量。

### version 0.25
1. 在服务端使用了线程池管理处理请求的线程，使得线程数得以控制。
2. 增加一个(#滑稽)小彩蛋。

***
