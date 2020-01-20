# EventBusDemo

## EventBus概述：
EventBus是一款针对Android优化的发布/订阅事件总线。
### 主要功能：
- 1.替换Intent，Handler,BroadCast在Fragment，Activity,Service，线程之间传递消息
- 2.优点是开销小，代码优雅
- 3.将发送者和接收者解耦 

## EventBus基本用法：
### 注册：
- 1.EventBus.getDefault().register(this);
- 2.EventBus.getDefault().register(this,methodName,EventBus.class);

## 订阅处理数据

通过注解@Subscribe可设置回调函数的多个属性，如线程模式，执行优先级等


### 取消注册：
EventBus.getDefault.unregister(this)

### 发布：
EventBus.getDefault.post(new Eveent(msg));