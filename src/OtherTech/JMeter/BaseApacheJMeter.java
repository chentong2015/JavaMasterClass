package OtherTech.JMeter;

public class BaseApacheJMeter {

    // Apache JMeter 测试压测工具，对java的一些接口进行压力测试
    // - Test Plan
    //   - Thread Group
    //       Number of Threads: 200       要压测的(并发)线程数
    //       Ramp-Up Period in seconds: 0 所有的线程数在多久的时间段内发送完成
    //       Loop Count: 4                压测循环的次数
    //     - HTTP Request
    //         Method: GET  Path: http://192.168.0.60/testStock
    //         Aggregate Report
    //         保存Report.jmx
}
