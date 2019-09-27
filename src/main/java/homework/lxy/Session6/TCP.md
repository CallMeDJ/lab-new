### TCP 握手挥手图

tcp.jpg

常见问题：
1.tcp连接握手为什么不是两次？
答：三次握手有两个重要的作用，一是确认双方都已经准备好，二是双方对初始序列号进行协商。
如果改成两次握手，服务端收到了客户端的连接请求，返回响应报文，并认为连接已建立。
此时响应报文传输过程中丢失，客户端没有收到响应报文，也无法确认序列号，将拒绝接收服务端发送过来的任何报文
而服务端发送的数据传输报文一致没有收到回复。将会继续重复发送，造成死锁。

2.三次握手的最后一次握手报文丢失了怎么办？
答：服务端会超时，返回RST报文并关闭，这么做是为了防止泛洪攻击（客户端多次请求而无响应，占用服务器资源）。

3.挥手时客户端发送最后一个报文后为什么要等待2MSL（最大报文段生存时间）?
答：按照常规操作，四个报文发送完就都进入closed状态，但是我们假设网络不稳定，最后一个ACK可能会丢失，
如果不等待直接关闭，服务端没有收到的话会重复发送FIN报文，占用服务器资源，而等待2MSL可保证丢失后重新发送，如果时间内没有再收到FIN报文，则认为服务端接收成功，就可以关闭了。


###  Nagel 算法伪代码
input : msgPackage
    if msgPackage.size >= MSS 
        then send
    else if msgPackage contains FIN
        then send
    else if TCP_NODELAY = true
        then send
    else if TCP_CORK=false and not exist littleSizePackage whitout ACK 
        then   send
    else if ACK timeout
        then send 
    else wait


### 描述清楚 TCP 与 UDP 的区别与联系，设计一个 TCP 与 UDP 混合使用的技术方案。