flink:
    spark: 用批模拟流
    flink: 用流模拟批
    
API:
    SQL: 作用于 stream & batch
    
处理流程：
MapReduce：input ==> map(reduce) ==> output
Spark    ：input ==> transformations ==> action ==> output
Storm    ：input ==> spout ==> bolt ==> output
Flink    ：input ==> transformation ==> sink