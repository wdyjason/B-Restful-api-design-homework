package com.thoughtworks.capability.gtb.restfulapidesign.constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupConstants {
    public static int GROUP_COUNT = 6;
    public static List<String> GROUP_NAMES = Stream.of("team 1", "team 2", "team 3", "team 4", "team 5", "team 6")
            .collect(Collectors.toList());
}
