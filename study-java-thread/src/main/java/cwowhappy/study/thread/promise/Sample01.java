package cwowhappy.study.thread.promise;

import java.util.concurrent.CompletableFuture;

public class Sample01 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 1");
            return "Task One";
        }).thenApply((result) -> {
            System.out.println("Task 2");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 2");
            return result + " -> Task Two";
        }).thenAccept(System.out::println).join();
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 1");
            return "Task One";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 2");
            return "Task Two";
        }), (result1, result2) -> result1 + " " + result2).thenAccept(System.out::println).join();
    }
}
