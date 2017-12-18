package cwowhappy.study;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestMultiCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final List<CompletableFuture<String>> completableFutures = IntStream.range(0, 16)
                .mapToObj(index -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (0 == index % 4) {
                        throw new RuntimeException("运行时异常[" + index + "]");
                    }
                    return String.format("[%d] -> ", index) + Thread.currentThread().getName();
        })).collect(Collectors.toList());

        CompletableFuture<String> completableFutureResult = CompletableFuture.supplyAsync(() -> {
            List<String> exceptionMessages = completableFutures.stream().map(completableFuture -> {
                try {
                    completableFuture.get();
                    return null;
                } catch (InterruptedException e) {
                    return "中断";
                } catch (ExecutionException e) {
                    return e.getCause().getMessage();
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            if (0 == exceptionMessages.size()) {
                return "成功";
            } else {
                throw new RuntimeException(String.join(",", exceptionMessages));
            }
        });
        completableFutureResult.whenCompleteAsync((result, throwable) -> {
            if (null != throwable) {
                System.out.println(throwable.getCause().getMessage());
            }
            if (null != result) {
                System.out.println(result);
            }
        });
        System.out.println("等待结果:");
        Thread.sleep(10000L);
    }
}
