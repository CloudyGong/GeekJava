GC summary:
*  Parallel/CMS/G1对比的时候，需要搭配具体的JVM参数来比较
*  Serial GC通常效率不高，作为server模式运行的时候，通常不用考虑
*  JDK 8默认GC为Parallel GC，建议配置的时候，显示指定GC算法
*  server模式的时候，建议Xms和Xmx配置相同
*  注意不能将所有虚拟机内存都用在Xmx上，因为Xmx只是指定了堆内存的大小
*  堆内存中每个空间的大小，在具体场景下，会影响GC的频率和耗时，调优时要关注
*  相同Xms/Xmx配置下，每种GC具体堆内存中Young（Eden/from/to）/Old的大小，最好通过下面的方式打印到文件来确认

    `-XX:+PrintGCDetails -Xloggc:gc.demo.log`
*  每种GC有通常的使用场景，选择的时候还要结合case做些尝试
*  Parallel GC默认支持自适应大小策略，会使得GC行为变得难以预测，可以考虑通过下面的方式关闭

    `-XX:-UseAdaptiveSizePolicy`
*  GC log除了肉眼分析，还可以使用GCEasy（在线）和GCViewer（离线）工具
