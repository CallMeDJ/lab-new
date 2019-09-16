### TCP 握手挥手图
握手原理图：
        tcp-establish.png
    
    TIME_WAIT
        挥手时，当Client接收到Server发送最后一次FIN ACK并做出应答，且开始计时，
        如果该应答丢失，则Service在超时后会重发，CLient接收到后会做出应答并重新计时，
        在Client等待2MSL（MSL 报文在网络中最大存活时长）后，未有新的FIN ACK，说明
        Client的应答Service已经收到，则断开连接。


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

###   描述滑动窗口、拥塞控制 的基础机制，以及这样设计能带来什么好处。
    a.滑动窗口
        基础机制：
            发送端和接收端均维护一个滑动窗口，接收端告知发送端当前可以处理的数据包数量K，允许发
            送端先后发送K个数据包，接收端接收到包后给出应答。当接收端接收到窗口左边连续M个数据包后，
            则向右滑动M个窗口位置。发送端接收到滑动窗口左边连续N个数据包后，则向右滑动N个位置。
            丢包时，会有缓存，超时重传，累计ACK来确保数据的完整，顺序性。
        
        好处：
            TCP通过确认的方式来确保数据传输的可靠性，但是如果每次只发送一个包，效率较低。
            采用滑动窗口的机制来控制端到端的流量，配合超时重传，累计ACK，即保证了可靠性，
            顺序性，又增强了处理效率。
        
    b.拥塞控制
        基础机制：
            根据当前的网络资源情况，依次通过慢开始、拥塞避免来控制短时间内注入网络的数据量,
            并配合快重传、快恢复来提高过程中的效率。
            计算慢开始门限ssthresh，初始时，cwnd=1MSS，门限为ssthresh，在cwnd<ssthresh时，
            next cwnd= cwnd<<2；当cwnd>=ssthresh时，next cwnd=cwnd+1；如果网络出现阻塞，则
            ssthresh = cwnd>>2 ,cwnd=1;继续上述操作。当出现多次ACK同一包时，说明数据有丢失，
            不必等待ACK超时即重发，且 ssthresh=cwnd>>2,next cwnd=cwnd>>2;
        好处：
            避免网络阻塞且高效利用网络资源。


### 描述清楚 TCP 与 UDP 的区别与联系，设计一个 TCP 与 UDP 混合使用的技术方案。