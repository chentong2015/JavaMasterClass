package JavaLambdaStreams.operations;

// Terminal Operation:
// 终端操作可能会遍历该流以产生结果或副作用，立即开始执行
// 执行终端操作后，流管道被视为已消耗，无法再使用
// 如果需要再次遍历同一数据源，则必须返回到数据源以获取新的流
public class OperationTerminal {

}
