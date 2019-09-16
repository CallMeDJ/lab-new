### 第七课 总纲

熟练掌握 SpringBoot 使用，以及实战中的逻辑分层，以及多环境配置项的使用

额外掌握一个，在复杂业务需求中，多业务扩展性问题。
### 学习资料


https://www.jianshu.com/p/e03b474c855a
https://blog.csdn.net/uniquewonderq/article/details/79963719

#研习室讨论 No.2
群里聊到程序的分层设计，大蕉分享了一些理解。
业务简单时，分controller，service，dao 三层就能满足需求，但是如果业务复杂，就需要分controller，service，manager，repository，dao 五层。
controller层管登陆态，和页面对象的转换；
service层管 rpc 层的事情，就是服务；
manager层管业务流程；
repository层管数据存储，包括tair和db或者其他，以及数据存储的策略；
dao层就是sql，增删改查。

举个例子：根据当前登陆用户获取订单列表，
controller层要做的是拿到userId，以及校验当前用户是否有获取订单的权限；
service层要做的是出入参，异常处理，以及接口暴露逻辑（rpc、http等）,本例中，传入的参数就是userId，service开放一个根据userId获取订单列表的接口，该接口除了controller层使用，也可能作为rpc接口提供给二方合作者使用；
manager层拿到userId，直接返回列表，有异常就抛出。这里是整个逻辑的编排聚集点。可能会调用外部服务，以及repository层；
repository层的逻辑可能是根据userId，先在redis上查询缓存，如果查到了就返回。查询不到就查db，然后回写到redis上。这一层的核心目标就是拿数据，任何数据的操作都在这里，
dao层很简单，就是根据目标数据库的调用方式，抽象出某个表或者域所对应的方法。比如增删改查，比如redis就是一堆 get remove，数据库就是一堆sql，但对外暴露都是增删改查方法。

大家比较难理解五层中的service，我们要清除定义定位service。service有很多种，从注解的角度来说，类加@Service注解，就变成了内部服务，加@RestController注解，就成了一个http服务，加dubbo的@Service注解，就变成了一个dubbo服务，以此类推。

### 主要关注内容

SpringBoot 项目的创建
多环境配置项的使用
逻辑分层的目的
业务线多的情况下的扩展性设计


### 作业:

完成一个标准的电商流程。商品详情、加购、下单、支付、发货、收货确认完成。

核心模型：
商品

class Item{
    private Long id;
    private String name;
    private Map<String,String> feature;
}

购物车

class Cart{
    private Long id;
    private List<Pair<Long,Integer>> items;
     private Map<String,String> feature;
}


交易单


class Trade{
    private Long id;
    private List<Pair<Long,Integer>> items;
    private PayStatus payStatus;
    private TradeStatus tradeStatus;
    private DeliverStatus deliverStatus;
    private Map<String,String> feature;
}


枚举类
public enum PayStatus{
    CREATED,PAY_SUCCESS,PAY_FAIL
}


public enum TradeStatus{
    CREATED,PAYING,PAY_SUCCESS,PAY_FAIL,DELIVERING,DONE
}


public enum DeliverStatus{
    DELIVERING,DELIVER_SUCCESS
}



PS:数据库层直接忽略掉。


需求：

默认下单流程：
1、看商品，有缓存就看缓存的，没缓存就从数据库捞。
2、加购，需要添加到数据库中。
3、创建交易单，需要创建交易单，交易单状态为CREATED。
4、创建支付单，创建支付单。交易单状态变为支付中。
5、支付，交易单的支付状态变为支付成功/失败。交易单状态变为支付成功/失败。
6、发货，交易单的发货状态变为快递中，交易单的状态变为快递中。
7、收货，交易单的发货状态变为快递成功，交易单的状态变为完结。


钢铁侠流程：
1、看商品，有缓存就看缓存的，没缓存就从数据库捞、如果商品中不包含 ENABLE=true 的feature，说明为内部商品，禁止查看。。
2、加购，需要添加到数据库中。
3、创建交易单，需要创建交易单，交易单状态为CREATED。如果当前时间的秒数不为5，则禁止创建交易单。
4、创建支付单，创建支付单。交易单状态变为支付中。如果单笔支付单超过50000万元，则不允许创建支付单。
5、支付，交易单的支付状态变为支付成功/失败。交易单状态变为支付成功/失败，需要调用 iromMan 公司的支付服务。
6、发货，交易单的发货状态变为快递中，交易单的状态变为快递中。
7、完结，交易单的发货状态变为快递成功，交易单的状态变为完结。



SPACEX流程
1、看商品，有缓存就看缓存的，没缓存就从数据库捞、如果商品中不包含 DISABLE=true 的feature，说明为外部商品，都允许查看。。
2、加购，需要添加到数据库中。
3、创建交易单，需要创建交易单，交易单状态为CREATED。如果当前时间的秒数的平方个位数不为5，则禁止创建交易单。
4、创建支付单，创建支付单。交易单状态变为支付中。如果单笔支付单小于0.5万元，则不允许创建支付单。
5、支付，交易单的支付状态变为支付成功/失败。交易单状态变为支付成功/失败，需要调用 SpaceX 公司的支付服务。如果调用 SpaceX 公司支付服务失败，则顺延调用 WePay 公司的支付服务。
6、发货，交易单的发货状态变为快递中，交易单的状态变为快递中，每个快递单最大允许5件商品，如果超过则需要拆单。
7、完结，交易单的发货状态变为快递成功，交易单的状态变为完结。

以上所有的更新操作都需要更新缓存，暂时忽略并发的场景。



#### 目标：

会用 SpringBoot。
能设计多业务扩展性。
能懂分层的意义。