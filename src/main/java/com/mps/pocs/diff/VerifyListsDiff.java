package com.mps.pocs.diff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VerifyListsDiff {
    public static void main(String[] args) throws IOException {
        if (args == null && args.length < 2) return;

        Set<String> list1, list2;
        try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
            list1 = lines.collect(Collectors.toSet());
        }
        try (Stream<String> lines = Files.lines(Paths.get(args[1]))) {
            list2 = lines.collect(Collectors.toSet());
        }
        if (list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty()) return;

        Set<String> itemsInList1NotInList2 = list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toSet());
        System.out.println("----Items in list 1 not in list 2: " + itemsInList1NotInList2.size());
        itemsInList1NotInList2.forEach(item -> System.out.println(item));

        Set<String> itemsInList2NotInList1 = list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toSet());
        System.out.println("\n----Items in list 2 not in list 1: " + itemsInList2NotInList1.size());
        itemsInList2NotInList1.forEach(item -> System.out.println(item));
    }
}
